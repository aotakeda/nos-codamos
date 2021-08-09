(ns nos-codamos.semana-3.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/noscodamos")

(defn open-connection []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn erase-database []
  (d/delete-database db-uri))

(def schema [{:db/ident       :full-name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cpf
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :email
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}])

(defn create-schema [conn]
  (d/transact conn schema))

(defn add-new-client [conn client]
  (d/transact conn client))