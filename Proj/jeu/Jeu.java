package jeu;

import java.util.Scanner;

//Cette Classe permet de regrouper un peu toutes les classe pour qu'elles interagissent entre elles

public class Jeu {
	//Le joueur est soit un Robot ou un Humain
	private Joueur joueur;
	private Plateau p;
	private Afficheur afficheur;
	
	public final static int TOT_LEVEL = 8;
	
	public Jeu() {
		this.demmandeInterface();
	}
	
	public Plateau getPlateau() {
		return p;
	}
	
	//Cette fonction demande qu'elle interface à utiliser
	private void demmandeInterface() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel Interface voulez-vous utiliser ?\n(1 -> Terminal | 2 -> Interface Graphique | 3 -> Robot)");
		
		int i = 0;
		boolean pbm = true;
		while(pbm || (i <= 0 || i > 3)) {
			pbm = false;
			String s = sc.next();
			try {
				i = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				pbm = true;
			}
		}
		if(i == 1) {
			afficheur = new TerminalAfficheur();
			Interacteur inter = new TerminalInteracteur(this);
			joueur = new Humain(inter);
		}
		else if(i == 3) {
			afficheur = new TerminalAfficheur();
			joueur = new Robot(this);
		} else {
			Visuelle v = new Visuelle(this);
			afficheur = (Afficheur) v;
			joueur = new Humain(v);
		}
		
		joueur.start();
	}
	
	public void newJoueur(String name) {
		joueur.quelNom(name);
	}
	
	public int getFusee() {
		return joueur.getFusee();
	}
	
	//Fonction pour lancer le jeu
	public void start(Plateau p) {
		this.p = p;
		affiche();
		next();
	}
	
	//Fonction pour deplacer les blocs
	public void move() {
		p.clearOffset();
		p.fall();
		p.left();
		affiche();
	}
	
	public void fusee(int j) {
		if(joueur.getFusee() > 0) {
			p.fusee(j);
			joueur.enleveFusee();
			move();
		}
	}
	
	//Fonction pour jouer dans la case i,j du plateau
	public void turn(int i, int j) {
		joueur.addScore(p.explose(i, j));
		move();
	}
	
	//Foncion pout afficher les infos utiles pour le joueur (plateau, score, coups restants)
	public void affiche() {
		afficheur.afficherP(p);
		afficheur.afficheCoup(p);
		afficheur.afficheFusee(joueur);
		afficheur.afficheScore(joueur);
	}
	
	//Fonction permettant de sauver les animaux qui se trouvent sur la derniere ligne
	//renvoie true pour dire qu'on en a sauvés
	public boolean rescue() {
		boolean b = p.rescue(joueur);
		return b;
	}
	
	//Fonction pour lancer la fin du jeu
	public void finDePartie() {
		if(p.aGagne()) {
			p.explosionFinale(joueur);
			joueur.nextLevel(p.getLevel());
		}
		joueur.saveBest(p.getLevel());
		if(joueur instanceof Humain) {
			Humain h = (Humain)joueur;
			h.save();
		}
		afficheur.afficherP(p);
		afficheur.afficheScore(joueur);
		afficheur.afficheFinDePartie(p, joueur);
		joueur.veutRejouer();
	}
	
	//Fonction pour demander au joueur son prochain coup
	public void next() {
		joueur.prochainCoup();
	}
	
	//fonction disant si oui ou non une partie est finie
	public boolean finished() {
		return p.levelIsOver(joueur);
	}
}
