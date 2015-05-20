/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.Model;

import java.util.Observable;

/**
 *
 * @author HP
 */
public class Game extends Observable {

    private Case[][] _plateau;

    //notifyObservers();

    public Game(int hauteur, int largeur) {

        _plateau = new Case[hauteur][largeur];
        //generation du plateau
    }

    public void recommencer() {
        //regeneration du plateau, notify observer ??

    }

    public void actionSurLaCase(int x, int y) {
        //action 

    }
}
