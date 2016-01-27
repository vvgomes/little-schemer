(ns little-schemer.chapter01)

; 1. Toys

(def atom? (complement seq?))
(def car first)
(def cdr rest)
(def null? empty?)
(def eq? =)

