package Controladores;
import ClasesFlexCup.Elementos;
import static ClasesFlexCup.Elementos.*;

%%
%class MAELEX
%type Elementos
L=[a-zA-Z_]+
H=[0,a-fA-F_]+
D=[0-9]+
B=[0-1]+
S=[.,),(,:,*,$,"[","]"]+
espacio=[ ,\t,\r]+
%{
    public String maeLexMe;
%}
%%
/*Pseudoinstrucciones*/
db | DB {maeLexMe=yytext(); return DB;}
dw | DW {maeLexMe=yytext(); return DW;}
equ | EQU {maeLexMe=yytext(); return EQU;}
"stack segment" | "STACK SEGMENT" | ".STACK" | ".stack" {maeLexMe=yytext(); return STACK;}
"data segment" | "DATA SEGMENT" | ".data" | ".DATA" {maeLexMe=yytext(); return DATA;}
"code segment" | "CODE SEGMENT" | ".code" | ".CODE" {maeLexMe=yytext(); return CODE;}
macro | MACRO {maeLexMe=yytext(); return MACRO;}
endm | ENDM {maeLexMe=yytext(); return ENDM;}
ends | ENDS {maeLexMe=yytext(); return ENDS;}
proc | PROC {maeLexMe=yytext(); return PROC;}
endp | ENDP {maeLexMe=yytext(); return ENDP;}
"byte ptr" | "BYTE PTR" {maeLexMe=yytext(); return BYTE_PTR;}
"word ptr" | "WORD PTR" {maeLexMe=yytext(); return WORD_PTR;}


/*Instrucciones*/
sti | STI {maeLexMe=yytext(); return STI;}
aam | AAM {maeLexMe=yytext(); return AAM;}
sli | SLI {maeLexMe=yytext(); return SLI;}
ret | RET {maeLexMe=yytext(); return RET;}
stosb | STOSB {maeLexMe=yytext(); return STOSB;}
aas | AAS {maeLexMe=yytext(); return AAS;}
idiv | IDIV {maeLexMe=yytext(); return IDIV;}
div | DIV {maeLexMe=yytext(); return DIV;}
mul | MUL {maeLexMe=yytext(); return MUL;}
not | NOT {maeLexMe=yytext(); return NOT;}
add | ADD {maeLexMe=yytext(); return ADD;}
les | LES {maeLexMe=yytext(); return LES;}
or | OR {maeLexMe=yytext(); return OR;}
sub | SUB {maeLexMe=yytext(); return SUB;}
jc | JC {maeLexMe=yytext(); return JC;}
jge | JGE {maeLexMe=yytext(); return JGE;}
jna | JNA {maeLexMe=yytext(); return JNA;}
js | JS {maeLexMe=yytext(); return JS;}
loopne | LOOPNE {maeLexMe=yytext(); return LOOPNE;}
jae | JAE {maeLexMe=yytext(); return JAE;}

/*Registros*/
ax | AX {maeLexMe=yytext(); return AX;}
ah | AH {maeLexMe=yytext(); return AH;}
al | AL {maeLexMe=yytext(); return AL;}
bx | BX {maeLexMe=yytext(); return BX;}
bh | BH {maeLexMe=yytext(); return BH;}
bl | BL {maeLexMe=yytext(); return BL;}
cx | CX {maeLexMe=yytext(); return CX;}
ch | CH {maeLexMe=yytext(); return CH;}
cl | CL {maeLexMe=yytext(); return CL;}
dx | DX {maeLexMe=yytext(); return DX;}
dh | DH {maeLexMe=yytext(); return DH;}
dl | DL {maeLexMe=yytext(); return DL;}
si | SI {maeLexMe=yytext(); return SI;}
di | DI {maeLexMe=yytext(); return DI;}
sp | SP {maeLexMe=yytext(); return SP;}
bp | BP {maeLexMe=yytext(); return BP;}
ss | SS {maeLexMe=yytext(); return SS;}
cs | CS {maeLexMe=yytext(); return CS;}
ds | DS {maeLexMe=yytext(); return DS;}
es | ES {maeLexMe=yytext(); return ES;}

/*Separadores*/
{espacio} {/*Ignorar*/}
(";"(.)*) {/*Ignorar*/}
(",") {/*Ignorar*/}

"\n" {return Linea;}
":" {maeLexMe=yytext(); return Dos_puntos;}

"\"" {maeLexMe=yytext(); return Comilla_d;}
"'" {maeLexMe=yytext(); return Comilla_s;}
"(" {maeLexMe=yytext(); return Parentesis_a;}
")" {maeLexMe=yytext(); return Parentesis_c;}
"[" {maeLexMe=yytext(); return Corchete_a;}
"]" {maeLexMe=yytext(); return Corchete_c;}
TITLE .* | title .* {maeLexMe=yytext(); return TITLE;}

(dup+"("+{D}")")|
(DUP+"("+{D}")")|
(DUP+"("+"\""+({L}|{espacio}|{D})+"$\""")")|
(dup+"("+"\""+({L}|{espacio}|{D})+"$\""")")|
(dup+"("+"'"+({L}|{espacio}|{D})+"$'"")")|
(DUP+"("+"'"+({L}|{espacio}|{D})+"$'"")")|
(dup+"(""?"")")|
(DUP+"(""?"")") {maeLexMe=yytext(); return DUP;}

("\""+*({L}|{D}|{espacio}|{S})+"$""\"")|
("'"+*({L}|{D}|{espacio}|{S})+"$""'")|
(",'"+"$"+"'")|
("'"+({L}|{D}|{espacio}|{S})+"'")|
("\""+({L}|{D}|{espacio}|{S})+"\"") {maeLexMe=yytext(); return Cadena;}
{D}*{D} {maeLexMe=yytext(); return ConstanteDec;}
{B}*{B}"B"|{B}*{B}"b" {maeLexMe=yytext(); return maeLexMe.length()==9 || maeLexMe.length()==17 ? ConstanteBin : ERROR;}
"0"+({D}|{H})"H"|"0"+({D}|{H})"h" {maeLexMe=yytext(); return maeLexMe.length()==4 || maeLexMe.length()==6 ? ConstanteHex : ERROR;}
{L}({L}|{D})|{L}("["{D}"]") {maeLexMe=yytext(); return Simbolo;}
{L}({L}|{D})*":" {maeLexMe=yytext();  maeLexMe=maeLexMe.replace(":", ""); return Etiqueta;}
{L}(",") {maeLexMe=yytext(); maeLexMe=maeLexMe.replace(",", ""); return SinComa;}
(", "){D}"h"|(", "){D}"H" {maeLexMe=yytext(); maeLexMe=maeLexMe.replace(", ", ""); return ERROR;}
"@"{L} {maeLexMe=yytext(); maeLexMe=maeLexMe.replace(", ", ""); return ERROR;}
{D}(",") {maeLexMe=yytext(); maeLexMe=maeLexMe.replace(",", ""); return ConstanteDec;}
{S}.*|{D}({L}|{S}) {maeLexMe=yytext(); return Separadores;}
 . {return ERROR;}