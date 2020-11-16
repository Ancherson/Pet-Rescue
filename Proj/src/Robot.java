
public class Robot extends Joueur{
	
	private Plateau plateau;
	private static String[] noms = {"Truc", "Robot", "Tas de Feraille", "Un Humain"};

	@Override
	public void quelNom() {
		String nom = noms[(int)(Math.random() * noms.length)];
		this.nom = nom;
	}

	@Override
	public Plateau quelLevel() {
		plateau = new Plateau((int)(Math.random() * 2 + 1));
		return plateau;
	}

	@Override
	public int[] quelleCase() {
		
		System.out.print("Je réfléchit ");
		for(int i = 0; i < 3; i++) {
			try {
				System.out.print(".");
				Thread.sleep(600);
			}catch(InterruptedException e) {
				
			}
		}
		System.out.println();
		int[] t = think();
		System.out.println("Je joue (" + t[0] + "," + t[1] + ")");
		return t;
	}
	
	//Premiere IA un peu débile, mais c'est pour tester
	//Faudra changer getCell parce que le robot peut actuellement exploser les blocs, donc a changer
	private int[] think() {
		Cell[][] cell = plateau.getCell();
		int i;
		int j;
		do {
			//System.out.println(i + " 0" + j);
			i = (int)(Math.random() * cell.length);
			j = (int)(Math.random() * cell[0].length);
		}while(!plateau.canExplose(i, j));
		
		int[] t = {i,j};
		return t;
	}
	
}
