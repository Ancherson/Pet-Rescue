import java.awt.Color;
import java.awt.Graphics;

public class Bloc extends Cell{
	private int color;
	private static Color[] colors = {Color.white, Color.red, Color.green, Color.blue, Color.yellow, Color.pink};
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
			g.setColor(colors[color]);
			g.fillRect(j * scl + 1 +  xOff, i * scl + 1 + yOff, scl - 1, scl - 1);
			if(xOff > 0) xOff--;
			if(yOff < 0) yOff++;
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
}
