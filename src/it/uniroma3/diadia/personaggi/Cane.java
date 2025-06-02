package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String PRESENTAZIONE = "Ciao sono {nome}, un cane. Mordo sempre, ma se mi regali il mio cibo preferito ti regalo qualcosa";
	
	private static final String MESSAGGIO = "Sei stato morso da un cane, perdi 1 CFU";
	
	private static final String MESSAGGIO_CIBO_GIUSTO = "Ho lasciato un attrezzo nella stanza";
	
	private static final String MESSAGGIO_CIBO_SBAGLIATO = "Il cibo non piaceva al cane, perdi 1 CFU";
	
	String ciboPreferito;
	Attrezzo attrezzoRegalo;


	public Cane(String nome, String ciboPreferito) {
		super(nome, PRESENTAZIONE.replace("{nome}", nome));
		this.ciboPreferito = ciboPreferito;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return MESSAGGIO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		if(attrezzo.getNome().equals(this.ciboPreferito)) {
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			partita.getStanzaCorrente().addAttrezzo(this.attrezzoRegalo);
			return MESSAGGIO_CIBO_GIUSTO;
		} else {
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
			return MESSAGGIO_CIBO_SBAGLIATO;
		}
	}
	
	
}