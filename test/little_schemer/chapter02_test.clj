(ns little-schemer.chapter02-test
  (:use midje.sweet)
  (:use [little-schemer.chapter02]))

; 2. Do it, Do It Again, an Again, and Again...

(facts "about lat?"
  (lat? '()) => true
  (lat? '(x)) => true
  (lat? '((x))) => false
  (lat? '(jack sprat could eat not chicken fat)) => true
  (lat? '(jack (sprat could) eat no chicken fat)) => false)

(facts "about member?"
  (member? 'x '()) => false
  (member? 'x '(x)) => true
  (member? 'x '(y)) => false
  (member? 'tea '(coffee tea or milk)) => true
  (member? 'poached '(fried eggs and scrambled eggs)) => false)

