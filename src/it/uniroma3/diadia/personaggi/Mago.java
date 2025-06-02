package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private static final String PRESENTAZIONE = "Ciao sono {nome}, un mago. Regalo attrezzi a chi interagisce con me e li aiuto con gli attrezzi!";
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, "
			+ "con una mia magica azione, troverai un nuovo oggetto " + "per il tuo borsone!";
	
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	private static final String MESSAGGIO_REGALO = "Ho dimezzato il peso dell'attrezzo e l'ho lasciato nella stanza";
	
	private Attrezzo attrezzo;

	public Mago(String nome, Attrezzo attrezzo) {
		super(nome, PRESENTAZIONE.replace("{nome}", nome));
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		} else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		attrezzo.setPeso(attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		
		return MESSAGGIO_REGALO;
	}
}