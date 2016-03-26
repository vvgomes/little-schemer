(in-ns 'little-schemer.core)

; 9. ... and Again, and Again, and Again,...

(def keep-looking
  (fn [a sorn lat]
    (or
      (eq? (pick sorn lat) a) 
      (and 
        (number? (pick sorn lat))
        (keep-looking a (pick sorn lat) lat)))
    ))
