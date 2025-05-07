package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaBuia {
	
	Stanza stanza;
	Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.stanza = new StanzaBuia("Stanza", "Lanterna");
		this.attrezzo = new Attrezzo("Lanterna", 1);
	}
	

	@Test
	void testSenzaOggetto() {
		assertEquals("qui c'è un buio pesto", stanza.getDescrizione());
	}
	
	@Test 
	void testConOggetto() {
		stanza.addAttrezzo(attrezzo);
		assertNotEquals("qui c'è un buio pesto", stanza.getDescrizione());
	}

}
