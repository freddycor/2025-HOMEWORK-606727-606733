package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestComandoPosa {
	
	Comando comando;
	Partita partita;
	
	Attrezzo attrezzo;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		
		comando = new ComandoPosa();
		comando.setIO(new IOConsole());
				
		partita = new Partita(Labirinto.newBuilder().addStanzaIniziale("atrio").getLabirinto());
		attrezzo = new Attrezzo("Martello",1);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}

	@Test
	void testAttrezzoNull() {
		comando.setParametro(null);
		comando.esegui(partita);
		
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testAttrezzoGiusto() {
		comando.setParametro(attrezzo.getNome());
		comando.esegui(partita);
		
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testAttrezzoSbagliato() {
		comando.setParametro("NomeSbagliato");
		comando.esegui(partita);
		
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}
	
}
