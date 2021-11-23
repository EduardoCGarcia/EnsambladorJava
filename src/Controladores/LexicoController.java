/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ClasesFlexCup.Elementos;
import ProgramaPrincipal.Lexico;
import static ClasesFlexCup.Elementos.*;
import static Controladores.CodificacionInstrucciones.*;
import static Controladores.SintaxController.existSimbol;
import static ProgramaPrincipal.Lexico.*;
import static Controladores.TablaController.*;
import java.io.IOException;
/**
 *
 * @author eduar
 */
public class LexicoController {

    public static Lexico winLex = new Lexico();

    public static void mostrar() {
        winLex.txtAnalisisLexicco.setText(Paginacion.renglonesInicial(cadena));
        winLex.setVisible(true);
    }

    public static void ocultar() {
        winLex.setVisible(false);
    }

    static int posicion;
    public static byte valorDeclaracion;
    public static int valDupCod;
    public static boolean stack, stackDW;
    public static boolean dB, dW;
    //STI,AAM,CLI,RET,STOSB,AAS
    public static String aOpInsBin[] = {"11111011", "1101010000001010", "11111010", "11000011", "10101010", "00111111"};
    public static boolean dsOpInsBin;

    //Codificacion para las instrucciones de uno o mas operandos
    // s = 0   
    public static String mod, reg, rm, s, w;

    static boolean ins;

