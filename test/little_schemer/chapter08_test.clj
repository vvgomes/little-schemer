(in-ns 'little-schemer.core-test)

; 8. Lambda the Ultimate

(facts "about remberf"
  ((remberf eq?) 'mint '(lamb chops and mint jelly))
                    => '(lamb chops and jelly)

  ((remberf equal?) 'mint '(lamb chops and mint jelly))
                       => '(lamb chops and jelly)

  ((remberf =) 'mint '(lamb chops and mint jelly))
                  => '(lamb chops and jelly)

  (remberfeq? 'mint '(lamb chops and mint jelly))
                 => '(lamb chops and jelly)

  (remberfequal? 'mint '(lamb chops and mint jelly))
                    => '(lamb chops and jelly)

  (remberf= 'mint '(lamb chops and mint jelly))
               => '(lamb chops and jelly))

(facts "about eq?c"
  ((eq?c 'salad) 'salad) => true
  ((eq?c 'salad) 'tuna) => false)

(facts "about eq?salad"
  (eq?salad 'salad) => true
  (eq?salad 'tuna) => false)

(facts "about insertLf"
  ((insertLf eq?) 'n 'o '(x o y o)) => '(x n o y o)
  ((insertLf equal?) 'n 'o '(x o y o)) => '(x n o y o)
  ((insertLf =) 'n 'o '(x o y o)) => '(x n o y o))

(facts "about insertRf"
  ((insertRf eq?) 'n 'o '(x o y o)) => '(x o n y o)
  ((insertRf equal?) 'n 'o '(x o y o)) => '(x o n y o)
  ((insertRf =) 'n 'o '(x o y o)) => '(x o n y o))

(facts "about insertg"
  (((insertg seqL) eq?) 'n 'o '(x o y o)) => '(x n o y o)
  (((insertg seqR) eq?) 'n 'o '(x o y o)) => '(x o n y o)
  (((insertg seqS) eq?) 'n 'o '(x o y o)) => '(x n y o))

(facts "about value"
  (value 13) => 13
  (value '(+ 1 3)) => 4
  (value '(+ 1 (* 3 4))) => 13)

(facts "about multiremberf"
  ((multiremberf eq?) 'tuna '(shrimp salad tuna salad and tuna)) => '(shrimp salad salad and))

(facts "about multirembereq"
  (multirembereq 'tuna '(shrimp salad tuna salad and tuna)) => '(shrimp salad salad and))

(facts "about multiremberT"
  (multiremberT eq?tuna '(shrimp salad tuna salad and tuna)) => '(shrimp salad salad and))

(facts "about multirember&co"
  (multirember&co 'tuna '() (fn [x y] (null? y))) => true
  (multirember&co 'tuna '(tuna) (fn [x y] (null? y))) => false
  (multirember&co 'tuna '(and tuna) (fn [x y] (null? y))) => false
  (multirember&co 'tuna '(strawberry tuna and swordfish) (fn [x y] (length x))) => 3)

(facts "about multiinsertLR"
  (multiinsertLR 'n 'x 'y '()) => '()
  (multiinsertLR 'n 'x 'y '(x)) => '(n x)
  (multiinsertLR 'n 'x 'y '(y)) => '(y n)
  (multiinsertLR 'n 'x 'y '(x y)) => '(n x y n)
  (multiinsertLR 'n 'x 'y '(z w)) => '(z w))

(facts "about multiinsertLR&co"
  (multiinsertLR&co 'salty 'fish 'chips
    '(chips and fish or fish and chips)
    (fn [newlat L R] newlat))
    => '(chips salty and salty fish or salty fish and chips salty)

  (multiinsertLR&co 'salty 'fish 'chips
    '(chips and fish or fish and chips)
    (fn [newlat L R] L)) => 2

  (multiinsertLR&co 'salty 'fish 'chips
    '(chips and fish or fish and chips)
    (fn [newlat L R] R)) => 2)

(facts "about evensonly*"
  (evensonly* '((9 1 2 8) 3 10 ((9 9) 7 6) 2)) => '((2 8) 10 (() 6) 2))

