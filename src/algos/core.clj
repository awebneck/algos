(ns algos.core)

(def ^:dynamic *opscount* 0)

(defn incops []
  (when (thread-bound? #'*opscount*)
    (set! *opscount* (inc *opscount*))))

(defn batch-analyze
  ([fun lseq]
   (batch-analyze fun lseq 10))
  ([fun lseq pows]
   (reduce (fn [acc n]
             (assoc acc n (analyze (fun (take n lseq)))))
           {}
           (map #(int (Math/pow 10 %)) (range pows)))))

(defmacro analyze [form]
  `(binding [*opscount* 0]
     ~form
     *opscount*))
