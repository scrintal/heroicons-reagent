(ns com.scrintal.heroicons.outline.chevron-right)

(defn render []
  [:svg {:xmlns       "http://www.w3.org/2000/svg"
         :fill        "none"
         :viewBox     "0 0 24 24"
         :strokeWidth "1.5"
         :stroke      "currentColor"
         :aria-hidden "true"
         :dataSlot    "icon"}
   [:path {:strokeLinecap "round"
           :strokeLinejoin "round"
           :d "m8.25 4.5 7.5 7.5-7.5 7.5"}]])