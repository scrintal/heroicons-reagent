(ns com.scrintal.heroicons.outline.bolt)

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
           :d "m3.75 13.5 10.5-11.25L12 10.5h8.25L9.75 21.75 12 13.5H3.75Z"}]])