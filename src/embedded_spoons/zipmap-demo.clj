(ns embedded-spoons.zipmap-demo)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;                                                                            ;;
;; Small functions to play around with zipmap.                                ;;
;;                                                                            ;;
;; Run the `demo` function to see all the functions in action.                ;;
;;                                                                            ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn default-map
  "Create a default map from the specified keys, set to the default value."
  [default-keys default-value]
  (zipmap default-keys (repeat (count default-keys) default-value)))

(defn update-map-with-default-values
  "Replace all nil values in a map with a default value."
  [base-map default-value]
  (let [default-vals (map #(or % default-value) (vals base-map))]
    (zipmap (keys base-map) default-vals)))

(defn increment-values-in-map
  "Increments the values of the supplied map by 1.
  Will convert nil values to 0 before incrementing."
  [map-to-incr]
  (let [non-nil-map (update-map-with-default-values map-to-incr 0)]
    (zipmap (keys map-to-incr) (map inc (vals non-nil-map)))))

(defn increment-values-with-default-map
  "Increments the values of a supplied map by 1, merging it with a default map.
  Default map keys are [:a :b :c], unless specified by `:default-keys`."
  [map-to-incr & {:keys [default-keys] :or {default-keys [:a :b :c]}}]
  (let [defaults (default-map default-keys 0)
        base-map (merge defaults map-to-incr)]
    (increment-values-in-map base-map)))

(defn demo
  "Runs all snippets in this module and prints their return values"
  []
  (let [divider "----"]
    (map println [divider
                  (default-map [:a :b :c] 0)
                  (update-map-with-default-values {:a nil :b nil :c 1} 0)
                  (increment-values-in-map {:a nil :b 2})
                  (increment-values-with-default-map {:b 2} :default-keys [:a :b])
                  divider])))

(demo)
