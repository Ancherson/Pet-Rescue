import java.util.Scanner;

public class Jeu {
	Joueur j;
	Plateau p;
	Afficheur afficheur;
	
	public Jeu() {
		demmandeInterface();
		start();
	}
	
	private void demmandeInterface() {
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
		sc.close();
		if(i == 1) {
			afficheur = new TerminalAfficheur();
			j = new Humain(new TerminalInteracteur());
		}
		else if(i == 3) {
			afficheur = new TerminalAfficheur();
			j = new Robot();
		}
		//Faudra Renvoyer une instance de l'interface graphique
	}
	
	public void start() {
		j.quelNom();
		p = j.quelLevel();
		run();
	}
	
	public void turn() {
		afficheur.afficherP(p);
		afficheur.afficheScore(j);
		int[]coord = j.quelleCase();
		int score = p.explose(coord[0], coord[1]);
		j.addScore(score);
		p.fall();
		p.left();
		while(p.rescue(j)) {
			p.fall();
			p.left();
		}

	}
	
	public void run() {
		while(!finished()) {
			turn();
		}
		if(p.aGagne()) p.explosionFinale(j);
		
		afficheur.afficheFinDePartie(p, j);
	}
	
	public boolean finished() {
		return p.levelIsOver();
	}
}
