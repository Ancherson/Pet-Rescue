import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Cette classe gère l'affichage du plateau sur l'interface graphique

public class VisuPlateau extends JPanel{
	private Visuelle v;
	private Cell[][]copyPlateau;
	private int[][] xOffs;
	private int[][] yOffs;
	
	//Largeur et hauteur des blocs
	private final int scl = 50;
	
	public final int largeur;
	public final int hauteur;
	
	//si lock = true, empeche le joueur d'interagir avec le plateau
	private boolean lock = false;
	
	private boolean fusee = false;
	private int xFusee = -1;
	private BufferedImage imageFusee;
	
	private BufferedImage imageMur;
	private BufferedImage[] imagesPet = new BufferedImage[2];
	private Color[] colors = {Color.red, Color.green, Color.blue, Color.yellow, Color.MAGENTA, Color.cyan};
	private boolean isMoving = false;
	
	public VisuPlateau(Visuelle v, Plateau p){
		this.v = v;
		largeur = scl * p.getLargeur();
		hauteur = scl * p.getHauteur() + 80;
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
				if(fusee) {
					v.fusee(xFusee);
					toggleFusee();
					return;
				}
				int i = evt.getY() / scl;
				int j = evt.getX() / scl;
				v.joue(i, j);
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent evt) {
				if(fusee) {
					xFusee = evt.getX() / scl;
					repaint();
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});
		
		this.setOpaque(false);
		
		this.setPreferredSize(new Dimension(largeur,hauteur));
		
		try {
			imageMur = ImageIO.read(new File("./mur.png"));
		} catch (IOException e) {
			throw new RuntimeException("error load ./mur.png");
		}
		
		try {
			imagesPet = new BufferedImage[2];
			imagesPet[0] = ImageIO.read(new File("./animal.png"));
			imagesPet[1] = ImageIO.read(new File("./panda.png"));	
		} catch(IOException e) {
			throw new RuntimeException();
		}
		melange();
		
		try {
			imageFusee = ImageIO.read(new File("./fusee1.png"));
		}catch(IOException e) {
			throw new RuntimeException("error load ./fusee1.png");
		}
	}
	
	public void lock() {
		lock = true;
	}
	
	public void unLock() {
		lock = false;
	}
	
	public void melange() {
		int n = (int)(Math.random() * 20);
		for(int i = 0; i < n; i++) {
			int a = (int)(Math.random() * colors.length);
			int b = (int)(Math.random() * colors.length);
			Color c = colors[a];
			colors[a] = colors[b];
			colors[b] = c;
		}
	}
	
	public void prepare(Plateau p) {
		copyPlateau = p.copy();
		xOffs = new int[copyPlateau.length][copyPlateau[0].length];
		yOffs = new int[copyPlateau.length][copyPlateau[0].length];
		for(int i = 0; i < copyPlateau.length; i++) {
			for(int j = 0; j < copyPlateau[i].length; j++) {
				xOffs[i][j] = copyPlateau[i][j].getXOff() * scl;
				yOffs[i][j] = copyPlateau[i][j].getYOff() * scl;
			}
		}
	}
	
	
	public void afficheP(Graphics g) {
		isMoving = false;
		for(int i = 0; i < copyPlateau.length; i++) {
			for(int j = 0; j < copyPlateau[i].length; j++) {
				Cell c = copyPlateau[i][j];
				if(c.estMur()) {
					g.drawImage(imageMur, j * scl, i * scl, scl, scl, this);
				}else if(c.estPet()) {
					Pet p = (Pet)c;
					g.drawImage(imagesPet[p.getImageNum()], j * scl + 1 + xOffs[i][j], i * scl + 1 + yOffs[i][j], scl - 1, scl - 1, this);
				}else if(!c.estVide()) {
					Bloc b = (Bloc)c;
					g.setColor(colors[b.getColor() - 1]);
					g.fillRect(j * scl + 1 + xOffs[i][j], i * scl + 1 + yOffs[i][j], scl - 1, scl - 1);
				}
				if(xOffs[i][j] > 0) {
					xOffs[i][j] -= 2;
					isMoving = true;
				}
				if(yOffs[i][j] < 0) {
					yOffs[i][j] += 2;
					isMoving = true;
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0,0,0,100));
		g.fillRect(0, 0, this.getWidth(), this.getHeight() - 80);
		afficheP(g);
		if(fusee && xFusee >= 0) {
			g.drawImage(imageFusee, xFusee * scl, this.getHeight() - 80, scl, 80, this);
		}
		if(!isMoving) {
			v.rescue();
		}

	}	
	
	public void toggleFusee() {
		fusee = !fusee;
		if(!fusee) {
			xFusee = -1;
			repaint();
		}
	}
}
