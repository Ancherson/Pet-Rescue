import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//Cette classe représente le menu du jeu, lors de l'affichage du niveau sur l'interface graphique

public class MenuJeu extends JPanel{
	private Visuelle v;
	private VisuPlateau vPlateau;
	
	private JButton fusee = new JButton();
	private ImageIcon imageFusee1;
	private ImageIcon imageFusee2;
	private int nbFusee = 0;
	
	private JLabel score = new JLabel("Score : 0");
	private JLabel coup = new JLabel();
	
	
	public MenuJeu(Visuelle v, Plateau p) {
		this.v = v;
		this.vPlateau = new VisuPlateau(v,p);
		
		//JPanel mainPanel = new JPanel(new BorderLayout());
		
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
		
		//mainPanel.add(haut, BorderLayout.NORTH);
		this.add(haut, BorderLayout.NORTH);
		
		GridBagLayout gb = new GridBagLayout();
		JPanel corps = new JPanel(gb);
		corps.setOpaque(false);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.weighty = 1;
		
		JPanel gauche = new JPanel(new GridLayout(4,1));
		gauche.setOpaque(false);
		gauche.add(this.getVide());
		gauche.add(this.getVide());
		gauche.add(this.getVide());
		gauche.add(this.getPanelButton(fusee));
		initialiseImageFusee();
		createButtonFusee();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.10;
		corps.add(gauche, gc);
		
		JPanel panneauPlateau = new JPanel();
		panneauPlateau.add(vPlateau);
		panneauPlateau.setOpaque(false);
		int panneauHeight = v.getHeight() - (int)haut.getPreferredSize().getHeight();
		panneauPlateau.setLayout(new FlowLayout(FlowLayout.CENTER,0,(panneauHeight - vPlateau.getHeight()) / 2));
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0.8;
		corps.add(panneauPlateau, gc);
		
		JPanel droite = new JPanel(new GridLayout(4,1));
		droite.setOpaque(false);
		JButton retour = new JButton("Retour");
		retour.addActionListener(event -> v.changeToLevel());
		droite.add(this.getVide());
		droite.add(this.getVide());
		droite.add(this.getVide());
		droite.add(getPanelButton(retour));
		
		gc.gridx = 2;
		gc.gridy = 0;
		gc.weightx = 0.10;
		corps.add(droite, gc);
		
		
		this.add(corps, BorderLayout.CENTER);
		
	}
	
	public void initialiseImageFusee() {
		ImageIcon image = new ImageIcon("./fusee2.png");
		Image imageNew = image.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
		imageFusee1 = new ImageIcon(imageNew);
		
		image = new ImageIcon("./fusee2Dead.png");
		imageNew = image.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
		imageFusee2 = new ImageIcon(imageNew);
	}
	
	public void createButtonFusee() {
		fusee.setIcon(imageFusee1);
		fusee.setVerticalTextPosition(SwingConstants.BOTTOM);
		fusee.setHorizontalTextPosition(SwingConstants.CENTER);
		fusee.setBackground(new Color(50,50,50));
		fusee.setFont(new Font("Arial", Font.BOLD, 12));
		fusee.setForeground(Color.white);
		fusee.setPreferredSize(new Dimension(80,80));
		fusee.setBorderPainted(false);
		fusee.setFocusPainted(false);
		
		fusee.addActionListener((evt) -> {
			System.out.println(this.nbFusee);
			if(this.nbFusee > 0) {
				this.fuseeChangeCouleur();
				vPlateau.toggleFusee();
			}
		});
	}
	
	public void fuseeChangeCouleur() {
		if(fusee.getBackground().getGreen() == 50) {
			fusee.setBackground(new Color(0,150,0));
		}else {
			fusee.setBackground(new Color(50,50,50));
		}
	}
	
	public void setNbFusee(int nb) {
		fusee.setBackground(new Color(50,50,50));
		if(nb != nbFusee) {
			this.nbFusee = nb;
			if(nb > 0) {
				fusee.setIcon(imageFusee1);
				fusee.setText(nb + "");
			}else {
				fusee.setIcon(imageFusee2);
				fusee.setText(null);
			}
		}
	}
	
	public JPanel getPanelButton(JButton button) {
		JPanel panelButton = new JPanel();
		panelButton.setOpaque(false);
		panelButton.add(button);
		return panelButton;
	}
	
	public JPanel getVide() {
		JPanel vide = new JPanel();
		vide.setOpaque(false);
		return vide;
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
	
	public void prepare(Plateau p) {
		vPlateau.prepare(p);
	}
}
