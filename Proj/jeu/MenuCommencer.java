package jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

//Classe représentant le menu pour commencer pour l'interface graphique

public class MenuCommencer extends JPanel{
	private JButton bouton = new JButton("Commencer");
	private Visuelle v;
	private BufferedImage titre;

	public MenuCommencer(Visuelle v) {
		this.v = v;
		
		
		try {
			titre = ImageIO.read(new File("./images/PetRescue.png"));
		} catch (IOException e) {
			throw new RuntimeException("Erreur load image ./images/PetRescue.png");
		}
		
		
		bouton.addActionListener((event) -> {
			v.changeToName();
		});
		bouton.setBackground(new Color(0x25275E));
		bouton.setFocusPainted(false);
		bouton.setForeground(Color.white);
		bouton.setFont(new Font("Arial", Font.BOLD, 30));
		bouton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				bouton.setBackground(Color.white);
				bouton.setForeground(new Color(0x25275E));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				bouton.setBackground(new Color(0x25275E));	
				bouton.setForeground(Color.white);

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			;
		});
		this.setLayout(new GridLayout(3,1));
		JPanel pBouton = new JPanel();
		pBouton.setOpaque(false);
		bouton.setPreferredSize(new Dimension(300,50));
		pBouton.add(bouton);
		JPanel vide1 = new JPanel();
		vide1.setOpaque(false);
		JPanel vide2 = new JPanel();
		vide2.setOpaque(false);
		this.add(vide1);
		this.add(vide2);
		this.add(pBouton);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(v.getBack(), 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(titre, 200, 20, 200 + 200, 50 + 200, this);
	}

}
