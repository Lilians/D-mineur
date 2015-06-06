package demineur.VueControlleur;

import demineur.Model.Game;
import demineur.Option;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
 * @author CLARAS Damien et BEGOU Sylvain
 */
public class GameGraph extends JFrame implements Observer {

    private Game game;

    /**
     * Constucteur
     *
     * @param game
     */
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

    /**
     * Construit la fenetre de jeu
     */
    public void build() {

        JMenuBar jm = new JMenuBar();

        JMenu m = new JMenu("Jeu");

        JMenuItem mi = new JMenuItem("Recommencer");
        mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                game.recommencer();
                reinit();
                affichage();
            }
        });

        JMenuItem mi2 = new JMenuItem("Options");
        GameGraph temp = this;
        mi2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Option option = new Option(game, temp);
                option.setVisible(true);
            }
        });

        JMenuItem mi3 = new JMenuItem("Aide");
        mi3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                javax.swing.JOptionPane.showMessageDialog(null, "<html><body width='" + Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 + "'> Jeu de démineur classique. Vous pouvez aussi jouer avec un personnage en le controlant avec les touches directionnelles du clavier. Pour commencer à jouer le personnage, appuyez sur  la fleche de droite ou cliquez sur une case");
            }
        });

        JMenuItem mi4 = new JMenuItem("Credits");
        mi4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                javax.swing.JOptionPane.showMessageDialog(null, "<html><body width='" + Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 + "'> Jeu réalisé par CLARAS DAMIEN et BEGOU Sylvain en java, durant leurs première année du cycle ingénieur à l'école Polytech Lyon");
            }
        });

        m.add(mi);
        m.add(mi2);
        m.add(mi3);
        m.add(mi4);

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
        ((JLabel) but).setText(((JLabel) but).getText() + game.getCompteurBombe());

        add(pan, BorderLayout.CENTER);
        add(but, BorderLayout.NORTH);

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && game.getPY() != -1) {
                    game.deplacementGauche();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && game.getPY() != -1) {
                    game.deplacementBas();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    game.deplacementDroite();
                } else if (e.getKeyCode() == KeyEvent.VK_UP && game.getPY() != -1) {
                    game.deplacementHaut();
                } else {

                }
            }
        });
    }

    /**
     * Update la fenetre de jeu sur notifiaction de l'observé
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

        affichage();
        if (arg instanceof Boolean) {
            if ((Boolean) arg == true) {
                javax.swing.JOptionPane.showMessageDialog(null, "Boom ! Vous avez perdu !");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Pas de boom ! Vous avez gagné !");
            }
            game.recommencer();
            reinit();
            affichage();
        }
    }

    /**
     * Reconstruit la fenetre de jeu
     */
    public void reinit() {
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
        JComponent but = new JLabel("Nombre de Mine : " + game.getCompteurBombe());
        but.setEnabled(true);
        add(pan, BorderLayout.CENTER);
        add(but, BorderLayout.NORTH);

    }

    /**
     * Affiche le jeu dans la fentre de jeu
     */
    public void affichage() {
        ((JLabel) this.getContentPane().getComponent(1)).setText("Nombre de Mine : " + game.getCompteurBombe());

        for (int i = 0; i < game.getGrille().getHauteur(); i++) {
            for (int j = 0; j < game.getGrille().getLargeur(); j++) {
                ((CaseGraph) ((JPanel) this.getContentPane().getComponent(0)).getComponent(i * game.getGrille().getLargeur() + j)).affichage();
            }

        }

        if (this.game.getPY() != -1 && !this.game.getGrille().getCaseAt(this.game.getPX(), this.game.getPY()).isEstMinee()) {
            ((JPanel) ((JPanel) this.getContentPane().getComponent(0)).getComponent(this.game.getPX() * this.game.getGrille().getLargeur() + this.game.getPY())).setBackground(Color.blue);
        }

    }
}
