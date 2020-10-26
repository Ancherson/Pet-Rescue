import java.util.Scanner;

public class Joueur {
	private String nom;
	private int score;
	public Joueur(String nom) {
		this.nom = nom;
		score = 0;
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	
}
