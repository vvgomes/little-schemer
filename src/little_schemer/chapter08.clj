(in-ns 'little-schemer.core)

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

(def atom-to-function
  (fn [a]
    (cond (eq? a '+) + :else *)))

(def value
  (fn [exp]
    (cond
      (atom? exp) exp
      :else 
        ((atom-to-function (operator exp))
           (value (first-sub-exp exp))
           (value (second-sub-exp exp))))))

(def multiremberf
  (fn [test?]
    (fn [e l]
      (cond
        (null? l) l
        (test? (car l) e) ((multiremberf test?) e (cdr l))
        :else (cons (car l) ((multiremberf test?) e (cdr l)))))))

(def multirembereq (multiremberf eq?))

(def eq?tuna (eq?c 'tuna))

(def multiremberT
  (fn [test? l]
    (cond
      (null? l) l
      (test? (car l)) (multiremberT test? (cdr l))
      :else (cons (car l) (multiremberT test? (cdr l))))))

