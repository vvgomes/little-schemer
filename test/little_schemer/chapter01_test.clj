(in-ns 'little-schemer.core-test)

; 1. Toys

(facts "about atom?"
  (atom? 'turkey) => true
  (atom? '1492) => true
  (atom? '()) => false
  (atom? '(x)) => false)

(facts "about car"
  (car '(a b c)) => 'a
  (car '((a b c) x y z)) => '(a b c)
  (car '(((hotdogs)) (and) (pickle) relish)) => '((hotdogs)))

(facts "about cdr"
  (cdr '(a b c)) => '(b c)
  (cdr '((a b c) x y z)) => '(x y z)
  (cdr '(hamburger)) => '())

(facts "about cons"
  (cons 'x '()) => '(x)
  (cons 'peanut '(butter and jelly)) => '(peanut butter and jelly)
  (cons '(banana and) '(peanut butter and jelly))
     => '((banana and) peanut butter and jelly))

(facts "about null?"
  (null? '()) => true
  (null? '(a)) => false
  (null? '(a b c)) => false)

(facts "about eq?"
  (eq? 'Harry 'Harry) => true
  (eq? 'margarine 'butter) => false)

