package demineur.VueControlleur;

import demineur.Model.Case;
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
    private Case myCase;
    private JLabel label;

    public CaseGraph(Game game, Case myCase) {
        super();
        label = new JLabel(" ");
        this.add(label);
        this.game = game;
        this.myCase = myCase;
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
                if (arg0.getButton() == MouseEvent.BUTTON1 && !myCase.isDrapeau() && !myCase.isEstVisible()) {
                    game.actionSurLaCase(myCase);
                } else if (arg0.getButton() == MouseEvent.BUTTON3 && !myCase.isEstVisible()) {
                    game.drapeauSurLaCase(myCase);
                }

            }

        });

    }

    public void affichage() {

        if (myCase.isDrapeau()) {
            label.setText("|>");
        } else if (myCase.isEstVisible()) {
            label.setText("" + myCase.getNbBombesAutour());
            setBackground(Color.WHITE);

        } else if (!myCase.isEstVisible()) {
            label.setText("");
            setBackground(Color.GRAY);

        }

    }

}
