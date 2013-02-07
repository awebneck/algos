(ns algos.growth)

(defn logarithmic
  [n]
  (/ (Math/log n) (Math/log 2)))

(defn linear
  [n]
  n)

(defn linearithmic
  [n]
  (* n (logarithmic n)))

(defn polynomial
  [n e]
  (Math/pow n e))

(defn quadratic
  [n]
  (polynomial n 2))

(defn cubic
  [n]
  (polynomial n 3))

(defn quartic
  [n]
  (polynomial n 4))

(defn quintic
  [n]
  (polynomial n 5))
