package demineur.VueControlleur;

import demineur.Model.Game;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author HP
 */
public class CaseGraph extends JPanel {

    private Game game;
    private int positionX;
    private int positionY;
    private JLabel label;

    public CaseGraph(Game game, int x, int y) {
        super();
        label = new JLabel(" ");
        this.add(label);
        this.game = game;
        this.positionX = x;
        this.positionY = y;
        setBackground(Color.GRAY);
        Border blackline = BorderFactory.createLineBorder(Color.black, 1);
        setBorder(blackline);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent arg0) {
                super.mouseEntered(arg0);
                Border blueline = BorderFactory.createLineBorder(Color.blue, 1);
                setBorder(blueline);

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                super.mouseExited(arg0);
                Border blackline = BorderFactory.createLineBorder(Color.black, 1);
                setBorder(blackline);

            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                super.mouseClicked(arg0);
                if (arg0.getButton() == MouseEvent.BUTTON3) {
                    game.drapeauSurLaCase(positionX, positionY);
                } else {
                    game.actionSurLaCase(positionX, positionY);
                }

            }

        });

    }

    public void affichage() {

        if (game.getCaseAt(positionX, positionY).getDrapeau()) {
            label.setText("|>");
        } else if (game.getCaseAt(positionX, positionY).isEstVisible()) {
            label.setText("" + game.getCaseAt(positionX, positionY).getNbBombesAutour());
            setBackground(Color.WHITE);

        }

    }

}
