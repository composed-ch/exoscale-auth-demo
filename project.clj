(defproject exoscale-auth-demo "0.1.0-SNAPSHOT"
  :description "demo application for github.com/composed-ch/exoscale-auth"
  :url "https://github.com/composed-ch/exoscale-auth-demo"
  :license {:name "LGPL-3.0-or-later"
            :url "https://www.gnu.org/licenses/lgpl+gpl-3.0.txt"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-http "3.12.4"]
                 [org.clojure/data.json "2.5.1"]
                 [com.github.composed-ch/exoscale-auth "0.1.0-SNAPSHOT"]]
  :main ^:skip-aot exoscale-auth-demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
