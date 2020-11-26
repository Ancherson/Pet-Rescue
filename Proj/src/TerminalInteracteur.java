import java.util.Scanner;

public class TerminalInteracteur implements Interacteur{
	private Scanner sc = new Scanner(System.in);
	private Jeu j;
	
	public TerminalInteracteur(Jeu j) {
		this.j = j;
	}
	
	@Override
	public void start() {
		String nom = quelNom();
		int level = quelLevel();
		j.start(nom, new Plateau(level));
	}

	public String quelNom() {
		System.out.println("Quel est votre nom ?");
		return sc.next();
	}

	public int quelLevel() {
		System.out.println("Quel level (entre 1 et 1)");
		int i = 0;
		boolean pbm = true;
		while(pbm || !(i < 3 && i > 0)) {
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

	@Override
	public void prochainCoup() {
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
		
		this.j.turn(i,j);
		while(this.j.rescue()) {}
		if(this.j.finished()) this.j.finDePartie();
		else this.j.next();
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
