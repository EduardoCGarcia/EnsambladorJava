
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Sun Oct 24 14:38:38 CDT 2021
//----------------------------------------------------

package ClasesFlexCup;

import java_cup.runtime.Symbol;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Sun Oct 24 14:38:38 CDT 2021
  */
public class MAESintax extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public MAESintax() {super();}

  /** Constructor which sets the default scanner. */
  public MAESintax(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public MAESintax(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\037\000\002\002\004\000\002\002\003\000\002\002" +
    "\003\000\002\002\003\000\002\002\003\000\002\002\003" +
    "\000\002\002\003\000\002\002\003\000\002\002\003\000" +
    "\002\002\003\000\002\002\003\000\002\004\005\000\002" +
    "\004\005\000\002\004\005\000\002\006\005\000\002\006" +
    "\005\000\002\006\005\000\002\006\005\000\002\006\005" +
    "\000\002\006\005\000\002\006\005\000\002\006\006\000" +
    "\002\006\006\000\002\006\006\000\002\006\006\000\002" +
    "\006\006\000\002\006\006\000\002\006\005\000\002\006" +
    "\005\000\002\006\005\000\002\007\004" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\052\000\026\006\011\010\007\011\006\012\004\013" +
    "\020\015\012\016\010\017\014\112\017\113\016\001\002" +
    "\000\004\002\ufffa\001\002\000\004\002\054\001\002\000" +
    "\004\002\ufffc\001\002\000\004\002\000\001\002\000\004" +
    "\112\053\001\002\000\010\107\045\110\046\111\047\001" +
    "\002\000\004\002\ufffe\001\002\000\004\002\uffff\001\002" +
    "\000\004\002\ufffd\001\002\000\004\002\ufffb\001\002\000" +
    "\004\002\ufff7\001\002\000\010\005\022\006\023\007\024" +
    "\001\002\000\004\002\ufff9\001\002\000\004\002\ufff8\001" +
    "\002\000\012\106\041\107\036\110\037\111\040\001\002" +
    "\000\010\107\030\110\031\111\032\001\002\000\010\107" +
    "\025\110\026\111\027\001\002\000\004\002\uffe6\001\002" +
    "\000\004\002\uffe5\001\002\000\004\002\uffe4\001\002\000" +
    "\006\002\uffef\105\035\001\002\000\006\002\uffee\105\034" +
    "\001\002\000\006\002\uffed\105\033\001\002\000\004\002" +
    "\uffe7\001\002\000\004\002\uffe8\001\002\000\004\002\uffe9" +
    "\001\002\000\006\002\ufff2\105\044\001\002\000\006\002" +
    "\ufff1\105\043\001\002\000\006\002\ufff0\105\042\001\002" +
    "\000\004\002\ufff3\001\002\000\004\002\uffea\001\002\000" +
    "\004\002\uffeb\001\002\000\004\002\uffec\001\002\000\004" +
    "\105\052\001\002\000\004\105\051\001\002\000\004\105" +
    "\050\001\002\000\004\002\ufff4\001\002\000\004\002\ufff5" +
    "\001\002\000\004\002\ufff6\001\002\000\004\002\uffe3\001" +
    "\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\052\000\012\002\004\004\012\006\014\007\020\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
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
  protected CUP$MAESintax$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$MAESintax$actions(this);
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
    return action_obj.CUP$MAESintax$do_action(act_num, parser, stack, top);
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
class CUP$MAESintax$actions {
  private final MAESintax parser;

  /** Constructor */
  CUP$MAESintax$actions(MAESintax parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$MAESintax$do_action(
    int                        CUP$MAESintax$act_num,
    java_cup.runtime.lr_parser CUP$MAESintax$parser,
    java.util.Stack            CUP$MAESintax$stack,
    int                        CUP$MAESintax$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$MAESintax$result;

      /* select the action based on the action number */
      switch (CUP$MAESintax$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // DefProc ::= PROC Simbolo 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("DefProc",5, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-1)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // Declaracion ::= Simbolo EQU ConstanteHex 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // Declaracion ::= Simbolo EQU ConstanteBin 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // Declaracion ::= Simbolo EQU ConstanteDec 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // Declaracion ::= Simbolo DW ConstanteHex DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-3)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // Declaracion ::= Simbolo DW ConstanteBin DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-3)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // Declaracion ::= Simbolo DW ConstanteDec DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-3)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // Declaracion ::= Simbolo DB ConstanteHex DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-3)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // Declaracion ::= Simbolo DB ConstanteBin DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-3)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // Declaracion ::= Simbolo DB ConstanteDec DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-3)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // Declaracion ::= Simbolo DW ConstanteHex 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // Declaracion ::= Simbolo DW ConstanteBin 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // Declaracion ::= Simbolo DW ConstanteDec 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // Declaracion ::= Simbolo DB ConstanteHex 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // Declaracion ::= Simbolo DB ConstanteBin 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // Declaracion ::= Simbolo DB ConstanteDec 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // Declaracion ::= Simbolo DB Cadena 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("Declaracion",4, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // DeclaraStack ::= DW ConstanteHex DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("DeclaraStack",2, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // DeclaraStack ::= DW ConstanteBin DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("DeclaraStack",2, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // DeclaraStack ::= DW ConstanteDec DUP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("DeclaraStack",2, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-2)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // PROGRAMA ::= Etiqueta 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // PROGRAMA ::= DefProc 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // PROGRAMA ::= MACRO 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // PROGRAMA ::= CODE 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // PROGRAMA ::= Declaracion 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // PROGRAMA ::= DATA 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // PROGRAMA ::= ENDP 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // PROGRAMA ::= ENDS 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // PROGRAMA ::= DeclaraStack 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // PROGRAMA ::= STACK 
            {
              Object RESULT =null;

              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("PROGRAMA",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          return CUP$MAESintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= PROGRAMA EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-1)).value;
		RESULT = start_val;
              CUP$MAESintax$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$MAESintax$stack.elementAt(CUP$MAESintax$top-1)), ((java_cup.runtime.Symbol)CUP$MAESintax$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$MAESintax$parser.done_parsing();
          return CUP$MAESintax$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

