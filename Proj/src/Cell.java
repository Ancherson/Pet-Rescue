
public class Cell {
	private Bloc bloc;
	private int x;
	private int y;
	public Cell(int c, int x, int y) {
		bloc = new Bloc(c);
		this.x = x;
		this.y = y;
	}
	public void afficheT() {
		if(bloc != null)
			bloc.afficheT();
		else
			System.out.print(" ");
	}
}
