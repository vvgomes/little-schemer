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

(def multirember&co
  (fn [a lat col]
    (cond
      (null? lat)
        (col '() '())
      (eq? (car lat) a)
        (multirember&co a (cdr lat)
          (fn [newlat seen] (col newlat (cons (car lat) seen))))
      :else
        (multirember&co a (cdr lat)
          (fn [newlat seen] (col (cons (car lat) newlat) seen))))))

(def multiinsertLR
  (fn [n oL oR lat]
    (cond
      (null? lat) lat
      (eq? (car lat) oL)
        (cons n (cons oL (multiinsertLR n oL oR (cdr lat))))
      (eq? (car lat) oR)
        (cons oR (cons n (multiinsertLR n oL oR (cdr lat))))
      :else
        (cons (car lat) (multiinsertLR n oL oR (cdr lat))))))

(def multiinsertLR&co
  (fn [n oL oR lat co]
    (cond
      (null? lat)
        (co '() 0 0)
      (eq? (car lat) oL)
        (multiinsertLR&co n oL oR (cdr lat)
          (fn [newlat L R]
            (co (cons n (cons oL newlat)) (add1 L) R)))
      (eq? (car lat) oR)
        (multiinsertLR&co n oL oR (cdr lat)
          (fn [newlat L R]
            (co (cons oR (cons n newlat)) L (add1 R))))
      :else
        (multiinsertLR&co n oL oR (cdr lat)
          (fn [newlat L R]
            (co (cons (car lat) newlat) L R))))))

(def evensonly*
  (fn [l]
    (cond
      (null? l) l
      (atom? (car l))
        (cond
          (even? (car l)) (cons (car l) (evensonly* (cdr l)))
          :else (evensonly* (cdr l)))
      :else
        (cons (evensonly* (car l)) (evensonly* (cdr l))))))

