package it.uniroma3.diadia;

import java.util.Scanner;

public class IOSimulator implements IO {
	
	Scanner scannerDiLinee;
	
	String comandi;
	String[] risultati;
	int numeroRisultati;
	
	public IOSimulator(String comandi) {
		this.comandi = comandi;
		this.risultati = new String[100];
		this.numeroRisultati = 0;
		scannerDiLinee = new Scanner(comandi);
	}

	@Override
	public void mostraMessaggio(String msg) {
		risultati[numeroRisultati++] = msg;
	}

	@Override
	public String leggiRiga() {
        String riga = scannerDiLinee.nextLine();
        return riga;
	}
	
	public boolean contieneRisultato(String risultato) {
		
		for(int i=0; i<numeroRisultati; i++) {
			if(risultati[i].contains(risultato)) return true;
		}
		
		return false;
	}

	
}
