#lang racket

;;;procedure is data
;;;程序是写给人看的，附带能在机器上运行
;;;                       --- sicp

;;;abstract data structure

(define (consn x y)
  (lambda (m) (m x y)))
(define (carn x)
  (x (lambda (a b) a)))
(define (cdrn x)
  (x (lambda (a b) b)))

(define (conss x y)
  (define (d m)
    (cond ((= m 0) x)
          ((= m 1) y)
          (else (error "wrong argument"))))
    d)
(define (cars z) (z 0))
(define (cdrs z) (z 1))

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

(define exp (cons 1(cons 2(cons 3(cons 4 5)))))
;(cdr exp)
(define str (list 1 2 3 4))
;(car (cdr str))
;(cons 10 str)
(define (list-get l n)
  (cond ((= n 0) (car l))
        (else (list-get (cdr l) (- n 1)))))
;(list-get str 2)
(define (length l)
  (if (null? l)
        0
        (+ 1 (length (cdr l)))))
;(length str)
(define (append l1 l2)
  (cond ((null? l1) l2)
        (else (append (cdr l1) (cons (car l1) l2)))))
;(append (list 9 10 `a) str)
;;;binary trees
(define bt (cons (list 1 2) (list 3 4)))
(define (count-leaves l)
  (cond ((null? l) 0)
        ((not (pair? l)) 1)
        (else (+ (count-leaves (car l))
                 (count-leaves (cdr l))))))

;;;symbolic differentiation
