
public class Pet extends Cell{
	public Pet(int i, int j) {
		super(i, j);
	}
	
	public boolean estVide() {
		return false;
	}
	
	public void afficheT() {
		System.out.print("@");
	}
	
	public boolean estPet() {
		return true;
	}
}
