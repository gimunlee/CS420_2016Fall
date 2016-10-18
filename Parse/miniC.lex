package Parse;
import java_cup.runtime.*;

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
private Token tok(int kind) {
  return tok(kind, null);
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
/\*[^*]*\*/ {}
[ \r\t\n]+ {}
","	{return tok(sym.COMMA);}
":" {return tok(sym.COLON);}
; {return tok(sym.SEMICOLON);}

"(" {return tok(sym.LPAREN);}
")" {return tok(sym.RPAREN);}
"{" {return tok(sym.LBRACE);}
"}" {return tok(sym.RBRACE);}
"[" {return tok(sym.LBRACK);}
"]" {return tok(sym.RBRACK);}

">" { return tok(sym.GT);}
">=" { return tok(sym.GE);}
"<" { return tok(sym.LT);}
"<=" { return tok(sym.LE);}
"==" { return tok(sym.EQ); }
"!=" { return tok(sym.NEQ); }

"." {return tok(sym.DOT);}
"=" { return tok(sym.ASSIGN); }
"+" {return tok(sym.PLUS);}
"-" {return tok(sym.MINUS);}
/[~/] {return tok(sym.DIVIDE); }
"*" {return tok(sym.TIMES); }

"&&" { return tok(sym.AND); }
"||" { return tok(sym.OR); }

"int" {return tok(sym.INT);}
"float" {return tok(sym.FLOAT);}
"if" { return tok(sym.IF); }
"else" { return tok(sym.ELSE); }
"while" { return tok(sym.WHILE); }
"for" { return tok(sym.FOR); }
"do" { return tok(sym.DO); }
"break" { return tok(sym.BREAK); }
"return" { return tok(sym.RETURN); }
"switch" { return tok(sym.SWITCH); }
"default" { return tok(sym.DEFAULT); }
"case" { return tok(sym.CASE); }

[0-9]+ {return tok(sym.INTNUM, new Integer(yytext())); }
[0-9]+.[0-9]+ {return tok(sym.FLOATNUM, new Float(yytext()));}
[A-Za-z][A-Za-z0-9_]* {return tok(sym.IDENTIFIER);}