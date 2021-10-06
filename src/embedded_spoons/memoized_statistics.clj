(ns embedded-spoons.memoized-fibonacci)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;                                                                            ;;
;; Explores the use of loop/recur and memoization with combinations and       ;;
;; permutations.                                                              ;;
;;                                                                            ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def factorial
  (memoize (fn [n]
             (loop [cnt n
                    acc 1]
               (if (zero? cnt)
                 acc
                 (recur (dec cnt) (* acc cnt)))))))

(defn permutation-without-replacement
  "Calculate the number of permutations for a set of size `n` with `r` choices.

  The formula for permutation without replacement is:

      n! / (n - r)!"
  [n r]
  (/ (factorial n)
     (factorial (- n r))))

(defn combination-without-replacement
  "Calculate the number of combinations for a set of size `n` with `r` choices.

  The formula for combination without replacement is:

    n! / r! (n - r)!"
  [n r]
  (/ (factorial n)
     (* (factorial r)
        (factorial (- n r)))))

(println (permutation-without-replacement 3 2))
(println (combination-without-replacement 3 2))
