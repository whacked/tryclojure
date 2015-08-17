(ns tryclojure.views.eval
  (:use [overtone.live]
        [overtone.inst.piano]
        [overtone.inst.sampled-piano]
        [overtone.synth.stringed])
  (:require [tryclojure.models.eval :refer [eval-request]]
            [noir.response :as resp]
            [overtone.inst.synth :as synth]
            [clojure.pprint :as pp])
  (:import java.io.StringWriter))

(defn eval-json [expr-orig jsonp]
  ;; initially was explicitly wrapping expr-orig with the imports we want, and
  ;; sending it to the sandbox; but then we have to deal with the thread
  ;; timeout control. Will do another day...
  ;; FYI, looks like this:
  ;; (str
  ;;  "(do
  ;;    (require '[overtone.inst.synth :as synth])
  ;;    (use '[overtone.live])
  ;;    (use '[overtone.inst.piano])
  ;;    (use '[overtone.inst.sampled-piano])
  ;;    (use '[overtone.synth.stringed])"
  ;;  expr-orig
  ;;  ")")
  (let [expr expr-orig
        form (read-string expr)
        ]
    (pp/pprint form)
    (let [{:keys [expr result error message] :as res} ;; (eval-request expr)
          {:expr form
           :result (let [res (binding [*ns* (find-ns 'tryclojure.views.eval)]
                               (eval form))
                         out (StringWriter.)]
                     [out res])}
          data (if error
                 res
                 (let [[out res] result]
                   {:expr (pr-str expr)
                    :result (str out (pr-str res))}))]
      (if jsonp
        (resp/jsonp jsonp data)
        (resp/json data)))))
