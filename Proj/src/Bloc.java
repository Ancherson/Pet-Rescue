import java.awt.Color;
import java.awt.Graphics;

public class Bloc extends Cell{
	private int color;
	private static Color[] colors = {Color.red, Color.green, Color.blue, Color.yellow, Color.MAGENTA, Color.cyan};
	private boolean vide = false;
	private int xOff = 0;
	private int yOff = 0;
	public Bloc(int i, int j, int c) {
		super(i,j);
		color = c;
	}
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
	
	public void afficheT() {
		if(vide) System.out.print(" ");
		else System.out.print(color);
	}
	public void afficheG(Graphics g) {
		int scl = VisuPlateau.scl;
		/*g.setColor(Color.white);
		g.fillRect(j * scl + xOff, i * scl + yOff, scl, scl);*/
		if(!vide) {
			g.setColor(colors[color - 1]);
			g.fillRect(j * scl + 1 +  xOff, i * scl + 1 + yOff, scl - 1, scl - 1);
			if(xOff > 0) xOff -= 2;
			if(yOff < 0) yOff += 2;
		}
	}
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
	
	public static void swapColor(int i, int j) {
		Color ci = colors[i];
		colors[i] = colors[j];
		colors[j] = ci;
	}
	
	public static void melangeCouleur() {
		int n = (int)(Math.random() * 20);
		for(int i = 0; i < n; i++) {
			int i1 = (int)(Math.random() * (colors.length - 1)) + 1;
			int i2 = (int)(Math.random() * (colors.length - 1)) + 1;
			swapColor(i1,i2);
		}
	}
}
