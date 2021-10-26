package Controladores;
import ClasesFlexCup.symb;
import java_cup.runtime.Symbol;
%%
%class MAELEXSEMCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
H=[0,a-fA-F_]+
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

db | DB {return new Symbol(symb.DB, yychar, yyline, yytext());} 
dw | DW {return new Symbol(symb.DW, yychar, yyline, yytext());} 
equ | EQU {return new Symbol(symb.EQU, yychar, yyline, yytext());}
"stack segment" | "STACK SEGMENT" | ".STACK" | ".stack" {return new Symbol(symb.STACK, yychar, yyline, yytext());} 
"data segment" | "DATA SEGMENT" | ".data" | ".DATA" {return new Symbol(symb.DATA, yychar, yyline, yytext());} 
"code segment" | "CODE SEGMENT" | ".code" | ".CODE" {return new Symbol(symb.CODE, yychar, yyline, yytext());} 
macro | MACRO {return new Symbol(symb.MACRO, yychar, yyline, yytext());} 
endm | ENDM {return new Symbol(symb.ENDM, yychar, yyline, yytext());} 
ends | ENDS {return new Symbol(symb.ENDS, yychar, yyline, yytext());} 
proc | PROC {return new Symbol(symb.PROC, yychar, yyline, yytext());} 
endp | ENDP {return new Symbol(symb.ENDP, yychar, yyline, yytext());} 
"byte ptr" | "BYTE PTR" {return new Symbol(symb.BYTE_PTR, yychar, yyline, yytext());} 
"word ptr" | "WORD PTR" {return new Symbol(symb.WORD_PTR, yychar, yyline, yytext());} 


/*Instrucciones*/
sti | STI {return new Symbol(symb.STI, yychar, yyline, yytext());} 
aam | AAM {return new Symbol(symb.AAM, yychar, yyline, yytext());} 
sli | SLI {return new Symbol(symb.SLI, yychar, yyline, yytext());} 
ret | RET {return new Symbol(symb.RET, yychar, yyline, yytext());} 
stosb | STOSB {return new Symbol(symb.STOSB, yychar, yyline, yytext());} 
aas | AAS {return new Symbol(symb.AAS, yychar, yyline, yytext());} 
idiv | IDIV {return new Symbol(symb.IDIV, yychar, yyline, yytext());} 
div | DIV {return new Symbol(symb.DIV, yychar, yyline, yytext());} 
mul | MUL {return new Symbol(symb.MUL, yychar, yyline, yytext());} 
not | NOT {return new Symbol(symb.NOT, yychar, yyline, yytext());} 
add | ADD {return new Symbol(symb.ADD, yychar, yyline, yytext());} 
les | LES {return new Symbol(symb.LES, yychar, yyline, yytext());} 
or | OR {return new Symbol(symb.OR, yychar, yyline, yytext());} 
sub | SUB {return new Symbol(symb.SUB, yychar, yyline, yytext());} 
jc | JC {return new Symbol(symb.JC, yychar, yyline, yytext());} 
jge | JGE {return new Symbol(symb.JGE, yychar, yyline, yytext());} 
jna | JNA {return new Symbol(symb.JNA, yychar, yyline, yytext());} 
js | JS {return new Symbol(symb.JS, yychar, yyline, yytext());} 
loopne | LOOPNE {return new Symbol(symb.LOOPNE, yychar, yyline, yytext());} 
jae | JAE {return new Symbol(symb.JAE, yychar, yyline, yytext());} 

