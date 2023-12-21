# heroicons-reagent

[![Clojars Project](https://img.shields.io/clojars/v/com.scrintal/heroicons-reagent.svg)](https://clojars.org/com.scrintal/heroicons-reagent)

A library containing all the SVGs from [heroicons](https://github.com/tailwindlabs/heroicons)
converted into [reagent](https://github.com/reagent-project/reagent) components.

## Installation
Add the dependency to your `deps.edn` from Clojars:
```clojure
  com.scrintal/heroicons-reagent {:mvn/version "2.1.1"}
```

or directly from GitHub:
```clojure
  com.scrintal/heroicons-reagent {:git/url "https://github.com/scrintal/heroicons-reagent"
                                  :git/tag "v2.1.1"
                                  :git/sha "87c780c"}
```

or to your `shadow-cljs.edn`:
```clojure
  [com.scrintal/heroicons-reagent "2.1.1"]
```

## Usage
```clojure
(ns se.example
  (:require [com.scrintal.heroicons.solid.academic-cap :as academic-cap]))

(defn app []
  [:div
   [academic-cap/render]])

```

## Development

### Building
To build a new version matching the latest version of the Heroicons, simply run:
```sh
clj -T:build build
```

This will download the latest version of Heroicons, build a new version, commit the changes, and 
tag the commit with the same version number as the Heroicons.

### Install Locally
To test the newly built library locally, use the following command:
```sh
clj - T:build install
```

This will install the latest version to your local Maven repository.

### Deploying to Clojars
After building a new version and testing it locally by installing it, the following command can be
run to deploy the library to Clojars:
```sh
clj - T:build deploy
```

## License

This library is MIT licensed same as [heroicons](https://github.com/tailwindlabs/heroicons).
