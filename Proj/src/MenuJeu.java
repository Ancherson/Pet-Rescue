import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Cette classe représente le menu du jeu, lors de l'affichage du niveau sur l'interface graphique

public class MenuJeu extends JPanel{
	private Visuelle v;
	private VisuPlateau vPlateau;
	
	private JLabel score = new JLabel("Score : 0");
	private JLabel coup = new JLabel();
	
	public MenuJeu(Visuelle v, Plateau p) {
		this.v = v;
		this.vPlateau = new VisuPlateau(v, p);
		
		this.setLayout(new BorderLayout());
		
		JPanel haut = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
		haut.setPreferredSize(new Dimension(vPlateau.largeur, 70));
		score.setFont(new Font("Arial",Font.BOLD,30));
		score.setForeground(Color.white);
		coup.setFont(new Font("Arial",Font.BOLD,30));
		coup.setForeground(Color.white);
		haut.setBackground(new Color(0x25275E));
		haut.add(score);
		haut.add(coup);
		
		this.add(haut, BorderLayout.NORTH);
		
		JPanel panneauPlateau = new JPanel();
		panneauPlateau.add(vPlateau);
		panneauPlateau.setOpaque(false);
		
		this.add(panneauPlateau, BorderLayout.CENTER);
		int panneauHeight = v.getHeight() - (int)haut.getPreferredSize().getHeight();
		panneauPlateau.setLayout(new FlowLayout(FlowLayout.CENTER,0,(panneauHeight - vPlateau.getHeight()) / 2));
		//this.setSize(vPlateau.largeur, vPlateau.hauteur + haut.getPreferredSize().height);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(v.getBack(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public void setScore(int score) {
		this.score.setText("Score : " + score);
	}
	
	public void setCoup(int coup) {
		this.coup.setText("Coup : " + coup);
	}
	
	public void afficheP() {
		vPlateau.repaint();
		Toolkit.getDefaultToolkit().sync();
	}
	
	//Les fonction lock et unlock sont utiles pour empecher le Joueur de jouer lorse de l'animation des blocs qui se déplacent
	
	public void lock() {
		vPlateau.lock();
	}
	
	public void unLock() {
		vPlateau.unLock();
	}
}
