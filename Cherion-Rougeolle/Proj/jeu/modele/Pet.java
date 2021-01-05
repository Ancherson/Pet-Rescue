package jeu.modele;

//Cette classe représente les animaux que le joueur doit sauver

public class Pet extends Cell{
	
	//xOff et yOff représente la différence de déplacement du bloc entre deux affichages
	private int xOff = 0;
	private int yOff = 0;
	private int imageNum = (int)(Math.random() * 2);
	
	public Pet(int i, int j)  {
		super(i, j);
	}
	public boolean estVide() {
		return false;
	}
	
	public void change(int i, int j) {
		if(this.j != j) xOff += 1;
		if(this.i != i) yOff -= 1;
		this.i = i;
		this.j = j;
	}
	
	public boolean estPet() {
		return true;
	}
	
	public Pet clone() {
		Pet p = new Pet(i,j);
		p.xOff = xOff;
		p.yOff = yOff;
		p.imageNum = imageNum;
		return p;
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
	
	public int getImageNum() {
		return imageNum;
	}
}
