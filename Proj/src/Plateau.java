
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.Graphics;

public class Plateau {
	private Cell[][] cells;
	public int totPet = 0;
	private LinkedList<Mur> murs = new LinkedList<Mur>();

	private Plateau(int[][]t) {
		cells = new Cell[t.length][t[0].length];
		for(int i = 0; i < t.length; i++) {
			for(int j = 0; j < t[i].length; j++) {
				switch(t[i][j]) {
					case -2: 
						cells[i][j] = new Pet(i,j); 
						totPet++; 
						break;
					case -1: 
						Mur m = new Mur(i, j);
						murs.add(m);
						cells[i][j] = m;
						break;
					case 0 :
						cells[i][j] = new Cell(i, j);
						break;
					default: 
						cells[i][j] = new Bloc(i, j, t[i][j]);
				}
			}
		}
	}
	
	public Plateau(int niveau) {
		this(readFile(niveau));
	}
	
	public boolean estVide(int i, int j) {
		return cells[i][j].estVide();
	}
	
	public boolean estMur(int i, int j) {
		return cells[i][j].estMur();
	}
	public int getLargeur() {
		return cells[0].length;
	}
	public int getHauteur() {
		return cells.length;
	}
	
	//TO DO: améliorer l'affichage pour plus tard
	public void afficheT() {
		System.out.println("# ".repeat(cells[0].length + 2));
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(j == 0) System.out.print("# ");
				cells[i][j].afficheT();
				System.out.print(" ");
			}
			System.out.println("# ");
		}
		System.out.println("# ".repeat(cells[0].length + 2));
	}
	
	public void afficherG(Graphics g, int scl) {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].afficheG(g, scl);
			}
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
					if(s.charAt(j) == '@') t[i][j] = -2;
					else if(s.charAt(j) == '#') t[i][j] = -1;
					else if(s.charAt(j) == '0') t[i][j] = 0;
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
		return totPet == 0 || !canPlay();
	}
	
	public boolean aGagne() {
		return totPet == 0;
	}

	
	private boolean canPlay() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(canExplose(i, j)) return true;
			}
		}
		return false;
	}
	
	public boolean correctInput(int i, int j) {
		return !(j < 0 || j >= cells[0].length || i < 0 || i >= cells.length);
	}
	
	public boolean canExplose(int i, int j) {
		if(!correctInput(i,j)) return false;
		if(cells[i][j].estMur() || cells[i][j].estVide() || cells[i][j].estPet()) return false;
		int color = cells[i][j].getColor();
		if(correctInput(i - 1, j) && color == cells[i - 1][j].getColor()) return true;
		if(correctInput(i + 1, j) && color == cells[i + 1][j].getColor()) return true;
		if(correctInput(i, j - 1) && color == cells[i][j - 1].getColor()) return true;
		if(correctInput(i, j + 1) && color == cells[i][j + 1].getColor()) return true;
		return false;
	}
	
	public int explose(int i, int j) {
		if(canExplose(i,j)) {
			return explose(i,j,cells[i][j].getColor());
		}
		return 0;
	}
	
	public int explose(int i, int j, int color) {
		if(!correctInput(i, j)) return 0;
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
		
		cells[i1][j1].change(i1, j1);
		cells[i2][j2].change(i2, j2);
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
	
	
	//Fonction left pour les blocs murs, Il faudra faire la fonction left pour la derniere ligne
	public boolean left1() {
		boolean b = false;
		for(Mur mur : murs) {
			int[] coord = mur.getIJ();
			int i = coord[0];
			int j = coord[1];
			if((correctInput(i, j - 1) && cells[i][j - 1].estVide()) || (correctInput(i, j - 1) && cells[i][j - 1].estMur() && correctInput(i - 1,j - 1) && cells[i - 1][j - 1].estVide())) {
				
				b = deplaceLeft(i - 1, j) || b;
			}
		}
		return b;
	}
	
	//fonction left pour la derniere ligne
	public boolean left2() {
		boolean b = false;
		for(int j = 1; j < cells[0].length; j++) {
			if(cells[cells.length - 1][j - 1].estVide() && !cells[cells.length - 1][j].estVide() && !cells[cells.length -1][j].estMur()) {
				b = deplaceLeft(cells.length - 1,j) || b;
			}
		}
		return b;
	}
	
	public boolean deplaceLeft(int ii, int j) {
		boolean b = false;
		for(int i = ii; i >= 0; i--) {
			if(cells[i][j].estMur() || cells[i][j].estVide()) break;
			if(cells[i][j - 1].estVide()) {
				swap(i,j,i,j-1);
				b = true;
			}
			
		}
		fall();
		return b;
	}
	
	
	public void left() {
		boolean l1 = true;
		boolean l2 = true;
		while(l1 || l2) {
			l1 = left1();
			l2 = left2();
		}
	}
	
	public boolean rescue(Joueur joueur) {
		boolean b = false;
		for(int j = 0; j < cells[0].length; j++) {
			if(cells[cells.length - 1][j].estPet()) {
				joueur.addScore(10);
				cells[cells.length - 1][j] = new Cell(cells.length - 1,j);
				totPet--;
				b = true;
			}
		}
		return b;
	}
	
	
	// à la fin de la partie on compte le nombre total de blocs restant et on met ce nombre au carré puis on le multiplie par 10,
	// cela permet d'encourager le joueur à faire le moins de coups possible !
	public void explosionFinale(Joueur joueur) {
		int score = 0;
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(!cells[i][j].estVide() && !cells[i][j].estMur()) {
					score++;
				}
			}
		}
		joueur.addScore(score);
	}
	
}
