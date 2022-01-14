(ns nomis-minimal-shadow-cljs-example.system.webserver
  (:require
   [clojure.pprint :as pp]
   [clojure.tools.logging :as log]
   [com.stuartsierra.component :as component]
   [org.httpkit.server :as http]
   [ring.middleware.content-type :refer [wrap-content-type]]
   [ring.middleware.not-modified :refer [wrap-not-modified]]
   [ring.middleware.resource :refer [wrap-resource]]))

(def middleware (-> (fn [_req]
                      {:status  404
                       :headers {"Content-Type" "text/plain"}
                       :body    "Invalid request."})
                    (wrap-resource "public")
                    wrap-content-type
                    wrap-not-modified))

(defrecord ^:private WebServer [;; Injected
                                port
                                ;; handler ; no handler
                                ;; Added here
                                stop-fun]

  component/Lifecycle

  (start [this]
    (if stop-fun
      (do
        (log/warn "Webserver is already running on port" port)
        this)
      (let [start (fn []
                    (try
                      (http/run-server middleware {:port port})
                      (catch java.net.BindException e
                        (throw
                         (Exception.
                          (pp/cl-format nil
                                        "Failed to run webserver on port ~A.~@
                                         ~A"
                                        port e))))))]
        (log/info ">>>> Starting webserver on port" port)
        (let [stop-fun (start)]
          (log/info "<<<< Started web server on port" port)
          (assoc this :stop-fun stop-fun)))))

  (stop [this]
    (if-not stop-fun
      (do
        (log/warn "Webserver is not running so cannot stop it")
        this)
      (do
        (log/info ">>>> Stopping webserver")
        (stop-fun)
        (log/info "<<<< Stopped webserver")
        (assoc this :stop-fun nil)))))

(defn make-webserver [port]
  (map->WebServer {:port port}))
