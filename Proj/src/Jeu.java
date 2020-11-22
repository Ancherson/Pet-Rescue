import java.util.Scanner;

public class Jeu {
	Joueur j;
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
			j = new Humain(inter);
		}
		else if(i == 3) {
			afficheur = new TerminalAfficheur();
			j = new Robot(this);
		} else {
			Visuelle v = new Visuelle(this);
			afficheur = (Afficheur) v;
			j = new Humain(v);
		}
		
		j.start();
	}
	
	public void start(String name, Plateau p) {
		j.quelNom(name);
		this.p = p;
		next();
	}
	
	public void next() {
		afficheur.afficherP(p);
		afficheur.afficheScore(j);
		j.prochainCoup();
	}
	
	public void turn(int ii, int jj) {
		int score = p.explose(ii, jj);
		j.addScore(score);
		p.fall();
		p.left();
		while(p.rescue(j)) {
			p.fall();
			p.left();
		}
		
		if(finished()) {
			System.out.println("A FINI");
			if(p.aGagne()) p.explosionFinale(j);
			afficheur.afficherP(p);
			afficheur.afficheScore(j);
			afficheur.afficheFinDePartie(p, j);
		}else {
			next();
		}
	}
	
	public boolean finished() {
		return p.levelIsOver();
	}
}
