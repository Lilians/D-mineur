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
    private int nbBombesAutours;
    private boolean estVisible;

    public Case(boolean drapeau, boolean estMinee, int nbBombesAutours, boolean estVisible) {
        this.drapeau = drapeau;
        this.estMinee = estMinee;
        this.nbBombesAutours = nbBombesAutours;
        this.estVisible = estVisible;
    }

    public Case() {
        this(false, false, 0);
    }

    public void action() {
        
    }
    
    public boolean getDrapeau() {
        return drapeau;
    }

    public boolean isEstMinee() {
        return estMinee;
    }

    public int getNbBombesAutours() {
        return nbBombesAutours;
    }

    public void setEstBalisee(boolean drapeau) {
        this.drapeau = drapeau;
    }

    public void setEstMinee(boolean estMinee) {
        this.estMinee = estMinee;
    }

    public void setNbBombesAutours(int nbBombesAutours) {
        this.nbBombesAutours = nbBombesAutours;
    }

    public void setDrapeau(boolean drapeau) {
        this.drapeau = drapeau;
    }

    public void setEstVisible(boolean estVisible) {
        this.estVisible = estVisible;
    }

    
}
