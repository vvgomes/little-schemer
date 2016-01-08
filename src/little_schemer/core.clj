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

(def member*
  (fn [n l]
    (cond
      (null? l) false
      (atom? (car l))
        (or (eq? (car l) n) (member* n (cdr l)))
      :else (or (member* n (car l)) (member* n (cdr l))))))

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

(def eqlist?
  (fn [l1 l2]
    (cond
      (and (null? l1) (null? l2)) true
      (or (null? l1) (null? l2)) false
      :else (and
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
      (member? (car (cdr exp)) '(+ × ↑))
        (and
          (numbered? (car exp))
          (numbered? (car (cdr (cdr exp)))))
      :else false)))

