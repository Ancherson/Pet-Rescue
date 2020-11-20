import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuNom extends JPanel{
	private JLabel question = new JLabel("Quel est ton nom baby ?");
	private JTextField reponse = new JTextField(30);
	private boolean aRepondu = false;
	
	public MenuNom() {
		JPanel p = new JPanel(new GridLayout(2,1));
		p.add(question);
		p.add(reponse);
		reponse.addActionListener((event) -> {
			aRepondu = true;
		});
		
		this.add(p);
	}
	
	public boolean aRepondu() {
		return aRepondu;
	}
	
	public String getText() {
		return reponse.getText();
	}
}
