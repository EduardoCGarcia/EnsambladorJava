
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sun Oct 17 22:10:13 CDT 2021
//----------------------------------------------------

package Controladores;

import java_cup.runtime.Symbol;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Sun Oct 17 22:10:13 CDT 2021
  */
public class MAESemantico extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public MAESemantico() {super();}

  /** Constructor which sets the default scanner. */
  public MAESemantico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public MAESemantico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\032\000\002\002\004\000\002\002\004\000\002\002" +
    "\003\000\002\003\005\000\002\005\005\000\002\005\005" +
    "\000\002\005\005\000\002\004\005\000\002\006\004\000" +
    "\002\006\003\000\002\007\005\000\002\007\005\000\002" +
    "\007\005\000\002\007\005\000\002\007\005\000\002\007" +
    "\005\000\002\007\005\000\002\007\006\000\002\007\006" +
    "\000\002\007\006\000\002\007\006\000\002\007\006\000" +
    "\002\007\006\000\002\007\005\000\002\007\005\000\002" +
    "\007\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\050\000\004\010\004\001\002\000\004\006\042\001" +
    "\002\000\006\002\uffff\011\010\001\002\000\004\002\007" +
    "\001\002\000\004\002\001\001\002\000\004\112\014\001" +
    "\002\000\004\002\000\001\002\000\004\015\041\001\002" +
    "\000\006\015\ufff8\112\014\001\002\000\010\005\015\006" +
    "\016\007\017\001\002\000\012\106\034\107\031\110\032" +
    "\111\033\001\002\000\010\107\023\110\024\111\025\001" +
    "\002\000\010\107\020\110\021\111\022\001\002\000\006" +
    "\015\uffea\112\uffea\001\002\000\006\015\uffe9\112\uffe9\001" +
    "\002\000\006\015\uffe8\112\uffe8\001\002\000\010\015\ufff3" +
    "\105\030\112\ufff3\001\002\000\010\015\ufff2\105\027\112" +
    "\ufff2\001\002\000\010\015\ufff1\105\026\112\ufff1\001\002" +
    "\000\006\015\uffeb\112\uffeb\001\002\000\006\015\uffec\112" +
    "\uffec\001\002\000\006\015\uffed\112\uffed\001\002\000\010" +
    "\015\ufff6\105\037\112\ufff6\001\002\000\010\015\ufff5\105" +
    "\036\112\ufff5\001\002\000\010\015\ufff4\105\035\112\ufff4" +
    "\001\002\000\006\015\ufff7\112\ufff7\001\002\000\006\015" +
    "\uffee\112\uffee\001\002\000\006\015\uffef\112\uffef\001\002" +
    "\000\006\015\ufff0\112\ufff0\001\002\000\004\015\ufff9\001" +
    "\002\000\004\002\ufffa\001\002\000\010\107\045\110\046" +
    "\111\047\001\002\000\004\015\044\001\002\000\006\002" +
    "\ufffe\011\ufffe\001\002\000\004\105\052\001\002\000\004" +
    "\105\051\001\002\000\004\105\050\001\002\000\004\015" +
    "\ufffb\001\002\000\004\015\ufffc\001\002\000\004\015\ufffd" +
    "\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\050\000\006\002\005\003\004\001\001\000\004\005" +
    "\042\001\001\000\004\004\010\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\006\011\007\012\001\001\000" +
    "\002\001\001\000\002\001\001\000\006\006\037\007\012" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$MAESemantico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$MAESemantico$actions(this);
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
    return action_obj.CUP$MAESemantico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    private Symbol s;
    
    public void syntax_error(Symbol s){
        this.s = s;
    }

    public Symbol getS(){
        return this.s;
}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$MAESemantico$actions {
  private final MAESemantico parser;

  /** Constructor */
  CUP$MAESemantico$actions(MAESemantico parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$MAESemantico$do_action(
    int                        CUP$MAESemantico$act_num,
    java_cup.runtime.lr_parser CUP$MAESemantico$parser,
    java.util.Stack            CUP$MAESemantico$stack,
    int                        CUP$MAESemantico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$MAESemantico$result;

      /* select the action based on the action number */
      switch (CUP$MAESemantico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // Declaracion ::= Simbolo EQU ConstanteHex 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // Declaracion ::= Simbolo EQU ConstanteBin 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // Declaracion ::= Simbolo EQU ConstanteDec 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // Declaracion ::= Simbolo DW ConstanteHex DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-3)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // Declaracion ::= Simbolo DW ConstanteBin DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-3)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // Declaracion ::= Simbolo DW ConstanteDec DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-3)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // Declaracion ::= Simbolo DB ConstanteHex DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-3)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // Declaracion ::= Simbolo DB ConstanteBin DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-3)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // Declaracion ::= Simbolo DB ConstanteDec DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-3)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // Declaracion ::= Simbolo DW ConstanteHex 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // Declaracion ::= Simbolo DW ConstanteBin 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // Declaracion ::= Simbolo DW ConstanteDec 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // Declaracion ::= Simbolo DB ConstanteHex 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // Declaracion ::= Simbolo DB ConstanteBin 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // Declaracion ::= Simbolo DB ConstanteDec 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Declaracion ::= Simbolo DB Cadena 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaracion",5, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // Declaraciones ::= Declaracion 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaraciones",4, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Declaraciones ::= Declaracion Declaraciones 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Declaraciones",4, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-1)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // DataSegment ::= DATA Declaraciones ENDS 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("DataSegment",2, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // DeclaraStack ::= DW ConstanteHex DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("DeclaraStack",3, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // DeclaraStack ::= DW ConstanteBin DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("DeclaraStack",3, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // DeclaraStack ::= DW ConstanteDec DUP 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("DeclaraStack",3, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // StackSegment ::= STACK DeclaraStack ENDS 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("StackSegment",1, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-2)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Programa ::= StackSegment 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Programa",0, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // Programa ::= StackSegment DataSegment 
            {
              Object RESULT =null;

              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("Programa",0, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-1)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          return CUP$MAESemantico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= Programa EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-1)).value;
		RESULT = start_val;
              CUP$MAESemantico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.elementAt(CUP$MAESemantico$top-1)), ((java_cup.runtime.Symbol)CUP$MAESemantico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$MAESemantico$parser.done_parsing();
          return CUP$MAESemantico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

