/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ProgramaPrincipal.Semantico;
import java.awt.Color;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;

/**
 *
 * @author eduar
 */
public class SemanticoController {
    static Semantico winSe = new Semantico();
    
    public static void mostrar() throws IOException{winSe.setVisible(true);}
    
    public static void ocultar(){winSe.setVisible(false);}
    
    public static void analisisSemantico(String ST){
        MAESemantico s = new MAESemantico(new MAELEXSEMCup((new StringReader(ST))));
        try {
            s.parse();
            winSe.txtAnalisisSemantico.setText("Felicidades!!!\n No se detecta ningun error");
            winSe.txtAnalisisSemantico.setForeground(new Color(25,111,61));
        } catch (Exception ex) {
            Symbol sym = s.getS();
            if (sym.value==null) {
                sym.value = "Se esperaba algo mas en la linea";
            }
            winSe.txtAnalisisSemantico.setText("ERROR\n Linea: "+(sym.right+1)+"\n ERROR = " + sym.value);
            winSe.txtAnalisisSemantico.setForeground(Color.red);
        }
        
    }
}
