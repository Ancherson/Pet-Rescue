
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
		for(int i = 0; i < t.length; i++) {
			for(int j = 0; j < t[i].length; j++) {
				//TO DO: Faire un if pour voire si c'est un animal, pour plus tard
				cells[i][j] = new Bloc(i, j, t[i][j]);
			}
		}
	}
	
	public Plateau(int niveau) {
		this(readFile(niveau));
	}
	
	//TO DO: améliorer l'affichage pour plus tard
	public void afficheT() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].afficheT();
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
			for(int i = 0; i < t.length; i++) {
				String s = liste.get(i);
				for(int j = 0; j < t[i].length; j++) {
					t[i][j] = (s.charAt(j)) - 48;
				}
			}
			return t;
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error readFile " + "./niveaux/niveau" + n + ".txt", e);
		}
	}
	
	public boolean correctInput(int i, int j) {
		return !(j < 0 || j >= cells[0].length || i < 0 || i >= cells.length);
	}
	
	public void explose(int i, int j, int color) {
		if(!correctInput(i, j)) return;
		if(cells[i][j].explose(color)) {;
			explose(i + 1, j, color);
			explose(i - 1, j, color);
			explose(i, j + 1, color);
			explose(i, j - 1, color);
		}
	}
}
