package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLabirinto {
	
	Labirinto labirinto;
	
	@BeforeEach
	public void setUp() throws Exception {
		labirinto = Labirinto.newBuilder().getLabirinto();
	}
	
	@Test
	void testGetStanzaInizialeNull() {
		assertNull(labirinto.getStanzaIniziale());
	}

	@Test
	void testGetStanzaVincenteNull() {
		assertNull(labirinto.getStanzaVincente());
	}
}
