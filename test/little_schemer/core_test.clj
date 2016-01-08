(ns little-schemer.core-test
  (:use midje.sweet)
  (:use [little-schemer.core]))

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

; 2. Do it, Do It Again, an Again, and Again...

(facts "about lat?"
  (lat? '()) => true
  (lat? '(x)) => true
  (lat? '((x))) => false
  (lat? '(jack sprat could eat not chicken fat)) => true
  (lat? '(jack (sprat could) eat no chicken fat)) => false)

(facts "about member?"
  (member? 'x '()) => false
  (member? 'x '(x)) => true
  (member? 'x '(y)) => false
  (member? 'tea '(coffee tea or milk)) => true
  (member? 'poached '(fried eggs and scrambled eggs)) => false)

; 3. Cons the Magnificent

(facts "about rember"
  (rember 'x '()) => '()
  (rember 'x '(x)) => '()
  (rember 'x '(y)) => '(y)
  (rember 'mint '(lamb chops and mint jelly))
             => '(lamb chops and jelly))

(facts "about firsts"
  (firsts '()) => '()
  (firsts '(x)) => '()
  (firsts '((x))) => '(x)
  (firsts '(((x)))) => '((x))

  (firsts '((apple peach pumpkin)
            (plum pear cherry)
            (grape raising pea)
            (bean carrot eggplant))) => '(apple plum grape bean)

  (firsts '(((five plums) four)
            (eleven green oranges)
            ((no) more))) => '((five plums) eleven (no)))

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

; 5. Oh My Gawd: It's Full of Stars

(facts "about rember*"
  (rember* 'x '(a x b)) => '(a b)
  (rember* 'x '(a (x) b)) => '(a () b)
  (rember* 'cup '((coffee) cup ((tea) cup) (and (hick)) cup))
    => '((coffee) ((tea)) (and (hick))))

(facts "about insertR*"
  (insertR* 'n 'o '(x o y)) => '(x o n y)
  (insertR* 'n 'o '(x (o) y)) => '(x (o n) y)
  (insertR* 'n 'o '(o x o)) => '(o n x o n)
  (insertR* 'roast 'chuck
    '((how much (wood)) could ((a (wood) chuck))
     (((chuck))) (if (a) ((wood chuck)))
        could chuck wood))
     =>
    '((how much (wood)) could ((a (wood) chuck roast))
     (((chuck roast))) (if (a) ((wood chuck roast)))
        could chuck roast wood))

(facts "about occur*"
  (occur* 'x '()) => 0
  (occur* 'x '(y)) => 0
  (occur* 'x '(x)) => 1
  (occur* 'x '((x))) => 1
  (occur* 'x '((x) x)) => 2
  (occur* 'x '(x (x))) => 2
  (occur* 'banana 
    '((banana) (split ((((banana ice))) (cream (banana)) sherbet))
      (banana) (bread) (banana brandy))) => 5)

(facts "about subst*"
  (subst* 'n 'o '()) => '()
  (subst* 'n 'o '(x y z)) => '(x y z)
  (subst* 'n 'o '(x o z)) => '(x n z)
  (subst* 'n 'o '(x (o) z)) => '(x (n) z)
  (subst* 'orange 'banana
    '((banana) (split ((((banana ice)))
      (cream (banana)) sherbet))
      (banana) (bread) (banana brandy)))
    =>
    '((orange) (split ((((orange ice)))
      (cream (orange)) sherbet))
      (orange) (bread) (orange brandy)))

(facts "about insertL*"
  (insertL* 'n 'o '()) => '()
  (insertL* 'n 'o '(x y z)) => '(x y z)
  (insertL* 'n 'o '(x o z)) => '(x n o z)
  (insertL* 'n 'o '(x (o) z)) => '(x (n o) z)
  (insertL* 'pecker 'chuck
    '((how much (wood)) could ((a (wood) chuck))
     (((chuck))) (if (a) ((wood chuck))) could chuck wood))
    =>
    '((how much (wood)) could ((a (wood) pecker chuck))
     (((pecker chuck))) (if (a) ((wood pecker chuck)))
        could pecker chuck wood))

(facts "about member*"
  (member* 'n '()) => false
  (member* 'n '(x)) => false
  (member* 'n '(n)) => true
  (member* 'n '((n))) => true
  (member* 'chips '((potato) (chips ((with) fish) (chips)))) => true)

(facts "about leftmost"
  (leftmost '(x)) => 'x
  (leftmost '((x))) => 'x
  (leftmost '(((x)))) => 'x
  (leftmost '((potato) (chips ((with) fish) (chips)))) => 'potato
  (leftmost '(((hot) (tuna (and))) cheese)) => 'hot)

(facts "about eqlist?"
  (eqlist? '() '()) => true
  (eqlist? '() '(x)) => false
  (eqlist? '(x) '()) => false
  (eqlist? '(x) '(x)) => true
  (equal? '((x)) '((x))) => true

  (eqlist? '(strawberry ice cream)
           '(strawberry ice cream)) => true
 
  (eqlist? '(strawberry ice cream)
           '(strawberry cream ice)) => false
 
  (eqlist? '(banana ((split)))
           '((banana) (split))) => false
 
  (eqlist? '(beef ((sausage)) (and (soda)))
           '(beef ((salami)) (and (soda)))) => false
 
  (eqlist? '(beef ((sausage)) (and (soda)))
           '(beef ((sausage)) (and (soda)))) => true)

(facts "about equal?"
  (equal? 'x 'x) => true
  (equal? 'x 'y) => false
  (equal? 'x '(x)) => false
  (equal? '(x) 'x) => false
  (equal? '() '()) => true
  (equal? '() '(x)) => false
  (equal? '(x) '()) => false
  (equal? '(x) '(x)) => true
  (equal? '((x)) '(x)) => false
  (equal? '((x)) '((x))) => true)

(facts "about rembersexp"
  (rembersexp 'x '()) => '()
  (rembersexp 'x '(x)) => '()
  (rembersexp 'x '(y)) => '(y)
  (rembersexp '(x) '((x) y)) => '(y))

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

