import java.util.Scanner;

public class InteractionText implements Interaction{
	Joueur j;
	Plateau p;
	Scanner sc = new Scanner(System.in);
	
	public void QuelEstTonNom() {
		System.out.println("Quel est ton nom ?");
		j = new Joueur(sc.next());
	}
	
	public void QuelLevel() {
		System.out.println("Quel level ?");
		p = new Plateau(sc.nextInt());
	}
	
	public int[] demande() {
		int i = sc.nextInt();
		int j = sc.nextInt();
		while(!p.canExplose(i, j)) {
			System.out.println("Veuillez Donner des Coordonnées Cohérentes");
		}
		int[]t = {i,j};
		return t;
	}
	
	public void turn() {
		p.afficheT();
		int[]coord = demande();
		j.addScore(p.explose(coord[0], coord[1], -1));
		p.fall();
		//Faire p.left()
		//Vérifier si des Pet sont en bas et les enlever, puis ajouter au score du joueur
		//refaire p.left()
	}
	
	public boolean finished() {
		return p.levelIsOver();
	}
}
