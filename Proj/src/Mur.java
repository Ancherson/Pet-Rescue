import java.awt.Color;
import java.awt.Graphics;

public class Mur extends Cell{
	
	public Mur(int i, int j) {
		super(i, j);
	}
	public int[] getIJ() {
		int[] t = {i,j};
		return t;
	}
	public void afficheG(Graphics g, int scl) {
		g.setColor(Color.black);
		g.fillRect(j * scl, i * scl, scl, scl);
	}
	
	public boolean estMur() {
		return true;
	}
	public boolean estVide() {
		return false;
	}
	public void afficheT() {
		System.out.print("#");
	}
}
