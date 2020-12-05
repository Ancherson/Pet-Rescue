import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//Classe représentant le menu pour commencer pour l'interface graphique

public class MenuCommencer extends JPanel{
	private JLabel title = new JLabel("PET RESCUE");
	private JButton bouton = new JButton("Commencer");
	private Visuelle v;
	private BufferedImage background;
	private BufferedImage titre;

	public MenuCommencer(Visuelle v) {
		this.v = v;
		
		
		try {
			background = ImageIO.read(new File("./back.png"));
			titre = ImageIO.read(new File("./PetRescue.png"));
		} catch (IOException e) {
			throw new RuntimeException("Erreur load image ./back.png");
		}
		
		
		this.repaint();
		
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
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
		g.drawImage(titre, 175, 25, 175 + 200, 25 + 200, this);
	}

}
