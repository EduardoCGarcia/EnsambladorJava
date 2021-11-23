/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.util.Scanner;

/**
 *
 * @author eduar
 */
public class Conversor {
    static final String INSTRUCTIONS = "Introducir el número que se desea convertir, \n"
            + "seguido de un espacio y la base en la que se encuentra. \nPor ejemplo: \n"
            + " 12345 10 (indica el número 12,345 en decimal)\n";
            
    static final String ERROR = "El dato ingresado no contiene el formato correcto. \n"
            + "Intentalo nuevamente.";

    public static int binToDec(String numeroBinario) {
        return Integer.parseInt(numeroBinario, 2);
    }

    public static String decToBin(int numeroDecimal) {
        return Integer.toBinaryString(numeroDecimal);
    }

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println(INSTRUCTIONS);
                Scanner scan = new Scanner(System.in);
                String entrada = scan.nextLine();
                String[] dato = entrada.split(" ");
                if (dato[1].equals("2")) {
                    System.out.println(binToDec(dato[0]));
                    break;
                }
                if (dato[1].equals("10")) {
                    System.out.println(decToBin(Integer.valueOf(dato[0])));
                    break;
                }
            } catch (Exception e) {
                System.out.println(ERROR);
            }
        }
    }
}
