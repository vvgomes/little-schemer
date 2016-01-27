(ns little-schemer.chapter05
  (:use [little-schemer.chapter01])
  (:use [little-schemer.chapter04]))

; 5. Oh My Gawd: It's Full of Stars

(def rember*
  (fn [e l]
    (cond
      (null? l) l
      (atom? (car l))
        (cond
          (eq? (car l) e) (rember* e (cdr l))
          :else (cons (car l) (rember* e (cdr l))))
      :else (cons (rember* e (car l)) (rember* e (cdr l))))))
  
(def insertR*
  (fn [n o l ]
    (cond
      (null? l) l
      (atom? (car l))
        (cond
          (eq? (car l) o) (cons o (cons n (insertR* n o (cdr l))))
          :else (cons (car l) (insertR* n o (cdr l))))
      :else (cons (insertR* n o (car l)) (insertR* n o (cdr l))))))

(def occur*
  (fn [e l]
    (cond
      (null? l) 0
      (atom? (car l))
        (cond
          (eq? (car l) e) (add1 (occur* e (cdr l)))
          :else (occur* e (cdr l)))
      :else (add (occur* e (car l)) (occur* e (cdr l))))))

(def subst*
  (fn [n o l]
    (cond
      (null? l) l
      (atom? (car l))
        (cond
          (eq? (car l) o) (cons n (subst* n o (cdr l)))
          :else (cons (car l) (subst* n o (cdr l))))
      :else (cons (subst* n o (car l)) (subst* n o (cdr l))))))

(def insertL*
  (fn [n o l]
    (cond
      (null? l) l
      (atom? (car l))
        (cond
          (eq? (car l) o) (cons n (cons o (insertL* n o (cdr l))))
          :else (cons (car l) (insertL* n o (cdr l))))
      :else (cons (insertL* n o (car l)) (insertL* n o (cdr l))))))

(def leftmost
  (fn [l]
    (cond
      (atom? (car l)) (car l)
      :else (leftmost (car l)))))

(def eqlist?
  (fn [l1 l2]
    (cond
      (and (null? l1) (null? l2)) true
      (or (null? l1) (null? l2)) false
      :else (and (eq? (car l1) (car l2)) (eqlist? (cdr l1) (cdr l2))))))

(def equal?
  (fn [s1 s2]
    (cond
      (and (atom? s1) (atom? s2)) (eq? s1 s2)
      (or (atom? s1) (atom? s2)) false
      :else (eqlist? s1 s2))))

(def member*
  (fn [n l]
    (cond
      (null? l) false
      (atom? (car l))
        (or (eq? (car l) n) (member* n (cdr l)))
      (equal? (car l) n) true
      :else (or (member* n (car l)) (member* n (cdr l))))))

(def eqlist?
  (fn [l1 l2]
    (cond
      (and (null? l1) (null? l2)) true
      (or (null? l1) (null? l2)) false
      :else
        (and
          (equal? (car l1) (car l2))
          (eqlist? (cdr l1) (cdr l2))))))

(def rembersexp
  (fn [s l]
    (cond
      (null? l) l
      (equal? (car l) s) (cdr l)
      :else (cons (car l) (rembersexp s (cdr l))))))

