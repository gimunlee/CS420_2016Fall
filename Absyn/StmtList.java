package Absyn;
import Symbol.Symbol;

public class StmtList {
	public Stmt head;
	public StmtList tail;
	public StmtList(Stmt h, StmtList t) {head=h; tail=t;}
}
