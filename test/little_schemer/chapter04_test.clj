(in-ns 'little-schemer.core-test)

; 4. Numbers Games

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

(facts "about tup+ for same length tups"
  (tup+ '() '()) => '()
  (tup+ '(1) '(2)) => '(3)
  (tup+ '(1 2) '(2 3)) => '(3 5)
  (tup+ '(3 6 9 11 4) '(8 5 2 0 7)) => '(11 11 11 11 11))

(facts "about tup+ for different length tups"
  (tup+ '(1) '()) => '(1)
  (tup+ '() '(1)) => '(1)
  (tup+ '(3 7) '(4 6 8 1)) => '(7 13 8 1)
  (tup+ '(3 7 8 1) '(4 6)) => '(7 13 8 1))

(facts "about gt?"
  (gt? 0 0) => false
  (gt? 12 133) => false
  (gt? 120 11) => true
  (gt? 12 12) => false)

(facts "about lt?"
  (lt? 0 0) => false
  (lt? 4 6) => true
  (lt? 8 3) => false
  (lt? 6 6) => false)

(facts "about eqn?"
  (eqn? 0 0) => true
  (eqn? 3 3) => true
  (eqn? 8 3) => false)

(facts "about pow"
  (pow 1 1) => 1
  (pow 2 3) => 8
  (pow 5 3) => 125)

(facts "about div"
  (div 4 15) => 0
  (div 15 4) => 3)

(facts "about length"
  (length '()) => 0
  (length '(6)) => 1
  (length '(hotdogs with mustard sauerkraut and pickles)) => 6)

(facts "about pick"
  (pick 1 '(x)) => 'x
  (pick 4 '(lasagna spaghetti ravioli macaroni meatball)) => 'macaroni)

(facts "about rempick"
  (rempick 1 '(6 5 4)) => '(5 4)
  (rempick 2 '(6 5 4)) => '(6 4)
  (rempick 3 '(6 5 4)) => '(6 5)
  (rempick 3 '(hotdogs with hot mustard)) => '(hotdogs with mustard))

(facts "about nonums"
  (nonums '()) => '()
  (nonums '(5)) => '()
  (nonums '(x)) => '(x)
  (nonums '(5 pears 6 prunes 9 dates)) => '(pears prunes dates))

(facts "about allnums"
  (allnums '()) => '()
  (allnums '(5)) => '(5)
  (allnums '(a)) => '()
  (allnums '(5 pears 6 prunes 9 dates)) => '(5 6 9))

(facts "about occur"
  (occur 'x '()) => 0
  (occur 'x '(x)) => 1
  (occur 'x '(x y x z)) => 2
  (occur 'x '(a b c)) => 0)

(facts "about one?"
  (one? 0) => false
  (one? 1) => true
  (one? 2) => false)

