
//Cette classe permet l'Affichage dans le terminal du Jeu

public class TerminalAfficheur implements Afficheur{

	@Override
	public void afficherP(Plateau p) {
		p.afficheT();
	}

	@Override
	public void afficheScore(Joueur j) {
		j.affiche();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void afficheFinDePartie(Plateau p, Joueur j) {
		if(p.aGagne()) {
			System.out.println();
			System.out.println("#################");
			System.out.println("#               #");
			System.out.println("#     BRAVO !   #");
			System.out.println("#               #");
			System.out.println("#################");
		}else {
			System.out.println();
			System.out.println("#################");
			System.out.println("#               #");
			System.out.println("#     PERDU !   #");
			System.out.println("#               #");
			System.out.println("#################");
		}
		
		j.affiche();
	}

	@Override
	public void afficheCoup(Plateau p) {
		int coup = p.getCoup();
		if(coup >= 0)
			System.out.println("Coup : " + coup);
		
	}
}
