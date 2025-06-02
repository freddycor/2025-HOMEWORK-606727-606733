package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	private String nomeAttrezzo;
	
	private IO io;
	
	public ComandoPrendi(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		this.nomeAttrezzo = this.getParametro();
		
		if(nomeAttrezzo == null) {
			io.mostraMessaggio("Per favore, specifica il nome dell'attrezzo");
			return;
		}
		
		if(!partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			io.mostraMessaggio("Attrezzo non trovato nella stanza");
			return;
		}
		
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		partita.getStanzaCorrente().removeAttrezzo(attrezzo);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		
		io.mostraMessaggio("Attrezzo inserito nella borsa");
		
	}

}
