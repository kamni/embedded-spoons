(ns embedded-spoons.core
  (:gen-class)
  (:require [embedded-spoons.evil :as evil]))

(defn -main [& args]
  (println (evil/summon)))
