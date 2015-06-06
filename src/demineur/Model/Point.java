package demineur.Model;

/**
 *
 * @author CLARAS Damien et BEGOU Sylvain
 */
public class Point {

    private int x;
    private int y;

    /**
     * Constructeur
     *
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la postion x du point
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Affecte une valeur à la position x du point
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la position y du point
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Affecte une valeur à la position x du point
     *
     * @param x
     */
    public void setY(int y) {
        this.y = y;
    }

}
