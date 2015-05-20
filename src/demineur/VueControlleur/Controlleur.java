package demineur.VueControlleur;

import demineur.Model.Game;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author fred
 */
public class Controlleur extends JFrame {

    JTextField jt;
    Game m;

    public Controlleur(Game _m) {
        m = _m;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 250);

        jt = new JTextField("");
        jt.setEditable(false);
        add(jt, BorderLayout.NORTH);

        jt.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                jt.setText("");
            }

        });

        JPanel jpGrille = new JPanel(new GridLayout(4, 3));
        add(jpGrille, BorderLayout.CENTER);

        JButton jb;
        for (String s : new String[]{"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "+", "0", "(", ")"}) {
            jb = new JButton(s);
            jpGrille.add(jb);
            jb.addActionListener(
                    new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            jt.setText(jt.getText() + ((JButton) e.getSource()).getText());
                        }

                    });
        }

        jb = new JButton("=");

        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //m.calc(jt.getText());
            }
        });

        jpGrille.add(jb);

        m.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                /*if (!m.getErr()) {
                 jt.setText(m.getValue() + "");
                 } else {
                 jt.setText("Err");
                 }*/
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Controlleur vc = new Controlleur(new Game());
        vc.setVisible(true);

    }

}
