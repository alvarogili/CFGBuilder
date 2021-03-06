
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Wed Jun 14 21:54:21 ART 2017
//----------------------------------------------------

package ar.edu.unrc.asp.cfgbuilder.parser;

import java_cup.runtime.*;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import ar.edu.unrc.asp.cfgbuilder.CFGBuilder;
import ar.edu.unrc.asp.model.Node;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Wed Jun 14 21:54:21 ART 2017
  */
public class Parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\024\000\002\002\004\000\002\002\004\000\002\002" +
    "\003\000\002\004\003\000\002\004\016\000\002\004\012" +
    "\000\002\004\012\000\002\005\005\000\002\003\003\000" +
    "\002\003\005\000\002\003\005\000\002\003\005\000\002" +
    "\003\005\000\002\003\005\000\002\003\005\000\002\003" +
    "\005\000\002\006\003\000\002\006\003\000\002\007\003" +
    "\000\002\010\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\061\000\010\020\007\021\010\025\004\001\002\000" +
    "\036\002\uffef\004\uffef\005\uffef\006\uffef\007\uffef\011\uffef" +
    "\013\uffef\014\uffef\015\uffef\016\uffef\017\uffef\020\uffef\021" +
    "\uffef\025\uffef\001\002\000\014\002\uffff\013\uffff\020\007" +
    "\021\010\025\004\001\002\000\014\002\ufffe\013\ufffe\020" +
    "\ufffe\021\ufffe\025\ufffe\001\002\000\004\012\054\001\002" +
    "\000\004\010\041\001\002\000\004\002\040\001\002\000" +
    "\004\014\013\001\002\000\006\025\004\026\017\001\002" +
    "\000\026\002\ufff0\004\ufff0\005\ufff0\006\ufff0\007\ufff0\011" +
    "\ufff0\013\ufff0\020\ufff0\021\ufff0\025\ufff0\001\002\000\026" +
    "\002\ufff9\004\030\005\032\006\033\007\031\011\ufff9\013" +
    "\ufff9\020\ufff9\021\ufff9\025\ufff9\001\002\000\014\002\ufffa" +
    "\013\ufffa\020\ufffa\021\ufffa\025\ufffa\001\002\000\026\002" +
    "\uffee\004\uffee\005\uffee\006\uffee\007\uffee\011\uffee\013\uffee" +
    "\020\uffee\021\uffee\025\uffee\001\002\000\034\002\ufff1\004" +
    "\ufff1\005\ufff1\006\ufff1\007\ufff1\011\ufff1\013\ufff1\015\022" +
    "\016\023\017\021\020\ufff1\021\ufff1\025\ufff1\001\002\000" +
    "\006\025\004\026\017\001\002\000\006\025\004\026\017" +
    "\001\002\000\006\025\004\026\017\001\002\000\016\002" +
    "\ufff3\011\ufff3\013\ufff3\020\ufff3\021\ufff3\025\ufff3\001\002" +
    "\000\016\002\ufff1\011\ufff1\013\ufff1\020\ufff1\021\ufff1\025" +
    "\ufff1\001\002\000\016\002\ufff2\011\ufff2\013\ufff2\020\ufff2" +
    "\021\ufff2\025\ufff2\001\002\000\016\002\ufff4\011\ufff4\013" +
    "\ufff4\020\ufff4\021\ufff4\025\ufff4\001\002\000\006\025\004" +
    "\026\017\001\002\000\006\025\004\026\017\001\002\000" +
    "\006\025\004\026\017\001\002\000\006\025\004\026\017" +
    "\001\002\000\016\002\ufff6\011\ufff6\013\ufff6\020\ufff6\021" +
    "\ufff6\025\ufff6\001\002\000\016\002\ufff7\011\ufff7\013\ufff7" +
    "\020\ufff7\021\ufff7\025\ufff7\001\002\000\016\002\ufff5\011" +
    "\ufff5\013\ufff5\020\ufff5\021\ufff5\025\ufff5\001\002\000\016" +
    "\002\ufff8\011\ufff8\013\ufff8\020\ufff8\021\ufff8\025\ufff8\001" +
    "\002\000\004\002\000\001\002\000\006\025\004\026\017" +
    "\001\002\000\004\011\043\001\002\000\004\023\044\001" +
    "\002\000\004\012\045\001\002\000\010\020\007\021\010" +
    "\025\004\001\002\000\004\013\047\001\002\000\016\002" +
    "\ufffc\013\ufffc\020\ufffc\021\ufffc\022\050\025\ufffc\001\002" +
    "\000\004\012\051\001\002\000\010\020\007\021\010\025" +
    "\004\001\002\000\004\013\053\001\002\000\014\002\ufffd" +
    "\013\ufffd\020\ufffd\021\ufffd\025\ufffd\001\002\000\010\020" +
    "\007\021\010\025\004\001\002\000\004\013\056\001\002" +
    "\000\004\024\057\001\002\000\004\010\060\001\002\000" +
    "\006\025\004\026\017\001\002\000\004\011\062\001\002" +
    "\000\014\002\ufffb\013\ufffb\020\ufffb\021\ufffb\025\ufffb\001" +
    "\002\000\006\002\001\013\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\061\000\012\002\010\004\004\005\005\007\011\001" +
    "\001\000\002\001\001\000\012\002\062\004\004\005\005" +
    "\007\011\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\012" +
    "\003\015\006\014\007\017\010\013\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\010\006\026\007\024\010\013\001" +
    "\001\000\010\006\025\007\024\010\013\001\001\000\010" +
    "\006\023\007\024\010\013\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\010" +
    "\006\036\007\024\010\013\001\001\000\010\006\035\007" +
    "\024\010\013\001\001\000\010\006\034\007\024\010\013" +
    "\001\001\000\010\006\033\007\024\010\013\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\012\003\041\006\014\007" +
    "\017\010\013\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\012\002\045\004\004\005\005\007" +
    "\011\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\012\002\051\004\004\005\005\007\011\001" +
    "\001\000\002\001\001\000\002\001\001\000\012\002\054" +
    "\004\004\005\005\007\011\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\012\003\060\006\014" +
    "\007\017\010\013\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


   

    public int endIfCounter=0;
    public int endDoCounter=0;
    private int nodeCounter=0;
    public List<Node> nodeList = new LinkedList<>();

    public String getNodeName(){
        return "n" + nodeCounter++;
    }
    

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$Parser$actions {
  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // Integer ::= INT 
            {
              String RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                    RESULT = i;
                
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Integer",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // Identifier ::= ID 
            {
              String RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                    RESULT = i;
                
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Identifier",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // Value ::= Integer 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                    RESULT = i;
                
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Value",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // Value ::= Identifier 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                    RESULT = i;
                
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Value",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // Expression ::= Identifier EQL Value 
            {
              Object RESULT =null;
		int i1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int i1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String i1 = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int i2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int i2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i2 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        RESULT = new String(i1.toString() + "==" + i2.toString());
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Expression",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // Expression ::= Identifier LSS Value 
            {
              Object RESULT =null;
		int i1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int i1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String i1 = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int i2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int i2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i2 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        RESULT = new String(i1.toString() + "<" + i2.toString());
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Expression",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // Expression ::= Identifier GRT Value 
            {
              Object RESULT =null;
		int i1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int i1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String i1 = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int i2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int i2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i2 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        RESULT = new String(i1.toString() + ">" + i2.toString());
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Expression",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // Expression ::= Value SLASH Value 
            {
              Object RESULT =null;
		int i1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int i1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object i1 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int i2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int i2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i2 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        RESULT = new String(i1.toString() + "/" + i2.toString());
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Expression",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // Expression ::= Value TIMES Value 
            {
              Object RESULT =null;
		int i1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int i1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object i1 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int i2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int i2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i2 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        RESULT = new String(i1.toString() + "*" + i2.toString());
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Expression",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Expression ::= Value MINUS Value 
            {
              Object RESULT =null;
		int i1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int i1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object i1 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int i2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int i2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i2 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        RESULT = new String(i1.toString() + "-" + i2.toString());
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Expression",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // Expression ::= Value PLUS Value 
            {
              Object RESULT =null;
		int i1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int i1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object i1 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int i2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int i2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i2 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        RESULT = new String(i1.toString() + "+" +  i2.toString());
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Expression",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Expression ::= Value 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        RESULT = i;
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Expression",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Assignment ::= Identifier BECOMES Expression 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String i = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                      RESULT = new String(i.toString() + ":=" + e.toString());
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Assignment",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Statement ::= DOSYM LBRACE Grammar RBRACE WHILESYM LPAREN Expression RPAREN 
            {
              Object RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		                      
                        Node doBody = (Node) s;
                        doBody.setPosition(sleft);

                        Node lastDoBody = doBody.getLast();
                        Node exp = new Node(parser.getNodeName(), (String)e);
                        exp.setPosition(eleft);
                        parser.nodeList.add(exp);
                        lastDoBody.addNexts("exp", exp);

                        Node endDo = new Node(parser.getNodeName(), "endDo" + parser.endDoCounter++);
                        endDo.setPosition(null);
                        parser.nodeList.add(endDo);
                        exp.addNexts("ifFalse", endDo);
                        exp.addNexts("ifTrue", doBody);
                        exp.addPrevious(lastDoBody);
                        endDo.addPrevious(lastDoBody);
                        RESULT = doBody;
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Statement ::= IFSYM LPAREN Expression RPAREN THENSYM LBRACE Grammar RBRACE 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		                        
                        Node exp = new Node(parser.getNodeName(), (String)e);
                        exp.setPosition(eleft);
                        parser.nodeList.add(exp);
                        Node ifTrue = (Node) s;
                        exp.addNexts("ifTrue", ifTrue);
                        ifTrue.addPrevious(exp);

                        Node endIf = new Node(parser.getNodeName(), "endIf" + parser.endIfCounter);
                        endIf.setPosition(null);
                        parser.nodeList.add(endIf);
                        parser.endIfCounter++;

                        Node lastNodeIfTrue = ifTrue.getLast();
                        lastNodeIfTrue.addNexts("next", endIf);
                        endIf.addPrevious(lastNodeIfTrue);
                        endIf.addPrevious(exp);
                        exp.addNexts("ifFalse", endIf);
                        RESULT = exp;
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-7)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Statement ::= IFSYM LPAREN Expression RPAREN THENSYM LBRACE Grammar RBRACE ELSESYM LBRACE Grammar RBRACE 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-9)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-9)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-9)).value;
		int s1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int s1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		Object s1 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int s2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int s2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object s2 = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		                         
                        Node exp = new Node(parser.getNodeName(), (String)e);      
                        exp.setPosition(eleft);
                        parser.nodeList.add(exp);
                        Node ifTrue = (Node) s1;
                        Node ifFalse = (Node) s2;
                        ifTrue.addPrevious(exp);
                        ifFalse.addPrevious(exp);
                        exp.addNexts("ifTrue", ifTrue);
                        exp.addNexts("ifFalse", ifFalse);

                        Node endIf = new Node(parser.getNodeName(), "endIf" + parser.endIfCounter);
                        endIf.setPosition(null);
                        parser.nodeList.add(endIf);
                        parser.endIfCounter++;

                        Node lastNodeIfTrue = ifTrue.getLast();
                        lastNodeIfTrue.addNexts("next", endIf);

                        Node lastNodeIfFalse = ifFalse.getLast();
                        lastNodeIfFalse.addNexts("next", endIf);
                        endIf.addPrevious(lastNodeIfTrue);
                        endIf.addPrevious(lastNodeIfFalse);
                        RESULT = exp;
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-11)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Statement ::= Assignment 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object a = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		                          
                        Node n = new Node(parser.getNodeName(), (String)a);     
                        n.setPosition(aleft);
                        parser.nodeList.add(n);
                        RESULT = n;
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Statement",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Grammar ::= Statement 
            {
              Object RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		      
                        Node newNode = (Node) s;
                        RESULT = newNode;
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Grammar",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= Grammar EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // Grammar ::= Statement Grammar 
            {
              Object RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int gleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int gright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object g = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
                        Node newNode = (Node) s;
                        Node lastNode = newNode.getLast();
                        Node grammarNode = (Node) g;
                        grammarNode.addPrevious(lastNode);
                        lastNode.addNexts("next", grammarNode);
                        RESULT = newNode;
                    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Grammar",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

