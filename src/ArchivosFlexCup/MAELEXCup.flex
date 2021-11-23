package Controladores;
import ClasesFlexCup.symb;
import java_cup.runtime.Symbol;
%%
%class MAELEXCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
H=[a-fA-F_]+
D=[0-9]+
B=[0-1]+
S=[.,:,*,$]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }
    private Symbol symbol(int type){
        return new Symbol(type,yyline,yycolumn);
    }
%}
%%
/*Pseudoinstrucciones*/

db | DB {return new Symbol(sym.DB, yychar, yyline, yytext());} 
dw | DW {return new Symbol(sym.DW, yychar, yyline, yytext());} 
equ | EQU {return new Symbol(sym.EQU, yychar, yyline, yytext());}
"stack segment" | "STACK SEGMENT" | ".STACK" | ".stack" {return new Symbol(sym.STACK, yychar, yyline, yytext());} 
"data segment" | "DATA SEGMENT" | ".data" | ".DATA" {return new Symbol(sym.DATA, yychar, yyline, yytext());} 
"code segment" | "CODE SEGMENT" | ".code" | ".CODE" {return new Symbol(sym.CODE, yychar, yyline, yytext());} 
macro | MACRO {return new Symbol(sym.MACRO, yychar, yyline, yytext());} 
endm | ENDM {return new Symbol(sym.ENDM, yychar, yyline, yytext());} 
ends | ENDS {return new Symbol(sym.ENDS, yychar, yyline, yytext());} 
proc | PROC {return new Symbol(sym.PROC, yychar, yyline, yytext());} 
endp | ENDP {return new Symbol(sym.ENDP, yychar, yyline, yytext());} 
"byte ptr" | "BYTE PTR" {return new Symbol(sym.BYTE_PTR, yychar, yyline, yytext());} 
"word ptr" | "WORD PTR" {return new Symbol(sym.WORD_PTR, yychar, yyline, yytext());} 


/*Instrucciones*/
sti | STI {return new Symbol(sym.STI, yychar, yyline, yytext());} 
aam | AAM {return new Symbol(sym.AAM, yychar, yyline, yytext());} 
cli | CLI {return new Symbol(sym.CLI, yychar, yyline, yytext());} 
ret | RET {return new Symbol(sym.RET, yychar, yyline, yytext());} 
stosb | STOSB {return new Symbol(sym.STOSB, yychar, yyline, yytext());} 
aas | AAS {return new Symbol(sym.AAS, yychar, yyline, yytext());} 
idiv | IDIV {return new Symbol(sym.IDIV, yychar, yyline, yytext());} 
div | DIV {return new Symbol(sym.DIV, yychar, yyline, yytext());} 
mul | MUL {return new Symbol(sym.MUL, yychar, yyline, yytext());} 
not | NOT {return new Symbol(sym.NOT, yychar, yyline, yytext());} 
add | ADD {return new Symbol(sym.ADD, yychar, yyline, yytext());} 
les | LES {return new Symbol(sym.LES, yychar, yyline, yytext());} 
or | OR {return new Symbol(sym.OR, yychar, yyline, yytext());} 
sub | SUB {return new Symbol(sym.SUB, yychar, yyline, yytext());} 
jc | JC {return new Symbol(sym.JC, yychar, yyline, yytext());} 
jge | JGE {return new Symbol(sym.JGE, yychar, yyline, yytext());} 
jna | JNA {return new Symbol(sym.JNA, yychar, yyline, yytext());} 
js | JS {return new Symbol(sym.JS, yychar, yyline, yytext());} 
loopne | LOOPNE {return new Symbol(sym.LOOPNE, yychar, yyline, yytext());} 
jae | JAE {return new Symbol(sym.JAE, yychar, yyline, yytext());} 

