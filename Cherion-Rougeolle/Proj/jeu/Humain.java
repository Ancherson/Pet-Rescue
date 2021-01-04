package jeu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Cette classe représente un Joueur Humain

public class Humain extends Joueur{
	
	//Un humain a besoin d'un interacteur pour pouvoir jouer
	private Interacteur interacteur;
	
	public Humain(Interacteur i) {
		interacteur = i;
	}
	
	@Override
	public void start() {
		interacteur.start();
	}

	@Override
	public void prochainCoup() {
		interacteur.prochainCoup();
	}
	
	@Override
	public void quelNom(String name) {
		super.quelNom(name);
		try {
			load();
		} catch (IOException e) {
			throw new RuntimeException("Error loading data of the player " + nom);
		}
		interacteur.setMaxLevel(levelMax);
	}
	
	public void save() {
		File f = new File("./Sauvegardes/" + nom + ".txt");
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeInt(levelMax);
			oos.writeInt(nbFusee);
			for(int i = 0; i < bestScores.length; i++) {
				oos.writeInt(bestScores[i]);
			}
			oos.close();
		} catch (IOException e) {
			throw new RuntimeException("Error saving data of the player " + nom);
		} 
	}
	
	public void load() throws IOException {
		File f = new File("./Sauvegardes/" + nom + ".txt");
		if(!f.exists()) return;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		
		levelMax = ois.readInt();
		nbFusee = ois.readInt();
		int i = 0;
		boolean pbm = false;
		while(!pbm && i < bestScores.length) {
			try {
				bestScores[i] = ois.readInt();
			}catch(IOException e) {
				pbm = true;
			}
			i++;
		}
		ois.close();
	}
	
	@Override
	public void veutRejouer() {
		super.veutRejouer();
		interacteur.veutRejouer();
	}
	
	@Override
	public void nextLevel(int level) {
		super.nextLevel(level);
		interacteur.setMaxLevel(levelMax);
	}
}
