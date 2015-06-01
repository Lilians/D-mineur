/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur.VueControlleur;

import demineur.Model.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author frederic
 */
public class GameGraph extends JFrame implements Observer {

    private Game game;

    public GameGraph(Game game) {
        super();
        this.game = game;
        game.initialisationObserver(this);
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

        JMenuBar jm = new JMenuBar();

        JMenu m = new JMenu("Jeu");

        JMenuItem mi = new JMenuItem("Recommancer");
        mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                game.recommencer();
                reinit();
                affichage();
            }
        });
        m.add(mi);

        jm.add(m);

        setJMenuBar(jm);

        setTitle("Démineur");
        setSize(400, 400);
        JComponent pan = new JPanel(new GridLayout(game.getGrille().getHauteur(), game.getGrille().getLargeur()));

        for (int i = 0; i < game.getGrille().getHauteur(); i++) {
            for (int j = 0; j < game.getGrille().getLargeur(); j++) {
                JComponent ptest = new CaseGraph(game, game.getGrille().getCaseAt(i, j));
                pan.add(ptest);
            }
        }
        JComponent but = new JLabel("Nombre de Mine : ");
        but.setEnabled(false);
        ((JLabel) but).setText(((JLabel) but).getText() + game.getCompteurBombe());
        add(pan, BorderLayout.CENTER);
        add(but, BorderLayout.NORTH);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof Boolean) {
            System.out.println("ça marche");
        }
        affichage();
    }

    private void reinit() {
        this.getContentPane().remove(0);
        this.getContentPane().remove(0);
        this.game.recommencer();
        JComponent pan = new JPanel(new GridLayout(game.getGrille().getHauteur(), game.getGrille().getLargeur()));

        for (int i = 0; i < game.getGrille().getHauteur(); i++) {
            for (int j = 0; j < game.getGrille().getLargeur(); j++) {
                JComponent ptest = new CaseGraph(game, game.getGrille().getCaseAt(i, j));
                pan.add(ptest);
            }
        }
        JComponent but = new JLabel("Nombre de Mine : ");
        but.setEnabled(false);
        ((JLabel) but).setText(((JLabel) but).getText() + game.getCompteurBombe());
        add(pan, BorderLayout.CENTER);
        add(but, BorderLayout.NORTH);

    }

    private void affichage() {

        for (int i = 0; i < game.getGrille().getHauteur(); i++) {
            for (int j = 0; j < game.getGrille().getLargeur(); j++) {
                ((CaseGraph) ((JPanel) this.getContentPane().getComponent(0)).getComponent(i * game.getGrille().getHauteur() + j)).affichage();
            }

        }
    }

}
