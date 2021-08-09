(ns nos-codamos.semana-3.main
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [nos-codamos.semana-3.db :as db]
            [nos-codamos.semana-3.model :as model]))


(def conn (db/open-connection))

(db/create-schema conn)

(db/add-new-client conn [(model/new-client "Arthur Takeda", "123.456.789-10", "a@a.com")])