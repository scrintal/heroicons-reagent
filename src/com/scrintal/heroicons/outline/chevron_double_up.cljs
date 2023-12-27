(ns com.scrintal.heroicons.outline.chevron-double-up)

(defn render []
  [:svg {:xmlns       "http://www.w3.org/2000/svg"
         :fill        "none"
         :viewBox     "0 0 24 24"
         :strokeWidth "1.5"
         :stroke      "currentColor"
         :aria-hidden "true"
         :data-slot   "icon"}
   [:path {:strokeLinecap "round"
           :strokeLinejoin "round"
           :d "m4.5 18.75 7.5-7.5 7.5 7.5"}]
   [:path {:strokeLinecap "round"
           :strokeLinejoin "round"
           :d "m4.5 12.75 7.5-7.5 7.5 7.5"}]])