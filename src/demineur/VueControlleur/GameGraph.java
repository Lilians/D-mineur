/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.VueControlleur;

import demineur.Model.Game;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;

/**
 *
 * @author frederic
 */
public class GameGraph extends JFrame implements Observer {

    private Game game;

    public GameGraph(Game game) {
        super();
        this.game = game;
        this.build();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

    }

    public GameGraph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void build() {

        //JMenu jm = new JMenu();
        JMenuBar jm = new JMenuBar();

        JMenu m = new JMenu("Jeu");

        JMenuItem mi = new JMenuItem("Recommancer");
        mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                game.recommencer();
                affichage();
            }
        });
        m.add(mi);

        jm.add(m);

        setJMenuBar(jm);

        setTitle("Démineur");
        setSize(400, 400);
        JComponent pan = new JPanel(new GridLayout(game.getHauteur(), game.getLargeur()));

        for (int i = 0; i < game.getHauteur(); i++) {
            for (int j = 0; j < game.getLargeur(); j++) {
                JComponent ptest = new CaseGraph(game.getCaseAt(i, j);
                pan.add(ptest);
            }

        }
        add(pan);

    }

    @Override
    public void update(Observable o, Object arg) {

        affichage();
    }

    private void affichage() {

        for (int i = 0; i < game.getHauteur(); i++) {
            for (int j = 0; j < game.getLargeur(); j++) {
                this.getComponent(i * game.getHauteur() + j * game.getLArgeur()).affichage();
            }

        }
    }

}
