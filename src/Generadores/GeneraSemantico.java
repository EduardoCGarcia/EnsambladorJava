/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generadores;

import static Generadores.GenerarLexSintax.crearSemantico;

/**
 *
 * @author eduar
 */
public class GeneraSemantico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String rutaCUP = "D:/Ensamblador/ProyectoEnsamblador/src/ArchivosFlexCup/MAELEXSEMCup.flex";
        String[] rutaSintaxSem = {"-parser","MAESemantico","D:/Ensamblador/ProyectoEnsamblador/src/ArchivosFlexCup/MAESemantico.cup"};
        crearSemantico(rutaCUP,rutaSintaxSem);
    }
    
}
