/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.LexicoController.posicion;
import static Controladores.LexicoController.*;
import static Controladores.SintaxController.existSimbol;
import static ProgramaPrincipal.Lexico.*;

/**
 *
 * @author eduar
 */
public class CodificacionInstrucciones {
   
    public static void instruccionesDosOperandosMemReg(){
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        contadorDecimal = 4;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
    }
    
    public static void instruccionesUnOperandoMem(){
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        contadorDecimal = 2;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        
    }
    
    public static void instruccionesDosOperandos8BitsMemInm(int inm){
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2) + inm;
        contadorDecimal = 2 + 1;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        codigoMaquina += Integer.toHexString(inm);
    }
    public static void instruccionesDosOperandos16BitsMemInm(int inm){
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2) + inm;
        contadorDecimal = 2 + 2 ;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        codigoMaquina += Integer.toHexString(inm);
        
    }
    
    public static void InstruccionSimbolo16Bits(){
        var2 = 10;
        mod = "00";
        rm = "110";
        if(les){
            instruccionesDosOperandosMemReg();
        }else if(add){
            w = "00000011";
            instruccionesDosOperandosMemReg();
        }else if (or){
            w = "00001011";
            instruccionesDosOperandosMemReg();
        }else if (sub){
            w = "00101011";
            instruccionesDosOperandosMemReg();
        }                          
    }
    
    public static void InstruccionSimbolo8Bits(){
        /*
        Al ser Reg,Mem el valor de reg es decir del Reg ya esta asignado en la primera entrada, lo unico que cambia
        es el valor de la codificacion inicial w
        */
        var2 = 10;
        mod = "00";
        rm = "110";
        if(les){
            instruccionesDosOperandosMemReg();
        }else if(add){
            w = "00000010";
            instruccionesDosOperandosMemReg();
        }else if (or){
            w = "00001010";
            instruccionesDosOperandosMemReg();
        }else if (sub){
            w = "00101010";
            instruccionesDosOperandosMemReg();
        }
                                    
    }
    
    public static void analisisRegistros16(String mae) {
        cadena.add(String.format("%-70s\t%s", mae, "Registro\n"));
        if (simbolo != null || tipo != null) {
            simbolo = tipo = valor = null;
        }
        /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
        if (code != "desactivado" && etiqueta != null) {
            etiqueta = null;
        }
        if (!sim) {//no empieza con un simbolo
            if (var2 == -1) {
                tamreg = -2;
            } else if ((add || les || or || sub) && tamreg == 0) {
                tamreg = 16;
            } else if ((add || les || or || sub) && tamreg == 16) {
                tamreg2 = 16;
            } else if ((add || les || or || sub) && tamreg == 8) {
                tamreg2 = -1;
            }
        } else {
            if (var2 == 8) {
                //tamaños incorrectos
                tamreg = -1;
            } else if (var2 == 16) {
                tamreg = 16;
            } else {
                //simbolo no encontrado
                
                tamreg = -2;
            }

        }
        if (instUOper == "activado") {
            existSimbol = true;
        }

        if (code == "activado") {
            if (add) {
                if (mae.toUpperCase().equals("AX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "000";
                        w = "1";
                        reg = "000";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "000";
                        if(var2 == 16){
                            addMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("BX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "011";
                        w = "1";
                        reg = "011";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "011";
                        if(var2 == 16){
                            addMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("CX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "001";
                        w = "1";
                        reg = "001";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "001";
                        if(var2 == 16){
                            addMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("DX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "010";
                        w = "1";
                        reg = "010";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "010";
                        if(var2 == 16){
                            addMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("SP")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "100";
                        w = "1";
                        reg = "100";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "100";
                        if(var2 == 16){
                            addMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("BP")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "101";
                        w = "1";
                        reg = "101";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "101";
                        if(var2 == 16){
                            addMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("SI")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "110";
                        w = "1";
                        reg = "110";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "110";
                        if(var2 == 16){
                            addMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("DI")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "111";
                        w = "1";
                        reg = "111";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "111";
                        if(var2 == 16){
                            addMemReg16Bist();
                        }else{
//                            contadorDecimal = Long.parseLong("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }
            } else if (sub) {
                if (mae.toUpperCase().equals("AX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "000";
                        w = "1";
                        reg = "000";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "000";
                        if(var2 == 16){
                            subMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("BX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "011";
                        w = "1";
                        reg = "011";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "011";
                        if(var2 == 16){
                            subMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("CX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "001";
                        w = "1";
                        reg = "001";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "001";
                        if(var2 == 16){
                            subMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("DX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "010";
                        w = "1";
                        reg = "010";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "010";
                        if(var2 == 16){
                            subMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("SP")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "100";
                        w = "1";
                        reg = "100";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "100";
                        if(var2 == 16){
                            subMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("BP")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "101";
                         w = "1";
                        reg = "101";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "101";
                        if(var2 == 16){
                            subMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("SI")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "110";
                        w = "1";
                        reg = "110";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "110";
                        if(var2 == 16){
                            subMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("DI")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "111";
                        w = "1";
                        reg = "111";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "111";
                        if(var2 == 16){
                            subMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }
            } else if (or) {
                if (mae.toUpperCase().equals("AX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "000";
                        w = "1";
                        reg = "000";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "000";
                        if(var2 == 16){
                            orMemReg16Bist();
                        }else{
////                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("BX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "011";
                        w = "1";
                        reg = "011";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "011";
                        if(var2 == 16){
                            orMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("CX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "001";
                        w = "1";
                        reg = "001";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "001";
                        if(var2 == 16){
                            orMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("DX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "010";
                        w = "1";
                        reg = "010";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "010";
                        if(var2 == 16){
                            orMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("SP")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "100";
                        w = "1";
                        reg = "100";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "100";
                        if(var2 == 16){
                            orMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("BP")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "101";
                        w = "1";
                        reg = "101";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "101";
                        if(var2 == 16){
                            orMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("SI")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "110";
                        w = "1";
                        reg = "110";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "110";
                        if(var2 == 16){
                            orMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                } else if (mae.toUpperCase().equals("DI")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "111";
                        w = "1";
                        reg = "111";
                    } else if (tamreg == 16 && (tamreg2 == 16 || var2 == 16)) {
                        w = "1";
                        reg = "111";
                        if(var2 == 16){
                            orMemReg16Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }
            } else if (les) {
                mod = "11";
                rm = "000";
                w = "11000101";
                reg = "000";
                if (mae.toUpperCase().equals("AX")) {
                    if (tamreg == 16) {
                        mod = "11";
                        rm = "000";
                        w = "11000101";
                        reg = "000";
                    }
                } else if (mae.toUpperCase().equals("BX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "011";
                        w = "11000101";
                        reg = "011";
                    }
                } else if (mae.toUpperCase().equals("CX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "001";
                        w = "11000101";
                        reg = "001";
                    }
                } else if (mae.toUpperCase().equals("DX")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "010";
                        w = "11000101";
                        reg = "010";
                    }
                } else if (mae.toUpperCase().equals("SP")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "100";
                        w = "11000101";
                        reg = "100";
                    }
                } else if (mae.toUpperCase().equals("BP")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "101";
                        w = "11000101";
                        reg = "101";
                    }
                } else if (mae.toUpperCase().equals("SI")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "110";
                        w = "11000101";
                        reg = "110";
                    }
                } else if (mae.toUpperCase().equals("DI")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "111";
                        w = "11000101";
                        reg = "111";
                    }
                }
            }else if(idiv){
                mod = "11";
                w = "11110111";
                reg = "111";
                rmRegistrosInstruccionesUnOperando16Bist(mae);
            }else if(div){
                mod = "11";
                w = "11110111";
                reg = "110";
                rmRegistrosInstruccionesUnOperando16Bist(mae);
            }else if(mul){
                mod = "11";
                w = "11110111";
                reg = "100";
                rmRegistrosInstruccionesUnOperando16Bist(mae);
            }else if(not){
                mod = "11";
                w = "11110111";
                reg = "010";
                rmRegistrosInstruccionesUnOperando16Bist(mae);
            }
        }
    }

    public static void rmRegistrosInstruccionesUnOperando16Bist(String mae){
        if (mae.toUpperCase().equals("AX")) {
            rm = "000";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("BX")) {
            rm = "011";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("CX")) {
            rm = "001";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("DX")) {
            rm = "010";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("SP")) {
            rm = "100";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("BP")) {
            rm = "101";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("SI")) {
            rm = "110";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("DI")) {
            rm = "111";
            instruccionesAUnOperando();
        }
    }
    
    
    public static void analisisRegistros8(String mae) {
        cadena.add(String.format("%-70s\t%s", mae, "Registro\n"));
        if (simbolo != null || tipo != null) {
            simbolo = tipo = valor = null;
        }
        /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
        if (code != "desactivado" && etiqueta != null) {
            etiqueta = null;
        }
        if (!sim) {
            if ((add || les || or || sub) && tamreg == 0) {
                tamreg = 8;
            } else if ((add || les || or || sub) && tamreg == 8) {
                tamreg2 = 8;
            } else if ((add || les || or || sub) && tamreg == 16) {
                tamreg2 = -1;
            }
        } else {
            if (var2 == 8) {
                tamreg = 8;
            } else if (var2 == 16) {
                //tamaños incorrectos
                tamreg = -1;
            } else {
                //no se encontro el simbolo
                tamreg = -2;
            }
        }
        if (instUOper == "activado") {
            existSimbol = true;
        }
        
        if (code == "activado") {
            if (add) {
                if (mae.toUpperCase().equals("AH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "100";
                        w = "0";
                        reg = "100";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "100";
                        if(var2 == 8){
                            addMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("AL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "000";
                        w = "0";
                        reg = "000";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "000";
                        if(var2 == 8){
                            addMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                        }
                    }
                }else if (mae.toUpperCase().equals("BH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "111";
                        w = "0";
                        reg = "111";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "111";
                        if(var2 == 8){
                            addMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                        }
                    }
                }else if (mae.toUpperCase().equals("BL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "011";
                        w = "0";
                        reg = "011";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "011";
                        if(var2 == 8){
                            addMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("CH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "101";
                        w = "0";
                        reg = "101";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "101";
                        if(var2 == 8){
                            addMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("CL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "001";
                        w = "0";
                        reg = "001";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "001";
                        if(var2 == 8){
                            addMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("DH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "110";
                        w = "0";
                        reg = "110";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "110";
                        if(var2 == 8){
                            addMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("DL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "010";
                        w = "0";
                        reg = "010";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "010";
                        if(var2 == 8){
                            addMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000000" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }
            } else if (sub) {
                if (mae.toUpperCase().equals("AH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "100";
                        w = "0";
                        reg = "100";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "100";
                        if(var2 == 8){
                            subMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("AL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "000";
                        w = "0";
                        reg = "000";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "000";
                        if(var2 == 8){
                            subMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("BH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "111";
                        w = "0";
                        reg = "111";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "111";
                        if(var2 == 8){
                            subMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("BL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "011";
                        w = "0";
                        reg = "011";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "011";
                        if(var2 == 8){
                            subMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("CH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "101";
                        w = "0";
                        reg = "101";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "101";
                        if(var2 == 8){
                            subMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("CL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "001";
                        w = "0";
                        reg = "001";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "001";
                        if(var2 == 8){
                            subMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                        }
                    }
                }else if (mae.toUpperCase().equals("DH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "110";
                        w = "0";
                        reg = "110";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "110";
                        if(var2 == 8){
                            subMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("DL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "010";
                        w = "0";
                        reg = "010";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "010";
                        if(var2 == 8){
                            subMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0010100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }
                
            } else if (or) {
                if (mae.toUpperCase().equals("AH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "100";
                        w = "0";
                        reg = "100";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "100";
                        if(var2 == 8){
                            orMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                        }
                    }
                }else if (mae.toUpperCase().equals("AL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "000";
                        w = "0";
                        reg = "000";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "000";
                        if(var2 == 8){
                            orMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("BH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "111";
                        w = "0";
                        reg = "111";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "111";
                        if(var2 == 8){
                            orMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("BL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "011";
                        w = "0";
                        reg = "011";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "011";
                        if(var2 == 8){
                            orMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("CH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "101";
                        w = "0";
                        reg = "101";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "101";
                        if(var2 == 8){
                            orMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("CL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "001";
                        w = "0";
                        reg = "001";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "001";
                        if(var2 == 8){
                            orMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("DH")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "110";
                        w = "0";
                        reg = "110";
                    } else if (tamreg == 8 && (tamreg2 == 8 || var2 == 8)) {
                        w = "0";
                        reg = "110";
                        if(var2 == 8){
                            orMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }else if (mae.toUpperCase().equals("DL")) {
                    if (tamreg == 8 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "010";
                        w = "0";
                        reg = "010";
                    } else if (tamreg == 8 && tamreg2 == 8) {
                        w = "0";
                        reg = "010";
                        if(var2 == 8){
                            orMemReg8Bist();
                        }else{
//                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            contadorDecimal = 2;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                            contadorDecimal = Integer.parseInt("0000100" + w + mod + reg + rm, 2);
                            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
                            
                        }
                    }
                }
            } else if (les) {
                if (mae.toUpperCase().equals("AH")) {
                    if (tamreg == 16) {
                        mod = "11";
                        rm = "100";
                        w = "11000100";
                        reg = "100";
                    }
                } else if (mae.toUpperCase().equals("AL")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "000";
                        w = "11000100";
                        reg = "000";
                    }
                } else if (mae.toUpperCase().equals("BH")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "111";
                        w = "11000100";
                        reg = "111";
                    }
                } else if (mae.toUpperCase().equals("BL")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "011";
                        w = "11000100";
                        reg = "011";
                    }
                } else if (mae.toUpperCase().equals("CH")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "101";
                        w = "11000100";
                        reg = "101";
                    }
                } else if (mae.toUpperCase().equals("CL")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "001";
                        w = "11000100";
                        reg = "001";
                    }
                } else if (mae.toUpperCase().equals("DH")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "110";
                        w = "11000100";
                        reg = "110";
                    }
                } else if (mae.toUpperCase().equals("DL")) {
                    if (tamreg == 16 && tamreg2 == 0 && var2 == 0) {
                        mod = "11";
                        rm = "010";
                        w = "11000100";
                        reg = "010";
                    }
                }
            }else if(idiv){
                mod = "11";
                w = "11110111";
                reg = "111";
                rmRegistrosInstruccionesUnOperando8Bist(mae);
            }else if(div){
                mod = "11";
                w = "11110111";
                reg = "110";
                rmRegistrosInstruccionesUnOperando8Bist(mae);
            }else if(mul){
                mod = "11";
                w = "11110111";
                reg = "100";
                rmRegistrosInstruccionesUnOperando8Bist(mae);
            }else if(not){
                mod = "11";
                w = "11110111";
                reg = "010";
                rmRegistrosInstruccionesUnOperando8Bist(mae);
            }
        }
    }
    
    public static void rmRegistrosInstruccionesUnOperando8Bist(String mae){
        if (mae.toUpperCase().equals("AH")) {
            rm = "100";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("AL")) {
            rm = "000";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("BH")) {
            rm = "111";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("BL")) {
            rm = "011";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("CH")) {
            rm = "101";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("CL")) {
            rm = "001";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("DH")) {
            rm = "110";
            instruccionesAUnOperando();
        } else if (mae.toUpperCase().equals("DL")) {
            rm = "010";
            instruccionesAUnOperando();
        }
    }
    
    public static void addMemReg16Bist(){
        w = "00000001";
        mod = "00";
        rm = "110";
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        contadorDecimal = 4;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        
    }
    
    public static void subMemReg16Bist(){
        w = "00101001";
        mod = "00";
        rm = "110";
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        contadorDecimal = 4;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        
    }
    
    public static void orMemReg16Bist(){
        w = "00001001";
        mod = "00";
        rm = "110";
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        contadorDecimal = 4;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        
    }
    
    public static void addMemReg8Bist(){
        w = "00000000";
        mod = "00";
        rm = "110";
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        contadorDecimal = 4;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        
    }
    
    public static void subMemReg8Bist(){
        w = "00101000";
        mod = "00";
        rm = "110";
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        contadorDecimal = 4;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        
    }
    
    public static void orMemReg8Bist(){
        w = "00001000";
        mod = "00";
        rm = "110";
//        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
//        System.out.println(w + mod + reg + rm + contador);
//        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        contadorDecimal = 4;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        String contador = SumarAHexadecimal.completadorDeBits(tabla.get(posicion)[4].replace("H", ""));
        contadorDecimal = Long.parseLong((w + mod + reg + rm + contador),2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        
    }
    
    public static void instruccionesAUnOperando(){
//        System.out.println(w + mod + reg + rm);
//        contadorDecimal = Integer.parseInt(w + mod + reg + rm, 2);
        contadorDecimal = 2;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        contadorDecimal = Integer.parseInt(w + mod + reg + rm, 2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        
    }
    
    public static void instruccionesDosOperandosRegInm8Bits(int inm){
//        contadorDecimal = Integer.parseInt(w + mod + reg + rm, 2) + inm;
        contadorDecimal = 3;
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        contadorDecimal = Integer.parseInt(w + mod + reg + rm, 2);
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        codigoMaquina += Integer.toHexString(inm);
    }
    public static void instruccionesDosOperandosRegInm16Bits(int inm){
//        contadorDecimal = Integer.parseInt(w + mod + reg + rm, 2) + inm;
        if(inm<255){
            contadorDecimal = 3 ;
        }else{
            contadorDecimal = 2 + 2;
        }
        
        contadorHexadecimalAnterior = contadorHexadecimal;
        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        
        contadorDecimal = Integer.parseInt(w + mod + reg + rm, 2) ;
        codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        codigoMaquina += Integer.toHexString(inm);
    }
    
    public static void instruccionesDeSalto(String cod){
        String resta;
        resta = SumarAHexadecimal.restar(contadorHexadecimal,(Integer.parseInt(tabla.get(posicion)[4].replace("H", ""), 16)));//el de la linea menos el de la etiqueta
//                                    
        System.out.println("resta = " + resta);

        
        if(Integer.parseInt(resta,16) > 0){
            
            String contenedor = Integer.toHexString(~Integer.parseInt(resta,16) + 1);
            while(contenedor.contains("ff")){
                contenedor = contenedor.replace("ff", "f");
            }
            
            System.out.println("contenedor:  " + contenedor);
            contenedor = Integer.toBinaryString(Integer.parseInt(contenedor,16));
            System.out.println("contenedor:  " + contenedor);
            contadorDecimal = Long.parseLong(cod + (contenedor) ,2);
            System.out.println("contador decimal: " + contadorDecimal);
            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        }else{
            contadorDecimal = Long.parseLong(cod ,2);
            codigoMaquina = SumarAHexadecimal.sumar("000", contadorDecimal);
        }
    }
    
}
