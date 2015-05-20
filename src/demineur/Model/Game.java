/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Model;

import demineur.VueControlleur.GameGraph;
import java.util.ArrayList;
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
    private int proba;
    private int compteurBombe;

    //notifyObservers();
    public Game(int hauteur, int largeur, int proba) {

        plateau = new Case[hauteur][largeur];
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.proba = proba;
        this.genererPlateau();;
        //generation du plateau
    }

    public void recommencer() {
        this.genererPlateau();
    }

    public void initialisationObserver(GameGraph gameGraph) {
        this.addObserver(gameGraph);
    }

    public void actionSurLaCase(int x, int y) {
        //action
        this.plateau[x][y].action();
        this.setChanged();
        this.notifyObservers();

    }

    public void drapeauSurLaCase(int x, int y) {
        //action
        this.plateau[x][y].drapeau();
        this.setChanged();
        this.notifyObservers();

    }

    // pour 15% : proba = 15
    public void genererPlateau() {
        int compteurBombe = 0;
        Random rand = new Random();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                int alea = rand.nextInt(100);
                boolean estMinee;
                if (alea < this.proba - 1) { // mettre bombe
                    estMinee = true;
                    compteurBombe++;
                } else {
                    estMinee = false;
                }
                this.plateau[i][j] = new Case(false, estMinee, 0, false);
            }
        }
        this.compteurBombe = compteurBombe;
    }

    public void updateVoisins() {
        int compteur = 0;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                compteur = 0;
                ArrayList<Case> voisins = this.getVoisins(this.plateau[i][j]);
                for (Case c : voisins) {
                    if (c.isEstMinee()) {
                        compteur++;
                    }
                }
                this.plateau[i][j].setNbBombesAutour(compteur);
            }
        }
    }

    // Retourne la liste des cases voisine
    public ArrayList<Case> getVoisins(Case maCase) {
        // TODO !!
        return null;
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

    public int getProba() {
        return proba;
    }

    public int getCompteurBombe() {
        return compteurBombe;
    }

}
