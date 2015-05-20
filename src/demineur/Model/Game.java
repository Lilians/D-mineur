/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Model;

import java.util.Observable;
import java.util.Random;

/**
 *
 * @author HP
 */
public class Game extends Observable {

    private Case[][] plateau;
    private int hauteur; // X
    private int largeur; // Y

    //notifyObservers();
    public Game(int hauteur, int largeur) {

        plateau = new Case[hauteur][largeur];
        //generation du plateau
    }

    public void recommencer(int nbBombes) {
        this.genererPlateau(nbBombes);
    }

    public void actionSurLaCase(int x, int y) {
        //action

    }
    
    public void genererPlateau(int nbBombes) {
        int compteurBombe = 0;
        Random rand = new Random() ;
        for (int i = 0; i<hauteur; i++) {
            for (int j=0; j<largeur; j++) {
                if (compteurBombe < nbBombes) {
                    
                 //   nbBombes / (this.hauteur * this.largeur) 
                }
            }
        }
    }
    
    
    // Retourne la case correspondante
    public Case getCaseAt(int x, int y) {
           return this.plateau[x][y];
    }

    
    
    public Case[][] getPlateau() {
        return plateau;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }
    
    
}
