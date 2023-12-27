(ns com.scrintal.heroicons.outline.arrow-small-up)

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
           :d "M12 19.5v-15m0 0-6.75 6.75M12 4.5l6.75 6.75"}]])