import java.awt.Color;
import java.awt.Graphics;

public class Bloc extends Cell{
	private int color;
	private static Color[] colors = {Color.white, Color.red, Color.green, Color.blue, Color.yellow, Color.pink};
	private boolean vide = false;
	public Bloc(int i, int j, int c) {
		super(i,j);
		color = c;
	}
	public void afficheT() {
		if(vide) System.out.print(" ");
		else System.out.print(color);
	}
	public void afficheG(Graphics g, int scl) {
		g.setColor(Color.white);
		g.fillRect(j * scl, i * scl, scl, scl);
		g.setColor(colors[color]);
		g.fillRect(j * scl + 1, i * scl + 1, scl - 1, scl - 1);
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
