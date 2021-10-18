/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesFlexCup.MAESintax;
import ProgramaPrincipal.Inicio;
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
    public static Inicio winIni = new Inicio();
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
        String cadSinComa;
        MAESintax s = null;
        Symbol symb;
        try {
            LexicoController.analizarLexico(winIni.txtArchivo.getText());
        } catch (IOException ex) {
            Logger.getLogger(SintaxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        separatxtArchivo(txtarchivo);
        for (int i = 0; i < renglones.size(); i++) {
            if(!renglones.get(i).isEmpty() || renglones.get(i).isBlank() || renglones.get(i).startsWith("\n")){
                try {
                    s = new MAESintax(new MAELEXCup((new StringReader(renglones.get(i)))));
                    s.parse();
                    symb = s.getS();
                    resultSin.add(renglones.get(i) + "\t\t[<-C O R R E C T O->]" + "\n");
                } catch (Exception ex) {
                    symb = s.getS();
                    if(symb.value==null){
                        symb.value = renglones.get(i);
                    }
                    cadSinComa = (String) symb.value;
                    if(cadSinComa.contains(",")){
                        cadSinComa = cadSinComa.replace(",", "");
                        symb.value = cadSinComa;
                    }
                    if(renglones.get(i).endsWith("\t"))
                        renglones.get(i).replace("\t", "");
                    if(renglones.get(i).length()>1)
                        //resultSin.add(String.format("%s \t %s \t ERROR[ %s ] \n", renglones.get(i),"\t-------- I N C O R R E C T O --------",symb.value));
                        resultSin.add(renglones.get(i) + "\t\t[<-I N C O R R E C T O->]" + "-->ERROR[" + symb.value + "]\n");
                }
            }
        }
        

    }

}
