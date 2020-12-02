
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
	public void quelNom(String nom) {
		super.quelNom(nom);
		/**
		 * Load les donnes du joueur
		 */
		interacteur.setMaxLevel(levelMax);
	}
	
	@Override
	public void nextLevel() {
		super.nextLevel();
		interacteur.setMaxLevel(levelMax);
	}
}
