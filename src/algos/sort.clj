(ns algos.sort
  [:use [algos.core :only [incops]]])

(defn- merge-colls
  [left right]
  (loop [[lhead & ltail :as left]  left
         [rhead & rtail :as right] right
         coll                      []]
    (cond (and (nil? lhead)
               (nil? rhead))                                 (do (incops) (seq coll))
          (or (nil? lhead)
              (> lhead (or rhead Double/POSITIVE_INFINITY))) (do (incops) (recur left rtail (conj coll rhead)))
          :else                                              (do (incops) (recur ltail right (conj coll lhead))))))

(defn mergesort
  [coll]
  (if (= (count coll) 1)
    coll
    (let [midpoint (quot (count coll) 2)
          left     (mergesort (take midpoint coll))
          right    (mergesort (drop midpoint coll))]
      (merge-colls left right))))
