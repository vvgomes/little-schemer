(in-ns 'little-schemer.core-test)

; 9. ... and Again, and Again, and Again,...

(facts "about keep-looking"
  (keep-looking 'caviar 1 '(6 2 4 caviar 5 7 3)) =>
  (keep-looking 'caviar 6 '(6 2 4 caviar 5 7 3)) 

  (keep-looking 'caviar 6 '(6 2 4 caviar 5 7 3)) =>
  (keep-looking 'caviar 7 '(6 2 4 caviar 5 7 3)) 

  (keep-looking 'caviar 7 '(6 2 4 caviar 5 7 3)) =>
  (keep-looking 'caviar 3 '(6 2 4 caviar 5 7 3)) 

  (keep-looking 'caviar 3 '(6 2 4 caviar 5 7 3)) =>
  (keep-looking 'caviar 4 '(6 2 4 caviar 5 7 3)) 

  (keep-looking 'caviar 4 '(6 2 4 caviar 5 7 3)) => true

  (keep-looking 'caviar 4 '(6 2 4 tuna 5 7 3)) => false)

(facts "about shift"
  (shift '((a b) c)) => '(a (b c))
  (shift '((a b) (c d))) => '(a (b (c d))))

(facts "about length"
  (length '()) => 0
  (length '(a)) => 1
  (length '(a b)) => 2
  (length '(a b c)) => 3
  (length '(a b c d)) => 4)

(facts "about length-0"
  (length-0 '()) => 0
  (length-0 '(a)) => (throws RuntimeException))

(facts "about length-1"
  (length-1 '()) => 0
  (length-1 '(a)) => 1
  (length-1 '(a b)) => (throws RuntimeException))

(facts "about length-2"
  (length-2 '()) => 0
  (length-2 '(a)) => 1
  (length-2 '(a b)) => 2
  (length-2 '(a b c)) => (throws RuntimeException))

