(ns scraper.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]
            [postal.core :as postal]
            [environ.core :as environ])
  (:gen-class))

(use 'clojure.tools.trace)

(defn get-chess-dom
  []
  (html/html-snippet
    (:body @(http/get "https://www.chess.com/titles" {:insecure? true}))))

(defn get-articles-title
  [dom]
  (map html/text (html/select dom [:title.content :h2])))

(defn print-articles-title
  [titles]
  ; (doseq [title titles] (println title)))
  (do (doseq [title titles] (println title))
      (lazy-seq titles)))


(defn get-newest-title
  [titles]
  (first titles))

(def email (environ/env :email))
(def pass (environ/env :pass))

(def conn {:host "smtp.gmail.com"
           :ssl true
           :user email
           :pass pass})

(defn send-email
  [featured-title]
  (postal/send-message conn {:from email
                             :to email
                             :subject "New chess.com featured article"
                             :body featured-title}))

(defn -main
  ""
  [& args]
  (send-email
    (get-newest-title
      (print-articles-title
        (get-articles-title
          (get-chess-dom)))))) ; TODO: the -> bult in macro could also be used
