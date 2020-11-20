
public abstract class Joueur {
	protected String nom;
	protected int score;
	
	public void affiche() {
		System.out.println(nom + " : " + score);
	}
	
	public void addScore(int score) {
		this.score += score * score * 10;
	}
	
	public abstract void quelNom();
	
	public abstract Plateau quelLevel();
	
	public abstract int[] quelleCase();
	
}
