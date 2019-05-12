# interpreter using the RPython interrpreter toolchain
# RPython is a subset of the Python langauge that is supported by
# existing transpilation libraries to turn to C code, and thus accelarating
# the interpretation speed, so we aim to write interpreters using this langauge and then
# transform it into C code, so we have a interpreter written not in Python, but in C
# How smart!

# 1. free parser to turn my language into AST
# 2. turn AST into byte code
# 3. interpretation of byte code:
# frame -> varaible map
# context -> globle vars, env vars, interpreter configuration, global function
# bytecode -> represented as objects and passed to interpret(), represents an class/ function
# object space -> normalize application-level objects to interpreter-level

# JIT Compiling
# with hints, RPython can produce machine code and execute during interpretation
# 