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
	
	public void afficheT() {
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
			int[][] t = new int[liste.size()][liste.get(0).length()];
			for(int y = 0; y < t.length; y++) {
				String s = liste.get(y);
				for(int x = 0; x < t[y].length; x++) {
					t[y][x] = (s.charAt(x)) - 48;
				}
			}
			return t;
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error readFile " + "./niveaux/niveau" + n + ".txt", e);
		}
	}
}
