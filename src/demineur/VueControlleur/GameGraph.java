package demineur.VueControlleur;

import demineur.Model.Game;
import demineur.Option;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author CLARAS Damien et BEGOU Sylvain
 */
public class GameGraph extends JFrame implements Observer {

    private Game game;
    private int nbCoupJoue;

    /**
     * Constucteur
     *
     * @param game
     */
    public GameGraph(Game game) {
        super();
        this.game = game;
        this.nbCoupJoue = 0;
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

        JMenuItem mi3 = new JMenuItem("Regarder les statistiques des dernières parties jouées");
        mi3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Desktop d = Desktop.getDesktop();
                try {
                    d.open(new File("./Stats.txt"));
                } catch (UnsupportedOperationException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Votre system n'est pas assez récent pour supporter cette fonctionnalité.", "Erreur", JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Il y'a eu un problème lors de l'ouverture du fichier avec votre logiciel par défaut.", "Erreur", JOptionPane.WARNING_MESSAGE);
                } catch (IllegalArgumentException e) {
                    try {
                        File ff = new File("./Stats.txt");
                        ff.createNewFile();
                        FileWriter ffw = new FileWriter(ff);
                        ffw.write("");
                        ffw.close();
                        this.actionPerformed(ae);
                    } catch (Exception e2) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Un problème est survenu lors de la création du fichier de statistiques.", "Erreur", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Un problème est survenu.", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        );

        JMenuItem mi4 = new JMenuItem("Effacer les statistiques des dernières parties");
        mi4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Confirmez la suppression des statistiques ?", "Warning", dialogButton);

                if (dialogResult == JOptionPane.YES_OPTION) {
                    try {
                        File ff = new File("./Stats.txt");
                        ff.createNewFile();
                        FileWriter ffw = new FileWriter(ff);
                        ffw.write("");
                        ffw.close();
                    } catch (Exception e) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Un problème est survenu lors de la suppresion des statistiques.", "Erreur", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
        );

        JMenuItem mi5 = new JMenuItem("Aide");
        mi5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                javax.swing.JOptionPane.showMessageDialog(null, "<html><body width='" + Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 + "'> Jeu de démineur classique. Vous pouvez aussi jouer avec un personnage en le contrôlant avec les touches directionnelles du clavier. Pour commencer à jouer le personnage, appuyez sur  la flèche de droite ou cliquez sur une case", "Aide", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem mi6 = new JMenuItem("Crédits");
        mi6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                javax.swing.JOptionPane.showMessageDialog(null, "<html><body width='" + Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 + "'> Jeu réalisé par CLARAS Damien et BEGOU Sylvain en java, durant leur première année du cycle ingénieur à l'école Polytech Lyon", "Crédits", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JMenuItem mi7 = new JMenuItem("Quitter");
        mi7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        m.add(mi);
        m.add(mi2);
        m.add(mi3);
        m.add(mi4);
        m.add(mi5);
        m.add(mi6);
        m.add(mi7);

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

        JComponent but = new JLabel("Nombre de Mine(s) : ");
        ((JLabel) but).setText(((JLabel) but).getText() + game.getCompteurBombe());

        add(pan, BorderLayout.CENTER);
        add(but, BorderLayout.NORTH);

        addKeyListener(new KeyAdapter() {

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

        nbCoupJoue++;
        affichage();
        if (arg instanceof Boolean) {
            nbCoupJoue--;
            if ((Boolean) arg == true) {
                javax.swing.JOptionPane.showMessageDialog(null, "Boom ! Vous avez perdu !", "Defaite !", JOptionPane.PLAIN_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Pas de boom ! Vous avez gagné !", "Victoire !", JOptionPane.PLAIN_MESSAGE);
            }
            try {
                File ff = new File("./Stats.txt");
                ff.createNewFile();
                FileWriter ffw = new FileWriter(ff, true);
                String s = "";
                String c = " " + this.nbCoupJoue + " ";
                if ((Boolean) arg == true) {
                    s += " perdue ";
                } else {
                    s += " gagnée ";
                }
                ffw.write("" + System.getProperty("line.separator") + "Partie" + s + "en" + c + "coup(s). Largeur de la grille : " + this.game.getGrille().getLargeur() + ". Hauteur de la grille : " + this.game.getGrille().getHauteur() + ". Nombre de mine(s) : " + this.game.getNbBombes() + ".");
                ffw.close();
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Un problème est survenu lors de l'enregistrement des statistiques de la partie.");
            }
            reinit();
            affichage();
        }

    }

    /**
     * Reconstruit la fenetre de jeu
     */
    public void reinit() {
        this.nbCoupJoue = 0;
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
        JComponent but = new JLabel("Nombre de Mine(s) : " + game.getCompteurBombe());
        but.setEnabled(true);
        add(pan, BorderLayout.CENTER);
        add(but, BorderLayout.NORTH);
    }

    /**
     * Affiche le jeu dans la fentre de jeu
     */
    public void affichage() {
        ((JLabel) this.getContentPane().getComponent(1)).setText("Nombre de Mine(s) : " + game.getCompteurBombe());

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
