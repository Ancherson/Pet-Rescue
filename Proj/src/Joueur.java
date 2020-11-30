
//Cette classe représente les joueurs

public abstract class Joueur {
	protected String nom;
	protected int score;
	
	public void affiche() {
		System.out.println(nom + " : " + score);
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int score) {
		this.score += score * score * 10;
	}
	
	public void quelNom(String name) {
		nom = name;
	}
	
	public abstract void start();
	
	public abstract void prochainCoup();
	
}
