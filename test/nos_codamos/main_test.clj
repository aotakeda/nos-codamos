(ns nos-codamos.main-test
  (:require [clojure.test :refer :all]
            [nos-codamos.semana-1.main :refer :all])
  (:import (java.time LocalDate YearMonth)
           (java.time.temporal ChronoUnit)))

(def credit-card-test {:number          "111"
                       :cvv             "222"
                       :expiration-date (YearMonth/parse "2029-09")
                       :limit           3000})

(def purchase-1-test
  (purchase (LocalDate/parse "2021-10-07")
            100
            "iFood"
            "Restaurant"
            (update credit-card-test :limit - 100)))
(def purchase-2-test
  (purchase (LocalDate/parse "2021-10-07")
            200
            "iFood"
            "Restaurant"
            (update credit-card-test :limit - 200)))
(def purchase-3-test
  (purchase (LocalDate/parse "2021-10-07")
            300
            "iFood"
            "Restaurant"
            (update credit-card-test :limit - 300)))
(def purchase-4-test
  (purchase (LocalDate/parse "2021-10-07")
            6000
            "iFood"
            "Restaurant"
            (:credit-card purchase-3-test)))

(def all-purchases-test [purchase-1-test purchase-2-test purchase-3-test purchase-4-test])

(deftest valid-amount?-test
  (testing "valid purchase amount")
  (is (not (valid-amount? nil)))
  (is (not (valid-amount? 0)))
  (is (valid-amount? 0.1))
  (is (valid-amount? 1))
  (is (not (valid-amount? -0.1)))
  (is (not (valid-amount? "0.1"))))

(deftest purchase-test
  (testing "adding new purchase")
  (is (= {:date        (LocalDate/parse "2021-05-07")
          :amount      0
          :merchant    "iFood"
          :category    "Restaurant"
          :approved?   false
          :credit-card credit-card-test}
         (purchase (LocalDate/parse "2021-05-07")
                   0
                   "iFood"
                   "Restaurant"
                   credit-card-test)))
  (is (= {:date        (LocalDate/parse "2021-05-07")
          :amount      -1
          :merchant    "iFood"
          :category    "Restaurant"
          :approved?   false
          :credit-card credit-card-test}
         (purchase (LocalDate/parse "2021-05-07")
                   -1
                   "iFood"
                   "Restaurant"
                   credit-card-test)))
  (is (= {:date        (LocalDate/parse "2021-05-07")
          :amount      1
          :merchant    "iFood"
          :category    "Restaurant"
          :approved?   true
          :credit-card (update credit-card-test :limit - 1)}
         (purchase (LocalDate/parse "2021-05-07")
                   1
                   "iFood"
                   "Restaurant"
                   credit-card-test)))
  (is (= {:date        (LocalDate/parse "2021-05-07")
          :amount      5000
          :merchant    "iFood"
          :category    "Restaurant"
          :approved?   false
          :credit-card credit-card-test}
         (purchase (LocalDate/parse "2021-05-07")
                   5000
                   "iFood"
                   "Restaurant"
                   credit-card-test))))

(deftest search-purchases-test
  (testing "listing all approved purchases")
  (is (= (filter #(= (:approved? %) true) all-purchases-test)
         (search-purchases all-purchases-test {:approved? true}))))