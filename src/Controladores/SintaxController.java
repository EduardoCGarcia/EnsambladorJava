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
//    public static Inicio winIni = new Inicio();
    public static void mostrar(){
        winSin.txtAnalisisSintactico.setText(Paginacion.renglonesInicial(resultSin));
        winSin.setVisible(true);
    }
    public static void ocultar(){winSin.setVisible(false);}
    
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
        String cadSinComa;
        MAESintax s = null;
        Symbol symb;
        for (String sim : simbolos) {
            System.out.println(sim);
        }
        simbolos.clear();
        /*Ejecutamos el analizador lexico debido a que necesitamos llenar los renglones si no nos manda un nullpointer exception*/
        try {
                LexicoController.analizarLexico(txtarchivo);
        } catch (IOException ex) {
            Logger.getLogger(SintaxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Separamos por saltos de linea las el contenido total del texto que se obtiene del archivo*/
        separatxtArchivo(txtarchivo);
        //simbolos.clear();//vaciamos los simbolos para volver a llenarlos pero esta vez renglon por renglon
        /*Debemos inicializar el data ya que si no podria estar leyendo los datos simbolos y no guardandolos
        si no se llegaa abrir el data segment por obvias razones no deberia poderse declarar ningun dato*/
        data=null;
        for (int i = 0; i < renglones.size(); i++) {
            existSimbol = false;//Decimos que el simbolo no existe hasta que entre al analizador en donde puede cambiar dependiendo de si el simbolo se esta repitiendo
            if(!renglones.get(i).isEmpty() || renglones.get(i).isBlank() || renglones.get(i).startsWith("\n")){
                try {
                    s = new MAESintax(new MAELEXCup((new StringReader(renglones.get(i)))));//Analiza renglon por renglon
                    
                    LexicoController.analizarLexico(renglones.get(i));//mandamos un renglon para saber si el simbolo existe
                    /*Primero preguntamos si el simbolo ya existe antes de hacer el analisis sintactico*/
                    s.parse();/*Si el simbolo ya existe pero esta mal escrito algo sintacticamente te manda al catch ya que no se esta definiendo tal cual como un simbolo*/
                    //symb = s.getS();
                    if (existSimbol==true) {//si el simbolo ya existia en el arreglo simbolos manda un error
                        
                        resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[ REPETIDO ]\n");
                        existSimbolSem = true;
                        System.out.println("Sombolo repetido");
                    }else{
                        /*Si el simbolo no se repite no hay problema y manda un correcto en caso de que lo sea en el analisis sintactico*/
                        resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");   
                    }
                } catch (Exception ex) {
                    symb = s.getS();
                    if(symb.value==null){/*value es lo que nos da el usuario pero en algunas 
                        ocaciones puede llegar a ser nulo por eso le mandamos lo que tenemos 
                        en todo el renglon como un error general*/
                        symb.value = renglones.get(i);
                    }
                    cadSinComa = (String) symb.value;
                    if(cadSinComa.contains(",")){/*En algunas ocaciones tiene una coma pegada a los errores
                        y es importante eliminarla por lo tanto reasignamos el valor d cadSinComa a value*/
                        cadSinComa = cadSinComa.replace(",", "");
                        symb.value = cadSinComa;
                    }
                    if(renglones.get(i).endsWith("\t"))
                        renglones.get(i).replace("\t", "");
                    if(renglones.get(i).length()>1)/*Con esto nos permitimos eliminar los saltos de linea
                        que aparecen y son analizador pero no deberian ser mostrados*/
                        resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[" + symb.value + "]\n");
                }
            }
        }
        

    }

}
