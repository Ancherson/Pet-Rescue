
public class Humain extends Joueur{
	private Interacteur interacteur;
	
	public Humain(Interacteur i) {
		interacteur = i;
	}

	@Override
	public void quelNom() {
		this.nom = interacteur.quelNom();
	}

	@Override
	public Plateau quelLevel() {
		return interacteur.quelLevel();
	}

	@Override
	public int[] quelleCase() {
		return interacteur.quelleCase();
	}
	
}
