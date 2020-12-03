import java.util.Scanner;

//Cette Classe permet l'interaction entre le Joueur et le Jeu à travers le terminal

public class TerminalInteracteur implements Interacteur{
	private Scanner sc = new Scanner(System.in);
	private Jeu jeu;
	private int maxLevel = Jeu.TOT_LEVEL; 
	
	public TerminalInteracteur(Jeu j) {
		this.jeu = j;
	}
	

	@Override
	public void setMaxLevel(int levelMax) {
		this.maxLevel = levelMax;
	}
	
	@Override
	public void start() {
		String nom = quelNom();
		jeu.newJoueur(nom);
		int level = quelLevel();
		jeu.start(new Plateau(level));
	}
	
	public void restart() {
		int level = quelLevel();
		jeu.start(new Plateau(level));
	}

	public String quelNom() {
		System.out.println("Quel est votre nom ?");
		return sc.next();
	}

	public int quelLevel() {
		System.out.println("Quel level (entre 1 et " + maxLevel + ")");
		int i = 0;
		boolean pbm = true;
		while(pbm || !(i <= maxLevel  && i > 0)) {
			pbm = false;
			String s = sc.next();
			try {
				i = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				System.out.println("NON");
				pbm = true;
			}
		}
		return i;
	}
	
	public int[] quellesCases() {
		System.out.println("Quel Case ?");
		int i = -1;
		boolean pbm = true;
		while(pbm) {
			pbm = false;
			String s = sc.next();
			try {
				i = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				pbm = true;
			}
		}
		
		int j = -1;
	    pbm = true;
		while(pbm) {
			pbm = false;
			String s = sc.next();
			try {
				j = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				pbm = true;
			}
		}
		int[]res = {i,j};
		return res;
	}

	@Override
	public void prochainCoup() {
		int[]cases = this.quellesCases();
		int i = cases[0];
		int j = cases[1];
		
		this.jeu.turn(i, j);
		
		while(this.jeu.rescue()) {
			this.jeu.move();
		}
		
		if(jeu.finished()) {
			jeu.finDePartie();
		}else {
			jeu.next();
		}
		
//		this.j.turn(i,j);
//		while(this.j.rescue()) {}
//		if(this.j.finished()) this.j.finDePartie();
//		else this.j.next();
	
	}
	
	public void veutRejouer() {
		System.out.println("Veux-tu rejouer ? (oui/non)");
		String s = "";
		do {
			s = sc.next();
		}while(!(s.equals("oui") || s.equals("non")));
		
		if(s.equals("oui")) this.restart();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
