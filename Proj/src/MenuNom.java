import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuNom extends JPanel{
	private JLabel question = new JLabel("Quel est ton nom baby ?");
	private JTextField reponse = new JTextField();
	private Visuelle v;
	
	public MenuNom(Visuelle v) {
		this.v = v;
		
		JPanel panneauQuestion = new JPanel();
		panneauQuestion.add(question);
		question.setFont(new Font("Arial",Font.BOLD,40));
		question.setForeground(new Color(0x25275E));
		panneauQuestion.setBackground(Color.white);
		
		JPanel panneauReponse = new JPanel(new GridLayout(3,1));
		panneauReponse.setBackground(new Color(0x25275E));
		JPanel vide = new JPanel();
		vide.setBackground(new Color(0x25275E));
		panneauReponse.add(vide);
		
		JPanel panneauTextField = new JPanel();
		panneauTextField.add(reponse);
		panneauTextField.setBackground(new Color(0x25275E));
		
		reponse.setFont(new Font("Arial",Font.BOLD,50));
		reponse.setPreferredSize(new Dimension(400,50));
		reponse.setHorizontalAlignment(JTextField.CENTER);
		reponse.addActionListener((event) -> {
			v.changeToLevel();
		});
		panneauReponse.add(panneauTextField);
		this.setLayout(new BorderLayout());
		this.add(panneauQuestion, BorderLayout.NORTH);
		this.add(panneauReponse);
	}
	
	public String getText() {
		return reponse.getText();
	}
}
