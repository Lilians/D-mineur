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

    /* TODO
     répendre actionSurCase
     notify Observer en cas de défaite ou de victoire
     */
    public Game(int hauteur, int largeur, int proba) {
        this.proba = proba;
        this.grille = new Grille(hauteur, largeur);
        this.genererPlateau();
    }

    public void recommencer() {
        this.genererPlateau();
    }

    public void initialisationObserver(GameGraph gameGraph) {
        this.addObserver(gameGraph);
    }

    public void actionSurLaCase(Case maCase) {
        //action
        //Point point = this.grille.getCorrespondance().get(maCase);
        //this.grille.getPlateau()[point.getX()][point.getY()];
        
        maCase.action();
        if (maCase.isEstMinee()) {
            this.setChanged();
            this.notifyObservers();   // TODO : arg dans l'oberserver
        } else if (maCase.getNbBombesAutour() == 0) {
            // traiter
            this.setChanged();
            this.notifyObservers();
        } else {
            this.setChanged();
            this.notifyObservers(); // TODO : arg dans l'oberserver
        }
    }
    
    public void rependreCase() {
        
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
                this.grille.getPlateau()[i][j] = new Case(false, estMinee, 0, false);
                this.grille.getCorrespondance().put(this.grille.getPlateau()[i][j], new Point(i, j));
            }
        }
        this.compteurBombe = compteurBombe;
    }

    // exécutée à l'initialisation
    // Met à jour le nombre de voisins
    public void updateVoisins() {
        int compteur = 0;
        for (int i = 0; i < this.grille.getHauteur(); i++) {
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
        Point point = this.grille.getCorrespondance().get(maCase);
        if (point.getX() != 0) {
            listeVoisins.add(this.grille.getPlateau()[point.getX() - 1][point.getY()]);
            if (point.getY() != 0) {
                listeVoisins.add(this.grille.getPlateau()[point.getX() - 1][point.getY() - 1]);
            }
            if (point.getY() != this.grille.getLargeur() - 1) {
                listeVoisins.add(this.grille.getPlateau()[point.getX() - 1][point.getY() + 1]);
            }
        }
        if (point.getX() != this.grille.getHauteur() - 1) {
            listeVoisins.add(this.grille.getPlateau()[point.getX() + 1][point.getY()]);
            if (point.getY() != 0) {
                listeVoisins.add(this.grille.getPlateau()[point.getX() + 1][point.getY() - 1]);
            }
            if (point.getY() != this.grille.getLargeur() - 1) {
                listeVoisins.add(this.grille.getPlateau()[point.getX() + 1][point.getY() + 1]);
            }
        }
        if (point.getY() != 0) {
            listeVoisins.add(this.grille.getPlateau()[point.getX()][point.getY() - 1]);
        }
        if (point.getY() != this.grille.getLargeur() - 1) {
            listeVoisins.add(this.grille.getPlateau()[point.getX()][point.getY() + 1]);
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
