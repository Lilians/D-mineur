/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import demineur.VueControlleur.Vue;
import javax.swing.SwingUtilities;

/**
 *
 * @author HP
 */
public class Demineur {

    public static void main(String[] args) {
        System.out.println("0123456789".substring(0, 2));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //On crée une nouvelle instance de JDialog
                Vue fenetre = new Vue();
                fenetre.setVisible(true);//On la rend visible
            }
        });

    }

}
