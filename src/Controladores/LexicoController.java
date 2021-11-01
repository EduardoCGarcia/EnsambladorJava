/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesFlexCup.Elementos;
import ProgramaPrincipal.Lexico;
import static ClasesFlexCup.Elementos.*;
import static Controladores.SintaxController.existSimbol;
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
    
    static int posicion;
    
    public static void analizarLexico(String lec) throws IOException {
        cadena.clear();
        simbolos.clear();
        
        //String lec = (String) txtArchivo.getText();//Todo lo que se lee del archivo y que cae en txtArchivo
        MAELEX maeLex = new MAELEX(new java.io.StringReader(lec));//Se lo colocamos al maeLex porque es el que va a hacer el analisis lexico
        String result = "";
        simbolo=tipo=valor=etiqueta=instSalto=instUOper=null;
        add = les = or = sub = sim = err = valid = false;
        tamreg = tamreg2 = var2 = 0;
        while (true) {
            Elementos elemento = maeLex.yylex();
            if (elemento == null) {
                /*Si el data esta activado y los componentes de delcaracion de definicion de una varible estan activos
                llenamos la matriz que sera cargada en la tabla de datos
                si no esta activo el segmento de datos pregunta si el de codigo esta activo de ser asi solo comprueba si una
                etiqueta a sido leida de ser asi tambien sera enviada a la tabla de simbolos*/
                if(data=="activado" && simbolo!=null && tipo!=null && valor!=null){
                    llenarMatrizSimbolos();
                    /*Reiniciamos todos los datos leido*/
                    simbolo=tipo=valor=etiqueta=null;
                }
                if(code=="activado" && etiqueta!=null){
                    llenarMatrizEtiquetas();
                    simbolo=tipo=valor=etiqueta=null;
                }
                return;
            }     
                    
            switch (elemento) {
                case DATA-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    /*activamos el segmento de datos*/
                    if(data==null&&data!="desactivado")
                        data="activado";
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }
                case CODE-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if(code==null&&code!="desactivado"){
                        code="activado"; 
                        
                    }
                }
                
                case Simbolo -> {
                    if(maeLex.maeLexMe.length()<=10 && maeLex.maeLexMe.length()>=1 ){
                        cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Simbolo\n"));
                        if(data=="activado"&&simbolo==null)
                            simbolo=maeLex.maeLexMe;
                        else
                            simbolo=tipo=valor=null;
                        
                        if(instSalto=="activado"){
                            System.out.println("Sintaxis de salto correcta validando si existe el simbolo");
                            saltoVal=existInEtiqs(maeLex.maeLexMe);
                        }else if(instUOper == "activado"){
                            existSimbol = existInTable(maeLex.maeLexMe);
                        }
                        if((add || les || or || sub) && tamreg==0 && sim == false){//cuando empieza la instruccion son un operando en memoroia
                            if(existInTable(maeLex.maeLexMe)){//Comprobamos si existe en la tabla de simbolos
                                System.out.println("Encuentra el simbolo dentro de la tabla");
                                if(tabla.get(posicion)[3].equals("Byte")){
                                    //El tipo Byte no se puede guardar en un reg de 16
                                    var2 = 8;
                                }else if(tabla.get(posicion)[3].equals("Word")){
                                    var2 = 16;
                                }
                                sim = true;
                            }else{
                                System.out.println("no encontro el simbolo dentro de la tabla");
                                var2=-1;
                            }
                        }else if((add || les || or || sub) && tamreg==16){
                            if(existInTable(maeLex.maeLexMe)){
                                if(tabla.get(posicion)[3].equals("Byte")){
                                    //El tipo Byte no se puede guardar en un reg de 16
                                    var2 = -1;
                                }else if(tabla.get(posicion)[3].equals("Word")){
                                    var2 = 10;
                                }
                            }else{
                                //La varibale no existe
                                var2=-2;
                            }
                        }
                        else if((add || les || or || sub) && tamreg==8){
                            if(existInTable(maeLex.maeLexMe)){
                                if(tabla.get(posicion)[3].equals("Byte")){
                                    //El tipo Byte no se puede guardar en un reg de 16
                                    var2 = 10;
                                }else if(tabla.get(posicion)[3].equals("Word")){
                                    var2 = -1;
                                }
                            }else{
                                //La varibale no existe
                                var2=-2;
                            }
                        }
                        
                    }else{
                        cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Longitud > 10\n"));
                    }
                    
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }
                
                case EQU,DB,DW -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if(data=="activado" && simbolo!=null && tipo==null)
                        tipo=maeLex.maeLexMe;
                    else if(tipo!=null)
                        simbolo=tipo=null;
                    
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }
                
                case ConstanteDec -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Decimal\n"));
                    if(data=="activado" && simbolo!=null && tipo!=null)
                        if(valor==null)
                            valor=maeLex.maeLexMe;
                        else if (!valor.contains("DUP") && !valor.contains("dup"))
                            valor += " " + maeLex.maeLexMe;
                        else
                            simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    if((or || sub) && tamreg == 8){
                        if(Integer.parseInt(maeLex.maeLexMe) >= 255){
                            sim = true;
                            valid=true;
                            
                        }
                    }
                }
                
                case ConstanteBin -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Binaria\n"));
                    if(data=="activado" && simbolo!=null && tipo!=null)
                        if(valor==null)
                            valor=maeLex.maeLexMe;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    
                }

                case ConstanteHex -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Hexadecimal\n"));
                    if(data=="activado" && simbolo!=null && tipo!=null)
                        if(valor==null)
                            valor=maeLex.maeLexMe;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }
                case Cadena -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Caracter\n"));
                    if(data=="activado" && simbolo!=null && tipo!=null)
                        if(valor==null)
                            valor=maeLex.maeLexMe;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }
                
                case ENDS-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if(data=="activado"){;
                        data="desactivado";
                    }else if(code=="activado")
                        code="desactivado";
                }
                
                case DUP-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if(data=="activado" && simbolo!=null && tipo!=null && valor!=null){
                        if(!valor.contains("DUP") && !valor.contains("dup")){
                            valor += " " + maeLex.maeLexMe;
                        }
                        
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }
                
                case STACK,MACRO,ENDM,PROC,ENDP,BYTE_PTR-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }

                case STI,AAM,SLI,RET,STOSB,AAS-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }
                case ADD-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    add = true;
                    
                }
                case LES-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    les = true;
                }
                case OR-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    or = true;
                }
                case SUB-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    sub = true;
                }
                case IDIV,DIV,MUL,NOT-> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    if(code!=null){
                        instUOper = "activado";
                    }
                }
                case JC,JGE,JNA,JS,LOOPNE,JAE -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    if(code!=null){
                        System.out.println("Encontro un salto");
                        instSalto = "activado";
//                        saltoVal = true;
//                        System.out.println("Encontro la etiqueta en en la tabla");
                    }
                        
                }

                case AX,BX,CX,DX,SI,DI,SP,BP,SS,CS,DS,ES -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Registro\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    if(!sim){//no empieza con un simbolo
                        if(var2==-1){
                            tamreg = -2;
                        }else if((add || les || or || sub) && tamreg==0)
                            tamreg = 16;
                        else if((add || les || or || sub) && tamreg==16)
                            tamreg2=16;
                        else if((add || les || or || sub) && tamreg==8)
                            tamreg2=-1;
                    }else{
                        if(var2 == 8){
                            //tamaños incorrectos
                            tamreg = -1;
                        }else if(var2 == 16){
                            tamreg = 16;
                        }else{
                            //simbolo no encontrado
                            System.out.println("registro incorrecto porque no encontro el simbolo");
                            tamreg = -2;
                        }
                    
                    }
                    if(instUOper=="activado"){
                        existSimbol = true;
                    }
                    
                }
                case AH,AL,BH,BL,CH,CL,DH,DL -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Registro\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    if(!sim){
                        if((add || les || or || sub) && tamreg==0)
                            tamreg = 8;
                        else if((add || les || or || sub) && tamreg==8)
                            tamreg2=8;
                        else if((add || les || or || sub) && tamreg==16)
                            tamreg2=-1;
                    }else{
                        if(var2 == 8){
                            tamreg = 8;
                        }else if(var2 == 16){
                            //tamaños incorrectos
                            tamreg = -1;
                        }else{
                            //no se encontro el simbolo
                            tamreg = -2;
                        }
                    }
                    if(instUOper=="activado"){
                        existSimbol = true;
                    }
                }

                case Dos_puntos,Comilla_d,Comilla_s,Parentesis_a,Parentesis_c,Corchete_a,Corchete_c -> {
//                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Separador\n");
//                    cadena.add(result);
//result += "    < Separador >\t\t" + maeLex.maeLexMe + "\n";
                }
                case TITLE -> {
                    cadena.add(String.format("%-70s%s", maeLex.maeLexMe, "Titulo del programa\n"));
                    if(simbolo!=null || tipo!=null)
                        simbolo=tipo=valor=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                }
                
                case SinComa -> {
                    switch(maeLex.maeLexMe){
                        case "AX","AH","AL","BX","BH","BL","CX","CH","CL","DX","DH","DL","SI","DI","SP","BP","SS","CS","DS","ES",
                                "ax","ah","al","bx","bh","bl","cx","ch","cl","dx","dh","dl","si","di","sp","bp","ss","cs","ds","es"-> {
                            result = String.format("%-70s\t%s", maeLex.maeLexMe, "Registro\n");
                        }
                        default -> result = String.format("%-70s\t%s", maeLex.maeLexMe, "Simbolo\n");
                    }
                    cadena.add(result);
                }
                
                case Etiqueta -> {
                    /*Las etiquetas deben tener una longitud menor o igual a 10 y mayor a 1*/
                    if(maeLex.maeLexMe.length()<=10 && maeLex.maeLexMe.length()>=1 ){
                        cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Etiqueta\n"));
                        if(data!=null||simbolo!=null||tipo!=null){
                            simbolo=tipo=null;
                        }
                        if(code=="activado" && etiqueta==null){
                            etiqueta = maeLex.maeLexMe;
                        }
                        
                        /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
//                        if(code!="desactivado" && etiqueta!=null)
//                            etiqueta=null;
                        
                    }else{
                        cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Longitud > 10\n"));
                        
                    }
                    
//                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Etiqueta\n");
//                    cadena.add(result);
//                    if(data!=null||simbolo!=null||tipo!=null)
//                        simbolo=tipo=null;
//                    if(code!=null||etiqueta==null)
//                        etiqueta = maeLex.maeLexMe;
                    
                }
                case ERROR,Separadores -> {
                    if(maeLex.maeLexMe.contains(".")){
                        maeLex.maeLexMe = maeLex.maeLexMe.replace(".", "");
                    }
                    if(maeLex.maeLexMe.contains(",")){
                        maeLex.maeLexMe = maeLex.maeLexMe.replace(",", "");
                    }
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "No Identificado\n");
                    cadena.add(result);
                    if(data!=null||simbolo!=null||tipo!=null)
                        simbolo=tipo=null;
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if(code!="desactivado" && etiqueta!=null)
                        etiqueta=null;
                    err = true;
                    if((or || sub) && tamreg == 8 ){
                        if(maeLex.maeLexMe.length() < 9 || maeLex.maeLexMe.length() > 9 ){
                            sim = true;
                            valid=true;
                        }
                    }else if((or || sub) && tamreg == 16 ){
                        if(maeLex.maeLexMe.length() < 17 || maeLex.maeLexMe.length() > 17 ){
                            sim = true;
                            valid=true;
                        }
                    }
                }
                //default -> result += "  < Elemento No Identificado >\t\t " + maeLex.maeLexMe + " \t\n";
            }
        }
    }
    
    static void llenarMatrizSimbolos(){
        String tamaño = "";
        boolean val = false;
        if(tipo.equals("DB")||tipo.equals("db"))
            tamaño = "Byte";
        else if(tipo.equals("DW")||tipo.equals("dw"))//*************************
            tamaño = "Word";
        
        if(tipo.equals("EQU")||tipo.equals("equ"))
            tipo = "Constante";
        else
            tipo = "Variable";
        
        //val = existInTable(simbolo);
        val = existInTable(simbolo);
        
        if (!val) {//Si el simbolo existe en a tabla lo añade
            String [] fila = new String[]{simbolo,tipo,valor,tamaño};
            tabla.add(fila);
            simbolos.add(simbolo);//Guardamos solo el simbolo que es valido
        }
        existSimbol = val;
       //Si el simbolo no es añadido a la tabla significa que val=true entonces debemos mandar mensaje de error en el sintactico
    }
    static void llenarMatrizEtiquetas(){
        boolean val = false;
        tipo = "Etiqueta";
        
        val = existInTable(etiqueta);
        if (!val) {//Si LA etiqueta no existe en a tabla lo añade
            String [] fila = new String[]{etiqueta,tipo,"",""};
            tabla.add(fila);
            etiquetas.add(etiqueta);//Guardamos solo el simbolo que es valido
            
        }
        //Si el simbolo no es añadido a la tabla significa que val=true entonces debemos mandar mensaje de error en el sintactico
        
    }
    
    public static boolean existInTable(String buscado){
        
        for (posicion = 0; posicion < tabla.size(); posicion++) {
            if(tabla.get(posicion)[0].equals(buscado)){
                //System.out.println(tabla.get(i)[0]);
                return true;
            }
        }
        return false;
    }
    public static boolean existInEtiqs(String buscado){
        
        for (int i = 0; i < etiquetas.size(); i++) {
            if(etiquetas.get(i).equals(buscado)){
                return true;
            }
        }
        return false;
    }
    public static boolean existInSims(String buscado){
        
        for (int i = 0; i < simbolos.size(); i++) {
            if(simbolos.get(i).equals(buscado)){
                return true;
            }
        }
        return false;
    }
    
   
    
}
