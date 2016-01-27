(ns little-schemer.chapter07
  (:use [little-schemer.chapter01])
  (:use [little-schemer.chapter02])
  (:use [little-schemer.chapter03])
  (:use [little-schemer.chapter05]))

; 7. Friends and Relations

(def zet?
  (fn [lat]
    (cond
      (null? lat) true
      (member? (car lat) (cdr lat)) false
      :else (zet? (cdr lat)))))

(def makeset
  (fn [lat]
    (cond
      (null? lat) lat
      :else
        (cons (car lat) (makeset (multirember (car lat) (cdr lat)))))))

(def subset?
  (fn [s1 s2]
    (cond
      (null? s1) true
      :else (and
        (member? (car s1) s2)
        (subset? (cdr s1) s2)))))

(def eqset?
  (fn [s1 s2]
    (and (subset? s1 s2) (subset? s2 s1))))

(def intersect?
  (fn [s1 s2]
    (cond
      (or (null? s1) (null? s2)) false
      :else (or (member? (car s1) s2) (intersect? (cdr s1) s2)))))

(def intersect
  (fn [s1 s2]
    (cond
      (null? s1) s1
      (member? (car s1) s2) (cons (car s1) (intersect (cdr s1) s2))
      :else (intersect (cdr s1) s2))))

(def union
  (fn [s1 s2]
    (cond
      (null? s1) s2
      (null? s2) s1
      (member? (car s1) s2) (union (cdr s1) s2)
      :else (cons (car s1) (union (cdr s1) s2)))))

(def intersectall
  (fn [lset]
    (cond
      (or (null? (car lset)) (null? (cdr lset))) (car lset)
      :else (intersect (car lset) (intersectall (cdr lset))))))

(def apair?
  (fn [sexp]
    (cond
      (or (null? sexp) (null? (cdr sexp))) false
      (null? (cdr (cdr sexp))) true
      :else false)))

(def build
  (fn [sexp1 sexp2]
    (cons sexp1 (cons sexp2 '()))))

(def third
  (fn [l]
    (car (cdr (cdr l)))))

(def atompair?
  (fn [sexp]
    (cond
      (or
        (atom? sexp)
        (null? sexp)
        (null? (cdr sexp))) false
      (and
        (null? (cdr (cdr sexp)))
        (atom? (car sexp))
        (atom? (car (cdr sexp)))) true
      :else false)))

(def rel?
  (fn [sexp]
    (cond
      (null? sexp) false
      (and
        (atompair? (car sexp))
        (member* (car sexp) (cdr sexp))) false
      :else
        (and
          (atompair? (car sexp))
          (or
            (null? (cdr sexp))
            (rel? (cdr sexp)))))))

(def fun?
  (fn [rel]
    (zet? (firsts rel))))

(def revpair
  (fn [pair]
    (build (second pair) (first pair))))

(def revrel
  (fn [rel]
    (cond
      (null? rel) '()
      :else
        (cons
          (revpair (car rel))
          (revrel (cdr rel))))))

(def fullfun?
  (fn [rel]
    (and
      (fun? rel)
      (fun? (revrel rel)))))

