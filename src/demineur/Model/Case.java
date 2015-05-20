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

    private boolean _estBalisee;
    private boolean _estMinee;
    private int _nbBombesAutours;

    public Case(boolean _estBalisee, boolean _estMinee, int _nbBombesAutours) {
        this._estBalisee = _estBalisee;
        this._estMinee = _estMinee;
        this._nbBombesAutours = _nbBombesAutours;
    }

    public Case() {
        this(false, false, 0);
    }

    public boolean isEstBalisee() {
        return _estBalisee;
    }

    public boolean isEstMinee() {
        return _estMinee;
    }

    public int getNbBombesAutours() {
        return _nbBombesAutours;
    }

    public void setEstBalisee(boolean _estBalisee) {
        this._estBalisee = _estBalisee;
    }

    public void setEstMinee(boolean _estMinee) {
        this._estMinee = _estMinee;
    }

    public void setNbBombesAutours(int _nbBombesAutours) {
        this._nbBombesAutours = _nbBombesAutours;
    }

}
