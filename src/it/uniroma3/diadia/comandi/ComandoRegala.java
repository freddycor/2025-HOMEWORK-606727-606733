package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private static final String MESSAGGIO_A_CHI = "A chi dovrei regala qualcosa?";

	private String messaggio;
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		this.nomeAttrezzo = this.getParametro();
		
		if(nomeAttrezzo == null) {
			this.getIO().mostraMessaggio("Specifica il nome dell'attrezzo da regalare");
			return;
		}
		
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(attrezzo == null) {
			this.getIO().mostraMessaggio("Non hai questo attrezzo nella borsa");
			return;
		}
		
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.messaggio = personaggio.riceviRegalo(attrezzo, partita);
			this.getIO().mostraMessaggio(this.messaggio);
		} else
			this.getIO().mostraMessaggio(MESSAGGIO_A_CHI);
	}

	public String getMessaggio() {
		return this.messaggio;
	}
}
