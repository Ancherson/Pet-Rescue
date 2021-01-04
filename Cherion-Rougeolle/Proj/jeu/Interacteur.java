package jeu;

//Cette interface est utilisé pour les interacteur
public interface Interacteur {
	
	void start();
	void veutRejouer();
	void prochainCoup();
	void setMaxLevel(int levelMax);
}
