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

