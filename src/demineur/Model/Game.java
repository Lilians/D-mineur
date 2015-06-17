package demineur.Model;

import demineur.VueControlleur.GameGraph;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author CLARAS Damien et Sylvain BEGOU
 */
public class Game extends Observable {

    private int nbBombe;
    private int compteurBombe;
    private Grille grille;
    private int compteurCaseAction;
    private Personnage p;
    private boolean premierCoup;

    /**
     * Constructeur
     *
     * @param hauteur
     * @param largeur
     * @param nbBombe
     */
    public Game(int hauteur, int largeur, int nbBombe) {
        this.nbBombe = nbBombe;
        this.grille = new Grille(hauteur, largeur);
        this.compteurCaseAction = 0;
        this.compteurBombe = this.nbBombe;
        this.premierCoup = true;
        this.genererPlateau();
        this.updateVoisins();
        this.p = new Personnage(0, -1, this);
    }

    /**
     * Accesseur du compteur de bombe
     *
     * @return
     */
    public int getCompteurBombe() {
        return compteurBombe;
    }

    /**
     * Accesseur de la grille
     *
     * @return
     */
    public Grille getGrille() {
        return grille;
    }

    public int getNbBombes() {
        return this.nbBombe;
    }

    /**
     * Initialisation de l'observer
     *
     * @param gameGraph
     */
    public void initialisationObserver(GameGraph gameGraph) {
        this.addObserver(gameGraph);
    }

    /**
     * Reconstruit un plateau selon de nouveaux paramètre
     *
     * @param hauteur
     * @param largeur
     * @param nbBombe
     */
    public void reinitialisation(int hauteur, int largeur, int nbBombe) {
        this.nbBombe = nbBombe;
        this.grille = new Grille(hauteur, largeur);
        this.recommencer();

    }

    /**
     * Reconstruit un plateau pour une nouvelle partie
     */
    public void recommencer() {
        this.genererPlateau();
        this.updateVoisins();
        this.compteurCaseAction = 0;
        this.compteurBombe = this.nbBombe;
        this.premierCoup = true;

        this.p.setX(0);
        this.p.setY(-1);
    }

    /**
     * Construit le plateau
     */
    public void genererPlateau() {
        for (int i = 0; i < this.grille.getHauteur(); i++) {
            for (int j = 0; j < this.grille.getLargeur(); j++) {

                this.grille.nouvelleCaseAt(i, j);
            }
        }
        Random rand = new Random();
        int i = 0;
        int alea1;
        int alea2;
        while (i < this.nbBombe) {
            alea1 = rand.nextInt(this.grille.getHauteur());
            alea2 = rand.nextInt(this.grille.getLargeur());
            if (!this.grille.getCaseAt(alea1, alea2).isEstMinee()) {
                i++;
                this.grille.getCaseAt(alea1, alea2).setEstMinee(true);
            }
        }
    }

    /**
     * Met à jour chaque case afin de compter le nombre de bombe parmis ses
     * voisins
     */
    public void updateVoisins() {
        int compteur = 0;
        for (int i = 0; i < this.grille.getHauteur(); i++) {
            for (int j = 0; j < this.grille.getLargeur(); j++) {
                compteur = 0;
                ArrayList<Case> voisins = this.getVoisins(this.grille.getCaseAt(i, j));
                for (Case c : voisins) {
                    if (c.isEstMinee()) {
                        compteur++;
                    }
                }
                this.grille.getCaseAt(i, j).setNbBombesAutour(compteur);
            }
        }
    }

    /**
     * Retourne les voisins d'une case
     *
     * @param maCase
     * @return
     */
    public ArrayList<Case> getVoisins(Case maCase) {
        ArrayList<Case> listeVoisins = new ArrayList<Case>();
        Point point = this.grille.getPoint(maCase);
        if (point.getX() != 0) {
            listeVoisins.add(this.grille.getCaseAt(point.getX() - 1, point.getY()));
            if (point.getY() != 0) {
                listeVoisins.add(this.grille.getCaseAt(point.getX() - 1, point.getY() - 1));
            }
            if (point.getY() != this.grille.getLargeur() - 1) {
                listeVoisins.add(this.grille.getCaseAt(point.getX() - 1, point.getY() + 1));
            }
        }
        if (point.getX() != this.grille.getHauteur() - 1) {
            listeVoisins.add(this.grille.getCaseAt(point.getX() + 1, point.getY()));
            if (point.getY() != 0) {
                listeVoisins.add(this.grille.getCaseAt(point.getX() + 1, point.getY() - 1));
            }
            if (point.getY() != this.grille.getLargeur() - 1) {
                listeVoisins.add(this.grille.getCaseAt(point.getX() + 1, point.getY() + 1));
            }
        }
        if (point.getY() != 0) {
            listeVoisins.add(this.grille.getCaseAt(point.getX(), point.getY() - 1));
        }
        if (point.getY() != this.grille.getLargeur() - 1) {
            listeVoisins.add(this.grille.getCaseAt(point.getX(), point.getY() + 1));
        }
        return listeVoisins;
    }

