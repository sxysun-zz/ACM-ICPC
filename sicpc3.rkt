#lang racket

;;;procedure is data!!!

;;;abstract data structure

;(define (cons x y)
;  (define (d m)
;    (cond ((= m 0) x)
;          ((= m 1) y)
;          (else (error "wrong argument"))))
;    d)
;(define (car z) (z 0))
;(define (cdr z) (z 1))

(define (consn x y)
  (lambda (m) (m x y)))
(define (carn x)
  (x (lambda (a b) a)))
(define (cdrn x)
  (x (lambda (a b) b)))

;;;church numerals
(define zero (lambda (f) (lambda (x) x)))
(define (add-one n)
  (lambda (f) (lambda (x) (f ((n f) x)))))
(define one
  (lambda (f) (lambda (x) (f x))))
(define two
  (lambda (f) (lambda (x) (f (f x)))))
(define three
  (lambda (f) (lambda (x) (f (f (f x))))))
(define four
  (add-one three))
(define (plus a b)
  (lambda (f) (lambda (x) ((a f) ((b f) x)))))
(define succ
  (lambda (x) (+ x 1)))
(define (real-value f)
  ((f succ) 0))
;(real-value zero)
;(real-value one)
;(real-value two)
;(real-value three)
;(real-value four)
;(real-value (plus three three))

;;;hierarchical data an closure
;list/ sequence
;(cons 1(cons 2(cons 3(cons 4 nil))))
(define str (list 1 2 3 4))
(cdr str)
