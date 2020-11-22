import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuLevel extends JPanel{
	private final int numberLevel = 2;
	private JButton[] buttons = new JButton[numberLevel];
	private Visuelle v;
	
	public MenuLevel(Visuelle v) {
		this.v = v;
		for(int i = 0; i < buttons.length; i++) {
			final int j = i + 1;
			buttons[i] = new JButton(j + "");
			buttons[i].addActionListener(new ActionListener() {
				private int index = j;
				@Override
				public void actionPerformed(ActionEvent evt) {
					v.changeToPlateau(index);
				}
				
			});
			
			this.add(buttons[i]);
		}
	}
}
