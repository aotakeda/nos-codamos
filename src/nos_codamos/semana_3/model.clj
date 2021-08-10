(ns nos-codamos.semana-3.model)

(defn new-client [full-name cpf email]
  {:full-name full-name
   :cpf       cpf
   :email     email
   })

(defn new-credit-card [credit-card-number cvv expiration-date limit]
  {:credit-card-number credit-card-number
   :cvv                cvv
   :expiration-date    expiration-date
   :limit              limit})

(defn new-purchase [date amount merchant category]
  {:date     date
   :amount   amount
   :merchant merchant
   :category category})