/*Registros*/
ax | AX {return new Symbol(sym.AX, yychar, yyline, yytext());} 
ah | AH {return new Symbol(sym.AH, yychar, yyline, yytext());} 
al | AL {return new Symbol(sym.AL, yychar, yyline, yytext());} 
bx | BX {return new Symbol(sym.BX, yychar, yyline, yytext());} 
bh | BH {return new Symbol(sym.BH, yychar, yyline, yytext());} 
bl | BL {return new Symbol(sym.BL, yychar, yyline, yytext());} 
cx | CX {return new Symbol(sym.CX, yychar, yyline, yytext());} 
ch | CH {return new Symbol(sym.CH, yychar, yyline, yytext());} 
cl | CL {return new Symbol(sym.CL, yychar, yyline, yytext());} 
dx | DX {return new Symbol(sym.DX, yychar, yyline, yytext());} 
dh | DH {return new Symbol(sym.DH, yychar, yyline, yytext());} 
dl | DL {return new Symbol(sym.DL, yychar, yyline, yytext());} 
si | SI {return new Symbol(sym.SI, yychar, yyline, yytext());} 
di | DI {return new Symbol(sym.DI, yychar, yyline, yytext());} 
sp | SP {return new Symbol(sym.SP, yychar, yyline, yytext());} 
bp | BP {return new Symbol(sym.BP, yychar, yyline, yytext());} 
ss | SS {return new Symbol(sym.SS, yychar, yyline, yytext());} 
cs | CS {return new Symbol(sym.CS, yychar, yyline, yytext());} 
ds | DS {return new Symbol(sym.DS, yychar, yyline, yytext());} 
es | ES {return new Symbol(sym.ES, yychar, yyline, yytext());} 

/*Separadores*/
{espacio} {/*Ignorar*/}
(";"(.)*) {/*Ignorar*/}
(",") "\"" {return new Symbol(sym.Coma, yychar, yyline, yytext());}

":" {return new Symbol(sym.Dos_puntos, yychar, yyline, yytext());} 

"\"" {return new Symbol(sym.Comilla_d, yychar, yyline, yytext());}
"'" {return new Symbol(sym.Comilla_s, yychar, yyline, yytext());}
"(" {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}
")" {return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}
"[" {return new Symbol(sym.Corchete_a, yychar, yyline, yytext());}
"]" {return new Symbol(sym.Corchete_c, yychar, yyline, yytext());}
TITLE .* | title .* {return new Symbol(sym.TITLE, yychar, yyline, yytext());}

(dup+"("+{D}")")|
(DUP+"("+{D}")")|
(DUP+"("+"\""+({L}|{espacio}|{D})+"$\""")")|
(dup+"("+"\""+({L}|{espacio}|{D})+"$\""")")|
(dup+"("+"'"+({L}|{espacio}|{D})+"$'"")")|
(DUP+"("+"'"+({L}|{espacio}|{D})+"$'"")")|
(dup+"(""?"")")|
(DUP+"(""?"")") {return new Symbol(sym.DUP, yychar, yyline, yytext());}

("\""+*({L}|{D}|{espacio}|{S})+"$""\"")|
("'"+*({L}|{D}|{espacio}|{S})+"$""'")|
(",'"+"$"+"'")|
("'"+({L}|{D}|{espacio}|{S})+"'")|
("\""+({L}|{D}|{espacio}|{S})+"\"") {{return new Symbol(sym.Cadena, yychar, yyline, yytext());}}

{D}*{D} {return new Symbol(sym.ConstanteDec, yychar, yyline, yytext());}
{B}*{B}"B"|{B}*{B}"b" {return new Symbol(sym.ConstanteBin, yychar, yyline, yytext());}
"0"+({D}|{H})"H"|"0"+({D}|{H})"h"|
"0"+({D}*{H})"H"|"0"+({D}*{H})"h"|
"0"+({H}*{D})"H"|"0"+({H}*{D})"h" {return new Symbol(sym.ConstanteHex, yychar, yyline, yytext());}
{L}({L}|{D})*|{L}("["{D}"]") {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
{L}({L}|{D})*":" {return new Symbol(sym.Etiqueta, yychar, yyline, yytext());}
{L}(",") {return new Symbol(sym.SinComa, yychar, yyline, yytext());}
(", "){D}"h"|(", "){D}"H" {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
"@"{L} {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
{D}(",") {return new Symbol(sym.ConstanteDec, yychar, yyline, yytext());}
{S}.*|{D}({L}|{S}) {return new Symbol(sym.Separadores, yychar, yyline, yytext());}
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
