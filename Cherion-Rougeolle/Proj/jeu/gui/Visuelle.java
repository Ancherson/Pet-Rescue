package jeu.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jeu.Afficheur;
import jeu.Jeu;
import jeu.joueur.Interacteur;
import jeu.joueur.Joueur;
import jeu.modele.Plateau;


//Cette classe gère l'interface graphique, à la fois l'affichage et l'interaction avec le joueur

public class Visuelle extends JFrame implements Afficheur, Interacteur{
	
	private JPanel mainPanel;
	private MenuCommencer menu1;
	private MenuNom menu2;
	private MenuLevel menu3;
	private MenuJeu menuJeu;
	private MenuFin menuFin;
	//Ajouter pour plus tard menu plateau et menu fin
	
	private CardLayout cardLayout = new CardLayout();
	
	private Jeu j;
	
	//Attributs pour l'animation des blocs qui se déplacent
	private boolean running = true;
	private Thread t;
	
	private BufferedImage background;
	
	private int maxLevel;
	
	public Visuelle(Jeu j) {
		this.j = j;
		
		try {
			background = ImageIO.read(new File("./images/back.png"));
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
		this.setTitle("Pet Rescue");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.menu1 = new MenuCommencer(this);
		this.menu2 = new MenuNom(this);
		this.menu3 = new MenuLevel(this, maxLevel);
		
		this.mainPanel = new JPanel(cardLayout);
		this.mainPanel.add("commencer", menu1);
		this.mainPanel.add("nom", menu2);
		
		this.getContentPane().add(mainPanel);
		
		EventQueue.invokeLater(() -> {
			this.setVisible(true);
		});
		
	}
	
	public BufferedImage getBack() {
		return background;
	}
	
	@Override
	public void setMaxLevel(int levelMax) {
		this.maxLevel = levelMax;
	}
	
	public void start() {
		cardLayout.show(mainPanel, "commencer");
	}
	
	public void changeToName() {
		cardLayout.show(mainPanel, "nom");
	}
	
	public void newJoueur() {
		this.j.newJoueur(quelNom());
	}

	public void changeToLevel() {
		this.menu3 = new MenuLevel(this, maxLevel);
		this.mainPanel.add("level", menu3);
		cardLayout.show(mainPanel, "level");
	}
	
	public void changeToPlateau(int i) {
		Plateau p = new Plateau(i);
		menuJeu = new MenuJeu(this, p);
		this.mainPanel.add("jeu", menuJeu);
		
		int coup = p.getCoup();
		if(coup > 0) menuJeu.setCoup(coup);
		
		j.start(p);
	}
	
	public String quelNom() {
		return menu2.getText();
	}
	
	public void joue(int i, int j) {
		this.j.turn(i,j);
		
	}
	public void fusee(int j) {
		this.j.fusee(j);
	}
	
	public void rescue() {
		if(j.rescue()) j.move();
		else {
			if(j.finished()) fin();
			running = false;
			menuJeu.unLock();
		}
	}
	
	public void fin() {
		j.finDePartie();
	}
	
	@Override
	public void prochainCoup() {
		
	}
	
	@Override
	public void veutRejouer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficherP(Plateau p) {
		menuJeu.prepare(p);
		this.cardLayout.show(mainPanel, "jeu");
		running = true;
		menuJeu.lock();
		t = new Thread(() -> {
			while(running) {
				menuJeu.afficheP();
				try {
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			menuJeu.afficheP();
		});
		EventQueue.invokeLater(() -> t.start());
		
	}

	@Override
	public void afficheScore(Joueur j) {
		menuJeu.setScore(j.getScore());
	}


	@Override
	public void afficheCoup(Plateau p) {
		int coup = p.getCoup();
		if(coup >= 0) menuJeu.setCoup(coup);
		
	}
	
	@Override
	public void afficheFinDePartie(Plateau p, Joueur j) {
		menuFin = new MenuFin(this, j.getNom(), j.getScore(), j.getBestScore(p.getLevel()), p.aGagne());
		this.mainPanel.add("fin", menuFin);
		this.cardLayout.show(mainPanel, "fin");
		
	}

	@Override
	public void afficheFusee(Joueur j) {
		menuJeu.setNbFusee(j.getFusee());
		
	}

}