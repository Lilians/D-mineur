/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Model;

/**
 *
 * @author HP
 */
public class Case {

    private boolean drapeau;
    private boolean estMinee;
    private int nbBombesAutour;
    private boolean estVisible;

    public Case(boolean drapeau, boolean estMinee, int nbBombesAutours, boolean estVisible) {
        this.drapeau = drapeau;
        this.estMinee = estMinee;
        this.nbBombesAutour = nbBombesAutours;
        this.estVisible = estVisible;
    }

    public void action() {
        this.estVisible = true;
    }

    public void actionDrapeau() {
        if (drapeau) {
            this.drapeau = false;
        } else {
            this.drapeau = true;
        }
    }

    public boolean isEstMinee() {
        return estMinee;
    }

    public int getNbBombesAutour() {
        return nbBombesAutour;
    }

    public void setEstBalisee(boolean drapeau) {
        this.drapeau = drapeau;
    }

    public void setEstMinee(boolean estMinee) {
        this.estMinee = estMinee;
    }

    public void setNbBombesAutour(int nbBombesAutour) {
        this.nbBombesAutour = nbBombesAutour;
    }

    public void setDrapeau(boolean drapeau) {
        this.drapeau = drapeau;
    }

    public void setEstVisible(boolean estVisible) {
        this.estVisible = estVisible;
    }

    public boolean isDrapeau() {
        return drapeau;
    }

    public boolean isEstVisible() {
        return estVisible;
    }
}
