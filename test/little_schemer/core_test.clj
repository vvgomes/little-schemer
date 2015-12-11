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

(facts "about multiinsertR"
  (multiinsertR 'n 'o '()) => '()
  (multiinsertR 'n 'o '(x)) => '(x)
  (multiinsertR 'n 'o '(o)) => '(o n)
  (multiinsertR 'n 'o '(o x)) => '(o n x)
  (multiinsertR 'n 'o '(x o y)) => '(x o n y)
  (multiinsertR 'n 'o '(x o y o)) => '(x o n y o n))

(facts "about multiinsertL"
  (multiinsertL 'n 'o '()) => '()
  (multiinsertL 'n 'o '(x)) => '(x)
  (multiinsertL 'n 'o '(o)) => '(n o)
  (multiinsertL 'n 'o '(o x)) => '(n o x)
  (multiinsertL 'n 'o '(x o y)) => '(x n o y)
  (multiinsertL 'n 'o '(x o y o)) => '(x n o y n o))

(facts "about multisubst"
  (multisubst 'n 'o '()) => '()
  (multisubst 'n 'o '(x)) => '(x)
  (multisubst 'n 'o '(o)) => '(n)
  (multisubst 'n 'o '(o x)) => '(n x)
  (multisubst 'n 'o '(x o y)) => '(x n y)
  (multisubst 'n 'o '(x o y o)) => '(x n y n))

(facts "about add1"
  (add1 0) => 1
  (add1 67) => 68)

(facts "about sub1"
  (sub1 1) => 0
  (sub1 67) => 66)

(facts "about zero?"
  (zero? 0) => true
  (zero? 1492) => false)

(facts "about add"
  (add 0 12) => 12
  (add 46 0) => 46
  (add 46 12) => 58)

(facts "about sub"
  (sub 18 0) => 18
  (sub 14 3) => 11)

(facts "about addtup"
  (addtup '()) => 0
  (addtup '(1)) => 1
  (addtup '(1 2)) => 3
  (addtup '(3 5 2 8)) => 18)

(facts "about mult"
  (mult 0 0) => 0
  (mult 0 1) => 0
  (mult 0 2) => 0
  (mult 1 0) => 0
  (mult 1 1) => 1
  (mult 1 2) => 2
  (mult 2 0) => 0
  (mult 2 1) => 2
  (mult 2 2) => 4)

