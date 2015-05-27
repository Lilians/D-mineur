/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demineur.Model;

import java.util.HashMap;

/**
 *
 * @author p1407206
 */
public class Grille {
    
    private HashMap<Case, Point> correspondance;
    private Case[][] plateau;
    private int hauteur; // X
    private int largeur; // Y

    
    public Grille(int hauteur, int largeur) {
        this.correspondance = new HashMap<Case, Point> ();
        this.plateau = new Case[hauteur][largeur];
        this.hauteur = hauteur;
        this.largeur = largeur;
    }
    
        // Retourne la case correspondante
    public Case getCaseAt(int x, int y) {
        return this.plateau[x][y];
    }

    public HashMap<Case, Point> getCorrespondance() {
        return correspondance;
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
