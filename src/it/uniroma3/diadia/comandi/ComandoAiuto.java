package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	
	private IO io;
	static final private String[] elencoComandi = {"guarda", "vai", "aiuto", "fine","prendi","posa"};
	
	public ComandoAiuto(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}

}
