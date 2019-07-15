(defproject db-extractors-clj "0.1.0-SNAPSHOT"
  :description "Clojure based database extractors for JetBrains DataGrip/Intellij"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/java.jdbc "0.7.8"]
                 [org.xerial/sqlite-jdbc "3.23.1"]]
  :java-source-paths ["java"]
  :target-path "target/%s")
