/* Generated By:JJTree: Do not edit this line. ASTInt.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTInt extends SimpleNode {
  public ASTInt(int id) {
    super(id);
  }

  public ASTInt(CCAL p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(CCALVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=d28a7cdfc1577198f473ab687876a8eb (do not edit this line) */
