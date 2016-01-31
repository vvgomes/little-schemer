(in-ns 'little-schemer.core)

; 1. Toys

(def atom? (complement seq?))
(def car first)
(def cdr rest)
(def null? empty?)
(def eq? =)

