(ns extractors.db
  (:require [clojure.java.jdbc :as jdbc]))

(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/chinook.db"})

(comment
  (jdbc/query db ["SELECT count(*) from genres"]))


