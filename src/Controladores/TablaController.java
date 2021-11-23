/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.LexicoController.winLex;
import static Controladores.SintaxController.*;
import ProgramaPrincipal.Lexico;
import static ProgramaPrincipal.Lexico.*;
import ProgramaPrincipal.TablaDeSimbolos;
import static ProgramaPrincipal.TablaDeSimbolos.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author eduar
 */
public class TablaController {
    public static int contadorEtiqueta;
    public static String contadorHexaEtiqueta;
    public static String contadorAnteriorHexaEtiqueta;
    
    static TablaDeSimbolos winTable = new TablaDeSimbolos();
    public static void mostrar() throws IOException{
        winTable.setVisible(true);
    }
    public static void ocultar(){winTable.setVisible(false);}
    /*llenar tabla debe recibir el objeto de tipo TablaDeSimbolos para poder editar la tabla*/
    public static void llenarTabla(String txtarchivo,JTable table) throws IOException{
        /*Primero limipiamos los ArrayList involucrados para que no se 
        dupli que la info o tengan basura*/
        renglones.clear();
        tabla.clear();
        /*Inicializamos el data para que solo se pueda cerrar desde que comienza
        el analisis y no vuelva a ser null*/
        data = null;
        /*Inicializamos code con la misma finalidad que el data lo unico que tenemos 
        que garantizar es que una vez que el code se active se cerrara con ENDP y no volvera a activarse
        Esa validacion esta directamente en el LexicoController*/
        code = null;
        /*Separamos todo el rengolones para que el analisis de los simbolos solo
        considere lo que se ecuentra en un renglon ya que si tomamos bloques completos
        puede confundirse*/
        SintaxController.separatxtArchivo(txtarchivo);
        /*mandamos al analizador lexico cada linea para que sea analizada, recordar que renglones
        fuarda todo el documento pero en posiciones de un arrayList renglon por renglon
        
        Recordar tambien que el analizador lexico nos hace el llenado del arrayList tabla usando 
        el metodo llenar matriz*/
//        for (String linea : renglones) {
//            LexicoController.analizarLexico(linea);
//        }
        SintaxController.analisisSintax(txtarchivo);
       /*Establecemos el modelo de la tabla cada ve que entre aqui para que no
        se dupliquen los campos*/
        modeloLista =  new DefaultTableModel();
        /*Establecemos las columnas*/
        ArrayList<String> columna = new ArrayList<String> ();
        columna.add("SIMBOLO");
        columna.add("TIPO");
        columna.add("VALOR");
        columna.add("TAMAÑO");
        columna.add("DIRECCION");
        
        for(Object col:columna){
            modeloLista.addColumn(col);
        }
        winTable.tableSym.setModel(modeloLista);
        
        for (String[] vec : tabla){
            modeloLista.addRow(vec);
        }
        winTable.tableSym.setModel(modeloLista);
//        for (String sym : Lexico.simbolos) {
//            System.out.println(sym);
//        }
    }
}
