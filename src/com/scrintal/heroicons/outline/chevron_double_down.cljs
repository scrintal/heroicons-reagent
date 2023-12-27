(ns com.scrintal.heroicons.outline.chevron-double-down)

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
           :d "m4.5 5.25 7.5 7.5 7.5-7.5m-15 6 7.5 7.5 7.5-7.5"}]])