(ns convert
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [hickory.core :as hickory]
            [zprint.core :as zp]
            [util :as util]))

(defn new-line-str? [str]
  (and (string? str) (str/starts-with? str "\n")))

(defn empty-map? [m]
  (= {} m))

(defn aria-attr? [k]
  (and k (str/starts-with? (name k) "aria-")))

(def special-keywords
  {:clippath :clipPath
   :viewbox  :viewBox})

(defn svg->reagent [svg-str]
  (let [parsed-svg (-> svg-str
                       (hickory/parse-fragment)
                       (first)
                       (hickory/as-hiccup))]
    (clojure.walk/postwalk
      (fn [v]
        (cond
          (vector? v)
          (into []
                (remove #(or (empty-map? %) (new-line-str? %)))
                v)

          (keyword? v)
          (cond
            (contains? special-keywords v)
            (get special-keywords v)

            (aria-attr? v)
            v

            :else
            (util/->camel-case v))

          :else
          v))
      parsed-svg)))

(defn svg-hiccup->def-str
  [svg-hiccup]
  (str "(defn render [] " svg-hiccup ")"))

(defn save-icon-ns! [output-dir ns-name a-file]
  (let [file-name (util/file->file-name a-file)
        icon-def-str (svg-hiccup->def-str (-> a-file slurp svg->reagent))
        file-content (str "(ns com.scrintal.heroicons." ns-name "." file-name
                          (if (find-var (symbol "clojure.core" file-name))
                            (str "\n" "  (:refer-clojure :exclude [" file-name "]))")
                            ")")
                          "\n\n"
                          icon-def-str)
        file-name* (util/->snake-case file-name)
        file-path (str output-dir "/com/scrintal/heroicons/" (util/->snake-case ns-name))
        output-file (str file-path "/" file-name* ".cljs")]
    (.mkdirs (io/file file-path))
    (spit output-file file-content)
    (zp/zprint-file output-file
                    file-name
                    output-file
                    {:style  :hiccup
                     :fn-map {"defn" :arg2}
                     :map    {:justify?  true
                              :comma?    false
                              :force-nl? true}
                     :width  10000})
    (println " >" output-file)))

(defn convert [input-dir output-dir]
  (util/delete-directory-recursive output-dir)
  (println "> Cleaned output folder")

  (let [outline-icons (->> (io/file (str input-dir "/optimized/24/outline"))
                           file-seq
                           (filter util/svg?))
        solid-icons (->> (io/file (str input-dir "/optimized/24/solid"))
                         file-seq
                         (filter util/svg?))
        mini-icons (->> (io/file (str input-dir "/optimized/20/solid"))
                        file-seq
                        (filter util/svg?))]
    (println "> Creating mini icons")
    (doseq [icon mini-icons]
      (save-icon-ns! output-dir "mini" icon))

    (println "> Creating solid icons")
    (doseq [icon solid-icons]
      (save-icon-ns! output-dir "solid" icon))

    (println "> Creating outline icons")
    (doseq [icon outline-icons]
      (save-icon-ns! output-dir "outline" icon))))
