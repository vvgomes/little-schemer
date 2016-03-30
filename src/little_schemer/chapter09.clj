(in-ns 'little-schemer.core)

; 9. ... and Again, and Again, and Again,...

(def keep-looking
  (fn [a sorn lat]
    (cond
      (number? sorn)
        (keep-looking a (pick sorn lat) lat)
      :else
        (eq? sorn a))))

(def shift
  (fn [l]
    (build (first (first l)) (build (second (first l)) (second l)))))

(def length
  (fn [l]
   (cond
     (null? l) 0
     :else (add1 (length (cdr l))))))

(defn eternity [_] (throw (RuntimeException. "dead end")))

(def length-0
  (fn [l]
   (cond
     (null? l) 0
     :else (add1 (eternity (cdr l))))))

(def length-1
  (fn [l]
   (cond
     (null? l) 0
     :else (add1
       ((fn [l]
         (cond
           (null? l) 0
           :else (add1 (eternity (cdr l)))))(cdr l))))))

(def length-2
  (fn [l]
   (cond
     (null? l) 0
     :else (add1
       ((fn [l]
         (cond
           (null? l) 0
           :else (add1
             ((fn [l]
               (cond
                 (null? l) 0
                 :else (add1 (eternity (cdr l)))))(cdr l)))))(cdr l))))))

(def length-0
  ((fn [length]
    (fn [l]
     (cond
       (null? l) 0
       :else (add1 (length (cdr l)))))) eternity))

(def length-1
  ((fn [length]
    (fn [l]
     (cond
       (null? l) 0
       :else (add1 (length (cdr l))))))
    ((fn [length]
      (fn [l]
       (cond
         (null? l) 0
         :else (add1 (length (cdr l)))))) eternity)))

(def length-2
  ((fn [length]
    (fn [l]
     (cond
       (null? l) 0
       :else (add1 (length (cdr l))))))
    ((fn [length]
      (fn [l]
       (cond
         (null? l) 0
         :else (add1 (length (cdr l))))))
      ((fn [length]
        (fn [l]
         (cond
           (null? l) 0
           :else (add1 (length (cdr l)))))) eternity))))

(def length-0
  ((fn [mk-length]
    (mk-length eternity))
  (fn [length]
    (fn [l]
      (cond
        (null? l) 0
        :else (add1 (length (cdr l))))))))

(def length-1
  ((fn [mk-length]
    (mk-length (mk-length eternity)))
  (fn [length]
    (fn [l]
      (cond
        (null? l) 0
        :else (add1 (length (cdr l))))))))

(def length-2
  ((fn [mk-length]
    (mk-length (mk-length (mk-length eternity))))
  (fn [length]
    (fn [l]
      (cond
        (null? l) 0
        :else (add1 (length (cdr l))))))))

(def length-0
  ((fn [mk-length]
    (mk-length mk-length))
  (fn [length]
    (fn [l]
      (cond
        (null? l) 0
        :else (add1 (length (cdr l))))))))

(def length
  ((fn [mk-length]
    (mk-length mk-length))
  (fn [mk-length]
    (fn [l]
      (cond
        (null? l) 0
        :else (add1 ((mk-length mk-length) (cdr l))))))))

(def length
  ((fn [mk-length]
    (mk-length mk-length))
  (fn [mk-length]
    (fn [l]
      (cond
        (null? l) 0
        :else
          (add1
            ((fn [x] ((mk-length mk-length) x)) (cdr l))))))))

(def length
  ((fn [mk-length]
    (mk-length mk-length))
  (fn [mk-length]
    ((fn [length] 
      (fn [l] (cond (null? l) 0 :else (add1 (length (cdr l))))))
      (fn [x] ((mk-length mk-length) x))))))

(def length
  ((fn [le] 
    ((fn [mk-length] (mk-length mk-length))
      (fn [mk-length]
        (le (fn [x] ((mk-length mk-length) x))))))
    (fn [length] (fn [l] (cond (null? l) 0 :else (add1 (length (cdr l))))))))

(def Y
  (fn [le] 
    ((fn [f] (f f))
      (fn [f] (le (fn [x] ((f f) x)))))))

(def length
  (Y
    (fn [length]
      (fn [l]
        (cond
          (null? l) 0
          :else (add1 (length (cdr l))))))))

