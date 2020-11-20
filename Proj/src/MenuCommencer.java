import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuCommencer extends JPanel{
	private JLabel title = new JLabel("PET RESCUE");
	private JButton bouton = new JButton("Commencer");
	
	private boolean aAppuyer = false;
	public MenuCommencer() {
		title.setFont(new java.awt.Font("Arial",Font.BOLD,30));
		title.setForeground(new Color(0x25275E));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.white);
		p1.add(title);
		
		bouton.addActionListener((event) -> {
			aAppuyer = true;
		});
		bouton.setBackground(new Color(0x25273E));
		bouton.setFocusPainted(false);
		bouton.setForeground(Color.white);
		bouton.setFont(new Font("Arial", Font.BOLD, 20));
		bouton.setMargin(new Insets(50,50,100,10));
		
		JPanel p2 = new JPanel(new BorderLayout());
		p2.setBackground(new Color(0x25273E));
		p2.add(bouton, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.CENTER);
	}
	
	public boolean aAppuyer() {
		return aAppuyer;
	}
}
