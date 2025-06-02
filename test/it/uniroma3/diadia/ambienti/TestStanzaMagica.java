package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaMagica {
	
	Stanza stanza;
	Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.stanza = new StanzaMagica("Stanza", 1);
		this.attrezzo = new Attrezzo("Lanterna", 1);
	}
	

	@Test
	void testNonModificaOggettoTrue() {
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testModificaOggettoTrue() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.removeAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzo);
		
		assertTrue(this.stanza.hasAttrezzo(new StringBuilder(this.attrezzo.getNome()).reverse().toString()));
	}
	
	@Test
	void testNonModificaPesoOggetto() {
		this.stanza.addAttrezzo(attrezzo);
		assertEquals(this.attrezzo.getPeso(), this.stanza.getAttrezzo(attrezzo.getNome()).getPeso());
	}
	
	@Test
	void testModificaPesoOggetto() {
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.removeAttrezzo(attrezzo);
		this.stanza.addAttrezzo(attrezzo);
		assertNotEquals(this.attrezzo.getPeso(), this.stanza.getAttrezzo(new StringBuilder(this.attrezzo.getNome()).reverse().toString()).getPeso());
	}

}