    public static void analizarLexico(String lec) throws IOException {
        int contInstUO = 0;
        cadena.clear();
        simbolos.clear();

        //String lec = (String) txtArchivo.getText();//Todo lo que se lee del archivo y que cae en txtArchivo
        MAELEX maeLex = new MAELEX(new java.io.StringReader(lec));//Se lo colocamos al maeLex porque es el que va a hacer el analisis lexico
        String result = "";
        simbolo = tipo = valor = etiqueta = instSalto = instUOper = null;
        add = les = or = sub = sim = err = valid = dW = dB = false;
        div = idiv = not = mul = false;
        tamreg = tamreg2 = var2 = 0;
        contadorDecimal = 0;
        valDupCod = 0;
        while (true) {
            contInstUO += 1;
            Elementos elemento = maeLex.yylex();
            if (elemento == null) {
                /*Si el data esta activado y los componentes de delcaracion de definicion de una varible estan activos
                llenamos la matriz que sera cargada en la tabla de datos
                si no esta activo el segmento de datos pregunta si el de codigo esta activo de ser asi solo comprueba si una
                etiqueta a sido leida de ser asi tambien sera enviada a la tabla de simbolos*/
                if (data == "activado" && simbolo != null && tipo != null && valor != null) {
                    llenarMatrizSimbolos();
                    /*Reiniciamos todos los datos leido*/
                    simbolo = tipo = valor = etiqueta = null;
                }
                if (code == "activado" && etiqueta != null) {
                    llenarMatrizEtiquetas();
                    simbolo = tipo = valor = etiqueta = null;
                }
                if (data == null) {

                    if (code == "activado" && ins) {
                            contadorHexadecimalAnterior = contadorHexadecimalAnterior2;
                            contadorHexadecimalAnterior2 = contadorHexadecimal;
                        
                    } else {
                        contadorHexadecimalAnterior = contadorHexadecimal;
                        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                    }
                } else {
                    contadorHexadecimalAnterior = contadorHexadecimalAnterior2;
                    contadorHexadecimalAnterior2 = contadorHexadecimal;

                }
                return;
            }

            switch (elemento) {
                case DATA -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    /*activamos el segmento de datos*/
                    if (data == null && data != "desactivado") {
                        data = "activado";
                        contadorHexadecimalAnterior2 = contadorHexadecimal;
                        contadorHexadecimal = "0200";
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }

                }
                case CODE -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if (code == null && code != "desactivado") {
                        code = "activado";
                        contadorHexadecimalAnterior2 = contadorHexadecimal;
                        contadorHexadecimal = "0200";
                    }
                    //contadorHexadecimal = "0200";  

                }

                case Simbolo -> {
                    if (maeLex.maeLexMe.length() <= 10 && maeLex.maeLexMe.length() >= 1) {
                        cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Simbolo\n"));
                        if (data == "activado" && simbolo == null) {
                            simbolo = maeLex.maeLexMe;
                        } else {
                            simbolo = tipo = valor = null;
                        }

                        if (instSalto == "activado") {
                            saltoVal = existInEtiqs(maeLex.maeLexMe);
                            if(saltoVal){
                            contadorDecimal = 3;
                            contadorHexadecimalAnterior = contadorHexadecimal;
                            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);    
                            }
                        } else if (instUOper == "activado") {
                            existSimbol = existInTable(maeLex.maeLexMe);
                        }
                        if ((add || les || or || sub) && tamreg == 0 && sim == false) {//cuando empieza la instruccion son un operando en memoroia
                            if (existInTable(maeLex.maeLexMe)) {//Comprobamos si existe en la tabla de simbolos
                                if (tabla.get(posicion)[3].equals("Byte")) {
                                    //El tipo Byte no se puede guardar en un reg de 16
                                    var2 = 8;
                                } else if (tabla.get(posicion)[3].equals("Word")) {
                                    var2 = 16;
                                }
                                sim = true;
                            } else {
                                var2 = -1;
                            }
                        } else if ((add || les || or || sub) && tamreg == 16) {
                            if (existInTable(maeLex.maeLexMe)) {
                                if (tabla.get(posicion)[3].equals("Byte")) {
                                    //El tipo Byte no se puede guardar en un reg de 16
                                    InstruccionSimbolo16Bits();//Tambien es valido porque el tamaño es menor a los 16 bist
                                } else if (tabla.get(posicion)[3].equals("Word")) {
                                    InstruccionSimbolo16Bits();
                                }
                            } else {
                                //La varibale no existe
                                var2 = -2;
                            }
                        } else if ((add || les || or || sub) && tamreg == 8) {
                            if (existInTable(maeLex.maeLexMe)) {
                                if (tabla.get(posicion)[3].equals("Byte")) {
                                    InstruccionSimbolo8Bits();
                                } else if (tabla.get(posicion)[3].equals("Word")) {
                                    var2 = -1;
                                }
                            } else {
                                //La varibale no existe
                                var2 = -2;
                            }
                        }

                    } else {
                        cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Longitud > 10\n"));
                    }

                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    
                    if( div || idiv || not || mul){
                        if (existInTable(maeLex.maeLexMe)){
                            if (tabla.get(posicion)[3].equals("Byte")) {
                                mod = "00";
                                rm = "110";
                                w = "11110110";
                                if(div){
                                    reg = "110";
                                    instruccionesUnOperandoMem();
                                }else if (idiv){
                                    reg = "111";
                                    instruccionesUnOperandoMem();
                                }else if (not){
                                    reg = "010";
                                    instruccionesUnOperandoMem();
                                }else if (mul){
                                    reg = "100";
                                    instruccionesUnOperandoMem();
                                }
                            } else if (tabla.get(posicion)[3].equals("Word")) {
                                mod = "00";
                                rm = "110";
                                w = "11110111";
                                if(div){
                                    reg = "110";
                                    instruccionesUnOperandoMem();
                                }else if (idiv){
                                    reg = "111";
                                    instruccionesUnOperandoMem();
                                }else if (not){
                                    reg = "010";
                                    instruccionesUnOperandoMem();
                                }else if (mul){
                                    reg = "100";
                                    instruccionesUnOperandoMem();
                                }
                            }
                            
                        }else {
                            var2 = -1;
                        }
                    }
                    
                    
                    
                    
                }

