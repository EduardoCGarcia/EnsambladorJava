/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

/**
 *
 * @author eduar
 */
public class SumarAHexadecimal {
    public static String sumar(String hexa,long dec){
        String suma;
        long decsum = Long.parseLong(hexa,16) + dec;
        //System.out.println("decimal = " + decsum);
        suma = "0" + Long.toHexString(decsum);
        return suma;
    }
    public static String restar(String hexa,long dec){
        String suma;
        long decsum = Long.parseLong(hexa,16) - dec;
        //System.out.println("decimal = " + decsum);
        suma = "0" + Long.toHexString(decsum);
        return suma;
    }
    public static String completadorDeBits(String direccionHex){
        String contador = Long.toBinaryString(Long.parseLong(direccionHex,16));
        
        if (contador.length() < 16){
            int falta = 16 - contador.length();
            for (int i = 0; i < falta; i++) {
                contador = "0"+ contador;
            }
        }
        return contador;
    }
}
