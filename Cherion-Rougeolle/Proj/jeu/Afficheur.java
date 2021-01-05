package jeu;

import jeu.joueur.Joueur;
import jeu.modele.Plateau;

// Interface utilisé pour l'affichage du jeu


public interface Afficheur {
	void afficherP(Plateau p);
	void afficheScore(Joueur j);
	void afficheCoup(Plateau p);
	void afficheFusee(Joueur j);
	void afficheFinDePartie(Plateau p, Joueur j);
}
