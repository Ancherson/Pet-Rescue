
public class Cell {
	protected int i;
	protected int j;
	public Cell(int i, int j) {
		this.i = i;
		this.j = j;
	}
	public void change(int i, int j) {
		this.i = i;
		this.j = j;
	}
	public void afficheT() {
		System.out.print(" ");
	}
	public boolean explose(int color) {
		return false;
	}
	public int getColor() {
		return 0;
	}
	public boolean estVide() {
		return true;
	}
	
	public boolean estMur() {
		return false;
	}
	public boolean estPet() {
		return false;
	}
}
