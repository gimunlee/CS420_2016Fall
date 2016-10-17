package Parse;

%% 

%implements Lexer
%function nextToken
%type Token
%char

%{
private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private Token tok(int kind, Object value) {
    return new Token(kind, yychar, yychar+yylength(), value);
}

private ErrorMsg.ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg.ErrorMsg e) {
  this(s);
  errorMsg=e;
}

%}

%eofval{
	{
	 return tok(sym.EOF, null);
        }
%eofval}       


%%
" "	{}
\n	{newline();}
","	{return tok(sym.COMMA, null);}
":" {return new Symbol(sym.COLON);}
";" {return new Symbol(sym.SEMICOLON);}

"(" {return new Symbol(sym.LPAREN);}
")" {return new Symbol(sym.RPAREN);}
"{" {return new Symbol(sym.LBRACE);}
"}" {return new Symbol(sym.RBRACE);}
"[" {return new Symbol(sym.LBRACK);}
"]" {return new Symbol(sym.RBRACK);}

">" { return new Symbol(sym.GT);}
">=" { return new Symbol(sym.GE);}
"<" { return new Symbol(sym.LT);}
"<=" { return new Symbol(sym.LE);}
"==" { return new Symbol(sym.EQ); }
"!=" { return new Symbol(sym.NEQ); }

"." {return new Symbol(sym.DOT);}
"=" { return new Symbol(sym.ASSIGN); }
"+" {return new Symbol(sym.PLUS);}
"-" {return new Symbol(sym.MINUS);}
"/" {return new Symbol(sym.DIVIDE); }
"*" {return new Symbol(sym.TIMES); }

"&&" { return new Symbol(sym.AND); }
"||" { return new SYmbol(sym.OR); }

"int" {return new Symbol(sym.INT);}
"float" {return new Symbol(sym.FLOAT);}
"if" { return new Symbol(sym.IF); }
"else" { return new Symbol(sym.ELSE); }
"else if" { return new Symbol(sym.ELSEIF); }
"while" { return new Symbol(sym.WHILE); }
"for" { return new Symbol(sym.FOR); }
"do" { return new Symbol(sym.DO); }
"break" { return new Symbol(sym.BREAK); }

[0-9]+ {return new Symbol(sym.INTNUM, new Integer(yytext())); }
[0-9]+.[0-9]+ {return new Symbol(sym.FLOATNUM, new Float(yytext()));}
[A-Za-z][A-Za-z0-9_]* {return new SYmbol(sym.IDENTIFIER);}
