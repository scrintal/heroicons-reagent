(ns build
  (:require [clojure.tools.build.api :as b]
            [convert :as convert]))

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

(defn heroicons-version []
  (b/git-process
    {:git-command "git"
     :git-args    ["describe" "--tags"]
     :dir         "heroicons"}))

(defn ensure-heroicons! []
  (when-not (has-heroicons?)
    (util/delete-directory-recursive heroicons-dir)
    (b/git-process
      {:git-command "git"
       :git-args    ["clone" heroicons-repo heroicons-dir]})))

(defn needs-to-build? []
  (let [heroicons-version (heroicons-version)
        our-version (b/git-process
                      {:git-command "git"
                       :git-args    ["describe" "--tags"]})]
    (not= heroicons-version our-version)))

(defn switch-to-latest-version! []
  (let [latest-version (heroicons-version)]
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
     :git-args    ["commit" "-m" (str "\"Updated heroicons to version" version ".\"")]}))

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

  nil)
