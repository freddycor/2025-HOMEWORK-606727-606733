package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class TestComandoVai {
	
	Comando comando;
	Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception {
		comando = new ComandoVai(new IOConsole());
		partita = new Partita(new LabirintoBuilder().addStanzaIniziale("atrio").addStanza("N11").addAdiacenza("atrio", "N11", "est").getLabirinto());
	}

	@Test
	void testDirezioneNulla() {
		comando.setParametro(null);
		comando.esegui(partita);
		assertEquals(partita.getStanzaCorrente(), partita.getLabirinto().getStanzaIniziale());
	}
	
	
	@Test
	void testDirezioneNonNulla() {
		comando.setParametro("est");
		comando.esegui(partita);
		
		assertEquals(partita.getStanzaCorrente(), partita.getLabirinto().getStanzaIniziale().getStanzaAdiacente("est"));
		
	}
	
	@Test
	void testDiminuzioneCfu() {
		int cfu = partita.getGiocatore().getCfu();
		
		comando.setParametro("est");
		comando.esegui(partita);
		
		assertEquals(cfu-1, partita.getGiocatore().getCfu());
		
	}

}
