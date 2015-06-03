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

    private int nbBombe;
    private int compteurBombe;
    private Grille grille;
    private int compteurCaseAction;

    /* TODO
     répendre actionSurCase
     notify Observer en cas de défaite ou de victoire
     */
    public Game(int hauteur, int largeur, int nbBombe) {
        this.nbBombe = nbBombe;
        this.grille = new Grille(hauteur, largeur);
        this.compteurCaseAction = 0;
        this.compteurBombe = this.nbBombe;
        this.genererPlateau();
        this.updateVoisins();
    }

    public void reinitialisation(int hauteur, int largeur, int nbBombe) {
        this.nbBombe = nbBombe;
        this.grille = new Grille(hauteur, largeur);
        this.compteurCaseAction = 0;
        this.compteurBombe = this.nbBombe;
        this.genererPlateau();
        this.updateVoisins();

    }

    public void recommencer() {
        this.genererPlateau();
        this.updateVoisins();
        this.compteurCaseAction = 0;
    }

    public void initialisationObserver(GameGraph gameGraph) {
        this.addObserver(gameGraph);
    }

    public void actionSurLaCase(Case maCase) {
        //action
        //Point point = this.grille.getCorrespondance().get(maCase);
        //this.grille.getPlateau()[point.getX()][point.getY()];

        if (maCase.isEstMinee()) {
            maCase.action();
            this.setChanged();
            this.notifyObservers(true);
        } else if (maCase.getNbBombesAutour() == 0) {
            this.etendreCase(maCase);
            this.setChanged();
            this.notifyObservers();
        } else {
            maCase.action();
            this.compteurCaseAction++;
            this.setChanged();
            this.notifyObservers();
        }
        if (this.compteurCaseAction == this.grille.getLargeur() * this.grille.getHauteur() && this.compteurBombe == 0) {
            this.setChanged();
            this.notifyObservers(false);
        }
    }

    public void etendreCase(Case maCase) {

        if (maCase.getNbBombesAutour() == 0) {
            maCase.action();
            this.compteurCaseAction++;
            ArrayList<Case> voisins = this.getVoisins(maCase);

            for (int i = 0; i < voisins.size(); i++) {
                if (!voisins.get(i).isEstVisible()) {
                    this.etendreCase(voisins.get(i));
                }
            }

        } else if (!maCase.isEstMinee()) {
            this.compteurCaseAction++;
            maCase.action();
        }

    }

    public void drapeauSurLaCase(Case maCase) {
        //action
        Point point = this.grille.getCorrespondance().get(maCase);
        this.grille.getPlateau()[point.getX()][point.getY()].actionDrapeau();
        if (maCase.isDrapeau()) {
            this.compteurBombe--;
            this.compteurCaseAction++;
        } else {
            this.compteurBombe++;
            this.compteurCaseAction--;
        }
        if (this.compteurCaseAction == this.grille.getLargeur() * this.grille.getHauteur() && this.compteurBombe == 0) {
            this.setChanged();
            this.notifyObservers(false);
        } else {
            this.setChanged();
            this.notifyObservers();
        }
    }

    // exécutée à l'initialisation
    public void genererPlateau() {
        for (int i = 0; i < this.grille.getHauteur(); i++) {
            for (int j = 0; j < this.grille.getLargeur(); j++) {

                this.grille.getPlateau()[i][j] = new Case(false, false, 0, false);
                this.grille.getCorrespondance().put(this.grille.getPlateau()[i][j], new Point(i, j));
            }
        }
        Random rand = new Random();
        int i = 0;
        int alea1;
        int alea2;
        while (i < this.nbBombe) {
            alea1 = rand.nextInt(this.grille.getHauteur());
            alea2 = rand.nextInt(this.grille.getLargeur());
            if (!this.grille.getPlateau()[alea1][alea2].isEstMinee()) {
                i++;
                this.grille.getPlateau()[alea1][alea2].setEstMinee(true);
            }
        }
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

    public int getCompteurBombe() {
        return compteurBombe;
    }

    public Grille getGrille() {
        return grille;
    }

}
