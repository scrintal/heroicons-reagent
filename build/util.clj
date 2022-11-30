(ns util
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import (java.io File)))

(defn ->file [arg]
  (if (instance? File arg)
    arg
    (io/file arg)))

(defn exists? [arg]
  (let [^File file (->file arg)]
    (.exists file)))

(defn directory? [arg]
  (let [^File file (->file arg)]
    (.isDirectory file)))

(defn delete-directory-recursive
  "Recursively delete a directory."
  [arg]
  (let [^File file (->file arg)]
    (when (exists? file)
      (when (directory? file)
        (run! delete-directory-recursive (.listFiles file)))
      (io/delete-file file))))

(defn ->camel-case [a-str]
  (let [words (str/split (name a-str) #"-")
        capitalized (map str/capitalize (rest words))]
    (keyword (apply str (first words) capitalized))))

(defn ->snake-case [a-str]
  (str/replace a-str "-" "_"))

(defn file->file-name-parts
  [^File a-file]
  (str/split (.getName a-file) #"\."))

(defn file->file-ext
  [^File a-file]
  (last (file->file-name-parts a-file)))

(defn file->file-name [^File a-file]
  (first (file->file-name-parts a-file)))

(defn svg?
  [^File a-file]
  (and (.isFile a-file)
       (= "svg" (file->file-ext a-file))))
