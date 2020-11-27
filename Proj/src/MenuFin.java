import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuFin extends JPanel{
	public MenuFin(String nom, int score, boolean gagne) {
		JPanel haut = new JPanel();
		JLabel titre = new JLabel("FIN DE JEU");
		titre.setFont(new Font("Arial",Font.BOLD,40));
		titre.setForeground(new Color(0x25275E));
		haut.setBackground(Color.white);
		haut.add(titre);
		
		
		JPanel corps = new JPanel(new GridLayout(3,1));
	}
}
