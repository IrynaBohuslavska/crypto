(ns cypto.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))


;; (re-frame/reg-sub
;;  ::crypto
;;  (fn [db]
;;    (:crypto db)))

(re-frame/reg-sub
 ::loading
 (fn [db]
   (:loading db)))

(re-frame/reg-sub
 ::data
 (fn [db]
   (:data db)))