(ns com.scrintal.heroicons.micro.bell-alert)

(defn render []
  [:svg {:xmlns       "http://www.w3.org/2000/svg"
         :viewBox     "0 0 16 16"
         :fill        "currentColor"
         :aria-hidden "true"
         :data-slot   "icon"}
   [:path {:d "M3.6 1.7A.75.75 0 1 0 2.4.799a6.978 6.978 0 0 0-1.123 2.247.75.75 0 1 0 1.44.418c.187-.644.489-1.24.883-1.764ZM13.6.799a.75.75 0 1 0-1.2.9 5.48 5.48 0 0 1 .883 1.765.75.75 0 1 0 1.44-.418A6.978 6.978 0 0 0 13.6.799Z"}]
   [:path {:fillRule "evenodd"
           :d        "M8 1a4 4 0 0 1 4 4v2.379c0 .398.158.779.44 1.06l1.267 1.268a1 1 0 0 1 .293.707V11a1 1 0 0 1-1 1h-2a3 3 0 1 1-6 0H3a1 1 0 0 1-1-1v-.586a1 1 0 0 1 .293-.707L3.56 8.44A1.5 1.5 0 0 0 4 7.38V5a4 4 0 0 1 4-4Zm0 12.5A1.5 1.5 0 0 1 6.5 12h3A1.5 1.5 0 0 1 8 13.5Z"
           :clipRule "evenodd"}]])