/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generadores;

/**
 *
 * @author eduar
 */
public class LexicoSintactico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String rutaFlex = "D:/Ensamblador/ProyectoEnsamblador/src/ArchivosFlexCup/MAELEX.flex";
        String rutaLexCup = "D:/Ensamblador/ProyectoEnsamblador/src/ArchivosFlexCup/MAELEXCup.flex";
        String[] rutaSintax = {"-parser","MAESintax","D:/Ensamblador/ProyectoEnsamblador/src/ArchivosFlexCup/MAESintax.cup"};
        
        GenerarLexSintax.generarLexSin(rutaFlex, rutaLexCup, rutaSintax);
    }
    
}
