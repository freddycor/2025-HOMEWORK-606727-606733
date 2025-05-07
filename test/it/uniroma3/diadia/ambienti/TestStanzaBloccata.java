package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaBloccata {
	
	Stanza stanza1;
	Stanza stanza2;
	Attrezzo oggettoChiave;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.stanza1 = new StanzaBloccata("Stanza 1", "est", "Martello");
		this.stanza2 = new Stanza("Stanza 2");
		this.oggettoChiave = new Attrezzo("Martello", 1);
		this.stanza1.impostaStanzaAdiacente("est", stanza2);
	}
	

	@Test
	void testSenzaAttrezzo() {
		assertEquals(stanza1, stanza1.getStanzaAdiacente("est"));
	}
	
	@Test
	void testConAttrezzo() {
		stanza1.addAttrezzo(oggettoChiave);
		assertEquals(stanza2, stanza1.getStanzaAdiacente("est"));
	}
	
	@Test
	void testPrimaEDopo() {
		assertEquals(stanza1, stanza1.getStanzaAdiacente("est"));
		stanza1.addAttrezzo(oggettoChiave);
		assertEquals(stanza2, stanza1.getStanzaAdiacente("est"));
	}

}
