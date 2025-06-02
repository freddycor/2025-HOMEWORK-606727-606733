package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private Labirinto labirinto;

	private Stanza stanzaCorrente;
	
	private boolean finita;
	
	private Giocatore giocatore;
	
	private DiaDiaProperties properties;
	
	public Partita(Labirinto labirinto, DiaDiaProperties properties) {
		this.finita = false;
		this.labirinto = labirinto;
		this.stanzaCorrente = labirinto.getStanzaIniziale();
		this.giocatore = new Giocatore(properties);
		this.properties = properties;
	}
	
	
	public Partita(Labirinto labirinto) {
		this(labirinto, new DiaDiaProperties());
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo 
	 * se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo 
	 * se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || !giocatore.isVivo();
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	/**
	 * Restituisce il giocatore della partita
	 * @return giocatore
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	/**
	 * Restituisce il labirinto in 
	 * cui viene giocata la partita
	 * 
	 * @return labirinto del gioco
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	/**
	 * Imposta il labirinto della partita
	 * 
	 * @param Labirinto da impostare
	 */
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
	
	/**
	 * Restituisce la descrizione
	 * completa dello stato della partita
	 * 
	 * @return lo stato partita
	 */
	@Override
	public String toString() {
		return "Partita [labirinto=" + labirinto + ", stanzaCorrente=" + stanzaCorrente + ", finita=" + finita
				+ ", giocatore=" + giocatore + "]";
	}
	
	
}
