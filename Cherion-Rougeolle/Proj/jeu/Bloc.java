package jeu;

// Cette classe représente les blocs colorés qui peuvent explosés
 
public class Bloc extends Cell{
	//représente la couleur du bloc
	private int color;
	private boolean vide = false;
	
	//xOff et yOff représente la différence de déplacement du bloc entre deux affichages
	private int xOff = 0;
	private int yOff = 0;
	
	public Bloc(int i, int j, int c) {
		super(i,j);
		color = c;
	}
	
	//Lorsqu'un bloc se déplace il faut calculer le décalage par rapport la position de départ donc actualiser xOff et yOff
	public void change(int i, int j) {
		if(!vide) {
			if(this.j != j) xOff += 1;
			if(this.i != i) yOff -= 1;
		}
		this.i = i;
		this.j = j;
	}
	
	//Fonction pour exploser un bloc, il devient donc vide si la couleur en argument est la même que la sienne
	public boolean explose(int c) {
		if(vide || this.color != c) return false;
		vide = true;
		color = 0;
		return true;
	}
	
	public boolean estVide() {
		return vide;
	}
	
	public int getColor() {
		return color;
	}
	
	public Bloc clone() {
		Bloc b = new Bloc(i, j, color);
		b.xOff = xOff;
		b.yOff = yOff;
		b.vide = vide;
		return b;
	}
	
	public void clearOffset() {
		xOff = 0;
		yOff = 0;
	}
	
	public int getXOff() {
		return xOff;
	}
	
	public int getYOff() {
		return yOff;
	}
}
