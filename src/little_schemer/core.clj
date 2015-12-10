(ns little-schemer.core)

(def atom? (complement seq?))
(def car first)
(def cdr rest)
(def null? empty?)
(def eq? =)

(def lat?
  (fn [l]
    (cond
      (null? l) true
      (atom? (car l)) (lat? (cdr l))
      :else false)))

(def member?
  (fn [a l]
    (cond
      (null? l) false
      :else
        (or 
          (eq? (car l) a) 
          (member? a (cdr l))))))
