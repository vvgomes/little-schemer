(ns little-schemer.chapter03
  (:use [little-schemer.chapter02]))

; 3. Cons the Magnificent

(def rember
  (fn [e l]
    (cond
      (null? l) l
      (eq? (car l) e) (cdr l)
      :else (cons (car l) (rember e (cdr l))))))

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

(def multirember
  (fn [e l]
    (cond
      (null? l) l
      (eq? (car l) e) (multirember e (cdr l))
      :else (cons (car l) (multirember e (cdr l))))))

