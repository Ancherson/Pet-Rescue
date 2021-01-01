package jeu;

import java.util.Scanner;

//Cette classe représente un Robot

public class Robot extends Joueur{
	
	//Le robot n'a pas besoin d'interacteur contrairement à l'humain, et est donc relié directement au Jau
	private Scanner sc = new Scanner(System.in);
	private static String[] noms = {"Truc", "Robot", "Tas de Feraille", "Un Humain"};
	private Jeu jeu;
	
	public Robot(Jeu jeu) {
		this.nbFusee = 0;
		this.jeu = jeu;
	}
	
	@Override
	public void start() {
		System.out.println("Quel niveau (1 à 8, 0 pour random)?");
		int level = -1;
	    boolean pbm = true;
		while(pbm) {
			pbm = false;
			String s = sc.next();
			try {
				level = Integer.parseInt(s);
				if(level>Jeu.TOT_LEVEL ||level<0) {
					pbm=true;
					System.out.println("Mauvais chiffre");
				}
				}catch(NumberFormatException e) {
				pbm = true;
				System.out.println("Mauvais format");
				}
		}		
		String nom = noms[(int)(Math.random() * noms.length)];
		if(level==0) {
			level = (int)(Math.random() * Jeu.TOT_LEVEL + 1);
			System.out.println(nom + ": Je vais faire le niveau "+ level);
		}

		jeu.newJoueur(nom);
		jeu.start(new Plateau(level));
	}
	
	//Fonction pour préparer le coup du robot
	//Premiere IA un peu débile, mais c'est pour tester
	private int[] think() {
		Plateau plateau = jeu.getPlateau();
		int i;
		int j;
		do {
			//System.out.println(i + " 0" + j);
			i = (int)(Math.random() * plateau.getHauteur());
			j = (int)(Math.random() * plateau.getLargeur());
		}while(!plateau.canExplose(i, j));
		
		int[] t = {i,j};
		return t;
	}

	@Override
	public void prochainCoup()  {
		int[] prochainCoup = think();
		
		System.out.print("Je réfléchis ");
		for(int i = 0; i < 3; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(".");
		}
		System.out.println();
		char col = (char) (prochainCoup[1]+65);
		int ligne = prochainCoup[0]+1;
		System.out.println("Je joue en (" + col + "," + ligne + ")");
		
		jeu.turn(prochainCoup[0], prochainCoup[1]);
		
		while(this.jeu.rescue()) {
			this.jeu.move();
		}
		
		if(jeu.finished()) {
			jeu.finDePartie();
		}else {
			jeu.next();
		}
	}
	
	@Override
	public void veutRejouer() {
		System.out.println("C'est bon pour moi, j'en ai marre !");
	}
}
