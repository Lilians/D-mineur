package demineur.Model;

/**
 *
 * @author CLARAS Damien et BEGOU Sylvain
 */
public class Personnage {

    private int x;
    private int y;
    private Game game;

    public Personnage(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void deplacementGauche() {
        if (y > 0) {
            y--;
            action();
        }
    }

    public void deplacementDroite() {
        if (y < this.game.getGrille().getLargeur() - 1) {
            y++;
            action();
        }
    }

    public void deplacementHaut() {
        if (x > 0) {
            x--;
            action();
        }
    }

    public void deplacementBas() {
        if (x < this.game.getGrille().getLargeur() - 1) {
            x++;
            action();
        }
    }

    private void action() {
        if (!this.game.getGrille().getCaseAt(x, y).isEstVisible()) {
            this.game.actionSurLaCase(this.game.getGrille().getCaseAt(x, y));
        }

    }

}
