package it.uniroma3.diadia;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("serial")
public class DiaDiaProperties extends Properties {
	
	public DiaDiaProperties() {
		
		try {
			//Carica le impostazioni dal file
			this.load(new FileReader("diadia.properties"));
			
		} catch (IOException e) {
			//Il file non esiste (imposta quelle di default leggendole dal file distribuito con il codice)
			try {
				this.load(this.getClass().getClassLoader().getResourceAsStream("diadia.properties"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			try {
				//Salva il file con le impostazioni di default
				this.store(new FileWriter("diadia.properties"), "Configurazione del Gioco DiaDia");
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}
}
