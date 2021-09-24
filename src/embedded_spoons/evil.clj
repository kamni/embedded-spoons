(ns embedded-spoons.evil
  (:require [clojure.string :refer [join]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;                                                                            ;;
;; This is an intentionally obscure exercise intended to parody my feelings   ;;
;; that clojure is an esoteric language that might unintentionally summon     ;;
;; a demon.                                                                   ;;
;;                                                                            ;;
;; It's also the result of learning about the following constructions in      ;;
;; clojure yesterday:                                                         ;;
;;                                                                            ;;
;;     => (map #(%1 %2) [incr decr (partial / 2)] [2 3 4])                    ;;
;;     (3 2 2)                                                                ;;
;;                                                                            ;;
;;     => ((juxt inc dec) 3)                                                  ;;
;;     (4 2)                                                                  ;;
;;                                                                            ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- pentagram [incantations]
  (. Integer parseInt (join "" (take 3 (repeat (str incantations))))))

(defn- rend-asunder [sacrifice]
  (->> (map (juxt inc dec) sacrifice) (map #(reduce + %)) (map #(/ % 2))))

(defn- so-mote-it-be [all-hail] all-hail)

(defn summon []
  (let [bet 2
        gimel 3
        vav 6
        chet 8
        kaf 20
        lamed 30
        mem 40
        elements [:air :fire :water :earth]]
    (->>
      (repeat (count elements) (pentagram vav))
      (map #(/ %2 %1) (map pentagram [bet gimel gimel gimel]))
      (map #(* %1 %2) [(+ gimel kaf) (+ gimel mem) (+ chet lamed) (+ chet lamed)])
      (rend-asunder)
      (map #(%1 %2) [so-mote-it-be so-mote-it-be (partial + (-  gimel)) so-mote-it-be])
      (map char)
      (apply str)))) ; => EVIL
