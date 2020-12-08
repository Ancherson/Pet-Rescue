import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Cette classe représente les "Mur" ou blocs Fixes

public class Mur extends Cell{
	
	private static BufferedImage image;
	
	public Mur(int i, int j) {
		super(i, j);
		if(image == null) {
			try {
				image = ImageIO.read(new File("./mur.png"));
			} catch (IOException e) {
				throw new RuntimeException("error load ./mur.png");
			}
		}
	}
	public int[] getIJ() {
		int[] t = {i,j};
		return t;
	}
	public void afficheG(Graphics g) {
		int scl = VisuPlateau.scl;
		/*g.setColor(Color.black);
		g.fillRect(j * scl, i * scl, scl, scl);*/
		g.drawImage(image, j * scl, i * scl, scl, scl, null);
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
