package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGiocatore {
	
	Giocatore giocatore;
	
	@BeforeEach
	public void setUp() throws Exception {
		giocatore = new Giocatore();
	}

	@Test
	void testCfuInizialiMaggioriDiZero() {
		assertTrue(giocatore.getCfu() > 0);
	}
	
	@Test
	void testSetCfu() {
		giocatore.setCfu(100);
		assertEquals(100, giocatore.getCfu());
	}

}
