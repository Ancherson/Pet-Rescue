import java.awt.Graphics;

import javax.swing.JPanel;

public class VisuPlateau extends JPanel{
	private Visuelle v;
	private Plateau p;
	//Largeur des carre
	public static final int scl = 20;
	public final int largeur;
	public final int hauteur;
	public VisuPlateau(Visuelle v, Plateau p){
		this.v = v;
		this.p = p;
		largeur = scl * p.getLargeur();
		hauteur = scl * p.getHauteur();
		this.setSize(largeur, hauteur);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		p.afficherG(g, scl);
	}
}
