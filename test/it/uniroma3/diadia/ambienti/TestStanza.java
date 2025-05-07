package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanza {
	
	Stanza stanza1;
	Stanza stanza2;
	
	Attrezzo martello;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.stanza1 = new Stanza("Stanza 1");
		this.stanza2 = new Stanza("Stanza 2");
		this.martello = new Attrezzo("Martello", 10);
	}
	
	@Test
	public void testImpostaStanzaAdiacente() {
		this.stanza1.impostaStanzaAdiacente("nord", this.stanza2);
		assertEquals(this.stanza2, this.stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testHasAttrezzo() {
		assertFalse(this.stanza1.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testAddAttrezzo() {
		this.stanza1.addAttrezzo(this.martello);
		assertTrue(this.stanza1.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testAddAttrezzoFalso() {
		this.stanza1.addAttrezzo(this.martello);
		assertFalse(this.stanza1.hasAttrezzo("Spada"));
	}
	
	@Test
	public void testAddAttrezzoPrimaEDopo() {
		assertEquals(false, this.stanza1.hasAttrezzo("Martello"));
		this.stanza1.addAttrezzo(this.martello);
		assertTrue(this.stanza1.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		this.stanza1.addAttrezzo(this.martello);
		this.stanza1.removeAttrezzo(this.martello);
		assertFalse(this.stanza1.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoPrimaEDopo() {
		this.stanza1.addAttrezzo(this.martello);
		assertTrue(this.stanza1.hasAttrezzo("Martello"));
		this.stanza1.removeAttrezzo(this.martello);
		assertFalse(this.stanza1.hasAttrezzo("Martello"));
	}
	
	
}
