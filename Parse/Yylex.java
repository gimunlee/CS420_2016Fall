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
		/* 30 */ YY_END,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NOT_ACCEPT,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_END,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NOT_ACCEPT,
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
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"2:9,6,3,2:2,4,2:18,6,19,2:4,24,2,10,11,23,21,7,22,20,1,43:10,8,9,18,17,16,2" +
":2,44:26,14,2,15,2,45,2,32,39,42,38,33,29,44,36,26,44,40,30,44,27,31,44:2,3" +
"7,34,28,41,44,35,44:3,12,25,13,2:2,0,5")[0];

	private int yy_rmap[] = unpackFromString(1,85,
"0,1,2,3,1:9,4,5,6,1:4,7,8,1:6,9:2,1,9:2,10,9:8,11,9,12,13,14,15,16,17,18,10" +
",19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43" +
",44,45,46,47,48,49,50,51")[0];

	private int yy_nxt[][] = unpackFromString(52,46,
"1,2,-1,3:2,1,3,4,5,6,7,8,9,10,11,12,13,14,15,42,16,17,18,19,45,47,20,43:2,6" +
"0,43:3,69,82,77,43,83,46,78,43:2,70,21,43,-1:48,49,-1:47,3:2,-1,3,-1:56,22," +
"-1:45,23,-1:45,24,-1:54,43,48,43,28,43:16,-1:20,51,-1:22,21,-1:28,43:20,-1:" +
"43,33,-1:19,25,-1:31,30,-1:66,26,-1:47,43:5,29,43,84,43:12,-1:25,27,-1:46,4" +
"3:2,31,43:17,-1,49:2,30,44,30,49:40,-1:26,43:11,32,43:8,-1:26,43:7,34,43:12" +
",-1:26,43:7,35,43:12,-1:26,43:2,36,43:17,-1:26,43:7,37,43:12,-1:26,43:14,38" +
",43:5,-1:26,43:10,39,43:9,-1:26,43,40,43:18,-1:26,43:2,41,43:17,-1:26,43:4," +
"71,50,43:14,-1:26,43:8,52,43:11,-1:26,43:8,53,43:11,-1:26,43:6,54,43:13,-1:" +
"26,43:4,55,43:15,-1:26,43:6,56,43:13,-1:26,43:16,57,43:3,-1:26,43:11,58,43:" +
"8,-1:26,43:4,59,43:15,-1:26,43:4,61,43:15,-1:26,43:6,62,43:13,-1:26,43:5,63" +
",43:14,-1:26,64,43:19,-1:26,43:7,65,43:12,-1:26,43:2,66,43:17,-1:26,43:15,6" +
"7,43:4,-1:26,43:15,68,43:4,-1:26,43:10,72,43:9,-1:26,43:11,73,43:8,-1:26,74" +
",43:19,-1:26,43:2,75,43:17,-1:26,43:6,76,43:13,-1:26,43:9,79,43:10,-1:26,43" +
":7,80,43:12,-1:26,43:3,81,43:16");

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
						{return tok(sym.DIVIDE); }
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return tok(sym.COMMA);}
					case -5:
						break;
					case 5:
						{return tok(sym.COLON);}
					case -6:
						break;
					case 6:
						{return tok(sym.SEMICOLON);}
					case -7:
						break;
					case 7:
						{return tok(sym.LPAREN);}
					case -8:
						break;
					case 8:
						{return tok(sym.RPAREN);}
					case -9:
						break;
					case 9:
						{return tok(sym.LBRACE);}
					case -10:
						break;
					case 10:
						{return tok(sym.RBRACE);}
					case -11:
						break;
					case 11:
						{return tok(sym.LBRACK);}
					case -12:
						break;
					case 12:
						{return tok(sym.RBRACK);}
					case -13:
						break;
					case 13:
						{ return tok(sym.GT);}
					case -14:
						break;
					case 14:
						{ return tok(sym.ASSIGN); }
					case -15:
						break;
					case 15:
						{ return tok(sym.LT);}
					case -16:
						break;
					case 16:
						{return tok(sym.DOT);}
					case -17:
						break;
					case 17:
						{return tok(sym.PLUS);}
					case -18:
						break;
					case 18:
						{return tok(sym.MINUS);}
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
						{}
					case -31:
						break;
					case 31:
						{return tok(sym.INT);}
					case -32:
						break;
					case 32:
						{ return tok(sym.FOR); }
					case -33:
						break;
					case 33:
						{return tok(sym.FLOATNUM, new Float(yytext()));}
					case -34:
						break;
					case 34:
						{ return tok(sym.ELSE); }
					case -35:
						break;
					case 35:
						{ return tok(sym.CASE); }
					case -36:
						break;
					case 36:
						{return tok(sym.FLOAT);}
					case -37:
						break;
					case 37:
						{ return tok(sym.WHILE); }
					case -38:
						break;
					case 38:
						{ return tok(sym.BREAK); }
					case -39:
						break;
					case 39:
						{ return tok(sym.SWITCH); }
					case -40:
						break;
					case 40:
						{ return tok(sym.RETURN); }
					case -41:
						break;
					case 41:
						{ return tok(sym.DEFAULT); }
					case -42:
						break;
					case 43:
						{return tok(sym.IDENTIFIER);}
					case -43:
						break;
					case 44:
						{}
					case -44:
						break;
					case 46:
						{return tok(sym.IDENTIFIER);}
					case -45:
						break;
					case 48:
						{return tok(sym.IDENTIFIER);}
					case -46:
						break;
					case 50:
						{return tok(sym.IDENTIFIER);}
					case -47:
						break;
					case 52:
						{return tok(sym.IDENTIFIER);}
					case -48:
						break;
					case 53:
						{return tok(sym.IDENTIFIER);}
					case -49:
						break;
					case 54:
						{return tok(sym.IDENTIFIER);}
					case -50:
						break;
					case 55:
						{return tok(sym.IDENTIFIER);}
					case -51:
						break;
					case 56:
						{return tok(sym.IDENTIFIER);}
					case -52:
						break;
					case 57:
						{return tok(sym.IDENTIFIER);}
					case -53:
						break;
					case 58:
						{return tok(sym.IDENTIFIER);}
					case -54:
						break;
					case 59:
						{return tok(sym.IDENTIFIER);}
					case -55:
						break;
					case 60:
						{return tok(sym.IDENTIFIER);}
					case -56:
						break;
					case 61:
						{return tok(sym.IDENTIFIER);}
					case -57:
						break;
					case 62:
						{return tok(sym.IDENTIFIER);}
					case -58:
						break;
					case 63:
						{return tok(sym.IDENTIFIER);}
					case -59:
						break;
					case 64:
						{return tok(sym.IDENTIFIER);}
					case -60:
						break;
					case 65:
						{return tok(sym.IDENTIFIER);}
					case -61:
						break;
					case 66:
						{return tok(sym.IDENTIFIER);}
					case -62:
						break;
					case 67:
						{return tok(sym.IDENTIFIER);}
					case -63:
						break;
					case 68:
						{return tok(sym.IDENTIFIER);}
					case -64:
						break;
					case 69:
						{return tok(sym.IDENTIFIER);}
					case -65:
						break;
					case 70:
						{return tok(sym.IDENTIFIER);}
					case -66:
						break;
					case 71:
						{return tok(sym.IDENTIFIER);}
					case -67:
						break;
					case 72:
						{return tok(sym.IDENTIFIER);}
					case -68:
						break;
					case 73:
						{return tok(sym.IDENTIFIER);}
					case -69:
						break;
					case 74:
						{return tok(sym.IDENTIFIER);}
					case -70:
						break;
					case 75:
						{return tok(sym.IDENTIFIER);}
					case -71:
						break;
					case 76:
						{return tok(sym.IDENTIFIER);}
					case -72:
						break;
					case 77:
						{return tok(sym.IDENTIFIER);}
					case -73:
						break;
					case 78:
						{return tok(sym.IDENTIFIER);}
					case -74:
						break;
					case 79:
						{return tok(sym.IDENTIFIER);}
					case -75:
						break;
					case 80:
						{return tok(sym.IDENTIFIER);}
					case -76:
						break;
					case 81:
						{return tok(sym.IDENTIFIER);}
					case -77:
						break;
					case 82:
						{return tok(sym.IDENTIFIER);}
					case -78:
						break;
					case 83:
						{return tok(sym.IDENTIFIER);}
					case -79:
						break;
					case 84:
						{return tok(sym.IDENTIFIER);}
					case -80:
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
