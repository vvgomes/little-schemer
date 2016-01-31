(in-ns 'little-schemer.core)

; 6. Shadows

(def numbered?
  (fn [exp]
    (cond
      (atom? exp) (number? exp)
      :else
        (and
          (member? (car (cdr exp)) '(+ *))
          (numbered? (car exp))
          (numbered? (car (cdr (cdr exp))))))))

(def value
  (fn [exp]
    (cond
      (atom? exp) exp
      (eq? (car exp) '+)
        (+ (value (car (cdr exp))) (value (car (cdr (cdr exp)))))
      (eq? (car exp) '*)
        (* (value (car (cdr exp))) (value (car (cdr (cdr exp))))))))

(def first-sub-exp
  (fn [exp]
    (car (cdr exp))))

(def second-sub-exp
  (fn [exp]
    (car (cdr (cdr exp)))))

(def operator
  (fn [exp]
    (car exp)))

(def value
  (fn [exp]
    (cond
      (atom? exp) exp
      (eq? (operator exp) '+)
        (+ (value (first-sub-exp exp)) (value (second-sub-exp exp)))
      (eq? (operator exp) '*)
        (* (value (first-sub-exp exp)) (value (second-sub-exp exp))))))

(def sero?
  (fn [l]
    (null? l)))

(def edd1
  (fn [l]
    (cons '() l)))

(def zub1
  (fn [l]
    (cdr l)))

(def edd
  (fn [l1 l2]
    (cond
      (sero? l1) l2
      :else (edd (zub1 l1) (edd1 l2)))))

(def zub
  (fn [l1 l2]
    (cond
      (sero? l2) l1
      :else (zub (zub1 l1) (zub1 l2)))))

