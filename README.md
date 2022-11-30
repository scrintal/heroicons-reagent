# heroicons-reagent

A library containing all the SVGs from [heroicons](https://github.com/tailwindlabs/heroicons)
converted into [reagent](https://github.com/reagent-project/reagent) components.

## Intallation
Add the dependency to your `deps.edn` from Clojars:
```clojure
  com.scrintal/heroicons-reagent {:mvn/version "2.0.13"}
```

or directly from GitHub:
```clojure
  com.scrintal/heroicons-reagent {:git/url "https://github.com/scrintal/heroicons-reagent"
                                  :git/tag "v2.0.13"
                                  :git/sha "55c73aa"}
```

or to your `shadow-cljs.edn`:
```clojure
  [com.scrintal/heroicons-reagent "2.0.13"]
```

## Usage
```clojure
(ns se.example
  (:require [com.scrintal.heroicons.solid.academic-cap :as academic-cap]))

(defn app []
  [:div
   [academic-cap/render]])

```

## License

This library is MIT licensed same as [heroicons](https://github.com/tailwindlabs/heroicons).
