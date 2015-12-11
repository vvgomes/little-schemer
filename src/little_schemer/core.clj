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
  (fn [e l]
    (cond
      (null? l) false
      :else
        (or 
          (eq? (car l) e)
          (member? e (cdr l))))))

(def rember?
  (fn [e l]
    (cond
      (null? l) l
      (eq? (car l) e) (cdr l)
      :else (cons (car l) (rember? e (cdr l))))))

(def firsts
  (fn [l]
    (cond
      (null? l) l
      (atom? (car l)) (firsts (cdr l))
      :else (cons (car (car l)) (firsts (cdr l))))))

(def insertR
  (fn [n o l]
    (cond
      (null? l) l
      (eq? (car l) o) (cons o (cons n (cdr l)))
      :else (cons (car l) (insertR n o (cdr l))))))

(def insertL
  (fn [n o l]
    (cond
      (null? l) l
      (eq? (car l) o) (cons n l)
      :else (cons (car l) (insertL n o (cdr l))))))

(def subst
  (fn [n o l]
    (cond
      (null? l) l
      (eq? (car l) o) (cons n (cdr l))
      :else (cons (car l) (subst n o (cdr l))))))

(def multiinsertR
  (fn [n o l]
    (cond
      (null? l) l
      (eq? (car l) o) (cons o (cons n (multiinsertR n o (cdr l))))
      :else (cons (car l) (multiinsertR n o (cdr l))))))

(def multiinsertL
  (fn [n o l]
    (cond
      (null? l) l
      (eq? (car l) o) (cons n (cons o (multiinsertL n o (cdr l))))
      :else (cons (car l) (multiinsertL n o (cdr l))))))

(def multisubst
  (fn [n o l]
    (cond
      (null? l) l
      (eq? (car l) o) (cons n (multisubst n o (cdr l)))
      :else (cons (car l) (multisubst n o (cdr l))))))

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

