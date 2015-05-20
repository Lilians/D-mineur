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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author frederic
 */
public class GameGraph extends JFrame {
// reference de game ?

    private Game game;
    
   
    public GameGraph() {
        super();

        this.build();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

    }

    public void build() {

        //JMenu jm = new JMenu();
        JMenuBar jm = new JMenuBar();

        JMenu m = new JMenu("Jeu");

        JMenuItem mi = new JMenuItem("Recommancer");
        mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //ajout game.recommencer
            }
        });
        m.add(mi);

        jm.add(m);

        setJMenuBar(jm);

        setTitle("Démineur");
        setSize(400, 400);
        JComponent pan = new JPanel(new GridLayout(10, 10));

        for (int i = 0; i < 100; i++) {
            JComponent ptest = new CaseGraph();
            pan.add(ptest);
        }
        add(pan);
        //setContentPane(pan);

    }

}
