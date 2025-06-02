package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	private Labirinto() {}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	public static class LabirintoBuilder {
		
		Stanza stanzaIniziale;
		Stanza stanzaVincente;
		
		Map<String, Stanza> stanze;
		
		Stanza ultimaStanzaAggiunta;
		
		public LabirintoBuilder() {
			this.stanze = new HashMap<>();
		}
		
		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza nuovaStanza = new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, nuovaStanza);
			this.ultimaStanzaAggiunta = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza nuovaStanza = new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, nuovaStanza);
			this.stanzaIniziale = nuovaStanza;
			this.ultimaStanzaAggiunta = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder setStanzaIniziale(Stanza stanza) {
			this.stanzaIniziale = stanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			Stanza nuovaStanza = new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, nuovaStanza);
			this.stanzaVincente = nuovaStanza;
			this.ultimaStanzaAggiunta = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder setStanzaVincente(Stanza stanza) {
			this.stanzaVincente = stanza;
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo, int peso) {
			this.stanze.get(nomeStanza).addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeStanza, Attrezzo attrezzo) {
			this.stanze.get(nomeStanza).addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia) {
			StanzaMagica nuovaStanza = new StanzaMagica(nomeStanza, soglia);
			this.stanze.put(nomeStanza, nuovaStanza);
			this.ultimaStanzaAggiunta = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String nomeChiave) {
			StanzaBloccata nuovaStanza = new StanzaBloccata(nome, direzioneBloccata, nomeChiave);
			this.stanze.put(nome, nuovaStanza);
			this.ultimaStanzaAggiunta = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome, String nomeAttrezzo) {
			StanzaBuia nuovaStanza = new StanzaBuia(nome, nomeAttrezzo);
			this.stanze.put(nome, nuovaStanza);
			this.ultimaStanzaAggiunta = nuovaStanza;
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String nomeStanzaPartenza, String nomeStanzaArrivo, String direzione) {
			this.stanze.get(nomeStanzaPartenza).impostaStanzaAdiacente(direzione, this.stanze.get(nomeStanzaArrivo));
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String nomeStanza) {
			this.stanze.get(nomeStanza).setPersonaggio(new Strega(nome));
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, Attrezzo attrezzo, String nomeStanza) {
			this.stanze.get(nomeStanza).setPersonaggio(new Mago(nome,attrezzo));
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String ciboPreferito, String nomeStanza) {
			this.stanze.get(nomeStanza).setPersonaggio(new Cane(nome,ciboPreferito));
			return this;
		}
		
		
		public Stanza getStanza(String nomeStanza) {
			return this.stanze.get(nomeStanza);
		}
		
		public Map<String, Stanza> getListaStanze() {
			return this.stanze;
		}
		
		public Stanza getStanzaIniziale() {
			return this.stanzaIniziale;
		}
		
		public Stanza getStanzaVincente() {
			return this.stanzaVincente;
		}
		
		public Labirinto getDefaultLabirinto() {
			return this.addStanzaIniziale("Atrio")
			.addStanza("Aula N11")
			.addStanzaBuia("Aula N12", "lanterna")
			.addStanza("Aula N10")
			.addStanza("Laboratorio Campus")
			.addStanzaVincente("Biblioteca")
			.addAdiacenza("Atrio", "Biblioteca", "nord")
			.addAdiacenza("Atrio", "Aula N11", "est")
			.addAdiacenza("Atrio", "Aula N10", "sud")
			.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
			.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
			.addAdiacenza("Aula N11", "Atrio", "ovest")
			.addAdiacenza("Aula N11", "Aula N12", "sud")
			.addAdiacenza("Aula N12", "Aula N11", "nord")
			.addAdiacenza("Aula N10", "Atrio", "nord")
			.addAdiacenza("Aula N10", "Aula N11", "est")
			.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
			.addAdiacenza("Laboratorio Campus", "Atrio", "est")
			.addAdiacenza("Laboratorio Campus", "Aula N10", "ovest")
			.addAdiacenza("Biblioteca", "Atrio", "sud")
			.addAttrezzo("Aula N10", "lanterna", 3)
			.addAttrezzo("Atrio", "osso", 1)
			.getLabirinto();
		}
		
		public Labirinto getLabirinto() {
			Labirinto labirinto = new Labirinto();
			labirinto.setStanzaIniziale(this.stanzaIniziale);
			labirinto.setStanzaVincente(this.stanzaVincente);
			
			return labirinto;
		}
	}
    
    /**
     * Restituisce la stanza vincente
     * @return Stanza Vincente
     */
    public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
    
    /**
     * Restituisce la stanza iniziale
     * @return Stanza Iniziale
     */
    public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
    
    /**
     * Imposta la stanza vincente
     * @param Stanza Vincente
     */
    public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
    
    /**
     * Imposta la stanza iniziale
     * @param Stanza Iniziale
     */
    public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

}
