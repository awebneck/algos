(ns algos.core)

(def ^:dynamic *opscount* 0)

(defn incops []
  (when (thread-bound? #'*opscount*)
    (set! *opscount* (inc *opscount*))))

(defmacro measure-ops [form]
  `(binding [*opscount* 0]
     ~form
     *opscount*))

(defn batch-measure-ops
  ([fun lseq]
   (batch-measure-ops fun lseq 10))
  ([fun lseq pows]
   (into {} (reverse (reduce (fn [acc n]
             (assoc acc n (measure-ops (fun (take n lseq)))))
           {}
           (map #(int (Math/pow 10 %)) (range pows)))))))

(defn batch-analyze
  [fun lseq pows grofun]
  (into {} (map (fn [[key val]]
                 (let [groval (grofun key)]
                   [key {:ops val :growth groval :factor (if (zero? groval)
                                                           :undefined
                                                           (float (/ val groval)))}])) (batch-measure-ops fun lseq pows))))
