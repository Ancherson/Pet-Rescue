package jeu;

//Cette classe représente toutes les cellules du plateau, elle est étendue par Bloc, Pet, Mur
//Cette classe à elle toute seule représente un case vide
//Nombreuses des méthodes sont redéfinies dans les sous-classes
public class Cell {
	
	//i et j represente la position dans le plateau (i la ligne et j la colonne) 
	//ils sont utile pour l'affichage dans l'affichage dans l'interface graphique
	protected int i;
	protected int j;
	
	public Cell(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	//actualise i et j lors d'un déplacement dans plateau
	public void change(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	//Toutes les méthodes ci-dessous seront redéfinits dans les sous-classes
	
	public boolean explose(int color) {
		return false;
	}
	public int getColor() {
		return 0;
	}
	public boolean estVide() {
		return true;
	}
	
	public boolean estMur() {
		return false;
	}
	public boolean estPet() {
		return false;
	}
	public int getXOff() {
		return 0;
	}
	public int getYOff() {
		return 0;
	}
	public void clearOffset() {
		//rien
	}
	public Cell clone() {
		return new Cell(i,j);
	}
}
