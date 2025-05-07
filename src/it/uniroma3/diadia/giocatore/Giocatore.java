package it.uniroma3.diadia.giocatore;

public class Giocatore {

	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	
	private Borsa borsa;
	
	/**
	 * Crea un giocatore
	 */
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	/**
	 * Restituisce i CFU attuali del giocatore
	 * @return CFU attuali
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Imposta i CFU del giocatore
	 * @param cfu da impostare
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	/**
	 * Restituisce la borsa in cui 
	 * il giocatore può mettere oggetti
	 * 
	 * @return borsa del giocatore
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	/**
	 * Restituisce se il giocatore è vivo
	 * @return true se vivo
	 */
	public boolean isVivo() {
		return this.cfu > 0;
	}
}
