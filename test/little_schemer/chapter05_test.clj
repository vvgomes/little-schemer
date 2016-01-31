(in-ns 'little-schemer.core-test)

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
  (member* '(n) '((n))) => true
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

