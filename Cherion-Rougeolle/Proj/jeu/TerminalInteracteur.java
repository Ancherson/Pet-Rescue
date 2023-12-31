package jeu;

import java.util.Scanner;

import jeu.joueur.Interacteur;
import jeu.modele.Plateau;

//Cette Classe permet l'interaction entre le Joueur et le Jeu à travers le terminal

public class TerminalInteracteur implements Interacteur{
	private Scanner sc = new Scanner(System.in);
	private Jeu jeu;
	private int maxLevel = Jeu.TOT_LEVEL;
	
	public TerminalInteracteur(Jeu j) {
		this.jeu = j;
	}
	

	@Override
	public void setMaxLevel(int levelMax) {
		this.maxLevel = levelMax;
	}
	
	@Override
	public void start() {
		String nom = quelNom();
		jeu.newJoueur(nom);
		int level = quelLevel();
		jeu.start(new Plateau(level));
	}
	
	public void restart() {
		int level = quelLevel();
		jeu.start(new Plateau(level));
	}

	public String quelNom() {
		System.out.println("Quel est ton nom baby ?");
		return sc.next();
	}

	public int quelLevel() {
		if (maxLevel == 1) return 1;
		System.out.println("Quel niveau (entre 1 et " + maxLevel + ")");
		int i = 0;
		boolean pbm = true;
		while(pbm || !(i <= maxLevel  && i > 0)) {
			pbm = false;
			String s = sc.next();
			try {
				i = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				System.out.println("NON");
				pbm = true;
			}
		}
		return i;
	}
	
	public int[] quellesCases() {
		Plateau p = jeu.getPlateau();
		int i = quelleColonne();
		int j = quelleLigne();
		int[]res = {j,i};
		if(!p.canExplose(j, i)) {
			System.out.println("Mauvaise case, ne peut pas exploser !");
			return quellesCases();
		}
		return res;
	}
	
	public int quelleAction(int tot) {
		
		System.out.println("Quelle action veux-tu faire ?");
		String[]actions = {"Cases", "Abandonne", "Fusee"};
		String message = "";
		for(int i = 0; i < actions.length && i < tot; i++) {
			message += " | " + (i+1) + " -> " + actions[i];
		}
		message = message.substring(3);
		System.out.println(message);
		boolean pbm = true;
		int i = 0;
		while(pbm || !(i >= 1 && i <= tot)) {
			pbm = false;
			String s = sc.next();
			try {
				i = Integer.parseInt(s);
			}catch(NumberFormatException e) {
				System.out.println("Un entier entre 1 et " + tot);
				pbm = true;
			}
		}
		
		return i;
	}
	
	public int quelleColonne() {
		Plateau p = jeu.getPlateau();
		System.out.println("Quelle Colonne (A,B,C...)?");
		int max = jeu.getPlateau().getLargeur();
		boolean pbm = true;
		int j = 0;
		while(pbm || !(j >= 0 && j < max)) {
			pbm = false;
			String s = sc.next();
			s = s.toUpperCase();
			char col = s.charAt(0);
			j = (int) (col)-65;
			
			if(s.length()>1) {
				System.out.println("Mauvais format");
				pbm = true;
			}
			else if (j<0 || j>=p.getHauteur()) {
				System.out.println("Colonne inexistante");
			}
		}
		return j;
	}
	
	public int quelleLigne() {
		Plateau p = jeu.getPlateau();
		System.out.println("Quelle Ligne (1,2,3...)?");
		int j = -1;
	    boolean pbm = true;
		while(pbm) {
			pbm = false;
			String s = sc.next();
			try {
				j = Integer.parseInt(s)-1;
				}catch(NumberFormatException e) {
				pbm = true;
				System.out.println("Mauvais format");
				}
			if(!pbm) {
				pbm = (j<0 || j>= p.getLargeur());
				System.out.println("Ligne inexistante");
				}
			}
		return j;
		}
	public void turn() {
		int[] cases= this.quellesCases();
		int i = cases [0];
		int j = cases [1];
		
		jeu.turn(i, j);
	}
	
	public void fusee() {
		int col = quelleColonne();
		jeu.fusee(col);
	}

	@Override
	public void prochainCoup() {
		int action;
		if(jeu.getFusee() > 0) {
			action = quelleAction(3);
			if(action == 1) turn();
			else if(action == 3) fusee();
			else if(action == 2) jeu.finDePartie();
		}
		else {
			action = quelleAction(2);
			if(action == 1) turn();
			if(action == 2) jeu.finDePartie();
		}
		if(action == 1 || action == 3) move();
	}
	
	public void move() {
		while(jeu.rescue()) {
			jeu.move();
		}
		
		if(jeu.finished()) {
			jeu.finDePartie();
		}else {
			jeu.next();
		}
	}
	
	public void veutRejouer() {
		System.out.println("Veux-tu continuer à jouer ? (oui/non)");
		String s = "";
		do {
			s = sc.next();
		}while(!(s.equals("oui") || s.equals("non")));
		
		if(s.equals("oui")) this.restart();
	}

}
