package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		this.nomeAttrezzo = this.getParametro();
		
		if(nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Per favore, specifica il nome dell'attrezzo");
			return;
		}
		
		if(!partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			this.getIO().mostraMessaggio("Attrezzo non trovato nella stanza");
			return;
		}
		
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		partita.getStanzaCorrente().removeAttrezzo(attrezzo);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		
		this.getIO().mostraMessaggio("Attrezzo inserito nella borsa");
		
	}

}
