(ns com.scrintal.heroicons.outline.arrow-up-left)

(defn render []
  [:svg {:xmlns       "http://www.w3.org/2000/svg"
         :fill        "none"
         :viewBox     "0 0 24 24"
         :strokeWidth "1.5"
         :stroke      "currentColor"
         :aria-hidden "true"}
   [:path {:strokeLinecap "round"
           :strokeLinejoin "round"
           :d "M19.5 19.5l-15-15m0 0v11.25m0-11.25h11.25"}]])