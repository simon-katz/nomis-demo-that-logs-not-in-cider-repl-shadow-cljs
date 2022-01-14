(ns nomis-minimal-shadow-cljs-example.system.system
  (:require
   [clojure.pprint :as pp]
   [com.stuartsierra.component :as component]
   [nomis-minimal-shadow-cljs-example.system.webserver :as webserver]))

(defn make-system []
  (merge (component/system-map
          :webserver (webserver/make-webserver 3002))))

;;;; ___________________________________________________________________________
;;;; Avoid annoying printing of large system maps when pretty printing is
;;;; turned on.

(defmethod pp/simple-dispatch com.stuartsierra.component.SystemMap
  [_]
  (binding [*print-readably* false]
    (clojure.pprint/write "#<SystemMap>")))
