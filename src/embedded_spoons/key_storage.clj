(ns embedded-spoons.key-storage
  (:require [taoensso.carmine :as car]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;                                                                            ;;
;; This was part of a separate learning exercise, where for reasons I'm       ;;
;; unable to share the accompanying code, so this does not work without       ;;
;; additional context.                                                        ;;
;;                                                                            ;;
;; The purpose of this code is to demonstrate the middleware pattern of       ;;
;; clojure, and the ability of clojure to reuse code.                         ;;
;;                                                                            ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; This is *approximately* similar to the following python code:
;;
;;    def connect_to_keystore(func):
;;        def _keystore_connection_wrapper(redis, *args):
;;            return func(car.wcar(redis['connection']), *args)
;;        return _keystore_connection_wrapper
;;
(defn- connect-to-keystore
  "Wrapper that adds car/wcar call to the specified function.
  Attempts to eliminate formulaic calls to (car/wcar (:connection redis)).

  Example:

      (def foo (connect-to-redis car/foo))

  The resulting function will take a `redis` parameter, and pass any args to
  the wrapped taoensso/carmine function
  ---
  <funtion> func: function to wrap with wcar call. Ideally a function from the
      taoensso/carmine library.

  return: wrapped function
  ---
  "
  [func]
  (fn [redis & args]
    (car/wcar (:connection redis) (apply func args))))

;; Why not call these directly? This is part of a ports-and-adapters style of
;; programming, where in principle we could swap out another backend, but keep
;; everything pointing to the same functions
;;
;; See [Carmine](https://github.com/ptaoussanis/carmine/blob/master/src/taoensso/carmine/commands.edn)
;; for the acceptable parameters for each function
(def incr (connect-to-keystore car/incr))
(def setk (connect-to-keystore car/set))
(def getk (connect-to-keystore car/get))
(def delk (connect-to-keystore car/del))
