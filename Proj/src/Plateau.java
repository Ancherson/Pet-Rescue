
import java.io.File;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JPanel;

import java.awt.Graphics;

//Cette classe représente le plateau de jeu (la grille)

public class Plateau {
	private int numLevel;
	
	//Tableau contenant les cellules (Les blocs, murs et animal sont des cellules par héritage)
	private Cell[][] cells;
	private int totPet = 0;
	
	//Total coups restants (quand coup < 0 veut dire une infinité)
	private int coup = -1;
	
	//On stocke les murs (bloc fixe) pour ne pas à avoir à les chercher à chaque on parcourant cells
	private LinkedList<Mur> murs = new LinkedList<Mur>();

	//Ce constructeur contruit un plateau à partir d'un entier qui va ensuite chercher le fichier niveauNUM.txt
	public Plateau(int niveau) {
		numLevel = niveau;
		try {
			Scanner sc = new Scanner(new File("./niveaux/niveau" + niveau + ".txt"));
			sc.useDelimiter("\n");
			LinkedList<String> liste = new LinkedList<String>();
			while(sc.hasNext()) {
				liste.add(sc.next());
			}
			
			coup = Integer.parseInt(liste.removeFirst());
			
			cells = new Cell[liste.size()][liste.get(0).length()];
			for(int i = 0; i < cells.length; i++) {
				String s = liste.get(i);
				for(int j = 0; j < cells[i].length; j++) {
					switch(s.charAt(j)) {
						case '@' : 
							cells[i][j] = new Pet(i, j);
							totPet++;
							break;
						case '#' :
							Mur m = new Mur(i, j);
							murs.add(m);
							cells[i][j] = m;
							break;
						case '0' :
							cells[i][j] = new Cell(i, j);
							break;
						default: 
							cells[i][j] = new Bloc(i, j, s.charAt(j) - 48);
						
					}
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error readFile " + "./niveaux/niveau" + niveau + ".txt", e);
		}
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
	public int getCoup() {
		return coup;
	}
	public int getLevel() {
		return numLevel;
	}
	
	//Permet de savoir si la partie est fini
	public boolean levelIsOver(Joueur joueur) {
		return totPet == 0 || !canPlay(joueur) || coup == 0;
	}
	
	//Savoir si le joueur a gagné
	public boolean aGagne() {
		return totPet == 0;
	}

	//Savoir si le joueur peut encore jouer
	private boolean canPlay(Joueur joueur) {
		if(joueur.getFusee() > 0) return true;
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(canExplose(i, j)) return true;
			}
		}
		return false;
	}
	
	
	//Savoir si i et j correspondent à une case dans le tableau cells
	//Permet d'éviter les cas où i et j sont en dehors du tableau
	public boolean correctInput(int i, int j) {
		return !(j < 0 || j >= cells[0].length || i < 0 || i >= cells.length);
	}
	
	//Savoir si un bloc peut être explosé
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
	
	//fonction qui explose les blocs, et qui appelle la 2eme fonction explose qui explose tous les blocs qui ont la même couleur
	public int explose(int i, int j) {
		if(canExplose(i,j)) {
			coup--;
			return explose(i,j,cells[i][j].getColor());
		}
		return 0;
	}
	
	//fonction qui explose le bloc (i,j) si sa couleur est la meme que color
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
	
	//permet d'echanger les bloc de place
	private void swap(int i1, int j1, int i2, int j2) {
		Cell tmp = cells[i1][j1];
		cells[i1][j1] = cells[i2][j2];
		cells[i2][j2] = tmp;
		
		cells[i1][j1].change(i1, j1);
		cells[i2][j2].change(i2, j2);
	}
	
	//permet de savoir si un bloc peut chuter
	private boolean canFall(int i, int j) {
		return (i < cells.length - 1 && !cells[i][j].estMur() && !cells[i][j].estVide() && cells[i + 1][j].estVide());
	}
	
	//fait chuter un bloc jusqu'à ce qu'on ne puisse plus les faire chuter
	private void cellFall(int i, int j) {
		while(canFall(i, j)) {
			swap(i,j,i+1,j);
			i++;
		}
	}
	
	//fait chuter les blocs d'une colonne
	private void colonneFall(int j) {
		for(int i = cells.length - 1; i >= 0; i--) {
			cellFall(i,j);
		}
	}
	
	//fait chuter les blocs
	public void fall() {
		for(int j = 0; j < cells[0].length; j++) {
			colonneFall(j);
		}
	}
	
	
	//Fonction left pour les blocs murs
	//si a gauche du bloc mur y'a rien alors déplacer sur la gauche la pile de bloc au dessus
	//si a gauche du bloc mur, un autre bloc mur et au dessus de bloc mur y'a rien alors déplacer sur la gauche la pile de bloc au dessus
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
	//si un bloc et a gauche rien alors deplacer sur la gauche la pile de blocs
	public boolean left2() {
		boolean b = false;
		for(int j = 1; j < cells[0].length; j++) {
			if(!cells[cells.length -1][j].estPet()  && cells[cells.length - 1][j - 1].estVide() && !cells[cells.length - 1][j].estVide() && !cells[cells.length -1][j].estMur()) {
				b = deplaceLeft(cells.length - 1,j) || b;
			}
		}
		return b;
	}
	
	
	//deplace sur la gauche les pile de blocs
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
	
	//fonction permettant de le déplacement des blocs sur la gauche
	//tant qu'il y a un déplacement à faire
	public void left() {
		boolean l1 = true;
		boolean l2 = true;
		while(l1 || l2) {
			l1 = left1();
			l2 = left2();
		}
	}
	
	//sauve les animaux qui sont sur la derniere ligne
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
	
	public void fusee(int j) {
		for(int i = 0; i < cells.length; i++) {
			if(cells[i][j] instanceof Bloc) {
				cells[i][j] = new Cell(i,j);
			}
		}
	}
	
	// à la fin de la partie on compte le nombre total de blocs restant et on met ce nombre au carré puis on le multiplie par 10,
	// cela permet d'encourager le joueur à faire le moins de coups possible !
	public void explosionFinale(Joueur joueur) {
		int score = 0;
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(!cells[i][j].estVide() && !cells[i][j].estMur()) {
					cells[i][j] = new Cell(i, j);
					score++;
				}
			}
		}
		joueur.addScore(score);
	}
	
	public Cell[][] copy() {
		Cell[][] copyCells = new Cell[cells.length][cells[0].length];
		for(int i = 0; i < copyCells.length; i++) {
			for(int j = 0; j < copyCells[i].length; j++) {
				copyCells[i][j] = cells[i][j].clone();
			}
		}
		return copyCells;
	}
	
	public void clearOffset() {
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].clearOffset();
			}
		}
	}
	
}
