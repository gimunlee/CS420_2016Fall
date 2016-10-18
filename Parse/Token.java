package Parse;

// class Token extends java_cup.runtime.token {
class Token extends java_cup.runtime.Symbol {
  int left,right;
  Token(int l, int r, int kind) {
   super(kind);
   left=l; right=r;
  }
  Token(int kind, int l, int r, Object o) {
    super(kind,l,r,o);
    left=l; right=r;
  }
  Token(int kind) {
    super(kind);
  }
}

class StrToken extends Token {
  String val;
  StrToken(int l, int r, int kind, String v) { 
    super(l,r,kind);
    val=v;
  }
}

class IntToken extends Token {
  int val;
  IntToken(int l, int r, int kind, int v) { 
    super(l,r,kind);
    val=v;
  }
}
