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
  (def purchase-example (purchase (LocalDate/parse "2021-05-07")
                                  500
                                  "iFood"
                                  "Restaurant"
                                  credit-card))
  (is purchase-example))