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
    (build (first (first l)) (cons (second (first l)) (cdr l)))))
