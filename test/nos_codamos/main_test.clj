(ns nos-codamos.main-test
  (:require [clojure.test :refer :all]
            [nos-codamos.semana-1.main :refer :all])
  (:import (java.time LocalDate YearMonth)
           (java.time.temporal ChronoUnit)))

(deftest valid-amount?-test
  (testing "purchase amount nil")
  (is (not (valid-amount? nil)))
  (testing "purchase amount zero")
  (is (not (valid-amount? 0)))
  (testing "purchase amount positive")
  (is (valid-amount? 0.1))
  (testing "purchase amount negative")
  (is (not (valid-amount? -0.1)))
  (testing "purchase amount string")
  (is (not (valid-amount? "0.1"))))