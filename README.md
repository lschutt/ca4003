# Assignment 1: A Lexical and Syntax Analyser

Implements a lexical and syntax analyser using JavaCC for a language called CCAL.

CCAL is case sensitive, implements comments with // on a single line and /* */ on a block.

The following keywords are implemented for the CCAL language: VAR, CONST, RETURN, INTEGER, BOOLEAN, VOID, MAIN, IF, ELSE, TRUE, FALSE, WHILE, BEGIN, END, IS, SKIP
The following punctuation is implemented for the CCAL language: COMMA, SEMI_COLON, COLON, EQUALS, LBR, RBR, PLUS_SIGN, MINUS_SIGN, TILDA, OR_SIGN, AND_SIGN, EQUAL_TO, NOT_EQUAL_TO, LESS_THAN, LESS_OR_EQUAL, GREATER_THAN, GREATER_OR_EQUAL.
Numbers are headed as DIGIT and include numeric characters between and including 0 - 9.
Integers are defined as INT, and are positive or negative DIGITs.Identifiers are defined as ID and include letters, underscores and integers.

# Assignment 2: Semantic Analysis and Intermediate Representation

Adds semantic checks and intermediate representation generation to the lexical and syntax analyser. Extends submission to generate an AST, symbol table to handle scope, performs a set of semantic checks and generates and Intermediate Representation with 3 address code.
