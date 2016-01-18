(ns little-schemer.core)

; 1. Toys

(def atom? (complement seq?))
(def car first)
(def cdr rest)
(def null? empty?)
(def eq? =)

; 2. Do it, Do It Again, an Again, and Again...

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

; 9. Lambda the Ultimate

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

