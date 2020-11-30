import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

	public MenuCommencer(Visuelle v) {
		this.v = v;
		
		title.setFont(new Font("Arial",Font.BOLD,50));
		title.setForeground(new Color(0x25275E));
		//title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.white);
		p1.add(title);
		
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
		
		JPanel p2 = new JPanel(new GridLayout(3,1));
		JLabel test = new JLabel("");
		test.setBackground(new Color(0x25275E));

		p2.add(test);
		JPanel pBouton = new JPanel();
		pBouton.setBackground(new Color(0x25275E));
		pBouton.add(bouton);
		p2.setBackground(new Color(0x25275E));
		p2.add(pBouton);
		
		this.setLayout(new BorderLayout());
		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.CENTER);
	}

}
