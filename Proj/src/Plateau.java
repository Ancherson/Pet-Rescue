
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Plateau {
	private Cell[][] cells;
	private int totPet = 0;
	
	//Peut être Retirer ?
	private Plateau(Cell[][]c) {
		cells = c;
	}
	
	private Plateau(int[][]t) {
		cells = new Cell[t.length][t[0].length];
		for(int i = 0; i < t.length; i++) {
			for(int j = 0; j < t[i].length; j++) {
				//TO DO: Faire un if pour voire si c'est un animal, pour plus tard
				switch(t[i][j]) {
					case -1: cells[i][j] = new Mur(); break;
					default: cells[i][j] = new Bloc(/*i, j, */t[i][j]);
				}
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
					if(s.charAt(j) == '#') t[i][j] = -1;
					else t[i][j] = (s.charAt(j)) - 48;
				}
			}
			return t;
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error readFile " + "./niveaux/niveau" + n + ".txt", e);
		}
	}
	
	public boolean levelIsOver() {
		return false;
		//return totPet == 0;
		//Il faudra vérifier également si le joueur peut encore jouer(si tout les blocs non vides ne peuvent être exploser)
	}
	
	public boolean correctInput(int i, int j) {
		return !(j < 0 || j >= cells[0].length || i < 0 || i >= cells.length);
	}
	
	public boolean canExplose(int i, int j) {
		if(!correctInput(i,j)) return false;
		if(cells[i][j].estMur() || cells[i][j].estVide()) return false;
		int color = cells[i][j].getColor();
		if(correctInput(i - 1, j) && color == cells[i - 1][j].getColor()) return true;
		if(correctInput(i + 1, j) && color == cells[i + 1][j].getColor()) return true;
		if(correctInput(i, j - 1) && color == cells[i][j - 1].getColor()) return true;
		if(correctInput(i, j + 1) && color == cells[i][j + 1].getColor()) return true;
		return false;
	}
	
	public int explose(int i, int j, int color) {
		if(!correctInput(i, j)) return 0;
		if(color == -1) color = cells[i][j].getColor();
		if(cells[i][j].explose(color)) {
			int tot = 1;
			tot += explose(i + 1, j, color);
			tot += explose(i - 1, j, color);
			tot += explose(i, j + 1, color);
			tot += explose(i, j - 1, color);
			return tot;
		}
		return 0;
	}
	
	private void swap(int i1, int j1, int i2, int j2) {
		Cell tmp = cells[i1][j1];
		cells[i1][j1] = cells[i2][j2];
		cells[i2][j2] = tmp;
	}
	
	private boolean canFall(int i, int j) {
		return (i < cells.length - 1 && !cells[i][j].estMur() && !cells[i][j].estVide() && cells[i + 1][j].estVide());
	}
	
	private void cellFall(int i, int j) {
		while(canFall(i, j)) {
			swap(i,j,i+1,j);
			i++;
		}
	}
	
	private void colonneFall(int j) {
		for(int i = cells.length - 1; i >= 0; i--) {
			cellFall(i,j);
		}
	}
	
	public void fall() {
		for(int j = 0; j < cells[0].length; j++) {
			colonneFall(j);
		}
	}
	
	/**
	 * FAIRE LA FONCTION QUI DECALE SUR LA GAUCHE LES BLOCS
	 * 
	 * L'idée (peut être d'autres subtilités): Pour chaque bloc Mur (peut être les stocker quelque part) regarder a gauche: 
	 * 	- si un bloc vide alors pour toute la suite de bloc audessus du bloc Mur, si cette suite peut aller sur la gauche, la déplacer sur la gauche
	 *  - si un bloc Mur, regarder audessus de ce bloc et si bloc vide alors pour toute la suite de bloc audessus du bloc Mur, si cette suite peut aller sur la gauche, la déplacer sur la gauche
	 *  - sinon ne rien faire
	 */
	
	
}
