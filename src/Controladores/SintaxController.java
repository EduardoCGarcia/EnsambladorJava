/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesFlexCup.MAESintax;
import ProgramaPrincipal.Inicio;
import static ProgramaPrincipal.Lexico.*;
import ProgramaPrincipal.Sintactico;
import ProgramaPrincipal.TablaDeSimbolos;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
/**
 *
 * @author eduar
 */
public class SintaxController {
    public static ArrayList<String> renglones = new ArrayList<String>();
    public static ArrayList<String> resultSin = new ArrayList<String>();
    public static Sintactico winSin = new Sintactico();
    public static boolean existSimbol;
    public static boolean existSimbolSem;
    public  static boolean existEtiq;
//    public static Inicio winIni = new Inicio();
    public static void mostrar(){
        winSin.txtAnalisisSintactico.setText(Paginacion.renglonesInicial(resultSin));
        winSin.setVisible(true);
    }
    public static void ocultar(){winSin.setVisible(false);}
    
    public static void valTamInst(int i){
        if(renglones.get(i).toUpperCase().startsWith("ADD ")){
            if(tamreg2==-1){
                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
            }else{
                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ LOS OPERANDOS NO SON LOS CORRECTOS ]\n");
            }
            
            
//            if(renglones.get(i).toUpperCase().contains("X") && renglones.get(i).toUpperCase().contains("L")){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else if(renglones.get(i).toUpperCase().contains("X") && renglones.get(i).toUpperCase().contains("H")){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else if(
//                    (renglones.get(i).toUpperCase().contains("H") || renglones.get(i).toUpperCase().contains("L")) && 
//                    (renglones.get(i).toUpperCase().contains("SI") || renglones.get(i).toUpperCase().contains("DI") || renglones.get(i).toUpperCase().contains("BP") || renglones.get(i).toUpperCase().contains("SP"))){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else{
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ ERROR EN LOS OPERANDOS ]\n");
//            }
        }
        if(renglones.get(i).toUpperCase().startsWith("LES ")){
            resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ LOS OPERANDOS NO SON LOS CORRECTOS ]\n");
            
            
            
            
//            if(renglones.get(i).toUpperCase().contains("X") && renglones.get(i).toUpperCase().contains("L")){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ LOS OPERANDOS SON INCORRECTOS ]\n");
//            }else if(renglones.get(i).toUpperCase().contains("X") && renglones.get(i).toUpperCase().contains("H")){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ LOS OPERANDOS SON INCORRECTOS ]\n");
//            }else{
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ ERROR EN LOS OPERANDOS ]\n");
//            }
        }
        if(renglones.get(i).toUpperCase().startsWith("OR ")){
            if(tamreg2==-1){
                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
            }else{
                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ LOS OPERANDOS NO SON LOS CORRECTOS ]\n");
            }
            
            
//            if(renglones.get(i).toUpperCase().contains("X") && renglones.get(i).toUpperCase().contains("L")){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else if(renglones.get(i).toUpperCase().contains("X") && renglones.get(i).toUpperCase().contains("H")){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else if(
//                    (renglones.get(i).toUpperCase().contains("H") || renglones.get(i).toUpperCase().contains("L")) && 
//                    (renglones.get(i).toUpperCase().contains("SI") || renglones.get(i).toUpperCase().contains("DI") || renglones.get(i).toUpperCase().contains("BP") || renglones.get(i).toUpperCase().contains("SP"))){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else{
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ ERROR EN LOS OPERANDOS ]\n");
//            }
        }
        if(renglones.get(i).toUpperCase().startsWith("SUB ")){
            if(tamreg2==-1){
                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
            }else{
                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ LOS OPERANDOS NO SON LOS CORRECTOS ]\n");
            }
