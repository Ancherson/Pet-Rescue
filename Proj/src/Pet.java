import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Cette classe représente les animaux que le joueur doit sauver

public class Pet extends Cell{
	
	private static BufferedImage image;
	private int xOff = 0;
	private int yOff = 0;
	
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
	
	public void change(int i, int j) {
		if(this.j != j) xOff += VisuPlateau.scl;
		if(this.i != i) yOff -= VisuPlateau.scl;
		this.i = i;
		this.j = j;
	}
	
	
	public boolean isMoving() {
		return !(xOff == 0 && yOff == 0);
	}
	
	public void afficheG(Graphics g) {
		int scl = VisuPlateau.scl;
		/*g.setColor(Color.white);
		g.fillRect(j * scl + xOff, i * scl + yOff, scl, scl);
		g.setColor(Color.orange);
		g.fillRect(j * scl + 1 + xOff, i * scl + 1 + yOff, scl - 1, scl - 1);*/
		g.drawImage(image, j * scl + 1 + xOff, i * scl + 1 + yOff, scl - 1, scl - 1, null);
		if(xOff > 0) xOff -= 2;
		if(yOff < 0) yOff += 2;
	}
	
	public void afficheT() {
		System.out.print("@");
	}
	
	public boolean estPet() {
		return true;
	}
}
