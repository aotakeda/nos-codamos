(ns nos-codamos.semana-3.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/noscodamos")

(defn open-connection []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn erase-database []
  (d/delete-database db-uri))

(def schema [{:db/ident       :client/full-name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :client/cpf
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :client/email
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cc/credit-card-number
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cc/cvv
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cc/expiration-date
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cc/limit
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}
             {:db/ident       :purchase/date
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :purchase/amount
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}
             {:db/ident       :purchase/merchant
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :purchase/category
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}])

(defn create-schema [conn]
  (d/transact conn schema))

(defn add-new-client-with-credit-card [conn client credit-card]
  (d/transact conn [client credit-card]))

(defn add-new-purchase [conn purchase]
  (d/transact conn [purchase]))

(defn all-purchases [db]
  (d/q '[:find ?merchant ?amount ?category ?date
         :where
         [?purchase :purchase/category ?category]
         [?purchase :purchase/amount ?amount]
         [?purchase :purchase/merchant ?merchant]
         [?purchase :purchase/date ?date]] db))

(defn all-categories [db]
  (d/q '[:find ?category
         :where [_ :purchase/category ?category]] db))