#lang racket

;define constants
[define additive-iden 0]
[define multiplicative-iden 1]

;higher order function
(define (procedure-on-interval function start end iden procedure)
  (cond ((> start end) iden)
        (else (procedure-on-interval function (+ start 1) end (procedure (function start) iden) procedure))))
(define (sigma function start end)
  (procedure-on-interval function start end additive-iden +))
(define (big-pi function start end)
  (procedure-on-interval function start end multiplicative-iden *))

;arbitary methamatics function
(define (fac x)
  (define (fac-tail x acc)
  (cond ((<= x 1) acc)
        (else (fac-tail (- x 1) (* x acc)))))
  (fac-tail x 1))
(define (fib x)
  (define (fib-tail x a acc)
  (cond ((<= x 1) a)
        (else (fib-tail (- x 1) acc (+ acc a)))))
  (fib-tail x 1 1))

;aribitary playground


;function call
(big-pi fib 1 10) ;multiples of fibonnaci
(sigma (lambda (x) (/ 1.0 x)) 1 1000) ;sum of harmonic series