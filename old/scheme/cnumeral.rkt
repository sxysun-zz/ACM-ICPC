#lang racket
;;;church numerals
(define zero
  (lambda (f) (lambda (x) x)))
(define (inc n)
  (lambda (f) (lambda (x) (f ((n f) x)))))
(define one
  (inc zero))
(define (add n m)
  (lambda (f) (lambda (x) ((n f) ((m f) x)))))
(define (succ n)
  (n (lambda (x) (+ x 1))))
(define (rv n)
  ((succ n) 0))
;(rv zero)
;(rv one)
;(rv (add one one))

