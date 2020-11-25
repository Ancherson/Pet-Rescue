import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pet extends Cell{
	
	private static BufferedImage image;
	
	public Pet(int i, int j)  {
		super(i, j);
		if(image == null) {
			try {
				image = ImageIO.read(new File("./pet.png"));
			} catch(Exception e) {
				throw new RuntimeException();
			}
		}
	}
	public boolean estVide() {
		return false;
	}
	
	public void afficheG(Graphics g, int scl) {
		g.setColor(Color.white);
		g.fillRect(j * scl, i * scl, scl, scl);
		g.setColor(Color.orange);
		g.fillRect(j * scl + 1, i * scl + 1, scl - 1, scl - 1);
		g.drawImage(image, j * scl + 1, i * scl + 1, scl - 1, scl - 1, null);
	}
	
	public void afficheT() {
		System.out.print("@");
	}
	
	public boolean estPet() {
		return true;
	}
}
