(ns nos-codamos.core
  (:require [nos-codamos.semana-1.main :as semana-1]
            [nos-codamos.semana-3.main :as semana-3]))

;(println "Semana 1")
;(println "-------------------------------------------------")
;(println "Last transaction:" (last semana-1/all-purchases))
;(println "Credit Card most updated:" (:credit-card (last semana-1/all-purchases)))
;(println "-------------------------------------------------")
;(println "Expenses by category:" (semana-1/group-purchases-by-category semana-1/approved-purchases))
;(println "-------------------------------------------------")
;(println "Search by merchant using query: {:merchant iFood}:" (semana-1/search-purchases semana-1/all-purchases {:merchant "iFood"}))
;(println "Search by amount using query: {:amount 100}:" (semana-1/search-purchases semana-1/all-purchases {:amount 100}))
;(println "---------------------------------------------------")
;(println "Monthly bill of month 05" (semana-1/monthly-bill semana-1/approved-purchases 2021 5))
;(println "Monthly bill of month 10" (semana-1/monthly-bill semana-1/approved-purchases 2021 10))