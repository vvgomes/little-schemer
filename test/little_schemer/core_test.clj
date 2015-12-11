(ns little-schemer.core-test
  (:use midje.sweet)
  (:use [little-schemer.core]))

(facts "about atom?"
  (atom? 'a) => true
  (atom? '()) => false
  (atom? '(a)) => false
  (atom? '((a b) c)) => false)

(facts "about car"
  (car '(a b c)) => 'a
  (car '((a b c) x y z)) => '(a b c)
  (atom? (car '(a b c)))=> true)

(facts "about cdr"
  (cdr '(a b c)) => '(b c)
  (cdr '((a b c) x y z)) => '(x y z)
  (cdr '(a)) => '()
  (car (cdr '((b) (x y) ((c))))) => '(x y)
  (atom? (cdr '(a b c)))=> false)

(facts "about cons"
  (cons 'a '(b c)) => '(a b c)
  (cons 'a '()) => '(a)
  (cons 'a (car '((b) c d))) => '(a b)
  (cons 'a (cdr '((b) c d))) => '(a c d))

(facts "about null?"
  (null? '()) => true
  (null? '(a)) => false
  (null? '(a b c)) => false)

(facts "about eq?"
  (eq? 'a 'a) => true
  (eq? 'a 'b) => false
  (eq? (car '(a b)) 'a) => true)

(facts "about lat?"
  (lat? '()) => true
  (lat? '(a)) => true
  (lat? '(a b c)) => true
  (lat? '((a) b c)) => false
  (lat? '(a (b c))) => false)

(facts "about member?"
  (member? 'a '()) => false
  (member? 'a '(a)) => true
  (member? 'a '(b)) => false
  (member? 'a '(a b c)) => true
  (member? 'a '(b c d)) => false)

(facts "about rember?"
  (rember? 'a '()) => '()
  (rember? 'a '(a)) => '()
  (rember? 'a '(b)) => '(b)
  (rember? 'a '(a b c)) => '(b c)
  (rember? 'a '(b a c)) => '(b c)
  (rember? 'a '(b a c a)) => '(b c a))

