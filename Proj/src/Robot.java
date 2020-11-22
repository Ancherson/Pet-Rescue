
public class Robot extends Joueur{
	
	private static String[] noms = {"Truc", "Robot", "Tas de Feraille", "Un Humain"};
	private Jeu jeu;
	
	public Robot(Jeu jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public void start() {
		String nom = noms[(int)(Math.random() * noms.length)];
		int level = (int)(Math.random() * 2 + 1);
		jeu.start(nom, level);
	}
	//Premiere IA un peu débile, mais c'est pour tester
	//Faudra changer getCell parce que le robot peut actuellement exploser les blocs, donc a changer
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
	}
}
