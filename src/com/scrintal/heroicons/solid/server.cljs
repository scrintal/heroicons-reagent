(ns com.scrintal.heroicons.solid.server)

(defn render []
  [:svg {:xmlns       "http://www.w3.org/2000/svg"
         :viewBox     "0 0 24 24"
         :fill        "currentColor"
         :aria-hidden "true"
         :data-slot   "icon"}
   [:path {:d "M4.08 5.227A3 3 0 0 1 6.979 3H17.02a3 3 0 0 1 2.9 2.227l2.113 7.926A5.228 5.228 0 0 0 18.75 12H5.25a5.228 5.228 0 0 0-3.284 1.153L4.08 5.227Z"}]
   [:path {:fillRule "evenodd"
           :d        "M5.25 13.5a3.75 3.75 0 1 0 0 7.5h13.5a3.75 3.75 0 1 0 0-7.5H5.25Zm10.5 4.5a.75.75 0 1 0 0-1.5.75.75 0 0 0 0 1.5Zm3.75-.75a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z"
           :clipRule "evenodd"}]])