
public class Cell {
	private int i;
	private int j;
	public Cell(int i, int j) {
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
}
