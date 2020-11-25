import java.util.Scanner;

public class Jeu {
	Joueur joueur;
	Plateau p;
	Afficheur afficheur;
	
	public Jeu() {
		this.demmandeInterface();
	}
	
	public Plateau getPlateau() {
		return p;
	}
	
	public void demmandeInterface() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel Interface voulez-vous utiliser ? (1 -> Terminal | 2 -> Interface Graphique | 3 -> Robot)");
		
		int i = 0;
		boolean pbm = true;
		while(pbm || (i != 1 && i != 2 && i != 3)) {
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
	
	public void start(String name, Plateau p) {
		joueur.quelNom(name);
		this.p = p;
		next();
	}
	
	/*public void turn(int ii, int jj) {
		int score = p.explose(ii, jj);
		joueur.addScore(score);
		p.fall();
		p.left();
		while(p.rescue(joueur)) {
			p.fall();
			p.left();
		}
		
		if(finished()) {
			if(p.aGagne()) p.explosionFinale(joueur);
			afficheur.afficherP(p);
			afficheur.afficheScore(joueur);
			afficheur.afficheFinDePartie(p, joueur);
		}else {
			next();
		}
	}*/
	
	public void turn(int i, int j) {
		joueur.addScore(p.explose(i, j));
		p.fall();
		p.left();
	}
	
	public boolean rescue() {
		boolean b = p.rescue(joueur);
		p.fall();
		p.left();
		return b;
	}
	
	public void finDePartie() {
		if(p.aGagne()) p.explosionFinale(joueur);
		afficheur.afficherP(p);
		afficheur.afficheScore(joueur);
		afficheur.afficheFinDePartie(p, joueur);
	}
	
	public void next() {
		afficheur.afficherP(p);
		afficheur.afficheScore(joueur);
		joueur.prochainCoup();
	}
	
	public boolean finished() {
		return p.levelIsOver();
	}
}
