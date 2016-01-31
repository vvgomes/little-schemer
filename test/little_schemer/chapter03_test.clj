(in-ns 'little-schemer.core-test)

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

(facts "about multirember"
  (multirember 'x '()) => '()
  (multirember 'x '(x)) => '()
  (multirember 'x '(x x)) => '()
  (multirember 'x '(x y x)) => '(y)
  (multirember 'x '(y z)) => '(y z))

