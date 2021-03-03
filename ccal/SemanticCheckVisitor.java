import java.util.*;

public class SemanticCheckVisitor implements CCALVisitor
{
  //set staring scope to program. can be program/main/function
  String scope = "Program";
  String prevScope;
  HashMap<String, HashMap<String,STC>> symbolTable = new HashMap<>();
  HashMap<String, String> duplicateCheck = new HashMap<String, String>();
  STC symbEntry;
  int childrenNodes;

  //checks previously defined identifiers
  public Boolean existsInScope(String name, String inscope)
  {
      if(duplicateCheck.containsKey(name))
      {
        String key = duplicateCheck.get(name);
        // System.out.println("check " + name + " " + inscope + " " + key);
        if(key.equals(inscope))
        {
            return true;
        }
      }
      return false;
  }

  public Object visit(SimpleNode node, Object data)
  {
    node.childrenAccept(this, data);
    return null;
  }

  public Object visit(ASTProgram node, Object data)
  {
    //add node
    symbolTable.put(scope,new HashMap<String,STC>());
    //visit all nodes in program
    node.childrenAccept(this,data);

    System.out.println("********Symbol Table********\n");
    Set keys = symbolTable.keySet();
    Iterator it1 = keys.iterator();
    while(it1.hasNext())
    {
      String next = it1.next().toString();
      Set keys2 = symbolTable.get(next).keySet();
      System.out.println("Scope: " + next);
      System.out.println("Entries: " + keys2);
      Iterator it2 = keys2.iterator();
      while(it2.hasNext())
      {
        String name2 = it2.next().toString();
        symbEntry = symbolTable.get(next).get(name2);
        System.out.print("[" + "Name: "+ name2 + ", Type: "+ symbEntry.type +", DataType: "+ symbEntry.dataType);
        System.out.println(", Values: "+ symbEntry.values + "]");
      }
    }
    return null;
  }
  public Object visit(ASTDecl node, Object data)
  {
    String id = node.jjtGetChild(0).jjtAccept(this, data).toString();
    childrenNodes = node.jjtGetNumChildren();
    HashMap<String,STC> entry = symbolTable.get(scope);
    if(entry == null)
    {
      entry = new HashMap<String, STC>();
    }
    //read in next two nodes
    String name = node.jjtGetChild(0).jjtAccept(this,null).toString();
    String type = node.jjtGetChild(1).jjtAccept(this,null).toString();
    STC temp = new STC(name, type, scope, DataType.Var);
    entry.put(name, temp);
    //only write to symbolTable if valid
    if(!existsInScope(name, scope))
    {
      symbolTable.put(scope, entry);
      duplicateCheck.put(name, scope);
    }
    else
    {
      System.out.println("Error: identifier " + name + " already found in scope");
    }
    return node.value;
  }
  public Object visit(ASTVar node, Object data)
  {
    node.childrenAccept(this,data);
    return node.value;
  }
  public Object visit(ASTConst node, Object data)
  {
    childrenNodes = node.jjtGetNumChildren();
    HashMap<String,STC> entry = symbolTable.get(scope);
    if(entry == null)
    {
      entry = new HashMap<String,STC>();
    }

    String name = node.jjtGetChild(0).jjtAccept(this,null).toString();
    String type = node.jjtGetChild(1).jjtAccept(this,null).toString();
    STC temp = new STC(name, type, scope, DataType.Const);
    entry.put(name, temp);

    if(!existsInScope(name, scope))
    {
      symbolTable.put(scope, entry);
      duplicateCheck.put(name, scope);
    }
    else
    {
      System.out.println("Error: identifier " + name + " already found in scope");
    }

    return null;

  }
  public Object visit(ASTFuncList node, Object data)
  {
    node.childrenAccept(this,data);
    return node.value;
  }
  public Object visit(ASTFunc node, Object data)
  {
      HashMap<String,STC> entry = symbolTable.get(scope);
      if(entry == null)
      {
        entry = new HashMap<String,STC>();
      }

      String type = node.jjtGetChild(0).jjtAccept(this,null).toString();
      String name = node.jjtGetChild(1).jjtAccept(this,null).toString();
      STC temp = new STC(name, type, scope, DataType.Func);
      entry.put(name,temp);

      if(!existsInScope(name, scope))
      {
        symbolTable.put(scope, entry);
        duplicateCheck.put(name, scope);
      }
      else
      {
        System.out.println("Error: identifier " + name + " already found in scope");
      }
      //change scope for function
      prevScope = scope;
      scope = "Function: " + name;
      node.jjtGetChild(2).jjtAccept(this,null);
      node.jjtGetChild(3).jjtAccept(this,null);;
      scope = prevScope;

      return null;

  }
  public Object visit(ASTParams node, Object data)
  {
    HashMap<String,STC> entry = symbolTable.get(scope);
    if(entry == null)
    {
      entry = new HashMap<String,STC>();
    }

    childrenNodes = node.jjtGetNumChildren();
    String name = node.jjtGetChild(0).jjtAccept(this,null).toString();
    String type = node.jjtGetChild(1).jjtAccept(this,null).toString();
    STC temp = new STC(name,type,scope,DataType.ParamVar);

    entry.put(name,temp);
    if(!existsInScope(name, scope))
    {
      symbolTable.put(scope,entry);
      duplicateCheck.put(name, scope);
    }
    else
    {
      System.out.println("Error: identifier " + name + " already found in scope");
    }
    return null;
  }
  public Object visit(ASTMain node, Object data)
  {
    //once in main, change scope
    prevScope = scope;
    scope = "Main";
    node.childrenAccept(this, data);
    scope = prevScope;
    prevScope = "";
    return null;
  }
  public Object visit(ASTType node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTStateBlock node, Object data)
  {
    if(node.jjtGetNumChildren() > 0)
    {
      String name = node.jjtGetChild(0).jjtAccept(this, data).toString();
      if(!existsInScope(name, scope))
      {
        System.out.println("Error: " + name + " does not exist in scope");
      }
    }
    return node.jjtGetValue();
  }
  public Object visit(ASTBinOp node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTAdd node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTSub node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTOR node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTAND node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTCompOp node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTEqual node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTNotEqual node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTLessThan node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTLessOrEqual node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTGreaterThan node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTGreaterOrEqual node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTArgList node, Object data)
  {
    node.childrenAccept(this, data);
    return null;
  }
  public Object visit(ASTId node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTNum node, Object data)
  {
    return node.jjtGetValue();
  }
  public Object visit(ASTBool node, Object data)
  {
    return node.jjtGetValue();
  }
}