                case EQU -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if (data == "activado" && simbolo != null && tipo == null) {
                        tipo = maeLex.maeLexMe;
                        dW = true;
                    } else if (tipo != null) {
                        simbolo = tipo = null;
                    }

                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                }
                case DB -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if (data == "activado" && simbolo != null && tipo == null) {
                        tipo = maeLex.maeLexMe;
                        dB = true;
                    } else if (tipo != null) {
                        simbolo = tipo = null;
                    }

                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                }
                case DW -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if (data == "activado" && simbolo != null && tipo == null) {
                        tipo = maeLex.maeLexMe;
                        dW = true;
                    } else if (tipo != null) {
                        simbolo = tipo = null;
                    }

                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (stack) {
                        stackDW = true;
                    }
                }

                case ConstanteDec -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Decimal\n"));
                    if (data == "activado" && simbolo != null && tipo != null) {
                        if (valor == null) {
                            if (dB && Integer.parseInt(maeLex.maeLexMe) <= 255) {
                                valor = maeLex.maeLexMe;
                            } else if (dW && Integer.parseInt(maeLex.maeLexMe) <= 2147483647) {
                                valor = maeLex.maeLexMe;
                            } else {
                                valor = null;
                                err = true;
                            }

                        } else if (!valor.contains("DUP") && !valor.contains("dup")) {
                            valor += " " + maeLex.maeLexMe;
                        } else {
                            simbolo = tipo = valor = null;
                        }
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if ((or || sub || add) && tamreg == 8) {
                        if (Integer.parseInt(maeLex.maeLexMe) <= 255) {
                            //sim = true;
                            valid = true;
                            var2 = 10;
                            if (add){
                                w = "10000000";//s=0 w =0
                                reg = "000";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe));
                            }else if(sub){
                                w = "10000000";
                                reg = "101";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe));
                            }else if(or){
                                w = "10000000";
                                reg = "001";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe));
                            }
                        } else {
                            var2 = -1;
                        }
                    } else if ((or || sub || add) && tamreg == 16) {
                        if (Integer.parseInt(maeLex.maeLexMe) <= 65536) {
                            //sim = true;
                            valid = true;
                            var2 = 10;
                            if (add){
                                w = "10000001";//s=0 w =0
                                reg = "000";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe));
                            }else if(sub){
                                w = "10000001";
                                reg = "101";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe));
                            }else if(or){
                                w = "10000001";
                                reg = "001";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe));
                            }
                        } else {
                            var2 = -1;
                        }
                    } else if((or || sub || add) && var2 == 16){
                        if (Integer.parseInt(maeLex.maeLexMe) <= 65536) {
                            mod = "00";
                            rm = "110";
                            if (add){
                                w = "10000001";//s=0 w =0
                                reg = "000";

                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe));
                            }else if(sub){
                                w = "10000001";
                                reg = "101";
                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe));
                            }else if(or){
                                w = "10000001";
                                reg = "001";
                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe));
                            }
                        }
                    }else if((or || sub || add) && var2==8){
                        if (Integer.parseInt(maeLex.maeLexMe) <= 255) {
                            mod = "00";
                            rm = "110";
                            if (add){
                                w = "10000000";//s=0 w =0
                                reg = "000";

                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe));
                            }else if(sub){
                                w = "10000000";
                                reg = "101";
                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe));
                            }else if(or){
                                w = "10000000";
                                reg = "001";
                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe));
                            }
                        }
                    }else{
                        valid = true;
                        var2 = -1;
                    }

                    if (stack && stackDW) {
                        //contadorHexadecimalAnterior = contadorHexadecimal;
                        contadorDecimal += 2 * Integer.parseInt(maeLex.maeLexMe);
                        //contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                        stack = stackDW = false;
                    }
                    valorDeclaracion = 1;
                }

                case ConstanteBin -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Binaria\n"));
                    if (data == "activado" && simbolo != null && tipo != null) {
                        if (valor == null) {
                            if (dB && maeLex.maeLexMe.length() == 9) {
                                valor = maeLex.maeLexMe;
                            } else if (dW && maeLex.maeLexMe.length() == 17) {
                                valor = maeLex.maeLexMe;
                            } else {
                                valor = null;
                                err = true;
                            }
                        }
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    valorDeclaracion = 2;
//                    }
                    if ((or || sub || add) && tamreg == 8) {
                        if (maeLex.maeLexMe.length() == 9) {
                            //sim = true;
                            valid = true;
                            var2 = 10;
                            if (add){
                                w = "10000000";//s=0 w =0
                                reg = "000";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }else if(sub){
                                w = "10000000";
                                reg = "101";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }else if(or){
                                w = "10000000";
                                reg = "001";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }
                        } else {
                            var2 = -1;
                        }
                    } else if ((or || sub || add) && tamreg == 16) {
                        if (maeLex.maeLexMe.length() == 17 || maeLex.maeLexMe.length() == 9) {//Le puedes quitar el or segun lo que diga la profa
                            //sim = true;
                            valid = true;
                            var2 = 10;
                            if (add){
                                w = "10000001";//s=0 w =0
                                reg = "000";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }else if(sub){
                                w = "10000001";
                                reg = "101";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }else if(or){
                                w = "10000001";
                                reg = "001";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }
                        } else {
                            var2 = -1;
                        }
                    } else if((or || sub || add) && var2 == 16){
                        if (maeLex.maeLexMe.length() == 17 || maeLex.maeLexMe.length() == 9) {
                            mod = "00";
                            rm = "110";
                            if (add){
                                w = "10000001";//s=0 w =0
                                reg = "000";

                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }else if(sub){
                                w = "10000001";
                                reg = "101";
                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }else if(or){
                                w = "10000001";
                                reg = "001";
                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }
                        }
                    } else if((or || sub || add) && var2 == 8){
                        if (maeLex.maeLexMe.length() == 9) {
                            mod = "00";
                            rm = "110";
                            if (add){
                                w = "10000000";//s=0 w =0
                                reg = "000";

                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }else if(sub){
                                w = "10000000";
                                reg = "101";
                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }else if(or){
                                w = "10000000";
                                reg = "001";
                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("B", ""),2));
                            }
                        }
                    }else {
                       
                        var2 = -1;
                    }

                }

                case ConstanteHex -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Hexadecimal\n"));
                    if (data == "activado" && simbolo != null && tipo != null) {
                        if (valor == null) {
                            if (valor == null) {
                                if (dB && maeLex.maeLexMe.length() == 4) {
                                    valor = maeLex.maeLexMe;
                                } else if (dW && maeLex.maeLexMe.length() == 6) {
                                    valor = maeLex.maeLexMe;
                                } else {
                                    valor = null;
                                    err = true;
                                }
                            }
                        }
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    valorDeclaracion = 3;
                    if ((or || sub || add) && tamreg == 8) {
                        if (maeLex.maeLexMe.length() == 4) {
                            //sim = true;
                            //valid=true;
                            var2 = 10;
                            if (add){
                                w = "10000000";//s=0 w =0
                                reg = "000";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }else if(sub){
                                w = "10000000";
                                reg = "101";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }else if(or){
                                w = "10000000";
                                reg = "001";
                                instruccionesDosOperandosRegInm8Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }
                        } else {
                            var2 = -1;
                        }
                    } else if ((or || sub || add) && tamreg == 16) {
                        if (maeLex.maeLexMe.length() == 6 || maeLex.maeLexMe.length() == 4) {//Le puedes quitar el or segun lo que diga la profa
                            //sim = true;
                            //valid=true;
                            var2 = 10;
                            if (add){
                                w = "10000001";//s=0 w =0
                                reg = "000";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }else if(sub){
                                w = "10000001";
                                reg = "101";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }else if(or){
                                w = "10000001";
                                reg = "001";
                                instruccionesDosOperandosRegInm16Bits(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }
                        } else {
                            var2 = -1;
                        }
                    } else if((or || sub || add) && var2 == 16){
                        if (maeLex.maeLexMe.length() == 6 || maeLex.maeLexMe.length() == 4) {
                            mod = "00";
                            rm = "110";
                            if (add){
                                w = "10000001";//s=0 w =0
                                reg = "000";
                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }else if(sub){
                                w = "10000001";
                                reg = "101";
                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }else if(or){
                                w = "10000001";
                                reg = "001";
                                instruccionesDosOperandos16BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }
                        }
                    }else if((or || sub || add) && var2 == 8){
                        if (maeLex.maeLexMe.length() == 4) {
                            mod = "00";
                            rm = "110";
                            if (add){
                                w = "10000000";//s=0 w =0
                                reg = "000";
                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }else if(sub){
                                w = "10000000";
                                reg = "101";
                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }else if(or){
                                w = "10000000";
                                reg = "001";
                                instruccionesDosOperandos8BitsMemInm(Integer.parseInt(maeLex.maeLexMe.toUpperCase().replace("H", ""),16));
                            }
                        }
                    } else {
                        //valid=true;
                        var2 = -1;
                    }
                }

                case Cadena -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Constante Caracter\n"));
                    if (data == "activado" && simbolo != null && tipo != null) {
                        if (valor == null && dB) {
                            valor = maeLex.maeLexMe;
                        } else {
                            simbolo = tipo = valor = null;
                        }
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    valorDeclaracion = 4;
                }

                case ENDS -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if (data == "activado") {;
                        data = "desactivado";
                    } else if (code == "activado") {
                        code = "desactivado";
                    }
                    if (stack && stackDW) {
                        stack = stackDW = false;
                    }
                }

                case DUP -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if (data == "activado" && simbolo != null && tipo != null && valor != null) {
                        if (!valor.contains("DUP") && !valor.contains("dup")) {
                            valDupCod = Integer.parseInt(valor);
                            valor += " " + maeLex.maeLexMe;
                        }

                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                }

                case STACK -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    stack = true;
                    contadorHexadecimal = "0200";
                    contadorHexadecimalAnterior = contadorHexadecimal;

                }
                case MACRO,ENDM,PROC,ENDP,BYTE_PTR -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Pseudoinstrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                }

                case STI -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != "desactivado" && code != null) {
                        ins = true;
                        contadorDecimal = Integer.parseInt(aOpInsBin[0], 2);
                        contadorHexadecimalAnterior2 = contadorHexadecimal;
                        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                    }
                }
                case AAM -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != "desactivado" && code != null) {
                        ins = true;
                        contadorDecimal = Integer.parseInt(aOpInsBin[1], 2);
                        contadorHexadecimalAnterior2 = contadorHexadecimal;
                        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                    }
                }
                case CLI -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != "desactivado" && code != null) {
                        ins = true;
                        contadorDecimal = Integer.parseInt(aOpInsBin[2], 2);
                        contadorHexadecimalAnterior2 = contadorHexadecimal;
                        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                    }
                }
                case RET -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != "desactivado" && code != null) {
                        ins = true;
                        contadorDecimal = Integer.parseInt(aOpInsBin[3], 2);
                        contadorHexadecimalAnterior2 = contadorHexadecimal;
                        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                    }
                }
                case STOSB -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != "desactivado" && code != null) {
                        ins = true;
                        contadorDecimal = Integer.parseInt(aOpInsBin[4], 2);
                        contadorHexadecimalAnterior2 = contadorHexadecimal;
                        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                    }
                }
                case AAS -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != "desactivado" && code != null && maeLex.maeLexMe.toUpperCase().endsWith("AAS")) {
                        ins = true;
                        contadorDecimal = Integer.parseInt(aOpInsBin[5], 2);
                        contadorHexadecimalAnterior2 = contadorHexadecimal;
                        contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
                    }
                }
                case ADD -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    add = true;

                }
                case LES -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    les = true;
                }
                case OR -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    or = true;
                }
                case SUB -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    sub = true;
                }
                
                case IDIV -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != null) {
                        instUOper = "activado";
                    }
                    idiv = true;
                }
                case DIV -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != null) {
                        instUOper = "activado";
                    }
                    div = true;
                }
                
               
                
                case MUL -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != null) {
                        instUOper = "activado";
                    }
                    mul = true;
                }
                case NOT -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != null) {
                        instUOper = "activado";
                    }
                    not = true;
                }
                case JC,JGE,JNA,JS,LOOPNE,JAE -> {
                    cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Instrucción\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    if (code != null) {
                        instSalto = "activado";
//                        saltoVal = true;
//                        System.out.println("Encontro la etiqueta en en la tabla");
                    }

                }

                case AX -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case BX -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case CX -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case DX -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case SI -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case DI -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case SP -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case BP -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case SS -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case CS -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case DS -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }
                case ES -> {
                    analisisRegistros16(maeLex.maeLexMe);
                }

                case AH -> {
                    analisisRegistros8(maeLex.maeLexMe);
                }
                case AL -> {
                    analisisRegistros8(maeLex.maeLexMe);
                }
                case BH -> {
                    analisisRegistros8(maeLex.maeLexMe);
                }
                case BL -> {
                    analisisRegistros8(maeLex.maeLexMe);
                }
                case CH -> {
                    analisisRegistros8(maeLex.maeLexMe);
                }
                case CL -> {
                    analisisRegistros8(maeLex.maeLexMe);
                }
                case DH -> {
                    analisisRegistros8(maeLex.maeLexMe);
                }
                case DL -> {
                    analisisRegistros8(maeLex.maeLexMe);
                }

                case Dos_puntos,Comilla_d,Comilla_s,Parentesis_a,Parentesis_c,Corchete_a,Corchete_c -> {
//                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "Separador\n");
//                    cadena.add(result);
//result += "    < Separador >\t\t" + maeLex.maeLexMe + "\n";
                }
                case TITLE -> {
                    cadena.add(String.format("%-70s%s", maeLex.maeLexMe, "Titulo del programa\n"));
                    if (simbolo != null || tipo != null) {
                        simbolo = tipo = valor = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                }

                case SinComa -> {
                    switch (maeLex.maeLexMe) {
                        case "AX","AH","AL","BX","BH","BL","CX","CH","CL","DX","DH","DL","SI","DI","SP","BP","SS","CS","DS","ES",
                                "ax","ah","al","bx","bh","bl","cx","ch","cl","dx","dh","dl","si","di","sp","bp","ss","cs","ds","es" -> {
                            result = String.format("%-70s\t%s", maeLex.maeLexMe, "Registro\n");
                        }
                        default ->
                            result = String.format("%-70s\t%s", maeLex.maeLexMe, "Simbolo\n");
                    }
                    cadena.add(result);
                }

                case Etiqueta -> {
                    /*Las etiquetas deben tener una longitud menor o igual a 10 y mayor a 1*/
                    if (maeLex.maeLexMe.length() <= 10 && maeLex.maeLexMe.length() >= 1) {
                        cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Etiqueta\n"));
                        if (data != null || simbolo != null || tipo != null) {
                            simbolo = tipo = null;
                        }
                        if (code == "activado" && etiqueta == null) {
                            etiqueta = maeLex.maeLexMe;
                            //contadorDecimal = 5;
                        }

                        /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
//                        if(code!="desactivado" && etiqueta!=null)
//                            etiqueta=null;
                    } else {
                        cadena.add(String.format("%-70s\t%s", maeLex.maeLexMe, "Longitud > 10\n"));

                    }

                }
                case ERROR,Separadores -> {
                    if (maeLex.maeLexMe.contains(".")) {
                        maeLex.maeLexMe = maeLex.maeLexMe.replace(".", "");
                    }
                    if (maeLex.maeLexMe.contains(",")) {
                        maeLex.maeLexMe = maeLex.maeLexMe.replace(",", "");
                    }
                    result = String.format("%-70s\t%s", maeLex.maeLexMe, "No Identificado\n");
                    cadena.add(result);
                    if (data != null || simbolo != null || tipo != null) {
                        simbolo = tipo = null;
                    }
                    /*Si esta activo el codigo y la etiqueta y se lee cualquier otra cosa los reiniciamos*/
                    if (code != "desactivado" && etiqueta != null) {
                        etiqueta = null;
                    }
                    err = true;
                    var2 = -1;
                    tamreg2 =-1;
                    valid = true;
                }
                //default -> result += "  < Elemento No Identificado >\t\t " + maeLex.maeLexMe + " \t\n";
            }
        }
    }

    static void llenarMatrizSimbolos() {
        boolean val = existInTable(simbolo);
        if (!val) {
            codificacionSimbolos();
        }
        String tamaño = "";
        String type = tipo;
        //boolean val = false;
        if (tipo.equals("DB") || tipo.equals("db")) {
            tamaño = "Byte";
        } else if (tipo.equals("DW") || tipo.equals("dw"))//*************************
        {
            tamaño = "Word";
        }

        if (tipo.equals("EQU") || tipo.equals("equ")) {
            tipo = "Constante";
        } else {
            tipo = "Variable";
        }

        //val = existInTable(simbolo);
        if (!val) {//Si el simbolo existe en a tabla lo añade
            String[] fila = new String[]{simbolo, tipo, valor, tamaño, contadorHexadecimalAnterior.toUpperCase() + "H"};
            tabla.add(fila);
            simbolos.add(simbolo);//Guardamos solo el simbolo que es valido

        }
        existSimbol = val;
        //Si el simbolo no es añadido a la tabla significa que val=true entonces debemos mandar mensaje de error en el sintactico
    }

    static void llenarMatrizEtiquetas() {
        boolean val = existInTable(simbolo);
        tipo = "Etiqueta";
        
        val = existInTable(etiqueta);
        if (!val) {//Si LA etiqueta no existe en a tabla lo añade
            String[] fila = new String[]{etiqueta, tipo, "", "",contadorHexadecimalAnterior2.toUpperCase() + "H"};
            tabla.add(fila);
            etiquetas.add(etiqueta);//Guardamos solo el simbolo que es valido

        }
        //Si el simbolo no es añadido a la tabla significa que val=true entonces debemos mandar mensaje de error en el sintactico

    }

    public static boolean existInTable(String buscado) {

        for (posicion = 0; posicion < tabla.size(); posicion++) {
            if (tabla.get(posicion)[0].equals(buscado)) {
                //System.out.println(tabla.get(i)[0]);
                return true;
            }
        }
        return false;
    }

    public static boolean existInEtiqs(String buscado) {

        for (int i = 0; i < etiquetas.size(); i++) {
            if (etiquetas.get(i).equals(buscado)) {
                return true;
            }
        }
        return false;
    }

    public static boolean existInSims(String buscado) {

        for (int i = 0; i < simbolos.size(); i++) {
            if (simbolos.get(i).equals(buscado)) {
                return true;
            }
        }
        return false;
    }

    public static void codificacionSimbolos() {
        /*
       valorDeclaracion = 1  [Decimal]
       valorDeclaracion = 2  [Binario]
       valorDeclaracion = 3  [Hexadecimal]
       valorDeclaracion = 4  [Cadena]
         */

        if (tipo.toUpperCase().equals("DB") && valorDeclaracion == 1 && valor.toUpperCase().contains("DUP")) {
            contadorDecimal = valDupCod;
            contadorHexadecimalAnterior = contadorHexadecimal;
            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        } else if (tipo.toUpperCase().equals("DW") && valorDeclaracion == 1 && valor.toUpperCase().contains("DUP")) {
            contadorDecimal = 2 * valDupCod;
            contadorHexadecimalAnterior = contadorHexadecimal;
            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
        } else if (tipo.toUpperCase().equals("DB") && (valorDeclaracion == 1 || valorDeclaracion == 2 || valorDeclaracion == 3)) {
            contadorDecimal = 1;
            contadorHexadecimalAnterior = contadorHexadecimal;
            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
            //System.out.println("Contdor hexa = " + contadorHexadecimal);
            valorDeclaracion = 0;
        } else if (tipo.toUpperCase().equals("DW") && (valorDeclaracion == 1 || valorDeclaracion == 2 || valorDeclaracion == 3)) {
            contadorDecimal = 2;
            contadorHexadecimalAnterior = contadorHexadecimal;
            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
            //System.out.println("Contdor hexa = " + contadorHexadecimal);
            valorDeclaracion = 0;
        } else if (valorDeclaracion == 4) {
            contadorDecimal = valor.length() - 2;
            contadorHexadecimalAnterior = contadorHexadecimal;
            contadorHexadecimal = SumarAHexadecimal.sumar(contadorHexadecimal, contadorDecimal);
            //System.out.println("Contdor hexa = " + contadorHexadecimal);
            valorDeclaracion = 0;
        }
    }


}
