(ns nos-codamos.semana-3.main
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [nos-codamos.semana-3.db :as db]
            [nos-codamos.semana-3.model :as model]))


(def conn (db/open-connection))

(db/create-schema conn)

(db/add-new-client-with-credit-card
  conn (model/new-client "Arthur Takeda", "123.456.789-10", "a@a.com")
  (model/new-credit-card 8888 888 "09/2029" 100))

(db/add-new-purchase conn (model/new-purchase "15/07/2021" 100 "iFood" "Restaurant"))

(def db (d/db conn))

(d/q '[:find ?entity
      :where [?entity :purchase/merchant]] db)