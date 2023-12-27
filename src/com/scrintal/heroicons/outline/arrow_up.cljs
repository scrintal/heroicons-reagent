(ns com.scrintal.heroicons.outline.arrow-up)

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
           :d "M4.5 10.5 12 3m0 0 7.5 7.5M12 3v18"}]])