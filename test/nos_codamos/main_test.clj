(ns nos-codamos.main-test
  (:require [clojure.test :refer :all]
            [nos-codamos.semana-1.main :refer :all])
  (:import (java.time LocalDate YearMonth)
           (java.time.temporal ChronoUnit)))

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
  (def credit-card-test {:number          "111"
                         :cvv             "222"
                         :expiration-date (YearMonth/parse "2029-09")
                         :limit           3000})
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