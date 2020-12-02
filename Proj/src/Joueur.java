
//Cette classe représente les joueurs

public abstract class Joueur {
	protected String nom;
	protected int score;
	

	protected int levelMax = 1;
	private int[] bestScores = new int[Jeu.TOT_LEVEL];
	
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
		//Load les infos du joueur
	}
	
	public void saveBest(int niveau, int score) {
		if(score > bestScores[niveau - 1])
			bestScores[niveau - 1] = score;
	}
	
	public void nextLevel() {
		if(levelMax < Jeu.TOT_LEVEL)
			levelMax++;
	}
	
	public void save() {
		//Sauvegarder Joueur dans un fichier
	}
	
	public abstract void start();
	
	public abstract void prochainCoup();
	
}
