package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private String nomeAttrezzo;
	
	private IO io;
	
	public ComandoPosa(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null) {
			io.mostraMessaggio("Per favore, specifica il nome dell'attrezzo");
			return;
		}
		
		if(!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			io.mostraMessaggio("Attrezzo non trovato nella borsa");
			return;
		}
		
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		
		io.mostraMessaggio("Attrezzo posato nella stanza");
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
		
	}

}
