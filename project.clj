(defproject scraper "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [enlive "1.1.6"]
                 [org.clojure/tools.trace "0.7.9"]
                 [http-kit "2.1.18"]
                 [com.draines/postal "2.0.2"]
                 [environ "1.1.0"]]
  :main ^:skip-aot scraper.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
