package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

class TestCaricatoreLabirinto {

	@Test
	void testMonolocale() {
		String stringaDaLeggere = "Stanze:libreria,atrio\nInizio:libreria\nVincente:atrio";
		Labirinto monolocale = new CaricatoreLabirinto(stringaDaLeggere, false).getLabirinto();
	assertEquals("libreria", monolocale.getStanzaIniziale().getNome());
	assertEquals("atrio", monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testBilocale() {
		String stringaDaLeggere = "Stanze:libreria,atrio\nInizio:libreria\nVincente:atrio\nAttrezzi:martello 1 atrio\nUscite:libreria nord atrio,atrio sud libreria";
		Labirinto bilocale = new CaricatoreLabirinto(stringaDaLeggere, false).getLabirinto();
		
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertEquals(Collections.singletonList(Direzione.NORD),bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singletonList(Direzione.SUD),bilocale.getStanzaVincente().getDirezioni());
	}
	
	@Test
	public void testTrilocale(){
		String stringaDaLeggere = "Stanze:libreria,atrio,N11\nInizio:libreria\nVincente:atrio\nAttrezzi:martello 1 atrio\n"
				+ "Uscite:libreria nord atrio,atrio sud libreria,libreria sud N11";
		Labirinto trilocale = new CaricatoreLabirinto(stringaDaLeggere, false).getLabirinto();
		
		assertEquals("libreria", trilocale.getStanzaIniziale().getNome());
		assertEquals("atrio", trilocale.getStanzaVincente().getNome());
		assertEquals("N11",trilocale.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	void testMonolocaleConStrega() {
		String stringaDaLeggere = "Stanze:libreria,atrio\nInizio:libreria\nVincente:atrio\nStreghe:Circe libreria";
		Labirinto monolocale = new CaricatoreLabirinto(stringaDaLeggere, false).getLabirinto();
		assertNotNull(monolocale.getStanzaIniziale().getPersonaggio());
		assertEquals("Circe", monolocale.getStanzaIniziale().getPersonaggio().getNome());
	}
}
