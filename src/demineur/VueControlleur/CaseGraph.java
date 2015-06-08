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
 * @author CLARAS Damien et BEGOU Sylvain
 */
public class CaseGraph extends JPanel {

    private Game game;
    private Case myCase;
    private JLabel label;

    /**
     * Constructeur
     *
     * @param game
     * @param myCase
     */
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
                if (arg0.getButton() == MouseEvent.BUTTON1 && !myCase.isDrapeau()) {
                    game.actionSurLaCase(myCase);
                } else if (arg0.getButton() == MouseEvent.BUTTON3 && !myCase.isEstVisible()) {
                    game.drapeauSurLaCase(myCase);
                }

            }

        });

    }

    /**
     * Affiche la case
     */
    public void affichage() {

        if (myCase.isDrapeau()) {
            label.setText("|>");
            label.setForeground(Color.red);
        } else if (myCase.isEstVisible()) {
            if (!myCase.isEstMinee()) {
                label.setText("" + myCase.getNbBombesAutour());
                setBackground(Color.decode("#EFEFEF"));
                switch (myCase.getNbBombesAutour()) {
                    case 0:
                        label.setForeground(Color.decode("#288828"));
                        break;
                    case 1:
                        label.setForeground(Color.decode("#FFC000"));
                        break;
                    case 2:
                        label.setForeground(Color.decode("#FFA000"));
                        break;
                    case 3:
                        label.setForeground(Color.decode("#FF8000"));
                        break;
                    case 4:
                        label.setForeground(Color.decode("#FF6040"));
                        break;
                    case 5:
                        label.setForeground(Color.decode("#FF4040"));
                        break;
                    case 6:
                        label.setForeground(Color.decode("#FF2040"));
                        break;
                    case 7:
                        label.setForeground(Color.decode("#FF1040"));
                        break;
                    case 8:
                        label.setForeground(Color.decode("#FF0000"));
                        break;
                    default:
                        label.setForeground(Color.BLACK);
                        break;
                }
            } else {
                label.setText("*");
                setBackground(Color.RED);
            }

        } else {
            label.setText("");
            setBackground(Color.GRAY);
        }
    }

}
