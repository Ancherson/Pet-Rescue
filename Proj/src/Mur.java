
public class Mur extends Cell{
	
	public Mur(int i, int j) {
		super(i, j);
	}
	
	public boolean estMur() {
		return true;
	}
	public boolean estVide() {
		return false;
	}
	public void afficheT() {
		System.out.print("#");
	}
}
