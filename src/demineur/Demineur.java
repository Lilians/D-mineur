package demineur;

import demineur.Model.Game;
import demineur.VueControlleur.GameGraph;
import javax.swing.SwingUtilities;

/**
 *
 * @author CLARAS Damien et BEGOU Sylvain
 */
public class Demineur {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game(10, 10, 5);
                GameGraph fenetre = new GameGraph(game);
                fenetre.setVisible(true);
            }
        });

    }

}
