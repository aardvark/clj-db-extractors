(ns extractors.markdown.confluence.table
  (:require [extractors.core :as core])
  (:import (com.intellij.database.extensions DataRow)))

(def ^:dynamic COLUMNS)
(def ^:dynamic ROWS)
(def ^:dynamic OUT)

(defn header
  "Produce table header.
   Uses COLUMNS variable binding as source"
  []
  (core/join-and-wrap "||" (core/column-names COLUMNS)))

(defn rows
  "Produces table rows.
   Uses ROWS script variable as source."
  []
  (clojure.string/join "\n"
                       (for [^DataRow x ROWS]
                         (core/join-and-wrap "|" (.data x)))))

(defn write-to-OUT
  "Write table output to the OUT appender"
  []
  (.append OUT (str (header) "\n" (rows))))

(comment (write-to-OUT))
