
public class Bloc extends Cell{
	private int color;
	private boolean vide = false;
	public Bloc(int i, int j, int c) {
		super(i,j);
		color = c;
	}
	public void afficheT() {
		if(vide) System.out.print(" ");
		else System.out.print(color);
	}
	public boolean explose(int c) {
		if(vide || this.color != c) return false;
		vide = true;
		return true;
	}
	
	public boolean estVide() {
		return vide;
	}
	
	public int getColor() {
		return color;
	}
}
