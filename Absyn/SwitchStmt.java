package Absyn;
import Symbol.Symbol;

// for (init; condition; next) statement;
public class SwitchStmt extends Stmt {
  public Var identi;
  public CaseList caselist;
	
  public SwitchStmt(int p,Var i, CaseList cases) {
    pos=p;
    identi=i;
    caselist=cases;
  }
}

