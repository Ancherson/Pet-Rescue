
//Cette classe représente un Robot

public class Robot extends Joueur{
	
	//Le robot n'a pas besoin d'interacteur contrairement à l'humain, et est donc relié directement au Jau
	
	private static String[] noms = {"Truc", "Robot", "Tas de Feraille", "Un Humain"};
	private Jeu jeu;
	
	public Robot(Jeu jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public void start() {
		String nom = noms[(int)(Math.random() * noms.length)];
		int level = (int)(Math.random() * Jeu.TOT_LEVEL + 1);
		jeu.newJoueur(nom);
		jeu.start(new Plateau(level));
	}
	
	//Fonction pour préparer le coup du robot
	//Premiere IA un peu débile, mais c'est pour tester
	private int[] think() {
		Plateau plateau = jeu.getPlateau();
		int i;
		int j;
		do {
			//System.out.println(i + " 0" + j);
			i = (int)(Math.random() * plateau.getHauteur());
			j = (int)(Math.random() * plateau.getLargeur());
		}while(!plateau.canExplose(i, j));
		
		int[] t = {i,j};
		return t;
	}

	@Override
	public void prochainCoup()  {
		int[] prochainCoup = think();
		
		System.out.print("Je réfléchis ");
		for(int i = 0; i < 3; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(".");
		}
		System.out.println();
		System.out.println("Je joue en (" + prochainCoup[0] + "," + prochainCoup[1] + ")");
		
		jeu.turn(prochainCoup[0], prochainCoup[1]);
		
		while(this.jeu.rescue()) {
			this.jeu.move();
		}
		
		if(jeu.finished()) {
			jeu.finDePartie();
		}else {
			jeu.next();
		}
	}
	
	@Override
	public void veutRejouer() {
		System.out.println("C'est bon pour moi, j'en ai marre !");
	}
}
