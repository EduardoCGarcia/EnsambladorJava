/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generadores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author eduar
 */
public class GenerarLexSintax {
    public static void generarLexSin(String rutaLex,String rutaLexCup,String[] rutaSintax) throws IOException, Exception{
        File archivo;
        archivo = new File(rutaLex);
        JFlex.Main.generate(archivo);
        
        archivo = new File(rutaLexCup);
        JFlex.Main.generate(archivo);
        
        java_cup.Main.main(rutaSintax);
        
        moverMAELEX();
        moverMAELEXCup();
        moverSym();
        moverSintax();
    }
    
    public static void crearSemantico(String rutaCUP,String[] rutaSintaxSem) throws IOException, Exception{
        File archivo;
        archivo = new File(rutaCUP);
        JFlex.Main.generate(archivo);
        
        java_cup.Main.main(rutaSintaxSem);
        
        moverMAELEXSEMCup();
        moverSym2();
        moverMAESemantico();
    }
    
    public static void moverMAELEXSEMCup() throws IOException{
        Path rutSym = Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAELEXSEMCup.java");
        if (Files.exists(rutSym)) {
            Files.delete(rutSym);
        }
        Files.move(
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ArchivosFlexCup/MAELEXSEMCup.java"), 
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAELEXSEMCup.java" )
        );
    }
    public static void moverMAESemantico() throws IOException{
        Path rutSym = Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAESemantico.java");
        if (Files.exists(rutSym)) {
            Files.delete(rutSym);
        }
        Files.move(
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/MAESemantico.java"), 
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAESemantico.java" )
        );
    }
    
    public static void moverSym2() throws IOException{
        Path rutSym = Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/sym.java");
        if (Files.exists(rutSym)) {
            Files.delete(rutSym);
        }
        Files.move(
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/sym.java"), 
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/sym.java" )
        );
    }
    
    public static void moverSym() throws IOException{
        Path rutSym = Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/sym.java");
        if (Files.exists(rutSym)) {
            Files.delete(rutSym);
        }
        Files.move(
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/sym.java"), 
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/sym.java" )
        );
    }
    public static void moverSintax() throws IOException{
        Path rutSym = Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAESintax.java");
        if (Files.exists(rutSym)) {
            Files.delete(rutSym);
        }
        Files.move(
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/MAESintax.java"), 
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAESintax.java" )
        );
    }
    public static void moverMAELEX() throws IOException{
        Path rutSym = Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAELEX.java");
        if (Files.exists(rutSym)) {
            Files.delete(rutSym);
        }
        Files.move(
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ArchivosFlexCup/MAELEX.java"), 
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAELEX.java" )
        );
        System.out.println("MAELEX movido");
    }
    public static void moverMAELEXCup() throws IOException{
        Path rutSym = Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAELEXCup.java");
        if (Files.exists(rutSym)) {
            Files.delete(rutSym);
        }
        Files.move(
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ArchivosFlexCup/MAELEXCup.java"), 
                Paths.get("D:/Ensamblador/ProyectoEnsamblador/src/ClasesFlexCup/MAELEXCup.java" )
        );
    }
    
    
}
