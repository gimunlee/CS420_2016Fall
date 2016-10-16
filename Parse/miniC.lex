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


