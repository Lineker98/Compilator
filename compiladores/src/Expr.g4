grammar Expr;
prog:   mainClass (classDeclaration)* EOF ;

WHITESPACE : (' ' | '\t' | '\n' | '\r') -> skip ;
NUMBER     : [0-9]+ ('.' [0-9]+)? ;
LETTER : [a-z]+ | [A-Z]+;

classDeclaration : 'class' identifier ( 'extends' identifier )? '{' (varDeclaration)* (methodDeclaration)* '}';

varDeclaration : type identifier ';' ;

type : 'int' '[' ']'
    | 'boolean'
    | 'int'
    | identifier
    ;

methodDeclaration : 'public' type identifier '('
    (type identifier ( ',' type identifier )* )? ')'
    '{' ( varDeclaration )* ( statement )*
    'return' expression ';' '}'
;

mainClass: 'class' identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' identifier ')' '{' (statement)* '}' '}';

identifier: LETTER (LETTER | NUMBER)*;

statement: '{' (statement)* '}'
    | 'if'
    '(' expression ')' statement 'else' statement
    | 'while' '(' expression ')' statement
    | 'system.out.println' '(' expression ')' ';'
    | identifier '=' expression ';'
    | identifier '[' expression ']'
    '=' expression ';'
    ;

expression : expression ( '&&' | '<' | '+' | '-' | '*') expression
    | expression '[' expression ']'
    | expression '.' 'length'
    | expression '.' identifier '(' ( expression (',' expression )* )? ')'
    | NUMBER
    | 'true'
    | 'false'
    | identifier
    | 'this'
    | 'new' 'int' '[' expression ']'
    | 'new' identifier '(' ')'
    | '!' expression
    | '(' expression ')'
 ;

//mainClass:   mainClass ('*'|'/') mainClass
//    |   mainClass ('+'|'-') mainClass
//    |   INT
//    |   '(' mainClass ')'
//    ;