/*Registros*/
ax | AX {return new Symbol(symb.AX, yychar, yyline, yytext());} 
ah | AH {return new Symbol(symb.AH, yychar, yyline, yytext());} 
al | AL {return new Symbol(symb.AL, yychar, yyline, yytext());} 
bx | BX {return new Symbol(symb.BX, yychar, yyline, yytext());} 
bh | BH {return new Symbol(symb.BH, yychar, yyline, yytext());} 
bl | BL {return new Symbol(symb.BL, yychar, yyline, yytext());} 
cx | CX {return new Symbol(symb.CX, yychar, yyline, yytext());} 
ch | CH {return new Symbol(symb.CH, yychar, yyline, yytext());} 
cl | CL {return new Symbol(symb.CL, yychar, yyline, yytext());} 
dx | DX {return new Symbol(symb.DX, yychar, yyline, yytext());} 
dh | DH {return new Symbol(symb.DH, yychar, yyline, yytext());} 
dl | DL {return new Symbol(symb.DL, yychar, yyline, yytext());} 
si | SI {return new Symbol(symb.SI, yychar, yyline, yytext());} 
di | DI {return new Symbol(symb.DI, yychar, yyline, yytext());} 
sp | SP {return new Symbol(symb.SP, yychar, yyline, yytext());} 
bp | BP {return new Symbol(symb.BP, yychar, yyline, yytext());} 
ss | SS {return new Symbol(symb.SS, yychar, yyline, yytext());} 
cs | CS {return new Symbol(symb.CS, yychar, yyline, yytext());} 
ds | DS {return new Symbol(symb.DS, yychar, yyline, yytext());} 
es | ES {return new Symbol(symb.ES, yychar, yyline, yytext());} 

/*Separadores*/
{espacio} {/*Ignorar*/}
(";"(.)*) {/*Ignorar*/}
(",") "\"" {return new Symbol(symb.Coma, yychar, yyline, yytext());}

":" {return new Symbol(symb.Dos_puntos, yychar, yyline, yytext());} 

"\"" {return new Symbol(symb.Comilla_d, yychar, yyline, yytext());}
"'" {return new Symbol(symb.Comilla_s, yychar, yyline, yytext());}
"(" {return new Symbol(symb.Parentesis_a, yychar, yyline, yytext());}
")" {return new Symbol(symb.Parentesis_a, yychar, yyline, yytext());}
"[" {return new Symbol(symb.Corchete_a, yychar, yyline, yytext());}
"]" {return new Symbol(symb.Corchete_c, yychar, yyline, yytext());}
TITLE .* | title .* {return new Symbol(symb.TITLE, yychar, yyline, yytext());}

(dup+"("+{D}")")|
(DUP+"("+{D}")")|
(DUP+"("+"\""+({L}|{espacio}|{D})+"$\""")")|
(dup+"("+"\""+({L}|{espacio}|{D})+"$\""")")|
(dup+"("+"'"+({L}|{espacio}|{D})+"$'"")")|
(DUP+"("+"'"+({L}|{espacio}|{D})+"$'"")")|
(dup+"(""?"")")|
(DUP+"(""?"")") {return new Symbol(symb.DUP, yychar, yyline, yytext());}

("\""+*({L}|{D}|{espacio}|{S})+"$""\"")|
("'"+*({L}|{D}|{espacio}|{S})+"$""'")|
(",'"+"$"+"'")|
("'"+({L}|{D}|{espacio}|{S})+"'")|
("\""+({L}|{D}|{espacio}|{S})+"\"") {{return new Symbol(symb.Cadena, yychar, yyline, yytext());}}

{D}*{D} {return new Symbol(symb.ConstanteDec, yychar, yyline, yytext());}
{B}*{B}"B"|{B}*{B}"b" {return new Symbol(symb.ConstanteBin, yychar, yyline, yytext());}
"0"+({D}|{H})"H"|"0"+({D}|{H})"h" {return new Symbol(symb.ConstanteHex, yychar, yyline, yytext());}
{L}({L}|{D})*|{L}("["{D}"]") {return new Symbol(symb.Simbolo, yychar, yyline, yytext());}
{L}({L}|{D})*":" {return new Symbol(symb.Etiqueta, yychar, yyline, yytext());}
{L}(",") {return new Symbol(symb.SinComa, yychar, yyline, yytext());}
(", "){D}"h"|(", "){D}"H" {return new Symbol(symb.ERROR, yychar, yyline, yytext());}
"@"{L} {return new Symbol(symb.ERROR, yychar, yyline, yytext());}
{D}(",") {return new Symbol(symb.ConstanteDec, yychar, yyline, yytext());}
{S}.*|{D}({L}|{S}) {return new Symbol(symb.Separadores, yychar, yyline, yytext());}
 . {return new Symbol(symb.ERROR, yychar, yyline, yytext());}

