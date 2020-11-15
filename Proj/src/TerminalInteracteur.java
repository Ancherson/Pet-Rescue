import java.util.Scanner;

public class TerminalInteracteur implements Interacteur{
	private Scanner sc = new Scanner(System.in);

	@Override
	public Joueur quelNom() {
		System.out.println("Quel est votre nom ?");
		return new Joueur(sc.next());
	}

	@Override
	public Plateau quelLevel() {
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
		return new Plateau(i);
	}

	@Override
	public int[] quelleCase() {
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
		
		int[]t = {i,j};
		return t;
		
	}

}