    /**
     * Effectue les actions lors d'un clique ou d'un déplacement sur une case
     *
     * @param maCase
     */
    public void actionSurLaCase(Case maCase) {
        if (this.premierCoup) {
            this.premierCoup = false;
            if (maCase.isEstMinee()) {
                boolean worked = false;
                int alea1;
                int alea2;
                Random rand = new Random();
                while (!worked) {
                    alea1 = rand.nextInt(this.grille.getHauteur());
                    alea2 = rand.nextInt(this.grille.getLargeur());
                    if (!this.grille.getCaseAt(alea1, alea2).isEstMinee() && this.grille.getPoint(maCase).getX() != alea1 && this.grille.getPoint(maCase).getY() != alea2) {
                        this.grille.getCaseAt(alea1, alea2).setEstMinee(true);
                        worked = true;
                    }
                }
                this.premierCoup = false;
                maCase.setEstMinee(false);
                updateVoisins();
            }
        }
        this.p.setX(this.grille.getPoint(maCase).getX());
        this.p.setY(this.grille.getPoint(maCase).getY());
        if (maCase.isEstVisible()) {
            int nbDrapeau = 0;
            ArrayList<Case> voisins = this.getVoisins(maCase);

            for (int i = 0; i < voisins.size(); i++) {
                if (voisins.get(i).isDrapeau()) {
                    nbDrapeau++;
                }
            }
            if (nbDrapeau == maCase.getNbBombesAutour()) {
                for (int i = 0; i < voisins.size(); i++) {
                    if (!voisins.get(i).isEstVisible() && !voisins.get(i).isDrapeau()) {
                        this.actionSurLaCase(voisins.get(i));
                    }
                }
            }
            this.setChanged();
            this.notifyObservers();

        } else {
            if (maCase.isEstMinee()) {
                maCase.action();
                this.setChanged();
                this.notifyObservers(true);
            } else if (maCase.getNbBombesAutour() == 0) {
                this.etendreCase(maCase);
                this.setChanged();
                this.notifyObservers();
            } else {
                maCase.action();
                this.compteurCaseAction++;
                this.setChanged();
                this.notifyObservers();
            }
            if (!maCase.isEstMinee() && this.compteurCaseAction + this.compteurBombe == this.grille.getLargeur() * this.grille.getHauteur()) {
                this.setChanged();
                this.notifyObservers(false);
            }
        }
    }

    /**
     * Etend la visibilitée des cases selon les règles classique du démineur
     *
     * @param maCase
     */
    public void etendreCase(Case maCase) {

        if (maCase.getNbBombesAutour() == 0) {
            maCase.action();
            this.compteurCaseAction++;
            ArrayList<Case> voisins = this.getVoisins(maCase);

            for (int i = 0; i < voisins.size(); i++) {
                if (!voisins.get(i).isEstVisible() && !voisins.get(i).isDrapeau()) {
                    this.etendreCase(voisins.get(i));
                }
            }

        } else if (!maCase.isEstMinee()) {
            this.compteurCaseAction++;
            maCase.action();
        }

    }

    /**
     * Effectue les actions lors de la mise d'un drapeau sur une case
     *
     * @param maCase
     */
    public void drapeauSurLaCase(Case maCase) {
        Point point = this.grille.getPoint(maCase);
        this.grille.getCaseAt(point.getX(), point.getY()).actionDrapeau();
        if (maCase.isDrapeau()) {
            this.compteurBombe--;
            this.compteurCaseAction++;
        } else {
            this.compteurBombe++;
            this.compteurCaseAction--;
        }
        if (this.compteurCaseAction + this.compteurBombe == this.grille.getLargeur() * this.grille.getHauteur()) {
            this.setChanged();
            this.notifyObservers(false);
        } else {
            this.setChanged();
            this.notifyObservers();
        }
    }

    public int getPX() {
        return this.p.getX();
    }

    public int getPY() {
        return this.p.getY();
    }

    public void deplacementGauche() {
        this.p.deplacementGauche();
        this.setChanged();
        this.notifyObservers();
    }

    public void deplacementBas() {
        this.p.deplacementBas();
        this.setChanged();
        this.notifyObservers();
    }

    public void deplacementDroite() {
        this.p.deplacementDroite();
        this.setChanged();
        this.notifyObservers();
    }

    public void deplacementHaut() {
        this.p.deplacementHaut();
        this.setChanged();
        this.notifyObservers();
    }
}
