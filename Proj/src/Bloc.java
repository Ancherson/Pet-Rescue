//TO DO: Peut être créer un classe Animal qui hérite de Bloc
//		 Autre Idee, Bloc herite de Cell et Animal herite de Cell		
//       Autre Idee, Vu qu'il n'y a pas grand chose dans Cell, transformer Cell en Interface
public class Bloc {
	private int color;
	public Bloc(int c) {
		color = c;
	}
	public void afficheT() {
		System.out.print(color);
	}
}
