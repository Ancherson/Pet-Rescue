import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Plateau {
	private Cell[][] cells;
	
	//Peut être Retirer ?
	private Plateau(Cell[][]c) {
		cells = c;
	}
	
	private Plateau(int[][]t) {
		cells = new Cell[t.length][t[0].length];
		for(int y = 0; y < t.length; y++) {
			for(int x = 0; x < t[y].length; x++) {
				cells[y][x] = new Cell(t[y][x], x, y);
			}
		}
	}
	
	public Plateau(int niveau) {
		this(readFile(niveau));
	}
	
	//TO DO: améliorer l'affichage pour plus tard
	public void afficheT() {
		for(int y = 1; y < cells.length - 1; y++) {
			for(int x = 1; x < cells[y].length - 1; x++) {
				cells[y][x].afficheT();
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void afficheTout() {
		for(int y = 0; y < cells.length; y++) {
			for(int x = 0; x < cells[y].length; x++) {
				cells[y][x].afficheT();
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	private static int[][] readFile(int n) {
		try {
			Scanner sc = new Scanner(new File("./niveaux/niveau" + n + ".txt"));
			sc.useDelimiter("\n");
			LinkedList<String> liste = new LinkedList<String>();
			while(sc.hasNext()) {
				liste.add(sc.next());
			}
			//TO DO: Traiter le cas où la liste est vide
			int[][] t = new int[liste.size() + 2][liste.get(0).length() + 2];
			//TO DO or not TO DO: J'entoure de 0 pour faciliter plus tard les fonctions, mais peut-être changer ça plus tard
			for(int y = 0; y < t.length; y++) {
				String s = "";
				if(y != 0 && y != t.length - 1) s = liste.get(y - 1);
				for(int x = 0; x < t[y].length; x++) {
					if(y == 0 || y == t.length - 1 || x == 0 || x == t[y].length - 1) t[y][x] = 0;
					else t[y][x] = (s.charAt(x - 1)) - 48;
				}
			}
			return t;
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error readFile " + "./niveaux/niveau" + n + ".txt", e);
		}
	}
}
