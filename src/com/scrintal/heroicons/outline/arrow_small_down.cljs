(ns com.scrintal.heroicons.outline.arrow-small-down)

(defn render []
  [:svg {:xmlns       "http://www.w3.org/2000/svg"
         :fill        "none"
         :viewBox     "0 0 24 24"
         :strokeWidth "1.5"
         :stroke      "currentColor"
         :aria-hidden "true"}
   [:path {:strokeLinecap "round"
           :strokeLinejoin "round"
           :d "M12 4.5v15m0 0l6.75-6.75M12 19.5l-6.75-6.75"}]])