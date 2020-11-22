import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Visuelle extends JFrame implements Afficheur, Interacteur{
	
	private JPanel mainPanel;
	private MenuCommencer menu1;
	private MenuNom menu2;
	private MenuLevel menu3;
	private VisuPlateau plateau;
	//Ajouter pour plus tard menu plateau et menu fin
	
	private CardLayout cardLayout = new CardLayout();
	
	private Jeu j;
	
	public Visuelle(Jeu j) {
		this.j = j;
		
		this.setTitle("Pet Rescue");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,500);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		
		this.menu1 = new MenuCommencer(this);
		this.menu2 = new MenuNom(this);
		this.menu3 = new MenuLevel(this);
		
		this.mainPanel = new JPanel(cardLayout);
		this.mainPanel.add("commencer", menu1);
		this.mainPanel.add("nom", menu2);
		this.mainPanel.add("level", menu3);
		
		this.getContentPane().add(mainPanel);
		
		this.setVisible(true);
	}
	
	public void start() {
		cardLayout.show(mainPanel, "commencer");
	}
	
	public void changeToName() {
		cardLayout.show(mainPanel, "nom");
	}

	public void changeToLevel() {
		cardLayout.show(mainPanel, "level");
	}
	
	public void changeToPlateau(int i) {
		Plateau p = new Plateau(i);
		plateau = new VisuPlateau(this, p);
		j.start(quelNom(), p);
		this.mainPanel.add("plateau", plateau);
		this.cardLayout.show(mainPanel, "plateau");
		this.setSize(plateau.largeur, plateau.hauteur);
		//Change panel level -> plateau
	}
	
	public String quelNom() {
		return menu2.getText();
	}
	
	@Override
	public void prochainCoup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficherP(Plateau p) {
		plateau.repaint();
	}

	@Override
	public void afficheScore(Joueur j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficheFinDePartie(Plateau p, Joueur j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}