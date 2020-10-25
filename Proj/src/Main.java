
public class Main {
	

	public static void main(String[] args) {
		 Plateau p = new Plateau(1);
		 p.afficheT();
		 p.explose(3, 0, 1);
		 System.out.println();
		 p.afficheT();
		 System.out.println();
		 p.fall();
		 p.afficheT();
		 System.out.println();	 
	}

}
