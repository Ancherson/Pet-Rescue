import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuJeu extends JPanel{
	private Visuelle v;
	private VisuPlateau vPlateau;
	
	private JLabel score = new JLabel("Score : 0");
	private JLabel coup = new JLabel();
	
	public MenuJeu(Visuelle v, Plateau p) {
		this.v = v;
		this.vPlateau = new VisuPlateau(v, p);
		
		this.setLayout(new BorderLayout());
		
		JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
		haut.setPreferredSize(new Dimension(vPlateau.largeur, 70));
		score.setFont(new Font("Arial",Font.BOLD,40));
		score.setForeground(Color.white);
		coup.setFont(new Font("Arial",Font.BOLD,40));
		coup.setForeground(Color.white);
		haut.setBackground(new Color(0x25275E));
		haut.add(score);
		haut.add(coup);
		
		this.add(haut, BorderLayout.NORTH);
		this.add(vPlateau, BorderLayout.CENTER);
		this.setSize(vPlateau.largeur, vPlateau.hauteur + haut.getPreferredSize().height);
		
	}
	
	public void setScore(int score) {
		this.score.setText("Score : " + score);
	}
	
	public void setCoup(int coup) {
		this.coup.setText("Coup : " + coup);
	}
	
	public void afficheP() {
		vPlateau.repaint();
	}
}
