import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuNom extends JPanel{
	private JLabel question = new JLabel("Quel est ton nom baby ?");
	private JTextField reponse = new JTextField(30);
	private Visuelle v;
	
	public MenuNom(Visuelle v) {
		this.v = v;
		
		JPanel p = new JPanel(new GridLayout(2,1));
		p.add(question);
		p.add(reponse);
		reponse.addActionListener((event) -> {
			v.changeToLevel();
		});
		
		this.add(p);
	}
	
	public String getText() {
		return reponse.getText();
	}
}
