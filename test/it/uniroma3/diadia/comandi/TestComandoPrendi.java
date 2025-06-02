package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.ImagingOpException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestComandoPrendi {
	
	Comando comando;
	Partita partita;
	
	Attrezzo  attrezzo;
	
	@BeforeEach
	public void setUp() throws Exception {
		comando = new ComandoPrendi();
		comando.setIO(new IOConsole(null));
		
		partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("atrio").getLabirinto());
		attrezzo = new Attrezzo("Martello", 1);
		
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		
	}
	
	@Test
	void testAttrezzoNull() {
		comando.setParametro(null);
		comando.esegui(partita);
		
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testAttrezzoGiusto() {
		comando.setParametro(attrezzo.getNome());
		comando.esegui(partita);
		
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testAttrezzoSbagliato() {
		comando.setParametro("NomeSbagliato");
		comando.esegui(partita);
		
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
	}

}
