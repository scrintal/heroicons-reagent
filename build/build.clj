(ns build
  (:require
    [clojure.string :as str]
    [clojure.tools.build.api :as b]
    [convert :as convert]
    [org.corfield.build :as bb]))

(def lib 'com.scrintal/heroicons-reagent)
(def target-dir "target")
(def class-dir (str target-dir "/classes"))
(def basis (b/create-basis {:project "deps.edn"}))

(def heroicons-dir "heroicons")
(def heroicons-repo "https://github.com/tailwindlabs/heroicons")

(defn has-heroicons? []
  (when (and (util/exists? heroicons-dir)
             (util/directory? heroicons-dir))
    (= ".git"
       (b/git-process
         {:git-command "git"
          :git-args    ["rev-parse" "--git-dir"]
          :dir         heroicons-dir}))))

(defn latest-version
  ([dir]
   (-> (b/git-process
         (cond-> {:git-command "git"
                  :git-args    ["tag" "-l" "--sort=version:refname" "v*"]}

                 dir
                 (assoc :dir dir)))
       (str/split #"\n")
       (last)))
  ([]
   (latest-version nil)))

(defn ensure-heroicons! []
  (when-not (has-heroicons?)
    (b/delete {:path heroicons-dir})
    (b/git-process
      {:git-command "git"
       :git-args    ["clone" heroicons-repo heroicons-dir]})))

(defn needs-to-build? []
  (let [heroicons-version (latest-version heroicons-dir)
        our-version (latest-version)]
    (not= heroicons-version our-version)))

(defn switch-to-latest-version! []
  (let [latest-version (latest-version heroicons-dir)]
    (b/git-process
      {:git-command "git"
       :git-args    ["checkout" latest-version]
       :dir         "heroicons"})
    latest-version))

(defn commit! [version]
  (b/git-process
    {:git-command "git"
     :git-args    ["add" "--all"]})
  (b/git-process
    {:git-command "git"
     :git-args    ["commit" "-m" (str "Updated heroicons to version " version ".")]}))

(defn create-tag! [tag-name]
  (b/git-process
    {:git-command "git"
     :git-args    ["tag" tag-name]}))

(defn build [_args]
  (ensure-heroicons!)
  (if (needs-to-build?)
    (let [latest-version (switch-to-latest-version!)]
      (convert/convert heroicons-dir "src")
      (commit! latest-version)
      (create-tag! latest-version))
    (println "Latest version of heroicons is already built!")))

(defn jar [_args]
  (if-let [our-version (latest-version)]
    (let [version (subs our-version 1)
          jar-file (format (str target-dir "/%s-%s.jar") (name lib) version)
          opts {:class-dir class-dir
                :jar-file  jar-file
                :lib       lib
                :basis     basis
                :src-pom   "partial_pom.xml"
                :version   version}]
      (b/delete {:path target-dir})
      (bb/jar opts)
      (println jar-file "is ready.")
      (b/copy-file {:src    (b/pom-path {:class-dir class-dir
                                         :lib       lib})
                    :target (str target-dir "/pom.xml")})
      (-> opts
          (assoc :pom-file (str target-dir "/pom.xml"))
          (update :jar-file (comp #(.getCanonicalPath %) b/resolve-path))))
    (throw (ex-info "No valid version! Run the build command first!" {}))))

(defn deploy [args]
  (let [opts (jar args)]
    (bb/deploy opts)
    (println)
    (println (:version opts) "is deployed to the Clojars.")
    (println "  " lib (str "{:mvn/version " (:version opts) "}"))))

(defn install [args]
  (let [opts (jar args)]
    (bb/install opts)
    (println)
    (println lib (:version opts) "is installed to the local maven repository.")
    (println "  " lib (str "{:mvn/version " (:version opts) "}"))))

(comment
  ; Returns true if the heroicons GitHub repository is already
  ; fetched locally.
  (has-heroicons?)

  ; Returns true if we need to build a new version
  (needs-to-build?)

  ; Switches to the latest tag on heroicons repository
  (switch-to-latest-version!)

  ; Converts all SVGs to reagent components from heroicons
  ; repository
  (convert/convert heroicons-dir "src")

  ; Builds a new version if needed
  (build)

  ; Creates a new jar from the latest builds a new version if needed
  (jar)

  ; Creates a new jar and deploys it to the Clojars. Requires a Clojars deploy key
  ; to be available in the local environment.
  (deploy)

  ; Creates a new jar and installs it to the local maven.
  (install)

  nil)
