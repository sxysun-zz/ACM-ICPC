-- Intepretation in State Monad, Lambda Calculus
-- Monad of State Transformers
type Name = String

data Term =   Var Name
            | Con Int
            | Add Term Term
            | Lam Name Term
            | App Term Term
            | Count

data Value =  Wrong
            | Num Int
            | Fun (Value -> S Value)

type Environment = [(Name, Value)]

showint :: Int -> String
showint i = show i

showval :: Value -> String
showval Wrong = "<wrong>"
showval (Num i) = showint i
showval (Fun f) = "<function>"

interp :: Term -> Environment -> S Value
interp (Var x) e = Main.lookup x e
interp (Con i) e = unitS (Num i)
interp (Add u v) e = interp u e `bindS` (\a -> interp v e `bindS` (\b -> add a b))
interp (Lam x v) e = unitS(Fun (\a -> interp v ((x,a):e)))
interp (App t u) e = interp t e `bindS` (\f -> interp u e `bindS` (\a -> apply f a))
interp Count e = fetchS `bindS` (\i -> Num i)

lookup :: Name -> Environment -> S Value
lookup x [] = unitS Wrong
lookup x ((y,b):e) = if x == y then unitS b else Main.lookup x e

add :: Value -> Value -> S Value
add (Num i) (Num j) =  tickS `bindS` (\() -> unitS (Num (i+j)))
add a b = unitS Wrong

apply :: Value -> Value -> S Value
apply (Fun k) a = tickS `bindS` (\() -> k a)
apply f a = unitS Wrong

test :: Term -> String
test t = showS (interp t [])

-- Term for test, should evaluate to 42
term0 = (App (Lam "x" (Add (Var "x") (Var "x"))) (Add (Con 10) (Con 11)))

-- Monad Definition
type State = Int

type S a = State -> (a, State)

unitS :: a -> S a
unitS a = \s0 -> (a, s0)

bindS :: S a -> (a -> S b) -> S b
m `bindS` k = \s0 -> let (a, s1) = m s0 in k a s1

showS :: S Value -> String
showS m = let (a, s1) = m 0
          in "Value: " ++ showval a ++ "; Count: " ++ showint s1

tickS :: S ()
tickS = \s -> ((), s+1)

fetchS :: S State
fetchS = \s -> (s, s)