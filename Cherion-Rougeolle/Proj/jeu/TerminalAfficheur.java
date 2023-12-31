package jeu;

import jeu.joueur.Joueur;
import jeu.modele.Cell;
import jeu.modele.Plateau;

//Cette classe permet l'Affichage dans le terminal du Jeu

public class TerminalAfficheur implements Afficheur{

	@Override
	public void afficherP(Plateau p) {
		Cell[][] copyPlateau = p.copy();
		System.out.print(" ".repeat(5));
		for(int i = 0; i < copyPlateau[0].length; i++) {
			char colone = (char) (i+65);
			System.out.print(colone +" ");
		}
		System.out.println("");
		System.out.print(" ".repeat(3));
		System.out.println("# ".repeat(copyPlateau[0].length+2));
		for(int i = 0; i < copyPlateau.length; i++) {
			int ligne = i+1;
			
			if (ligne > 9) 	System.out.print(ligne+" ");
			
			else System.out.print(" " + ligne+" ");
			
			for(int j = 0; j < copyPlateau[i].length; j++) {
				if(j == 0) System.out.print("# ");
				Cell c = copyPlateau[i][j];
				if(c.estPet()) {
					System.out.print("@ ");
				}else if(c.estMur()) {
					System.out.print("# ");
				}else if(c.estVide()) {
					System.out.print("  ");
				}else {
					System.out.print(c.getColor() + " ");
				}
			}
			System.out.println("#");
		}
		System.out.print(" ".repeat(3));
		System.out.println("# ".repeat(copyPlateau[0].length+2));
	}

	@Override
	public void afficheScore(Joueur j) {
		System.out.println(j.getNom() + " : " + j.getScore());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void afficheFinDePartie(Plateau p, Joueur j) {
		if(p.aGagne()) {
			System.out.println();
			System.out.println("#################");
			System.out.println("#               #");
			System.out.println("#     BRAVO !   #");
			System.out.println("#               #");
			System.out.println("#################");
		}else {
			System.out.println();
			System.out.println("#################");
			System.out.println("#               #");
			System.out.println("#     PERDU !   #");
			System.out.println("#               #");
			System.out.println("#################");
		}
		System.out.println(j.getNom() + " : " + j.getScore());
		System.out.println("Best Score : " + j.getBestScore(p.getLevel()));
	}

	@Override
	public void afficheCoup(Plateau p) {
		int coup = p.getCoup();
		if(coup >= 0)
			System.out.println("Coup : " + coup);
		
	}

	@Override
	public void afficheFusee(Joueur joueur) {
		int fusee = joueur.getFusee();
		if(fusee > 0) 
			System.out.println("Fusees : " + fusee);
	}
}
