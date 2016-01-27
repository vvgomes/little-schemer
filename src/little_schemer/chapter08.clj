(ns little-schemer.chapter08
  (:use [little-schemer.chapter07]))

; 8. Lambda the Ultimate

(def remberf
  (fn [test? e l]
    (cond
      (null? l) l
      (test? (car l) e) (cdr l)
      :else (cons (car l) (rember e (cdr l))))))

(def eq?c
  (fn [x]
    (fn [y]
      (eq? x y))))

(def eq?salad (eq?c 'salad))

(def remberf
  (fn [test?]
    (fn [e l]
      (cond
        (null? l) l
        (test? (car l) e) (cdr l)
        :else (cons (car l) (rember e (cdr l)))))))

(def remberfeq? (remberf eq?))
(def remberfequal? (remberf equal?))
(def remberf= (remberf =))

(def insertLf
  (fn [test?]
    (fn [n o l]
      (cond
        (null? l) l
        (test? (car l) o) (cons n (cons o (cdr l)))
        :else (cons (car l) ((insertLf test?) n o (cdr l)))))))

(def insertRf
  (fn [test?]
    (fn [n o l]
      (cond
        (null? l) l
        (test? (car l) o) (cons o (cons n (cdr l)))
        :else (cons (car l) ((insertRf test?) n o (cdr l)))))))

(def seqL
  (fn [o n l]
    (cons n (cons o l))))

(def seqR
  (fn [o n l]
    (cons o (cons n l))))

(def seqS
  (fn [o n l]
    (cons n l)))

(def insertg
  (fn [s]
    (fn [test?]
      (fn [n o l]
        (cond
          (null? l) l
          (test? (car l) o) (s o n (cdr l))
          :else (cons (car l) (((insertg s) test?) n o (cdr l))))))))

