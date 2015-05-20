package demineur.VueControlleur;

import demineur.Model.Case;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author HP
 */
public class CaseGraph extends JPanel {

    private Case maCase;

    public CaseGraph(Case maCase) {
        super();

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
                //appel de la fonction qui gere le click dans game ??
            }

        });

    }

}
