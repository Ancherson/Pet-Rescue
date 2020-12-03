
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
		/**
		 * Load les donnes du joueur
		 */
		interacteur.setMaxLevel(levelMax);
	}
	
	public void save() {
		
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
