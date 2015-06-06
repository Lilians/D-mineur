package demineur.Model;

/**
 *
 * @author CLARAS Damien et BEGOU Sylvain
 */
public class Case {

    private boolean drapeau;
    private boolean estMinee;
    private int nbBombesAutour;
    private boolean estVisible;

    /**
     * Constructeur
     *
     * @param drapeau
     * @param estMinee
     * @param nbBombesAutours
     * @param estVisible
     */
    public Case(boolean drapeau, boolean estMinee, int nbBombesAutours, boolean estVisible) {
        this.drapeau = drapeau;
        this.estMinee = estMinee;
        this.nbBombesAutour = nbBombesAutours;
        this.estVisible = estVisible;
    }

    /**
     * Retourne le nombre de bombe autour de la case
     *
     * @return
     */
    public int getNbBombesAutour() {
        return nbBombesAutour;
    }

    /**
     * Affecte une valeur au nombre de bombe autour de la case
     *
     * @param nbBombesAutour
     */
    public void setNbBombesAutour(int nbBombesAutour) {
        this.nbBombesAutour = nbBombesAutour;
    }

    /**
     * Retourne si la case est minée
     *
     * @return
     */
    public boolean isEstMinee() {
        return estMinee;
    }

    /**
     * Definie si la case est minée
     *
     * @param estMinee
     */
    public void setEstMinee(boolean estMinee) {
        this.estMinee = estMinee;
    }

    /**
     * Retourne si la case à été balisée par un drapeau
     *
     * @return
     */
    public boolean isDrapeau() {
        return drapeau;
    }

    /**
     * Definie si la case à été balisée par un drapeau
     *
     * @param drapeau
     */
    public void setDrapeau(boolean drapeau) {
        this.drapeau = drapeau;
    }

    /**
     * Retourne si la case est visible
     *
     * @return
     */
    public boolean isEstVisible() {
        return estVisible;
    }

    /**
     * Definie si la case est visible
     *
     * @param estVisible
     */
    public void setEstVisible(boolean estVisible) {
        this.estVisible = estVisible;
    }

    /**
     * Effectue les actions lors d'un clique ou d'un mouvement sur la case
     */
    public void action() {
        this.estVisible = true;
    }

    /**
     * Effectue les actions lors de la mise d'un drapeau sur une case
     */
    public void actionDrapeau() {
        if (drapeau) {
            this.drapeau = false;
        } else {
            this.drapeau = true;
        }
    }

}
