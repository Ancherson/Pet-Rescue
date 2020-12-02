import java.awt.Color;
import java.awt.Graphics;

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
	
	//Affichage dans le terminal
	public void afficheT() {
		System.out.print(" ");
	}
	
	//Affichage dans l'interface graphique
	public void afficheG(Graphics g) {
		
	}
	
	//Toutes les méthodes ci-dessous seront redéfinits dans les sous-classes
	public boolean isMoving() {
		return false;
	}
	
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
}
