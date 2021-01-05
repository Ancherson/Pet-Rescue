package jeu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.Jeu;

//Classe représentant le menu de choix d'un level, sur l'interface graphique

public class MenuLevel extends JPanel{
	private JButton[] buttons;
	private JLabel[] fauxBoutons;
	private Visuelle v;
	
	public MenuLevel(Visuelle v, int levelMax) {
		buttons = new JButton[levelMax];
		
		JPanel haut = new JPanel();
		JLabel question = new JLabel("Quel Niveau Veux Tu Faire ?");
		question.setFont(new Font("Arial",Font.BOLD,40));
		question.setForeground(Color.white);
		haut.setBackground(new Color(0x25275E));
		haut.add(question);
		
		
		
		JPanel panneauBouton = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
		panneauBouton.setOpaque(false);
		this.v = v;
		for(int i = 0; i < buttons.length; i++) {
			final int j = i + 1;
			buttons[i] = new JButton(j + "");
			buttons[i].setBackground(new Color(0x25275E));
			buttons[i].setForeground(Color.white);
			buttons[i].setPreferredSize(new Dimension(70,70));
			buttons[i].setFont(new Font("Arial",Font.BOLD,30));
			buttons[i].addActionListener(new ActionListener() {
				private int index = j;
				@Override
				public void actionPerformed(ActionEvent evt) {
					v.changeToPlateau(index);
				}
				
			});
			
			buttons[i].addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {}
				
				@Override
				public void mousePressed(MouseEvent arg0) {}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					buttons[j - 1].setBackground(new Color(0x25275E));	
					buttons[j - 1].setForeground(Color.white);
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					buttons[j - 1].setBackground(Color.white);
					buttons[j - 1].setForeground(new Color(0x25275E));
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {}
			});
			panneauBouton.add(buttons[i]);
		}
		
		fauxBoutons = new JLabel[Jeu.TOT_LEVEL - levelMax];
		for(int i = 0; i < fauxBoutons.length; i++) {
			fauxBoutons[i] = new JLabel("" + (i + levelMax + 1));
			fauxBoutons[i].setOpaque(true);
			fauxBoutons[i].setBackground(new Color(0x27252E));
			fauxBoutons[i].setForeground(Color.white);
			fauxBoutons[i].setPreferredSize(new Dimension(70,70));
			fauxBoutons[i].setFont(new Font("Arial",Font.BOLD,30));
			fauxBoutons[i].setHorizontalAlignment(0);
			panneauBouton.add(fauxBoutons[i]);
		}
		
		this.setLayout(new BorderLayout());
		this.add(haut, BorderLayout.NORTH);
		this.add(panneauBouton);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(v.getBack(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
