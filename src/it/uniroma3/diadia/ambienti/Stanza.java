package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	private String nome;
	
    private Map<String, Attrezzo> attrezzi;
    
    private Map<Direzione, Stanza> stanzeAdiacenti;
    
    private AbstractPersonaggio personaggio;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	if(this.stanzeAdiacenti.size() >= Direzione.values().length) return;
        this.stanzeAdiacenti.put(Direzione.valueOf(direzione.toUpperCase()), stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
		Direzione direzioneEnum;
		try {
			direzioneEnum = Direzione.valueOf(direzione.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
       return this.stanzeAdiacenti.get(direzioneEnum);
	}
	
	/**
     * Restituisce la mappa delle stanze adiacenti
     * @param direzione
     */
	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
       return this.stanzeAdiacenti;
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return new ArrayList<Attrezzo>(this.attrezzi.values());
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	return this.attrezzi.put(attrezzo.getNome(), attrezzo) == null;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.stanzeAdiacenti.keySet())
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo a : attrezzi.values())
    		risultato.append(a.toString()+" ");
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome()) != null;
	}
	
	/**
	 * Imposta il personaggio alla stanza
	 * @param personaggio
	 */
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	/**
	 * Ritorna il personaggio associato alla stanz
	 * @return personaggio
	 */
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	/**
     * Restituisce la collezione delle direzioni disponibili dalla stanza.
     * @return la collezione di direzioni che si possono prendere dalla stanza.
     */
	public List<Direzione> getDirezioni() {
		return new ArrayList<Direzione>(this.stanzeAdiacenti.keySet());
    }
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza)o;
		return this.getNome().equals(that.getNome());
	}

}