(in-ns 'little-schemer.core-test)

; 6. Shadows

(facts "about numbered?"
  (numbered? 1) => true
  (numbered? 'x) => false
  (numbered? '(1 + 1)) => true
  (numbered? '(1 + x)) => false
  (numbered? '(1 * 1)) => true
  (numbered? '(1 * x)) => false
  (numbered? '(3 + (4 * 5))) => true
  (numbered? '(2 * sausage)) => false)

(facts "about value"
  (value 13) => 13
  (value '(+ 1 3)) => 4
  (value '(+ 1 (* 3 4))) => 13)

(facts "about sero?"
  (sero? '()) => true
  (sero? '(())) => false)

(facts "about edd1"
  (edd1 '()) => '(())
  (edd1 '(())) => '(() ()))

(facts "about zub1"
  (zub1 '(())) => '()
  (zub1 '(() ())) => '(()))

(facts "about edd"
  (edd '() '()) => '()
  (edd '(()) '()) => '(())
  (edd '(() ()) '(())) => '(() () ()))

(facts "about zub"
  (zub '() '()) => '()
  (zub '(()) '()) => '(())
  (zub '(() ()) '(())) => '(()))

