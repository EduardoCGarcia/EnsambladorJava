/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static ProgramaPrincipal.Lexico.*;
import java.util.ArrayList;

/**
 *
 * @author eduar
 */
public class Paginacion {
        public static String renglonesInicial(ArrayList cad) {
        cont = 0;
        String pantalla = String.format("%-55s %s\n", "CODIGO", "ANALISIS");
        if (cad.size() >= 20) {
            for (int i = 0; i < 20; i++) {
                pantalla += cad.get(i);
            }
        } else {
            for (int i = 0; i < cad.size(); i++) {
                pantalla += cad.get(i);
            }
            cont = cad.size();
        }

        return pantalla;
    }

    public static String renglonesSiguiente(ArrayList cad) {
        String pantalla = String.format("%-55s %s\n", "CODIGO", "ANALISIS");
        if (cont != cad.size()) {
            cont += 20;
            if ((cont + 20) <= cad.size()) {
                for (int i = cont; i < cont + 20; i++) {
                    pantalla += cad.get(i);
                }
            } else {

                for (int i = cont; i < cad.size(); i++) {
                    pantalla += cad.get(i);
                }
                cont = cad.size();
            }
        } else {
            for (int i = cad.size() - (cad.size() % 20); i < cad.size(); i++) {
                pantalla += cad.get(i);
            }
            cont = cad.size();
        }
        
        return pantalla;
    }

    public static String renglonesAtras(ArrayList cad) {
        String pantalla = String.format("%-55s %s\n", "CODIGO", "ANALISIS");
        if (cont == cad.size()) {
            cont -= (cad.size() % 20);
        }
        if ((cont - 20) >= 0) {
            cont -= 20;
            for (int i = cont; i < cont + 20; i++) {
                pantalla += cad.get(i);
            }
        } else {
            pantalla = renglonesInicial(cad);
        }

        return pantalla;
    }
}