//            }
//            if(renglones.get(i).toUpperCase().contains("X") && renglones.get(i).toUpperCase().contains("L")){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else if(renglones.get(i).toUpperCase().contains("X") && renglones.get(i).toUpperCase().contains("H")){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else if(
//                    (renglones.get(i).toUpperCase().contains("H") || renglones.get(i).toUpperCase().contains("L")) && 
//                    (renglones.get(i).toUpperCase().contains("SI") || renglones.get(i).toUpperCase().contains("DI") || renglones.get(i).toUpperCase().contains("BP") || renglones.get(i).toUpperCase().contains("SP"))){
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
//            }else{
//                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ ERROR EN LOS OPERANDOS ]\n");
//            }
        }
    }
    
    public static void separatxtArchivo(String txtarchivo){
        /*Obtiene el texto del archivo que se ecuentra en te textArea donde se abre el archivo
        Con ese texto haciendo uso de spit separa toda la cadena(texto) por saltos de linea
        es decir obtiene renglon por renglon y los guarda en un arrayList*/
        renglones.clear();
        String str = txtarchivo;
        String[] str2 = str.split("\n");
        for (int i = 0; i < str2.length; i++) {
            renglones.add(str2[i]);
        }
        eliminaComents();
    }
    
    public static void eliminaComents(){
        /*Para cada uno de los renglones que ya han sido separados
        se eliminan los comentarios en caso de tenerlos*/
        for (int i = 0; i < renglones.size(); i++) {
            if(renglones.get(i).contains(";"))
                renglones.set(i, renglones.get(i).substring(0, renglones.get(i).indexOf(";")));
        }
    }

    public static void analisisSintax(String txtarchivo){
        resultSin.clear();
        tabla.clear();
        simbolos.clear();
        //etiquetas.clear();
        String cadSinComa;
        MAESintax s = null;
        Symbol symb;
        simbolos.clear();
        /*Ejecutamos el analizador lexico debido a que necesitamos llenar los renglones si no nos manda un nullpointer exception*/
        try {
                TablaController.llenarTabla(txtarchivo, TablaDeSimbolos.tableSym);
                //LexicoController.analizarLexico(txtarchivo);
        } catch (IOException ex) {
            Logger.getLogger(SintaxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        simbolos.clear();
        tabla.clear();
        /*Separamos por saltos de linea las el contenido total del texto que se obtiene del archivo*/
        separatxtArchivo(txtarchivo);
        //simbolos.clear();//vaciamos los simbolos para volver a llenarlos pero esta vez renglon por renglon
        /*Debemos inicializar el data ya que si no podria estar leyendo los datos simbolos y no guardandolos
        si no se llegaa abrir el data segment por obvias razones no deberia poderse declarar ningun dato*/
        data=null;
        for (int i = 0; i < renglones.size(); i++) {
            if(renglones.get(i).contains(",")){
                renglones.set(i,renglones.get(i).replace(",", " , "));
            }
            saltoVal = false;
            existSimbol = false;//Decimos que el simbolo no existe hasta que entre al analizador en donde puede cambiar dependiendo de si el simbolo se esta repitiendo
            if(!renglones.get(i).isEmpty() || renglones.get(i).isBlank() || renglones.get(i).startsWith("\n")){
                try {
                    s = new MAESintax(new MAELEXCup((new StringReader(renglones.get(i)))));//Analiza renglon por renglon

                    LexicoController.analizarLexico(renglones.get(i));//mandamos un renglon para saber si el simbolo existe
                    /*Primero preguntamos si el simbolo ya existe antes de hacer el analisis sintactico*/
                    s.parse();/*Si el simbolo ya existe pero esta mal escrito algo sintacticamente te manda al catch ya que no se esta definiendo tal cual como un simbolo*/
                    //symb = s.getS();
                    
                    if(renglones.get(i).toUpperCase().contains("JC") || renglones.get(i).toUpperCase().contains("JGE") || renglones.get(i).toUpperCase().contains("JNA") || renglones.get(i).toUpperCase().contains("JS") || renglones.get(i).toUpperCase().contains("LOOPNE") || renglones.get(i).toUpperCase().contains("JAE")){
                        if(saltoVal){
                            System.out.println(renglones.get(i));
                            resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                        }else{
                            resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ NO EXISTE LA ETIQUETA USADA ]\n");
                        }
                    }else if(renglones.get(i).toUpperCase().contains("IDIV") || renglones.get(i).toUpperCase().contains("DIV") || renglones.get(i).toUpperCase().contains("MUL") || renglones.get(i).toUpperCase().contains("NOT")){
                        System.out.println("Instruccion = " + existSimbol);
                        if (existSimbol){
                            System.out.println("el simbolo para la instruccion si existe");
                            resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                        }else{
                            resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ NO EXISTE LA VARIABLE ]\n");
                        }
                    }else{
                        if(renglones.get(i).toUpperCase().startsWith("LES ")){
                            if(var2==10){
                                resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                            }else if(var2 == -1){
                                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
                            }else if(var2 == -2){
                                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ EL SIMBOLO NO EXISTE  ]\n");
                            }else{
                                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ LOS OPERANDOS NO SON LOS CORRECTOS ]\n");
                            }

                        }else if(renglones.get(i).toUpperCase().startsWith("OR ") || renglones.get(i).toUpperCase().startsWith("SUB ")){
                            if(sim || tamreg==-2){
                                if(var2 == -1 && tamreg == -2){
                                    resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ EL SIMBOLO NO EXISTE  ]\n");
                                }else if(tamreg==-1){
                                    resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
                                }else{
                                    resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                                }
                            }else if(var2 != 0){
                                if(var2==10){
                                    resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                                }else if(var2 == -1){
                                    resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ TAMAÑO DE LOS OPERANDOS INCORRECTO ]\n");
                                }else if(var2 == -2){
                                    resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ EL SIMBOLO NO EXISTE  ]\n");
                                }else{
                                    resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ LOS OPERANDOS NO SON LOS CORRECTOS ]\n");
                                }
                            }else{
                                resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                            }
                            
                        }else{
                            if (existSimbol==true) {//si el simbolo ya existia en el arreglo simbolos manda un error

                                resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ REPETIDO ]\n");
                                existSimbolSem = true;
                            }else{
                                /*Si el simbolo no se repite no hay problema y manda un correcto en caso de que lo sea en el analisis sintactico*/
                                resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                            }
                        }
                    }        
                } catch (Exception ex) {
                    symb = s.getS();
                    if(symb.value==null){/*value es lo que nos da el usuario pero en algunas 
                        ocaciones puede llegar a ser nulo por eso le mandamos lo que tenemos 
                        en todo el renglon como un error general*/
                        symb.value = renglones.get(i);
                    }
//                    cadSinComa = (String) symb.value;
//                    if(cadSinComa.contains(",")){/*En algunas ocaciones tiene una coma pegada a los errores
//                        y es importante eliminarla por lo tanto reasignamos el valor d cadSinComa a value*/
//                        cadSinComa = cadSinComa.replace(",", "");
//                        symb.value = cadSinComa;
//                    }
//                    if(renglones.get(i).endsWith("\t"))
//                        renglones.get(i).replace("\t", "");
                    
                    if(renglones.get(i).toUpperCase().contains("ADD") || renglones.get(i).toUpperCase().contains("LES") || renglones.get(i).toUpperCase().contains("OR") || renglones.get(i).toUpperCase().contains("SUB")){
                        if(renglones.get(i).contains("'$'")){
                            resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                        }else if(code!=null){
                            valTamInst(i);
                        }else{
                            System.out.println("Code desactivado");
                        }
                        
                    }else  if(renglones.get(i).length()<=1){
                        resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[" + renglones.get(i) + "]\n");
                    }else{
                        resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[" + renglones.get(i) + "]\n");
                    }
                    
                }
            }
        }
        

    }

}
