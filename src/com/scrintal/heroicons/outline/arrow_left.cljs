(ns com.scrintal.heroicons.outline.arrow-left)

(defn render []
  [:svg {:xmlns       "http://www.w3.org/2000/svg"
         :fill        "none"
         :viewBox     "0 0 24 24"
         :strokeWidth "1.5"
         :stroke      "currentColor"
         :aria-hidden "true"}
   [:path {:strokeLinecap "round"
           :strokeLinejoin "round"
           :d "M10.5 19.5L3 12m0 0l7.5-7.5M3 12h18"}]])