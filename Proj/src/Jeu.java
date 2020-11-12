import java.util.Scanner;

public class Jeu {
	Joueur j;
	Plateau p;
	Afficheur afficheur;
	Interacteur interacteur;
	
	public Jeu() {
		demmandeInterface();
		start();
	}
	
	private void demmandeInterface() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel Interface voulez-vous utiliser ? (1 -> Terminal | 2 -> Interface Graphique)");
		
		int i = 0;
		boolean pbm = true;
		while(pbm || (i != 1 && i != 2)) {
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
			interacteur = new TerminalInteracteur();
		}
		//Faudra Renvoyer une instance de l'interface graphique
	}
	
	public void start() {
		j = interacteur.quelNom();
		p = interacteur.quelLevel();
	}
	
	public void turn() {
		afficheur.afficher(p);
		int[]coord = interacteur.quelleCase();
		if(p.canExplose(coord[0], coord[1])) {
			j.addScore(p.explose(coord[0], coord[1], -1));
			p.fall();
		}
		//Faire p.left()
		//Vérifier si des Pet sont en bas et les enlever, puis ajouter au score du joueur
		//refaire p.left()
		
	}
	
	public void run() {
		while(!finished()) {
			turn();
		}
	}
	
	public boolean finished() {
		return p.levelIsOver();
	}
}
