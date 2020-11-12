import java.util.Scanner;

public class TerminalAfficheur implements Afficheur{
	
	Scanner sc = new Scanner(System.in);
	
	@Override
	public void afficher(Plateau p) {
		p.afficheT();
	}
}
