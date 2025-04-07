(ns exoscale-auth-demo.core 
  (:require [exoscale-auth.core :as exo])
  (:require [clj-http.client :as client])
  (:require [clojure.edn :as edn])
  (:gen-class))

(defn read-config [path]
  (let [data (slurp path)]
    (edn/read-string data)))

(defn exo-base-url-func [conf]
  (fn [suffix]
    (format "https://api-%s.exoscale.com/%s" (:exoscale-zone conf) suffix)))

(defn exo-get [resource query]
  (let [conf (read-config "conf.edn")
        api-key (:exoscale-api-key conf)
        api-secret (:exoscale-api-secret conf)
        headers {:content-type "application/json"}
        auth-header (exo/build-auth-header api-key api-secret "GET" resource {} query headers)
        headers (assoc headers :Authorization auth-header)
        url-func (exo-base-url-func {:exoscale-zone "ch-gva-2"})
        url (url-func resource)]
    (client/get url {:headers headers})))
                   
(defn -main
  [& args]
  (let [conf (read-config "conf.edn")]
    (println (exo-get "v2/instance/25fd0b49-be79-44a4-81b8-282d1d609b8e" {}))))
