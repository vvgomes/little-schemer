(ns little-schemer.chapter04
  (:use [little-schemer.chapter01]))

; 4. Numbers Games

(def add1 (partial + 1))

(defn flip [f]
  (comp (partial apply f) reverse list))

(def sub1 (partial (flip -) 1))

(def add
  (fn [n m]
    (cond
      (zero? n) m
      :else (add (sub1 n) (add1 m)))))

(def sub
  (fn [n m]
    (cond
      (zero? m) n
      :else (sub (sub1 n) (sub1 m)))))

(def addtup
  (fn [tup]
    (cond
      (null? tup) 0
      :else (add (car tup) (addtup (cdr tup))))))

(def mult
  (fn [n m]
    (cond
      (zero? n) 0
      :else (add (mult (sub1 n) m) m))))

(def tup+
  (fn [t1 t2]
    (cond
      (null? t1) t2
      (null? t2) t1
      :else (cons (add (car t1) (car t2)) (tup+ (cdr t1) (cdr t2))))))

(def gt?
  (fn [n m]
    (cond
      (zero? n) false
      (zero? m) true
      :else (gt? (sub1 n) (sub1 m)))))

(def lt?
  (fn [n m]
    (cond
      (zero? m) false
      (zero? n) true
      :else (lt? (sub1 n) (sub1 m)))))

(def eqn?
  (fn [n m]
    (cond
      (gt? n m) false
      (lt? n m) false
      :else true)))

(def pow
  (fn [n m]
    (cond
      (zero? m) 1
      :else (mult n (pow n (sub1 m))))))

(def div
  (fn [n m]
    (cond
      (lt? n m) 0
      :else (add1 (div (sub n m) m)))))

(def length
  (fn [l]
   (cond
     (null? l) 0
     :else (add1 (length (cdr l))))))

(def pick
  (fn [n l]
    (cond
      (eq? n 1) (car l)
      :else (pick (sub1 n) (cdr l)))))

(def rempick
  (fn [n l]
    (cond
      (eq? n 1) (cdr l)
      :else (cons (car l) (rempick (sub1 n) (cdr l))))))

(def nonums
  (fn [l]
    (cond
      (null? l) l
      (number? (car l)) (nonums (cdr l))
      :else (cons (car l) (nonums (cdr l))))))

(def allnums
  (fn [l]
    (cond
      (null? l) l
      (number? (car l)) (cons (car l) (allnums (cdr l)))
      :else (allnums (cdr l)))))

(def occur
  (fn [a l]
    (cond
      (null? l) 0
      (eq? (car l) a) (add1 (occur a (cdr l)))
      :else (occur a (cdr l)))))

(def one?
  (fn [n]
    (eq? 1 n)))

(def rempick
  (fn [n l]
    (cond
      (one? n) (cdr l)
      :else (cons (car l) (rempick (sub1 n) (cdr l))))))

