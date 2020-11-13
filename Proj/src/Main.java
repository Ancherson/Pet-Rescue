import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Plateau p = new Plateau(2);
		Joueur j = new Joueur("truc");
		p.afficheT();
		//System.out.println(p.explose(1, 1));
		System.out.println(p.explose(0, 0));
		p.fall();
		p.left();
		p.afficheT();
		
		System.out.println(p.explose(3, 0));
		p.fall();
		p.left();
		p.afficheT();
		
		System.out.println(p.explose(2, 5));
		p.fall();
		p.left();
		p.afficheT();
		
	}

}
