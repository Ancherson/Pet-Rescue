
//Cette classe représente les joueurs

public abstract class Joueur {
	protected String nom;
	protected int score;
	

	protected int levelMax = 1;
	private int[] bestScores = new int[Jeu.TOT_LEVEL];
	
	public String getNom() {
		return nom;
	}
	
	public void affiche() {
		System.out.println(nom + " : " + score);
	}
	
	public int getScore() {
		return score;
	}
	
	public int getBestScore(int level) {
		return bestScores[level - 1];
	}
	
	public void addScore(int score) {
		this.score += score * score * 10;
	}
	
	public void quelNom(String name) {
		nom = name;
	}
	
	public void saveBest(int niveau) {
		if(score > bestScores[niveau - 1])
			bestScores[niveau - 1] = score;
	}
	
	public void nextLevel(int level) {
		if(Jeu.TOT_LEVEL > levelMax && levelMax == level) levelMax++;
	}
	
	public void veutRejouer() {
		score = 0;
	}
	
	public abstract void start();
	
	public abstract void prochainCoup();
	
}
