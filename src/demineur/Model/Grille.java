package demineur.Model;

import java.util.HashMap;

/**
 *
 * @author CLARAS Damien et BEGOU Sylvain
 */
public class Grille {

    private HashMap<Case, Point> correspondance;
    private Case[][] plateau;
    private int hauteur; // X
    private int largeur; // Y

    /**
     * Constructeur
     *
     * @param hauteur
     * @param largeur
     */
    public Grille(int hauteur, int largeur) {
        this.correspondance = new HashMap<Case, Point>();
        this.plateau = new Case[hauteur][largeur];
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    /**
     * Retourne la case à la coordonée (x,y)
     *
     * @param x
     * @param y
     * @return
     */
    public Case getCaseAt(int x, int y) {
        return this.plateau[x][y];
    }

    /**
     * Retourne le point de coordonée (x,y) corespondant à la case
     *
     * @param maCase
     * @return
     */
    public Point getPoint(Case maCase) {
        return this.correspondance.get(maCase);
    }

    /**
     * Retourne la hauteur de la grille
     *
     * @return
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Retourne la largeur de la grille
     *
     * @return
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Initialise une nouvelle case au coordonée (x,y)
     *
     * @param x
     * @param y
     */
    public void nouvelleCaseAt(int x, int y) {
        this.plateau[x][y] = new Case(false, false, 0, false);
        this.correspondance.put(this.getCaseAt(x, y), new Point(x, y));
    }

}
