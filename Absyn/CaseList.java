package Absyn;
public class CaseList extends Absyn {
  // public List<Exp,StmtList,boolean> bodies;
  public StmtList defaultbody;
  public boolean br=false;

  public CaseList(int p, StmtList defb, CaseList t, boolean brr) {
    pos=p;
    // bodies=b;
    defaultbody=defb;
    br=brr;
  }
  public CaseList(int p) {
    pos=p;
    // bodies=b;
  }
}