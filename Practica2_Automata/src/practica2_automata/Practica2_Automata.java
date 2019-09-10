/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2_automata;

import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class Practica2_Automata {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dato = JOptionPane.showInputDialog("ingrese automata");
        Analizar analizar = new Analizar(dato);
        analizar.analizar();
    }
    
}
