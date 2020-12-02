
//Cette interface est utilisé pour les interacteur
public interface Interacteur {
	
	void start();
	void veutRejouer();
	void prochainCoup();
	void close();
	void setMaxLevel(int levelMax);
}
