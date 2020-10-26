import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Quelle Mode de Jeu ? (1 => visuel , 2 => textuel)");
		Scanner sc = new Scanner(System.in);
		int res = sc.nextInt();
		InteractionText i = new InteractionText();
		i.QuelEstTonNom();
		i.QuelLevel();
		while(!i.finished()) {
			i.turn();
		}
	}

}
