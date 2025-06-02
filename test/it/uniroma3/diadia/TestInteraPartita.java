package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class TestInteraPartita {
	
	IOSimulator ioSimulator;
	DiaDia diaDia;

	@Test
	void testVinta() {
		ioSimulator = new IOSimulator("vai nord");
		diaDia = new DiaDia(new LabirintoBuilder().getDefaultLabirinto(), ioSimulator);
		diaDia.gioca();
		
		assertTrue(ioSimulator.contieneRisultato("Hai vinto!"));
	}
	
	@Test
	void testAttrezziNellaPrimaStanza() {
		
		ioSimulator = new IOSimulator("prendi osso\nfine");
		diaDia = new DiaDia(new LabirintoBuilder().getDefaultLabirinto(), ioSimulator);
		diaDia.gioca();
		
		assertTrue(ioSimulator.contieneRisultato("Attrezzo inserito nella borsa"));
	}
	
	@Test
	void testVaiEst() {
		ioSimulator = new IOSimulator("vai est\nfine");
		diaDia = new DiaDia(new LabirintoBuilder().getDefaultLabirinto(), ioSimulator);
		diaDia.gioca();
		
		assertTrue(ioSimulator.contieneRisultato("Aula N11"));
	}
	
	@Test
	void testInteraPartita() {
		ioSimulator = new IOSimulator("vai sud\nprendi lanterna\nvai nord\nvai est\nvai sud\nguarda\nposa lanterna\nvai nord\nvai ovest\nvai nord");
		diaDia = new DiaDia(new LabirintoBuilder().getDefaultLabirinto(), ioSimulator);
		diaDia.gioca();
		
		assertTrue(ioSimulator.contieneRisultato("Aula N10"));
		assertTrue(ioSimulator.contieneRisultato("Attrezzo inserito nella borsa"));
		assertTrue(ioSimulator.contieneRisultato("Atrio"));
		assertTrue(ioSimulator.contieneRisultato("Aula N11"));
		assertTrue(ioSimulator.contieneRisultato("Aula N12"));
		assertTrue(ioSimulator.contieneRisultato("qui c'è un buio pesto"));
		assertTrue(ioSimulator.contieneRisultato("Attrezzo posato nella stanza"));
		assertTrue(ioSimulator.contieneRisultato("Aula N10"));
		assertTrue(ioSimulator.contieneRisultato("Hai vinto"));
	}
}
