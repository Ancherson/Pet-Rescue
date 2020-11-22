import java.awt.Color;
import java.awt.Graphics;

public class Pet extends Cell{
	public Pet(int i, int j) {
		super(i, j);
	}
	public boolean estVide() {
		return false;
	}
	
	public void afficheG(Graphics g, int scl) {
		g.setColor(Color.orange);
		g.fillRect(j * scl, i * scl, scl, scl);
	}
	
	public void afficheT() {
		System.out.print("@");
	}
	
	public boolean estPet() {
		return true;
	}
}
