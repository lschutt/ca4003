/* Generated By:JJTree: Do not edit this line. ASTCompOp.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTCompOp extends SimpleNode {
  public ASTCompOp(int id) {
    super(id);
  }

  public ASTCompOp(CCAL p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(CCALVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=eadb6ccb4b11e2809c859ef2809a9b19 (do not edit this line) */