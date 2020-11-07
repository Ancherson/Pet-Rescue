
public class Bloc extends Cell{
	private int color;
	public Bloc(int i, int j, int c) {
		super(i,j);
		color = c;
	}
	public void afficheT() {
		System.out.print(color);
	}
	public boolean explose(int c) {
		//color = 0 => plus rien dedans
		if(this.color == 0 || this.color != c) return false;
		this.color = 0;
		return true;
	}
	
	public boolean estVide() {
		return this.color == 0;
	}
	
	public int getColor() {
		return color;
	}
}
