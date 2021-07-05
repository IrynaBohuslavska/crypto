(ns cypto.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [cypto.events :as events]
   [cypto.views :as views]
   [cypto.subs :as subs]
   [cypto.config :as config]
   [clojure.core.async :refer [go-loop timeout <!]]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root)
  (go-loop [second 1]
    (<! (timeout 15000))
    (re-frame/dispatch [::events/fetch])
    (recur (+ second 15))))
