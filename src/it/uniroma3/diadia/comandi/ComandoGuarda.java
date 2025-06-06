package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("+---------------------------+\n"
				+ partita.getStanzaCorrente().getDescrizione() 
				+ "\n\nCfu = " 
				+ partita.getGiocatore().getCfu()
				+ "\n"
				+ partita.getGiocatore().getBorsa().toString()
				+ "\n"
				+ "+---------------------------+\n");
	}

}
