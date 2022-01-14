(ns dev
  "Namespace to support hacking at the CLJ REPL."
  {:clj-kondo/config
   '{:linters {:unused-namespace {:exclude [clojure.java.javadoc
                                            clojure.pprint]}
               :unused-referred-var {:exclude {clojure.java.javadoc [javadoc]
                                               clojure.pprint [pp pprint]}}
               :refer-all {:exclude [clojure.repl
                                     clojure.tools.namespace.move
                                     clojure.tools.namespace.repl
                                     nomis-clj-repl-tools]}}}}
  (:require
   [clojure.java.javadoc :refer [javadoc]]
   [clojure.pprint :refer [pp pprint]]
   [clojure.repl :refer :all ; [apropos dir doc find-doc pst source]
    ]
   [clojure.tools.namespace.move :refer :all]
   [clojure.tools.namespace.repl :as tnr :refer :all]
   [com.stuartsierra.component :as component]
   [nomis-clj-repl-tools :refer :all]
   [nomis-minimal-shadow-cljs-example.system.system :as system]))

;;;; ___________________________________________________________________________

(tnr/set-refresh-dirs "dev" "src" "test")

;;;; ___________________________________________________________________________
;;;; Reloaded workflow

(defonce the-system nil)

(defn init []
  (alter-var-root #'the-system
                  (constantly (system/make-system))))

(defn start []
  (alter-var-root #'the-system component/start))

(defn stop []
  (alter-var-root #'the-system
                  (fn [s] (when s (component/stop s)))))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn go []
  (init)
  (start))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn reset []
  (stop)
  (tnr/refresh :after 'dev/go))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn reset-all []
  (stop)
  (tnr/refresh-all :after 'dev/go))
