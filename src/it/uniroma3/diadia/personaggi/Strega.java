package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	private static final String PRESENTAZIONE = "Ciao sono {nome}, una strega. Vedo che mi hai salutato, quindi la prossima volta che interagirai con me ti aiuterò!";
	private static final String MESSAGGIO_SENZA_SALUTO = "Non mi hai salutato, ti trasferisco nella stanza adiacente peggiore";
	private static final String MESSAGGIO_CON_SALUTO = "Mi hai salutato! Ti trasferisco nella stanza adiacente migliore";
	private static final String MESSAGGIO_CON_RISATE = "HO ricevuto il tuo regalo, HA HA HA HA";

	public Strega(String nome) {
		super(nome, PRESENTAZIONE.replace("{nome}", nome));
	}

	@Override
	public String agisci(Partita partita) {
		String messaggio;
		if(this.haSalutato()) {
			messaggio = MESSAGGIO_CON_SALUTO;
			partita.setStanzaCorrente(getStanzeAdiacentiOrdinate(partita).get(partita.getStanzaCorrente().getMapStanzeAdiacenti().size() - 1));
		} else {
			messaggio = MESSAGGIO_SENZA_SALUTO;
			partita.setStanzaCorrente(getStanzeAdiacentiOrdinate(partita).get(0));
		}
		
		return messaggio;
	}
	
	private List<Stanza> getStanzeAdiacentiOrdinate(Partita partita) {
		List<Stanza> ordinati = new ArrayList<>();
		ordinati.addAll(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		
		Collections.sort(ordinati, new Comparator<Stanza>() {

			@Override
			public int compare(Stanza o1, Stanza o2) {
				return o1.getAttrezzi().size() - o2.getAttrezzi().size();
			}
			
		});
		return ordinati;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return MESSAGGIO_CON_RISATE;
	}
}