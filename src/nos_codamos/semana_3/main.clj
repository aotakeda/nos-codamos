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

(db/add-new-purchase conn (model/new-purchase "15/07/2021" 300 "iFood" "Restaurant"))
(db/add-new-purchase conn (model/new-purchase "13/07/2021" 500 "Playstation Store" "Entertainment"))
(db/add-new-purchase conn (model/new-purchase "15/07/2021" 60 "Netflix" "Entertainment"))
(db/add-new-purchase conn (model/new-purchase "14/07/2021" 231 "Pet shop" "Pet"))


(def db (d/db conn))

(pprint (db/all-purchases (d/db conn)))

(pprint (db/all-categories (d/db conn)))