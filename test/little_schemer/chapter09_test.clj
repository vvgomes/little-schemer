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
