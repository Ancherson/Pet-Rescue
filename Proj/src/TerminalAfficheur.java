import java.util.Scanner;

public class TerminalAfficheur implements Afficheur{
	
	Scanner sc = new Scanner(System.in);

	@Override
	public void afficherP(Plateau p) {
		p.afficheT();
	}

	@Override
	public void afficheScore(Joueur j) {
		j.affiche();
	}

	@Override
	public void afficheFinDePartie(Plateau p, Joueur j) {
		if(p.aGagne()) {
			System.out.println();
			System.out.println("#################");
			System.out.println("#               #");
			System.out.println("#     BRAVO !   #");
			System.out.println("#               #");
			System.out.println("#################");
		}else {
			System.out.println();
			System.out.println("#################");
			System.out.println("#               #");
			System.out.println("#     PERDU !   #");
			System.out.println("#               #");
			System.out.println("#################");
		}
		
		j.affiche();
	}
}
