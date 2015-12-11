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

(facts "about firsts"
  (firsts '()) => '()
  (firsts '(a)) => '()
  (firsts '((a))) => '(a)
  (firsts '(((a)))) => '((a))
  (firsts '((a) (b) (c))) => '(a b c)
  (firsts '((a) b (c))) => '(a c)
  (firsts '((a b) (b c) (c d))) => '(a b c)
  (firsts '((a b) ((b) c) (c d))) => '(a (b) c))

(facts "about insertR"
  (insertR 'n 'o '()) => '()
  (insertR 'n 'o '(x)) => '(x)
  (insertR 'n 'o '(o)) => '(o n)
  (insertR 'n 'o '(o x)) => '(o n x)
  (insertR 'n 'o '(x o y)) => '(x o n y)
  (insertR 'n 'o '(x o y o)) => '(x o n y o))

(facts "about insertL"
  (insertL 'n 'o '()) => '()
  (insertL 'n 'o '(x)) => '(x)
  (insertL 'n 'o '(o)) => '(n o)
  (insertL 'n 'o '(o x)) => '(n o x)
  (insertL 'n 'o '(x o y)) => '(x n o y)
  (insertL 'n 'o '(x o y o)) => '(x n o y o))

(facts "about subst"
  (subst 'n 'o '()) => '()
  (subst 'n 'o '(x)) => '(x)
  (subst 'n 'o '(o)) => '(n)
  (subst 'n 'o '(o x)) => '(n x)
  (subst 'n 'o '(x o y)) => '(x n y)
  (subst 'n 'o '(x o y o)) => '(x n y o))

