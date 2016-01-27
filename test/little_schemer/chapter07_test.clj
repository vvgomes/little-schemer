(ns little-schemer.chapter07-test
  (:use midje.sweet)
  (:use [little-schemer.chapter07]))

; 7. Friends and Relations

(facts "about zet?"
  (zet? '()) => true
  (zet? '(x)) => true
  (zet? '(x y)) => true
  (zet? '(x y x)) => false
  (zet? '(apple peaches apple plum)) => false
  (zet? '(apples peaches pears plums)) => true)

(facts "about makeset"
  (makeset '()) => '()
  (makeset '(x)) => '(x)
  (makeset '(x y)) => '(x y)
  (makeset '(x y x)) => '(x y)
  (makeset '(apple peach pear peach plum apple lemon peach)) =>
           '(apple peach pear plum lemon))

(facts "about subset?"
  (subset? '() '()) => true
  (subset? '(x) '(x)) => true
  (subset? '(x) '(x y)) => true
  (subset? '(x z) '(x y)) => false
  (subset?
    '(5 chicken wings)
    '(5 hamburgers 2 pieces fried chicken and light duckling wings))
    => true

  (subset?
    '(4 pounds of horseradish)
    '(four pounds chicken and 5 ounces horseradish))
    => false)

(facts "about eqset?"
  (eqset? '() '()) => true
  (eqset? '(x) '()) => false
  (eqset? '() '(x)) => false
  (eqset? '(x) '(x)) => true
  (eqset? '(x y) '(y x)) => true
  (eqset? '(x y) '(x z)) => false
  (eqset? '(six large chicken with wings)
          '(six chicken with large wings)) => true)

(facts "about intersect?"
  (intersect? '() '()) => false
  (intersect? '(x) '()) => false
  (intersect? '() '(x)) => false
  (intersect? '(x y) '(x z)) => true
  (intersect? '(stewed tomatos and macaroni)
              '(macaroni and cheese)) => true)

(facts "about intersect"
  (intersect '() '()) => '()
  (intersect '(x) '()) => '()
  (intersect '() '(x)) => '()
  (intersect '(x y) '(x z)) => '(x)
  (intersect '(stewed tomatos and macaroni)
              '(macaroni and cheese))
           => '(and macaroni))

(facts "about union"
  (union '() '()) => '()
  (union '(x) '()) => '(x)
  (union '() '(x)) => '(x)
  (union '(x y) '(x)) => '(y x)
  (union '(stewed tomatoes and macaroni casserole)
         '(macaroni and cheese))
      => '(stewed tomatoes casserole macaroni and cheese))

(facts "about intersectall"
  (intersectall '(())) => '()
  (intersectall '((x))) => '(x)
  (intersectall '(() ())) => '()
  (intersectall '(() (x))) => '()
  (intersectall '((x) ())) => '()
  (intersectall '((x) (y))) => '()
  (intersectall '((x) (x))) => '(x)
  (intersectall '((a b c) (c a d e) (e f g h a b))) => '(a)
  (intersectall '((6 pears and)
                  (3 peaches and 6 peppers)
                  (and 6 prunes with some apples)))
             => '(6 and))

(facts "about apair?"
  (apair? '()) => false
  (apair? '(x)) => false
  (apair? '(x x x)) => false
  (apair? '(pear pear)) => true
  (apair? '(3 7)) => true
  (apair? '((2) (pair))) => true
  (apair? '(full (house))) => true)

(facts "about build"
  (build 'x 'y) => '(x y)
  (build '(x) '(y)) => '((x) (y)))

(facts "about third"
  (third '(x y z)) => 'z
  (third '(x y z w)) => 'z)

(facts "about atompair?"
  (atompair? 'x) => false
  (atompair? '()) => false
  (atompair? '(x)) => false
  (atompair? '((x))) => false
  (atompair? '(x y)) => true
  (atompair? '(x y z)) => false
  (atompair? '((x) y)) => false
  (atompair? '(x (y))) => false
  (atompair? '((x) (y))) => false)

(facts "about rel?"
  (rel? '()) => false
  (rel? '(x)) => false
  (rel? '((x))) => false
  (rel? '((x y))) => true
  (rel? '((x y z))) => false
  (rel? '((x y) z)) => false
  (rel? '(apple peaches pumpkin pie)) => false
  (rel? '((apple peaches) (pumpkin pie))) => true
  (rel? '((apple peaches) (pumpkin pie) (apple peaches))) => false
  (rel? '((4 3) (4 2) (7 6) (6 2) (3 4))) => true)

(facts "about fun?"
  (fun? '((4 3) (4 2) (7 6) (6 2) (3 4))) => false
  (fun? '((8 3) (4 2) (7 6) (6 2) (3 4))) => true
  (fun? '((d 4) (b 0) (b 9) (e 5) (g 4))) => false)

(facts "about revpair"
  (revpair '(x y)) => '(y x))

(facts "about revrel"
  (revrel '()) => '()
  (revrel '((x y))) => '((y x))
  (revrel '((8 a) (pumpkin pie) (got sick)))
       => '((a 8) (pie pumpkin) (sick got)))

(facts "about fullfun?"
  (fullfun? '((8 3) (4 2) (7 6) (6 2) (3 4))) => false
  (fullfun? '((8 3) (4 8) (7 6) (6 2) (3 4))) => true
  (fullfun? '((grape raisin) (plum prune) (stewed prune))) => false
  (fullfun? '((grape raisin) (plum prune) (stewed grape))) => true)

