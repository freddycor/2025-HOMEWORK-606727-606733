package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		this.nomeAttrezzo = this.getParametro();
		
		if(nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Per favore, specifica il nome dell'attrezzo");
			return;
		}
		
		if(!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			this.getIO().mostraMessaggio("Attrezzo non trovato nella borsa");
			return;
		}
		
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		
		this.getIO().mostraMessaggio("Attrezzo posato nella stanza");
		
	}
}
