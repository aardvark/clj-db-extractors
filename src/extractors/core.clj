(ns extractors.core)

; Exposed script bindings:
;
; Available context bindings:
;   COLUMNS     List<DataColumn>
;   ROWS        Iterable<DataRow>
;   OUT         { append() }
;   FORMATTER   { format(row, col); formatValue(Object, col) }
;   TRANSPOSED  Boolean
; plus ALL_COLUMNS, TABLE, DIALECT
;
; where:
;   DataRow     { rowNumber(); first(); last(); data(): List<Object>; value(column): Object }
;   DataColumn  { columnNumber(), name() }


(defn column-names
  "Takes collection of DataColumn and return column names"
  [cols]
  (map #(.name %) cols))


(defn join-and-wrap
  "Join collection `col` to string on symbol `chr`.
   After that add `chr` to the start and end of produced string."
  [chr col]
  (str chr
       (clojure.string/join chr col)
       chr))
