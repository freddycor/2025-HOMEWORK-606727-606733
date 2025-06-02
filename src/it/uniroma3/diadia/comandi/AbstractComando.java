package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	
	private String parametro;
	private IO io;
	
	@Override
	public abstract void esegui(Partita partita);
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	@Override
	public IO getIO() {
		return this.io;
	}
	
	@Override
	public void setIO(IO io) {
		this.io = io;
	}
}
