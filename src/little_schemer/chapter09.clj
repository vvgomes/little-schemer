(in-ns 'little-schemer.core)

; 9. ... and Again, and Again, and Again,...

(def keep-looking
  (fn [a sorn lat]
    (cond
      (number? (pick sorn lat))
        (keep-looking a (pick sorn lat) lat)
      :else
        (eq? (pick sorn lat) a))))

