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

    private int proba; // pourcentage de bombe. Exemple : 15 pour 15%
    private int compteurBombe;
    private Grille grille;

    // TODO
    //notifyObservers();
    public Game(int hauteur, int largeur, int proba) {
        this.proba = proba;
        this.genererPlateau();
        this.grille = new Grille(hauteur, largeur);
    }

    public void recommencer() {
        this.genererPlateau();
    }

    public void initialisationObserver(GameGraph gameGraph) {
        this.addObserver(gameGraph);
    }

    public void actionSurLaCase(Case maCase) {
        //action
        Point point = this.grille.getCorrespondance().get(maCase);
        this.grille.getPlateau()[point.getX()][point.getY()].action();
        this.setChanged();
        this.notifyObservers();
    }

    public void drapeauSurLaCase(Case maCase) {
        //action
        Point point = this.grille.getCorrespondance().get(maCase);
        this.grille.getPlateau()[point.getX()][point.getY()].actionDrapeau();
        this.setChanged();
        this.notifyObservers();
    }

    // exécutée à l'initialisation
    public void genererPlateau() {
        int compteurBombe = 0;
        Random rand = new Random();
        for (int i = 0; i < this.grille.getHauteur(); i++) {
            for (int j = 0; j < this.grille.getLargeur(); j++) {
                int alea = rand.nextInt(100);
                boolean estMinee;
                if (alea < this.proba - 1) { // mettre bombe
                    estMinee = true;
                    compteurBombe++;
                } else {
                    estMinee = false;
                }
                this.grille.getPlateau()[i][j] = new Case(false, estMinee, 0, false, i, j);
                this.grille.getCorrespondance().put(this.grille.getPlateau()[i][j], new Point(i, j));
            }
        }
        this.compteurBombe = compteurBombe;
    }

    // exécutée à l'initialisation
    // Met à jour le nombre de voisins
    public void updateVoisins() {
        int compteur = 0;
        for (int i = 0; i <  this.grille.getHauteur(); i++) {
            for (int j = 0; j < this.grille.getLargeur(); j++) {
                compteur = 0;
                ArrayList<Case> voisins = this.getVoisins(this.grille.getPlateau()[i][j]);
                for (Case c : voisins) {
                    if (c.isEstMinee()) {
                        compteur++;
                    }
                }
                this.grille.getPlateau()[i][j].setNbBombesAutour(compteur);
            }
        }
    }

    // Retourne la liste des cases voisine
    public ArrayList<Case> getVoisins(Case maCase) {
        ArrayList<Case> listeVoisins = new ArrayList<Case>();
        if (maCase.getX() != 0) {
            listeVoisins.add(this.grille.getPlateau()[maCase.getX()-1][maCase.getY()]);
            if (maCase.getY() != 0) {
                listeVoisins.add(this.grille.getPlateau()[maCase.getX()-1][maCase.getY()-1]);
            }
            if (maCase.getY() != this.grille.getLargeur()-1) {
                 listeVoisins.add(this.grille.getPlateau()[maCase.getX()-1][maCase.getY()+1]);
            }
        }
        if (maCase.getX() !=  this.grille.getHauteur()-1) {
            listeVoisins.add(this.grille.getPlateau()[maCase.getX()+1][maCase.getY()]);
            if (maCase.getY() != 0) {
                 listeVoisins.add(this.grille.getPlateau()[maCase.getX()+1][maCase.getY()-1]);
            }
            if (maCase.getY() != this.grille.getLargeur()-1) {
                 listeVoisins.add(this.grille.getPlateau()[maCase.getX()+1][maCase.getY()+1]);
            }
        }
        if (maCase.getY() != 0) {
            listeVoisins.add(this.grille.getPlateau()[maCase.getX()][maCase.getY()-1]);
        }
        if (maCase.getY() != this.grille.getLargeur()-1) {
            listeVoisins.add(this.grille.getPlateau()[maCase.getX()][maCase.getY()+1]);
        }
        return listeVoisins;
    }

    public int getProba() {
        return proba;
    }

    public int getCompteurBombe() {
        return compteurBombe;
    }

    public Grille getGrille() {
        return grille;
    }

    
}
