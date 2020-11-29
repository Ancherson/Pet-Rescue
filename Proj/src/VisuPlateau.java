import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class VisuPlateau extends JPanel{
	private Visuelle v;
	private Plateau p;
	//Largeur des carre
	public static final int scl = 70;
	public final int largeur;
	public final int hauteur;
	private boolean lock = false;
	public VisuPlateau(Visuelle v, Plateau p){
		this.v = v;
		this.p = p;
		largeur = scl * p.getLargeur();
		hauteur = scl * p.getHauteur();
		this.setSize(largeur, hauteur);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(lock) return;
				int i = evt.getY() / scl;
				int j = evt.getX() / scl;
				System.out.println(i + " " + j);
				v.joue(i, j);
			}
		});
	}
	
	public void lock() {
		lock = true;
	}
	
	public void unLock() {
		lock = false;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		p.afficherG(g);
		if(!p.isMoving()) {
			v.rescue();
		}

	}	
}
