import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//Cette classe représente les "Mur" ou blocs Fixes

public class Mur extends Cell{
	
	public Mur(int i, int j) {
		super(i, j);
	}
	public int[] getIJ() {
		int[] t = {i,j};
		return t;
	}
	
	public boolean estMur() {
		return true;
	}
	public boolean estVide() {
		return false;
	}
	public Mur clone() {
		return new Mur(i,j);
	}
}
