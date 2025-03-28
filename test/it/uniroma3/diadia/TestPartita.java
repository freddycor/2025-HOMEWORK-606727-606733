package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPartita {
	
	Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.partita = new Partita();
	}
	
	@Test
	public void testVintaFalse() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	public void testVintaTrue() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	@Test
	public void testVintaPrimaEDopo() {
		assertFalse(this.partita.vinta());
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testIsFinitaFalse() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaVinta() {
		this.partita.setStanzaCorrente(this.partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinitaCfu() {
		this.partita.getGiocatore().setCfu(0);;
		assertTrue(this.partita.isFinita());
	}
}
