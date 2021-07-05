(ns cypto.views
  (:require
   [re-frame.core :as re-frame]
   [cypto.subs :as subs]
   [cypto.events :as events]
   ))


(defn display-data [{:keys [price] symbol :symbol}]
  [:tr {:key symbol}
   [:td symbol]
   [:td price]
   ]
  )

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        data (re-frame/subscribe [::subs/data])]
    [:div.container
     [:h1
      "Hello from " @name]
     [:button {:class "btn btn-primary container"
               :on-click #(re-frame/dispatch [::events/fetch])} "Make API Call"]
     [:button {:class "btn btn-primary container"
               :on-click #(re-frame/dispatch [::events/update-name (if (= @name (str "re-frame"))
                                                                     (str "second name")
                                                                     (str "re-frame"))])} "Show another name"]
     [:table {:class "table"}
      [:thead 
       [:th {:scope "coll"}
        "Symbol"]
       [:th {:scope "coll"}
        "Price"]
       ]
      [:tbody]
      (map display-data @data)
      ]
     ]))