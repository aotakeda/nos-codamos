(ns nos-codamos.semana-3.model)

(defn new-client [full-name cpf email]
  {:client/full-name full-name
   :client/cpf       cpf
   :client/email     email
   })

(defn new-credit-card [credit-card-number cvv expiration-date limit]
  {:cc/credit-card-number credit-card-number
   :cc/cvv                cvv
   :cc/expiration-date    expiration-date
   :cc/limit              limit})

(defn new-purchase [date amount merchant category]
  {:purchase/date     date
   :purchase/amount   amount
   :purchase/merchant merchant
   :purchase/category category})