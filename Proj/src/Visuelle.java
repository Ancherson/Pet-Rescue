import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Visuelle extends JFrame implements Afficheur, Interacteur{
	
	private JPanel mainPanel;
	private MenuCommencer menu1;
	private MenuNom menu2;
	private MenuLevel menu3;
	//Ajouter pour plus tard menu plateau et menu fin
	
	private CardLayout cardLayout = new CardLayout();
	
	public Visuelle() {
		this.setTitle("Pet Rescue");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,300);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.menu1 = new MenuCommencer();
		this.menu2 = new MenuNom();
		this.menu3 = new MenuLevel();
		
		this.mainPanel = new JPanel(cardLayout);
		this.mainPanel.add("commencer", menu1);
		this.mainPanel.add("nom", menu2);
		this.mainPanel.add("level", menu3);
		
		this.getContentPane().add(mainPanel);
		
		this.setVisible(true);
	}

	@Override
	public String quelNom() {
		while(!menu1.aAppuyer()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		cardLayout.show(mainPanel, "nom");
		
		while(!menu2.aRepondu()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		cardLayout.show(mainPanel, "level");
		
		return menu2.getText();
	}

	@Override
	public Plateau quelLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] quelleCase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afficherP(Plateau p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficheScore(Joueur j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficheFinDePartie(Plateau p, Joueur j) {
		// TODO Auto-generated method stub
		
	}
	
}