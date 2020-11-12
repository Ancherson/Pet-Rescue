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
		while(pbm || (i < 1 && i > 1)) {
			pbm = false;
			String s = sc.next();
			try {
				i = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				pbm = true;
			}
		}
		return new Plateau(i);
	}

	@Override
	public int[] quelleCase() {
		// TODO Auto-generated method stub
		return null;
	}

}
