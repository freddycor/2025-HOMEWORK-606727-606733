package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IOSimulator implements IO {
	
	Scanner scannerDiLinee;
	
	String comandi;
	List<String> risultati;
	Map<String, List<String>> risultatiPerComando;
	
	String lastComando;
	
	public IOSimulator(String comandi) {
		this.comandi = comandi;
		this.risultati = new ArrayList<>();
		this.risultatiPerComando = new HashMap<>();
		scannerDiLinee = new Scanner(comandi);
	}

	@Override
	public void mostraMessaggio(String msg) {
		risultati.add(msg);
		
		List<String> listaRisulati = risultatiPerComando.get(lastComando);
		if(listaRisulati == null) {
			listaRisulati = new ArrayList<>();
			risultatiPerComando.put(lastComando, listaRisulati);
		}
		listaRisulati.add(msg);
	}

	@Override
	public String leggiRiga() {
        String riga = scannerDiLinee.nextLine();
        lastComando = riga;
        return riga;
	}
	
	public boolean contieneRisultato(String risultato) {
		return this.risultati.contains(risultato);
	}

	public boolean contieneRisultatoPerComando(String comando, String risultato) {
		List<String> listaRisultati = this.risultatiPerComando.get(comando);
		if(listaRisultati == null) return false;
		
		return listaRisultati.contains(risultato);
	}
}
