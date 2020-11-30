
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
	
}
