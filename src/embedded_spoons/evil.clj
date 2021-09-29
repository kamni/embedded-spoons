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

(def ^:private bet 2)
(def ^:private gimel 3)
(def ^:private vav 6)
(def ^:private chet 8)
(def ^:private kaf 20)
(def ^:private lamed 30)
(def ^:private mem 40)

(defn- pentagram [incantations]
  (. Integer parseInt (join "" (take 3 (repeat (str incantations))))))

(defn- call-quarters []
  (let [elements [:air :fire :water :earth]]
    (repeat (count elements) (pentagram vav))))

(defn- consecrate-space [sacred-space]
  (map #(/ %1 %2) sacred-space (map pentagram [bet gimel gimel gimel])))

(defn- declare-intent [ritual-words]
  (let [hand-gestures [(+ gimel kaf) (+ gimel mem) (+ chet lamed) (+ chet lamed)]]
    (map #(* %1 %2) hand-gestures ritual-words)))

(defn- ritual-sacrifice [prepared-sacrifice]
  (->> (map (juxt inc dec) prepared-sacrifice) (map #(reduce + %)) (map #(/ % 2))))

(defn- so-mote-it-be [all-hail] all-hail)

(defn- invoke-the-demon [ring-the-bell]
  (let [words-of-power [so-mote-it-be so-mote-it-be (partial + (- gimel)) so-mote-it-be]]
    (map #(%1 %2) words-of-power ring-the-bell)))

(defn- sign-the-contract [blood]
  (apply str (map char blood)))

(defn summon []
  (->
    (call-quarters)
    (consecrate-space)
    (declare-intent)
    (ritual-sacrifice)
    (invoke-the-demon)
    (sign-the-contract))) ; => EVIL

(summon)
