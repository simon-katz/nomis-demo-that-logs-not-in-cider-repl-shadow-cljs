(ns nomis-minimal-shadow-cljs-example.cljs.main-cljs
  (:require
   [reagent.dom :as dom]))

;;;; ___________________________________________________________________________
;;;; Entry point

(defn ^:private render []
  (dom/render [:div
               [:p "Hello"]
               [:p "Hello"]
               [:p "Hello"]]
              (js/document.getElementById "app")))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^:export refresh-ui []
  (println "==== refresh-ui")
  ;; Add special refresh functionality here.
  ;; /eg/ For re-frame:
  ;; (rf/clear-subscription-cache!)
  (render))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^:export init []
  (println "==== init")
  ;; Add special refresh functionality here.
  ;; /eg/ For re-frame:
  ;; (rf/dispatch-sync [:initialize])
  (render))
