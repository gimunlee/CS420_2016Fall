package Parse;
import java_cup.runtime.*;


class Yylex implements Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

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
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NOT_ACCEPT,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NOT_ACCEPT,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NOT_ACCEPT,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"40:9,41,1,40:2,1,40:18,41,14,40:4,20,40,5,6,19,16,2,17,15,18,39:10,3,4,13,1" +
"2,11,40:2,42:26,9,40,10,40,43,40,28,35,38,34,29,25,42,32,22,42,36,26,42,23," +
"27,42:2,33,30,24,37,42,31,42:3,7,21,8,40:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,76,
"0,1,2,1:9,3,4,5,1:5,6,7,1:6,8:4,9,8:7,10,8,11,12,13,14,9,15,16,17,18,19,20," +
"21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43")[0];

	private int yy_nxt[][] = unpackFromString(44,44,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,40,15,16,17,18,19,42,44,20,41:2,55,41:3,63" +
",74,70,41,75,43,71,41:2,64,21,-1,2,41,-1:46,2,-1:39,2,-1:14,22,-1:43,23,-1:" +
"43,24,-1:53,41,45,41,28,41:14,-1:2,41:2,-1:2,46:37,21,46:4,-1:22,41:18,-1:2" +
",41:2,-1:39,32,-1:16,25,-1:51,26,-1:45,41:5,29,41:12,-1:2,41:2,-1:21,27,-1:" +
"44,41:2,30,41:15,-1:2,41:2,-1:22,41:11,31,41:6,-1:2,41:2,-1:22,41:7,33,41:1" +
"0,-1:2,41:2,-1:22,41:7,34,41:10,-1:2,41:2,-1:22,41:2,35,41:15,-1:2,41:2,-1:" +
"22,41:7,36,41:10,-1:2,41:2,-1:22,41:14,37,41:3,-1:2,41:2,-1:22,41:10,38,41:" +
"7,-1:2,41:2,-1:22,41,39,41:16,-1:2,41:2,-1:22,41:4,65,47,41:12,-1:2,41:2,-1" +
":22,41:8,48,41:9,-1:2,41:2,-1:22,41:8,49,41:9,-1:2,41:2,-1:22,41:6,50,41:11" +
",-1:2,41:2,-1:22,41:4,51,41:13,-1:2,41:2,-1:22,41:6,52,41:11,-1:2,41:2,-1:2" +
"2,41:16,53,41,-1:2,41:2,-1:22,41:11,54,41:6,-1:2,41:2,-1:22,41:4,56,41:13,-" +
"1:2,41:2,-1:22,41:6,57,41:11,-1:2,41:2,-1:22,41:5,58,41:12,-1:2,41:2,-1:22," +
"59,41:17,-1:2,41:2,-1:22,41:7,60,41:10,-1:2,41:2,-1:22,41:2,61,41:15,-1:2,4" +
"1:2,-1:22,41:15,62,41:2,-1:2,41:2,-1:22,41:10,66,41:7,-1:2,41:2,-1:22,41:11" +
",67,41:6,-1:2,41:2,-1:22,68,41:17,-1:2,41:2,-1:22,41:2,69,41:15,-1:2,41:2,-" +
"1:22,41:9,72,41:8,-1:2,41:2,-1:22,41:7,73,41:10,-1:2,41:2");

	public Token nextToken ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	{
	 return tok(sym.EOF, null);
        }
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{}
					case -3:
						break;
					case 3:
						{return tok(sym.COMMA);}
					case -4:
						break;
					case 4:
						{return tok(sym.COLON);}
					case -5:
						break;
					case 5:
						{return tok(sym.SEMICOLON);}
					case -6:
						break;
					case 6:
						{return tok(sym.LPAREN);}
					case -7:
						break;
					case 7:
						{return tok(sym.RPAREN);}
					case -8:
						break;
					case 8:
						{return tok(sym.LBRACE);}
					case -9:
						break;
					case 9:
						{return tok(sym.RBRACE);}
					case -10:
						break;
					case 10:
						{return tok(sym.LBRACK);}
					case -11:
						break;
					case 11:
						{return tok(sym.RBRACK);}
					case -12:
						break;
					case 12:
						{ return tok(sym.GT);}
					case -13:
						break;
					case 13:
						{ return tok(sym.ASSIGN); }
					case -14:
						break;
					case 14:
						{ return tok(sym.LT);}
					case -15:
						break;
					case 15:
						{return tok(sym.DOT);}
					case -16:
						break;
					case 16:
						{return tok(sym.PLUS);}
					case -17:
						break;
					case 17:
						{return tok(sym.MINUS);}
					case -18:
						break;
					case 18:
						{return tok(sym.DIVIDE); }
					case -19:
						break;
					case 19:
						{return tok(sym.TIMES); }
					case -20:
						break;
					case 20:
						{return tok(sym.IDENTIFIER);}
					case -21:
						break;
					case 21:
						{return tok(sym.INTNUM, new Integer(yytext())); }
					case -22:
						break;
					case 22:
						{ return tok(sym.GE);}
					case -23:
						break;
					case 23:
						{ return tok(sym.EQ); }
					case -24:
						break;
					case 24:
						{ return tok(sym.LE);}
					case -25:
						break;
					case 25:
						{ return tok(sym.NEQ); }
					case -26:
						break;
					case 26:
						{ return tok(sym.AND); }
					case -27:
						break;
					case 27:
						{ return tok(sym.OR); }
					case -28:
						break;
					case 28:
						{ return tok(sym.IF); }
					case -29:
						break;
					case 29:
						{ return tok(sym.DO); }
					case -30:
						break;
					case 30:
						{return tok(sym.INT);}
					case -31:
						break;
					case 31:
						{ return tok(sym.FOR); }
					case -32:
						break;
					case 32:
						{return tok(sym.FLOATNUM, new Float(yytext()));}
					case -33:
						break;
					case 33:
						{ return tok(sym.ELSE); }
					case -34:
						break;
					case 34:
						{ return tok(sym.CASE); }
					case -35:
						break;
					case 35:
						{return tok(sym.FLOAT);}
					case -36:
						break;
					case 36:
						{ return tok(sym.WHILE); }
					case -37:
						break;
					case 37:
						{ return tok(sym.BREAK); }
					case -38:
						break;
					case 38:
						{ return tok(sym.SWITCH); }
					case -39:
						break;
					case 39:
						{ return tok(sym.RETURN); }
					case -40:
						break;
					case 41:
						{return tok(sym.IDENTIFIER);}
					case -41:
						break;
					case 43:
						{return tok(sym.IDENTIFIER);}
					case -42:
						break;
					case 45:
						{return tok(sym.IDENTIFIER);}
					case -43:
						break;
					case 47:
						{return tok(sym.IDENTIFIER);}
					case -44:
						break;
					case 48:
						{return tok(sym.IDENTIFIER);}
					case -45:
						break;
					case 49:
						{return tok(sym.IDENTIFIER);}
					case -46:
						break;
					case 50:
						{return tok(sym.IDENTIFIER);}
					case -47:
						break;
					case 51:
						{return tok(sym.IDENTIFIER);}
					case -48:
						break;
					case 52:
						{return tok(sym.IDENTIFIER);}
					case -49:
						break;
					case 53:
						{return tok(sym.IDENTIFIER);}
					case -50:
						break;
					case 54:
						{return tok(sym.IDENTIFIER);}
					case -51:
						break;
					case 55:
						{return tok(sym.IDENTIFIER);}
					case -52:
						break;
					case 56:
						{return tok(sym.IDENTIFIER);}
					case -53:
						break;
					case 57:
						{return tok(sym.IDENTIFIER);}
					case -54:
						break;
					case 58:
						{return tok(sym.IDENTIFIER);}
					case -55:
						break;
					case 59:
						{return tok(sym.IDENTIFIER);}
					case -56:
						break;
					case 60:
						{return tok(sym.IDENTIFIER);}
					case -57:
						break;
					case 61:
						{return tok(sym.IDENTIFIER);}
					case -58:
						break;
					case 62:
						{return tok(sym.IDENTIFIER);}
					case -59:
						break;
					case 63:
						{return tok(sym.IDENTIFIER);}
					case -60:
						break;
					case 64:
						{return tok(sym.IDENTIFIER);}
					case -61:
						break;
					case 65:
						{return tok(sym.IDENTIFIER);}
					case -62:
						break;
					case 66:
						{return tok(sym.IDENTIFIER);}
					case -63:
						break;
					case 67:
						{return tok(sym.IDENTIFIER);}
					case -64:
						break;
					case 68:
						{return tok(sym.IDENTIFIER);}
					case -65:
						break;
					case 69:
						{return tok(sym.IDENTIFIER);}
					case -66:
						break;
					case 70:
						{return tok(sym.IDENTIFIER);}
					case -67:
						break;
					case 71:
						{return tok(sym.IDENTIFIER);}
					case -68:
						break;
					case 72:
						{return tok(sym.IDENTIFIER);}
					case -69:
						break;
					case 73:
						{return tok(sym.IDENTIFIER);}
					case -70:
						break;
					case 74:
						{return tok(sym.IDENTIFIER);}
					case -71:
						break;
					case 75:
						{return tok(sym.IDENTIFIER);}
					case -72:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
