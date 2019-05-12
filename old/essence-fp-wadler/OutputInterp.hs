-- Intepretation in Output Monad, Lambda Calculus
type Name = String

data Term =   Var Name
            | Con Int
            | Add Term Term
            | Lam Name Term
            | App Term Term
            | Out Term

data Value =  Wrong
            | Num Int
            | Fun (Value -> O Value)

type Environment = [(Name, Value)]

showint :: Int -> String
showint i = show i

showval :: Value -> String
showval Wrong = "<wrong>"
showval (Num i) = showint i
showval (Fun f) = "<function>"

interp :: Term -> Environment -> O Value
interp (Var x) e = Main.lookup x e
interp (Con i) e = unitO (Num i)
interp (Add u v) e = interp u e `bindO` (\a -> interp v e `bindO` (\b -> add a b))
interp (Lam x v) e = unitO(Fun (\a -> interp v ((x,a):e)))
interp (App t u) e = interp t e `bindO` (\f -> interp u e `bindO` (\a -> apply f a))
interp (Out u) e = interp u e `bindO` (\a -> outO a `bindO` (\() -> unitO a))

lookup :: Name -> Environment -> O Value
lookup x [] = unitO Wrong
lookup x ((y,b):e) = if x == y then unitO b else Main.lookup x e

add :: Value -> Value -> O Value
add (Num i) (Num j) =  unitO (Num (i + j))
add a b = unitO Wrong

apply :: Value -> Value -> O Value
apply (Fun k) a = k a
apply f a = unitO Wrong

test :: Term -> String
test t = showO (interp t [])

-- Term for test, should evaluate to 42
term0 = (App (Lam "x" (Add (Var "x") (Var "x"))) (Add (Con 10) (Con 11)))
term1 = (Add (Out (Con 41)) (Out (Con 1)))

-- Monad Definition
type O a = (String, a)

unitO :: a -> O a
unitO a = ("", a)

bindO :: O a -> (a -> O b) -> O b
m `bindO` k = let (r, a) = m
                  (s, b) = k a
              in (r ++ s, b)

showO :: O Value -> String
showO = "Output: " ++ s ++ " Value: " ++ showval a

-- outO :: Value -> Out ()
outO a = (showval a ++ "; ", a)