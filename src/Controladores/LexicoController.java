/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesFlexCup.Elementos;
import ProgramaPrincipal.Lexico;
import static ClasesFlexCup.Elementos.*;
import static ProgramaPrincipal.Lexico.*;
import java.io.IOException;

/**
 *
 * @author eduar
 */
public class LexicoController {
    public static  Lexico winLex = new Lexico();
    public static void mostrar(){
        winLex.txtAnalisisLexicco.setText(Paginacion.renglonesInicial(cadena));
        winLex.setVisible(true);
    }
    public static void ocultar(){winLex.setVisible(false);}
    
    public static void analizarLexico(String lec) throws IOException {
        cadena.clear();
        //String lec = (String) txtArchivo.getText();//Todo lo que se lee del archivo y que cae en txtArchivo
        MAELEX maeLex = new MAELEX(new java.io.StringReader(lec));//Se lo colocamos al maeLex porque es el que va a hacer el analisis lexico
        String result = "";
        simbolo=tipo=valor=null;
        while (true) {
            Elementos elemento = maeLex.yylex();
            if (elemento == null) {
                if(data=="activado" && simbolo!=null && tipo!=null && valor!=null){
                    System.out.println("entra");
                    llenarMatriz();
                    simbolo=tipo=valor=null;
                }
                return;
            }
            
            switch (elemento) {
                case DATA-> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n");
                    cadena.add(result);
                    if(data==null&&data!="desactivado")
                        data="activado"; 
                }
                
                case Simbolo -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Simbolo\n");
                    cadena.add(result);
                    if(data=="activado"&&simbolo==null)
                        simbolo=maeLex.maeLexMe;
                    else
                        simbolo=tipo=valor=null;
                }
                
                case EQU,DB,DW -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n");
                    cadena.add(result);
                    if(data=="activado" && simbolo!=null && tipo==null)
                        tipo=maeLex.maeLexMe;
                    else if(tipo!=null)
                        simbolo=tipo=null;
                }
                
                case ConstanteDec -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Decimal\n");
                    cadena.add(result);
                    if(data=="activado" && simbolo!=null && tipo!=null)
                        if(valor==null)
                            valor=maeLex.maeLexMe;
                        else if (!valor.contains("DUP") && !valor.contains("dup"))
                            valor += " " + maeLex.maeLexMe;
                        else
                            simbolo=tipo=valor=null;
                    
                    
                }
                
                case ConstanteBin -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Binaria\n");
                    cadena.add(result);
                    if(data=="activado" && simbolo!=null && tipo!=null)
                        if(valor==null)
                            valor=maeLex.maeLexMe;
                }

                case ConstanteHex -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Hexadecimal\n");
                    cadena.add(result);
                    if(data=="activado" && simbolo!=null && tipo!=null)
                        if(valor==null)
                            valor=maeLex.maeLexMe;
                }
                case Cadena -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Caracter\n");
                    cadena.add(result);
                    if(data=="activado" && simbolo!=null && tipo!=null)
                        if(valor==null)
                            valor=maeLex.maeLexMe;
                }
                
                case ENDS-> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n");
                    cadena.add(result);
                    if(data=="activado"){;
                        data="desactivado";
                    }
                }
                
                case DUP-> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n");
                    cadena.add(result);
                    if(data=="activado" && simbolo!=null && tipo!=null && valor!=null){
                        if(!valor.contains("DUP") && !valor.contains("dup")){
                            valor += " " + maeLex.maeLexMe;
                        }
                        
                    }
                }
                
                case STACK,MACRO,ENDM,PROC,ENDP,BYTE_PTR,CODE-> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n");
                    cadena.add(result);
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                }

                case STI,AAM,CLI,RET,STOSB,AAS,IDIV,DIV,MUL,NOT,ADD,LES,OR,SUB,JC,JGE,JNA,JS,LOOPNE,JAE -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n");
                    cadena.add(result);
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                }

                case AX,AH,AL,BX,BH,BL,CX,CH,CL,DX,DH,DL,SI,DI,SP,BP,SS,CS,DS,ES -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Registro\n");
                    cadena.add(result);
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                }

                case Dos_puntos,Comilla_d,Comilla_s,Parentesis_a,Parentesis_c,Corchete_a,Corchete_c -> {
//                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Separador\n");
//                    cadena.add(result);
//result += "    < Separador >\t\t" + maeLex.maeLexMe + "\n";
                }
                case TITLE -> {
                    result = String.format("%-70s%s", maeLex.maeLexMe, "Titulo del programa\n");
                    cadena.add(result);
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                }
//               

                
                case SinComa -> {
                    
                    switch(maeLex.maeLexMe){
                        case "AX","AH","AL","BX","BH","BL","CX","CH","CL","DX","DH","DL","SI","DI","SP","BP","SS","CS","DS","ES",
                                "ax","ah","al","bx","bh","bl","cx","ch","cl","dx","dh","dl","si","di","sp","bp","ss","cs","ds","es"-> {
                            result = String.format("%-70s\t%s", maeLex.maeLexMe, "Registro\n");
                        }
                        default -> result = String.format("%-70s\t%s", maeLex.maeLexMe, "Simbolo\n");
                    }
                    cadena.add(result);
                    
                    
//                        result += "    < Constante Cadena >\t\t" + maeLex.maeLexMe + "\n";
                }
                

                case Etiqueta -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Etiqueta\n");
                    cadena.add(result);
                    if(data!=null||simbolo!=null||tipo!=null)
                        simbolo=tipo=null;
//                        result += "    < Etiqueta >\t\t" + maeLex.maeLexMe + "\n";
                }
                case ERROR,Separadores -> {
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "No Identificado\n");
                    cadena.add(result);
                    if(data!=null||simbolo!=null||tipo!=null)
                        simbolo=tipo=null;
                }
                //default -> result += "  < Elemento No Identificado >\t\t " + maeLex.maeLexMe + " \t\n";
                }
            //}
            
            
            
        }
    }
    
    static void llenarMatriz(){
        String tamaño;
        boolean val = false;
        if(tipo.equals("DB")||tipo.equals("db"))
            tamaño = "Byte";
        else
            tamaño = "Word";
        
        if(tipo.equals("EQU")||tipo.equals("equ"))
            tipo = "Constante";
        else
            tipo = "Variable";
        for (int i = 0; i < tabla.size(); i++) {
            if(tabla.get(i)[0].contains(simbolo)){
                val = true;
            }
            
        }
        if (!val) {
            String [] fila = new String[]{simbolo,tipo,valor,tamaño};
            tabla.add(fila);
        }
        
    }
    
    
}
