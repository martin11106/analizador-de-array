/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_automata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * @author marti
 */
public class Analizar {
    private ArrayList<String> arrError;
    private ArrayList<String> arrLexema;
    private ArrayList<String> arrToken;
    private ArrayList<String> arrTipo;
    
    private int indice;
    private int estado;
    private String lexema;
    private String dato;
    private String finalText = "";
    private int pos = 0;
    private String identificador = "";
    private String concatenar;
    
    
    public Analizar(String dato) {
        indice = 0;
        estado = 0;
        lexema = "";
        arrError = new ArrayList<>();
        arrLexema = new ArrayList<>();
        arrToken = new ArrayList<>();
        arrTipo = new ArrayList<>();
        this.dato = dato;
        concatenar="";
        
    }
    
    public void analizar(){
        String todoTexto = dato;
        String textoLimpio = "";
    
        for (int i = 0; i < todoTexto.length(); i++) {
            char letra = todoTexto.charAt(i);
            switch(letra){
                case '\r':
                case '\t':
                case '\n':
                case '\b':
                case '\f':
                    break;
                default:
                    textoLimpio = textoLimpio + letra;
            }  
        }
        
        
        for(indice = 0; indice < textoLimpio.length(); indice++) {
            char letra = textoLimpio.charAt(indice);
            int codigoascii = letra;
            identificador = identificador.concat(String.valueOf(letra));
            switch(estado) {
                case 0:
                    if((codigoascii >=65 && codigoascii <=90) 
                            || (codigoascii >= 97 && codigoascii <= 122)) {
                        estado = 1;
                        lexema = "" + letra;
                        arrLexema.add(lexema);
                        arrTipo.add(lexema);
                    }else if(codigoascii >= 48 && codigoascii <= 57) {
                        estado = 0;
                        lexema = "" + letra;
                        arrLexema.add(lexema);
                    }else if(letra == ' '){
                        estado = 0;
                    }
                    else {
                        System.err.println("error lexema " + lexema);
                        estado = 0;
                        lexema = ""+ letra;
                        arrError.add(lexema);
                    }
                break;
            case 1:
                if((codigoascii >=65 && codigoascii <=90) 
                            || (codigoascii >= 97 && codigoascii <= 122)) {
                    estado = 1;
                    lexema = "" + letra;
                    arrLexema.add(lexema);
                    arrTipo.add(lexema);
                }else{
                    if(codigoascii == 32){
                        estado = 2;   
                    }else{
                        arrError.add(String.valueOf(letra));
                    }
                }
             break;
             case 2:
                 concatenar = arrTipo.stream().map(String::valueOf).collect(Collectors.joining());
                     if (concatenar.equals("int")) {
                        estado = 3;
                        indice--;
                     }
                     else if (concatenar.equals("float")) {
                        estado = 3;
                        indice--;
                     }
                     else if (concatenar.equals("String")) {
                        estado = 3;
                        indice--;
                     }
                     else if (concatenar.equals("char")) {
                        estado = 3;
                        indice--;
                     }
                    else {
                    lexema = "" + letra;
                    arrError.add(lexema);
                 }
                 
             break;
              case 3:
                if((codigoascii >=65 && codigoascii <=90) 
                            || (codigoascii >= 97 && codigoascii <= 122)) {
                    estado = 3;
                    lexema = "" + letra;
                    arrLexema.add(lexema);
                }else if(codigoascii == 91){
                    lexema = ""+letra;
                    arrLexema.add(lexema);
                    estado = 4;
                }else{
                    lexema = "" + letra; 
                    arrError.add(lexema);
                }  
            break;
             case 4:
                   concatenar = arrTipo.stream().map(String::valueOf).collect(Collectors.joining());
                     if (concatenar.equals("int")) {
                        estado = 5;
                        indice--;
                     }
                     else if (concatenar.equals("float")) {
                        estado = 4;
                        indice--;
                     }
                     else if (concatenar.equals("String")) {
                        estado = 6;
                        indice--;
                     }
                     else if (concatenar.equals("char")) {
                        estado = 7;
                        indice--;
                     }
                 else if(codigoascii == 46){
                    estado = 4;
                    lexema = "" + letra;
                    arrLexema.add(lexema);
                 }
             break;
             case 5:
                if((codigoascii>=48 && codigoascii <=57)) {
                    estado = 5;
                    lexema = ""+ letra;
                    arrLexema.add(lexema);
                }else if(codigoascii == 93){
                    lexema = "" + letra;
                    arrLexema.add(lexema);
                }else{
                    lexema = ""+ letra;
                    arrError.add(lexema);
                }
            break;
             case 6:
              if(((codigoascii >=65 && codigoascii <=90) 
                     || (codigoascii >= 97 && codigoascii <= 122))) {
                    estado = 6;
                    lexema = ""+ letra;
                    arrLexema.add(lexema);
                }else if(codigoascii == 93){
                    lexema = "" + letra;
                    arrLexema.add(lexema);
                }else{
                    lexema = ""+ letra;
                    arrError.add(lexema);
                }
                 break;
               case 7:
              if(((codigoascii >=65 && codigoascii <=90) 
                     || (codigoascii >= 97 && codigoascii <= 122))) {
                    estado = 7;
                    lexema = ""+ letra;
                    arrLexema.add(lexema);
                }else if(codigoascii == 46){
                    estado = 7;
                    lexema = "" + letra;
                }else if(codigoascii == 93){
                    lexema = "" + letra;
                    arrLexema.add(lexema);
                }else{
                    lexema = ""+ letra;
                    arrError.add(lexema);
                }
                 break;
            }
        }
        System.out.println("Carateres correctos: " + arrLexema);
        System.out.println("Caracteres errones:" + arrError);
        if(!arrError.isEmpty()){
            System.out.println("la cadena es incorrecta");
        }else{
            System.out.println("la cadena es correcta");
        }
    }   
}
