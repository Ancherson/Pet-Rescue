import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * Cette classe représente les blocs colorés qui peuvent explosés
 *
 */

public class Bloc extends Cell{
	//représente la couleur du bloc
	private int color;
	
	//représente l'ensemble des couleurs pour l'affichage
	private static int[] assocCouleur = {0,1,2,3,4,5};
	private static Color[] colors = {Color.red, Color.green, Color.blue, Color.yellow, Color.MAGENTA, Color.cyan};
	private boolean vide = false;
	
	//xOff et yOff sont utiles pour l'affichage sur l'interface graphique
	private int xOff = 0;
	private int yOff = 0;
	
	public Bloc(int i, int j, int c) {
		super(i,j);
		color = c;
	}
	
	//Lorsqu'un bloc se déplace il faut calculer le décalage par rapport la position de départ donc actualiser xOff et yOff
	public void change(int i, int j) {
		if(!vide) {
			if(this.j != j) xOff += VisuPlateau.scl;
			if(this.i != i) yOff -= VisuPlateau.scl;
		}
		this.i = i;
		this.j = j;
	}
	
	public boolean isMoving() {
		if(vide) return false;
		return !(xOff == 0 && yOff == 0);
	}
	
	//Fonction pour afficher dans le terminal
	public void afficheT() {
		if(vide) System.out.print(" ");
		else System.out.print(assocCouleur[color]);
	}
	
	//Fonction pour afficher dans l'interface graphique
	public void afficheG(Graphics g) {
		int scl = VisuPlateau.scl;
		if(!vide) {
			g.setColor(colors[assocCouleur[color]]);
			g.fillRect(j * scl + 1 +  xOff, i * scl + 1 + yOff, scl - 1, scl - 1);
			if(xOff > 0) xOff -= 2;
			if(yOff < 0) yOff += 2;
		}
	}
	
	//Fonction pour exploser un bloc, il devient donc vide si la couleur en argument est la même que la sienne
	public boolean explose(int c) {
		if(vide || this.color != c) return false;
		vide = true;
		return true;
	}
	
	public boolean estVide() {
		return vide;
	}
	
	public int getColor() {
		return color;
	}
	
	//Fonction pour echanger des couleur du tableau colors
	public static void swapColor(int i, int j) {
		int ci = assocCouleur[i];
		assocCouleur[i] = assocCouleur[j];
		assocCouleur[j] = ci;
	}
	
	//Fonction pour melanger le tableau colors, pour avoir des couleurs aléatoires lors de l'affichage
	public static void melangeCouleur() {
		int n = (int)(Math.random() * 20);
		for(int i = 0; i < n; i++) {
			int i1 = (int)(Math.random() * assocCouleur.length);
			int i2 = (int)(Math.random() * assocCouleur.length);
			swapColor(i1,i2);
		}
	}
}
