package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	static final private String[] elencoComandi = {"guarda", "vai", "aiuto", "fine","prendi","posa", "interagisci", "saluta"};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++)
			this.getIO().mostraMessaggio(elencoComandi[i]+" ");
		this.getIO().mostraMessaggio("");
	}

}
