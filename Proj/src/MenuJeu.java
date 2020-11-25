import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuJeu extends JPanel{
	private Visuelle v;
	private VisuPlateau vPlateau;
	
	private JLabel score = new JLabel("Score : 0");
	
	public MenuJeu(Visuelle v, Plateau p) {
		this.v = v;
		this.vPlateau = new VisuPlateau(v, p);
		
		this.setLayout(new BorderLayout());
		
		JPanel haut = new JPanel();
		haut.setSize(vPlateau.largeur, 70);
		score.setFont(new Font("Arial",Font.BOLD,50));
		score.setForeground(Color.white);
		haut.setBackground(new Color(0x25275E));
		haut.add(score);
		
		this.add(haut, BorderLayout.NORTH);
		this.add(vPlateau, BorderLayout.CENTER);
		System.out.println(haut.getHeight());
		this.setSize(vPlateau.largeur, vPlateau.hauteur + haut.getHeight());
		
	}
	
	public void setScore(int score) {
		this.score.setText("Score : " + score);
	}
	
	public void afficheP() {
		vPlateau.repaint();
	}
}
