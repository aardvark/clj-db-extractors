(ns extractors.markdown.confluence.test.table
  (:require [clojure.test :refer :all]
            [extractors.markdown.confluence.table :as table])
  (:import (java.util ArrayList)
           (com.intellij.database.extensions DataColumn DataRow)))


(defn dataColumn
  "Reify wrapper around com.intellij.database.extension.DataColumn for testing"
  [number name]
  (reify DataColumn
    (columnNumber [this] number)
    (name [this] name)))


(defn dataRow
  "Test wrapper around Intellij DataRow"
  [data]
  (reify DataRow
    (data [this] data)))


(defn dataRows
  "Takes collection and return vector of DataRow's"
  [col]
  (into [] (map dataRow) col))


(def capture "")

(def COLUMNS
  "Test fixture for COLUMNS script binding"
  (doto (new ArrayList)
    (.add (dataColumn 1 "First"))
    (.add (dataColumn 2 "Second"))))

(def ROWS
  "Test fixture for ROWS script binding"
  (dataRows [["A" "B"] ["C" "D"]]))

(def OUT
  "Test fixture for OUT script binding"
  (reify Appendable
    (^Appendable append [this ^CharSequence csq]
      (def capture (str csq))
      OUT)))

(defn init-rows-columns
  "Maintains ROWS, COLUMNS, OUT script binding variables"
  [f]
  (binding [table/COLUMNS COLUMNS
            table/ROWS ROWS
            table/OUT OUT]
   (f)))



(use-fixtures :once init-rows-columns)

(deftest confluence-table
  (testing "Converter to confluence table format"
    (testing "produces header in correct format"
      (is (= (table/header) "||First||Second||")))
    (testing "produces rows in correct format"
      (is (= (table/rows) "|A|B|\n|C|D|")))
    (testing "full table is written to appender"
      (table/write-to-OUT)
      (is (= capture "||First||Second||\n|A|B|\n|C|D|")))))