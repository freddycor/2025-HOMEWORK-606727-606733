package it.uniroma3.diadia.comandi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		for(String comando : this.creaListaComandi(this.getClass().getPackage().getName())) {
			this.getIO().mostraMessaggio(comando);
		}
		this.getIO().mostraMessaggio("");
	}
	
	public List<String> creaListaComandi(String packageName) {
	    List<String> comandi = new ArrayList<>();

	    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replace('.', '/'));
	    if (stream == null) {
	        return comandi;
	    }

	    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	    String line;
	    
	    try {
	        while ((line = reader.readLine()) != null) {
	            if (!line.endsWith(".class")) continue;
	            if (!line.startsWith("Comando")) continue;
	            if (line.contains("NonValido")) continue;

	            String nomeComando = line
	                .replace("Comando", "")
	                .replace(".class", "")
	                .toLowerCase();
	            comandi.add(nomeComando);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return comandi;
	}

}
