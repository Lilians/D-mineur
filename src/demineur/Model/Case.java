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

    private boolean estBalisee;
    private boolean estMinee;
    private int nbBombesAutours;

    public Case(boolean estBalisee, boolean estMinee, int nbBombesAutours) {
        this.estBalisee = estBalisee;
        this.estMinee = estMinee;
        this.nbBombesAutours = nbBombesAutours;
    }

    public Case() {
        this(false, false, 0);
    }

    public boolean isEstBalisee() {
        return estBalisee;
    }

    public boolean isEstMinee() {
        return estMinee;
    }

    public int getNbBombesAutours() {
        return nbBombesAutours;
    }

    public void setEstBalisee(boolean estBalisee) {
        this.estBalisee = estBalisee;
    }

    public void setEstMinee(boolean estMinee) {
        this.estMinee = estMinee;
    }

    public void setNbBombesAutours(int nbBombesAutours) {
        this.nbBombesAutours = nbBombesAutours;
    }

}
