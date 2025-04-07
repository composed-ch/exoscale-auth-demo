(ns exoscale-auth-demo.core 
  (:require [exoscale-auth.core :as exo])
  (:require [clj-http.client :as client])
  (:require [clojure.edn :as edn])
  (:require [clojure.data.json :as json])
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
        auth-header (exo/build-auth-header api-key api-secret "GET" resource {} query)
        headers (assoc headers :Authorization auth-header)
        url-func (exo-base-url-func conf)
        url (url-func resource)]
    (client/get url {:headers headers})))

(defn exo-do [method resource query body]
  (let [conf (read-config "conf.edn")
        api-key (:exoscale-api-key conf)
        api-secret (:exoscale-api-secret conf)
        headers {:content-type "application/json"}
        auth-header (exo/build-auth-header api-key api-secret method resource body query)
        headers (assoc headers :Authorization auth-header)
        url-func  (exo-base-url-func conf)
        url (url-func resource)
        func (case (.toLowerCase method)
               "get" client/get
               "post" client/post
               (fn [& args] "method %s not implemented" method))]
    (func url {:headers headers :body (json/write-str body)})))

                   
(defn -main
  [& args]
  (let [conf (read-config "conf.edn")]
    (println (exo-do "POST" "v2/security-group" {} {:name "sikjurity" :description "top sikret"}))))
