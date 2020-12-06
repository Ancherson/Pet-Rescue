import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Classe représentant l'écran de fin de jeu

public class MenuFin extends JPanel{
	
	private Visuelle v;
	
	public MenuFin(Visuelle v, String nom, int score, int best, boolean gagne) {
		
		this.v = v;
		
		JPanel haut = new JPanel();
		JLabel titre = new JLabel("FIN DE JEU");
		titre.setFont(new Font("Arial",Font.BOLD,40));
		titre.setForeground(Color.white);
		haut.setBackground(new Color(0x25275E));
		haut.add(titre);
		
		
		JPanel corps = new JPanel(new GridLayout(4,1));
		corps.setOpaque(false);
		
		JLabel message = new JLabel((gagne ? "Bravo " : "Vous avez perdu au jeu ") + nom + " !");
		message.setForeground(new Color(0x25275E));
		message.setFont(new Font("Arial",Font.BOLD,40));
		message.setHorizontalAlignment(0);
		
		JLabel scoreFinal = new JLabel("Votre Score : " + score);
		scoreFinal.setForeground(new Color(0x25275E));
		scoreFinal.setFont(new Font("Arial",Font.BOLD,40));
		scoreFinal.setHorizontalAlignment(0);
		
		JLabel bestScore = new JLabel("Best Score : " + best);
		bestScore.setForeground(new Color(0x25275E));
		bestScore.setFont(new Font("Arial",Font.BOLD,40));
		bestScore.setHorizontalAlignment(0);
		
		JButton recommencer = new JButton("Recommencer");
		recommencer.addActionListener((event) -> {
			v.changeToLevel();
		});
		
		
		JButton quitter = new JButton("Quitter");
		quitter.addActionListener((event) -> {
			System.exit(0);
		});
		corps.add(message);
		corps.add(scoreFinal);
		corps.add(bestScore);
		
		JPanel panneauButton = new JPanel();
		panneauButton.setOpaque(false);
		panneauButton.setBackground(new Color(0x25275E));
		panneauButton.add(recommencer);
		panneauButton.add(quitter);
		corps.add(panneauButton);
		
		this.setLayout(new BorderLayout());
		this.add(haut, BorderLayout.NORTH);
		this.add(corps);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(v.getBack(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
