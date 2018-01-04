#lang racket                                                  

;;; prototype interpreter for alayi-lang

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
; test
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

(infer `((lambda (x) (x * x)) (1 + (2 * 3))) env0)

(infer
'(let ([x 2])
   (let ([f (lambda (y) (* x y))])
(f 3))) env0)