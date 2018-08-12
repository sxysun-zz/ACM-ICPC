#lang racket                                                  

;;; lambda calculus interpreter

;; infix calculator
(define calc
  (lambda (exp)
    (match exp
      ((? number? x) x)
      (`(,x ,op ,y)
       (let ((px (calc x))
             (py (calc y)))
         (match op
           (`+ (+ px py))
           (`- (- px py))
           (`乘 (* px py))
           (`/ (/ px py))))))))

;(calc `(23 + (5 - (1 + (9 - (2 乘 2))))))

;; type-inference machine

; lexical scope
(struct closure (fun env))
; environment
(define env0 `())
(define ext-env
  (lambda (x v env)
    (cons (cons x v) env)))
(define search
  (lambda (x env)
    (cond
      ((eq? (car env) `()) #f)
      ((eq? (car (car env)) x) (cdr (car env)))
      (else (search x (cdr env))))))

(define infer
  (lambda (exp env)
    (match exp
      ((? symbol? x)
       (let ((sed (search x env)))
         (cond
           ((not sed)
            (display "undefined var"))
           (else sed))))
      ((? number? x) `int)
      ((? string? x) `string)
      ((? boolean? x) `boolean)
      (`(lambda (,x) ,e)
       (closure exp env))
      (`(`let ((,x ,e1)) ,e2)
       (let ((t (infer e1 env)))
         (infer e2 (ext-env x t env))))
      (`(if ,t ,e1 ,e2)
       (let ((b (infer t env)))
         (cond
           ((eq? b `bool)
            (let ((t1 (infer e1 env))
                  (t2 (infer e2 env)))
              (cond
                ((eq? t1 t2) t1)
                (else
                 (display "branch type mismatch"))))))))
      (`(,e1 ,e2)
       (let ((t1 (infer e1 env))
             (t2 (infer e2 env)))
         (match t1
           ((closure `(lambda (,x) ,e) env-old)
            (infer e (ext-env x t2 env-old))))))
      (`(,e1 ,op ,e2)
       (let ((t1 (infer e1 env))
             (t2 (infer e2 env)))
         (cond
           ((not (eq? t1 `int)) (display "first operand not int"))
           ((not (eq? t2 `int)) (display "second operand not int"))
           (else `int)))))))

;(infer `((lambda (y) ((lambda (x) ((y * x) * x)) (1 + (2 * -3)))) 2) env0)

;; simple interpreter
(define interps
  (lambda (exp env)
    (match exp
      ((? symbol? x)
       (let ((sed (search x env)))
         (cond
           ((not sed)
            (display "undefined var"))
           (else sed))))
      ((? number? x) x)
      ((? string? x) x)
      ((? boolean? x) x)
      (`(λ ,x . ,e)
       (closure exp env))
      (`(`let ((,x ,e1)) ,e2)
       (let ((t (interps e1 env)))
         (interps e2 (ext-env x t env))))
      (`(if ,t ,e1 ,e2)
       (let ((b (interps t env)))
        (let ((t1 (interps e1 env))
              (t2 (interps e2 env)))
          (cond
            (b (t1))
            (else
             (t2))))))
      (`(,e1 ,e2)
       (let ((t1 (interps e1 env))
             (t2 (interps e2 env)))
         (match t1
           ((closure `(λ ,x . ,e) env-old)
            (interps e (ext-env x t2 env-old))))))
      (`(,x ,op ,y)
       (let ((px (interps x env))
             (py (interps y env)))
         (match op
           (`+ (+ px py))
           (`- (- px py))
           (`* (* px py))
           (`/ (/ px py))))))))

;(interps `((lambda (y) ((lambda (x) ((y * x) * x)) (1 + (2 * -3)))) 2) env0)

;; toy lambda calculus

(define interp
  (lambda (exp)
    (interps exp env0)))

; church numerals
(define λzero
  `(λ f . (λ x . x))
  )
(define λinc
  `(λ n . (λ f . (λ x . (f ((n f ) x)))))
  )
(define λadd
  `(λ n . (λ m . (λ f . (λ x . ((n f) ((m f) x))))))
  )
(define λsucc
  `(λ n . (λ x . (x + 1)))
  )
;(define (rv n)
;((succ n) 0))

; boolean
(define λtrue
  `(λ x . (λ y . x))
  )
(define λfalse
  `(λ x . (λ y . y))
  )
(define λand
  `(λ x . (λ y . (x y false)))
  )
(define λor
  `(λ x . (λ y . (x true y)))
  )
(define λnot
  `(λ x . (x false true))
  )

; recursion
(define λY
  `(λ y . ((λ x . (y (x x))) (λ x . (y (x x)))))
  )

(interp `(((λ f . (λ x . x)) (λ x . (x + 1))) 0))