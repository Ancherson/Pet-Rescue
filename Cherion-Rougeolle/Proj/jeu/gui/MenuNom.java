package jeu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Cette classe représente le menu du choix du prénom du joueur, sur l'interface graphique

public class MenuNom extends JPanel{
	private JLabel question = new JLabel("Quel est ton nom baby ?");
	private JTextField reponse = new JTextField();
	private Visuelle v;
	
	public MenuNom(Visuelle v) {
		this.v = v;
		
		JPanel panneauQuestion = new JPanel();
		panneauQuestion.add(question);
		question.setFont(new Font("Arial",Font.BOLD,40));
		question.setForeground(Color.white);
		panneauQuestion.setBackground(new Color(0x25275E));
		
		JPanel panneauReponse = new JPanel(new GridLayout(3,1));
		panneauReponse.setOpaque(false);
		JPanel vide = new JPanel();
		vide.setOpaque(false);
		panneauReponse.add(vide);
		
		JPanel panneauTextField = new JPanel();
		panneauTextField.setOpaque(false);
		panneauTextField.add(reponse);
		
		reponse.setFont(new Font("Arial",Font.BOLD,50));
		reponse.setPreferredSize(new Dimension(400,50));
		reponse.setHorizontalAlignment(JTextField.CENTER);
		reponse.addActionListener((event) -> {
			v.newJoueur();
			v.changeToLevel();
		});
		panneauReponse.add(panneauTextField);
		this.setLayout(new BorderLayout());
		this.add(panneauQuestion, BorderLayout.NORTH);
		this.add(panneauReponse);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(v.getBack(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public String getText() {
		return reponse.getText();
	}
}
