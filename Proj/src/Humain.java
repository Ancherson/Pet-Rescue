
public class Humain extends Joueur{
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
