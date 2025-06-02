package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";      
	
	/* prefisso di una singola riga di testo contenente tutte le informazioni delle stanze magiche nel formato <nomeStanza> <sogliaMagica> */
	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";
	
	/* prefisso di una singola riga di testo contenente tutte le informazioni delle stanze buie nel formato <nomeStanza> <nomeAttrezzo> */
	private static final String STANZE_BUIE_MARKER = "StanzeBuie:";
	
	/* prefisso di una singola riga di testo contenente tutte le informazioni delle stanze bloccate nel formato <nomeStanza> <direzioneBloccata> <nomeChiave> */
	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	
	/* prefisso della riga contenente le specifiche dei personaggi di tipo cane, formato <nomeCane> <ciboPreferito> <nomeStanza> */
	private static final String CANI_MARKER = "Cani:";
	
	/* prefisso della riga contenente le specifiche dei personaggi di tipo mago, formato <nomeMago> <nomeAttrezzo> <pesoAttrezzo> <nomeStanza> */
	private static final String MAGHI_MARKER = "Maghi:";
	
	/* prefisso della riga contenente le specifiche dei personaggi di tipo strega, formato <nomeStrega> <nomeStanza> */
	private static final String STREGHE_MARKER = "Streghe:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	private Map<String, String> righeLette;

	LabirintoBuilder labirintoBuilder;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.labirintoBuilder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.righeLette = new HashMap<>();
		this.carica();
	}
	
	//la variabile booleana serve solo a differenziare i costruttori
	//per poter mantenere anche il vecchio
	public CaricatoreLabirinto(String stringaDaLeggere, boolean unused) {
		this.labirintoBuilder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(new StringReader(stringaDaLeggere));
		this.righeLette = new HashMap<>();
		this.carica();
	}

	public void carica() {
		try {
			this.leggiTutteLeRighe();
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaMaghi();
			this.leggiECollocaCani();
			this.leggiECollocaStreghe();
			this.leggiEImpostaUscite();
		}catch (FormatoFileNonValidoException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}
	
	private void leggiTutteLeRighe() throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			while(riga != null) {
				this.righeLette.put(riga.split(":")[0] + ":", riga);
				riga = this.reader.readLine();
			}
		} catch (IOException e) {
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+ "riga non valida");		
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		String riga = this.righeLette.get(marker);
		if(riga == null) return null;
		check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
		return riga.substring(marker.length());
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			labirintoBuilder.addStanza(nomeStanza);
		}
	}
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String specificheStanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		if(specificheStanzeMagiche == null) return;

		for(String specificaStanzaMagica : separaStringheAlleVirgole(specificheStanzeMagiche)) {
			String nomeStanzaMagica = null;
			String soglia = null;
			int sogliaIntera;
			try (Scanner scannerLinea = new Scanner(specificaStanzaMagica)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanzaMagica = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("La soglia per cui la stanza diventa magica "+nomeStanzaMagica+"."));
				soglia = scannerLinea.next();
				
				try {
					sogliaIntera = Integer.parseInt(soglia);
				} catch (IllegalArgumentException e) {
					throw new FormatoFileNonValidoException(e.getMessage());
				}
			}
			labirintoBuilder.addStanzaMagica(nomeStanzaMagica, sogliaIntera);
		}
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		if(specificheStanzeBuie == null) return;

		for(String specificaStanzaBuia : separaStringheAlleVirgole(specificheStanzeBuie)) {
			String nomeStanzaBuia = null;
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanzaBuia = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo che sblocca la stanza "+nomeStanzaBuia+"."));
				nomeAttrezzo = scannerLinea.next();
			}
			labirintoBuilder.addStanzaBuia(nomeStanzaBuia, nomeAttrezzo);
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		if(specificheStanzeBloccate == null) return;

		for(String specificaStanzaBloccata : separaStringheAlleVirgole(specificheStanzeBloccate)) {
			String nomeStanzaBloccata = null;
			String direzioneBloccata = null;
			String nomeChiave = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanzaBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della direzione che blocca la stanza "+nomeStanzaBloccata+"."));
				direzioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della chiave"));
				nomeChiave = scannerLinea.next();
			}
			labirintoBuilder.addStanzaBloccata(nomeStanzaBloccata, direzioneBloccata, nomeChiave);
		}
	}
	
	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		if(specificheMaghi == null) return;

		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			int pesoAttrezzoInt;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaMago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'atttrezzo del mago "+nomeMago+"."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo del mago "+nomeMago+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui mettere il mago "+nomeMago+"."));
				nomeStanza = scannerLinea.next();
				
				try {
					pesoAttrezzoInt = Integer.parseInt(pesoAttrezzo);
				} catch (IllegalArgumentException e) {
					throw new FormatoFileNonValidoException(e.getMessage());
				}
			}
			labirintoBuilder.addMago(nomeMago, new Attrezzo(nomeAttrezzo, pesoAttrezzoInt), nomeStanza);
		}
	}
	
	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);
		if(specificheCani == null) return;

		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String nomeCiboPreferito = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cibo preferito del cane "+nomeCane+"."));
				nomeCiboPreferito = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui mettere il cane "+nomeCane+"."));
				nomeStanza = scannerLinea.next();
			}
			labirintoBuilder.addCane(nomeCane, nomeCiboPreferito, nomeStanza);
		}
	}
	
	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		if(specificheStreghe == null) return;

		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui mettere la strega "+nomeStrega+"."));
				nomeStanza = scannerLinea.next();
			}
			labirintoBuilder.addStrega(nomeStrega, nomeStanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		while(scanner.hasNext()) {
			result.add(scanner.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		labirintoBuilder.setStanzaIniziale(labirintoBuilder.getStanza(nomeStanzaIniziale));
		labirintoBuilder.setStanzaVincente(labirintoBuilder.getStanza(nomeStanzaVincente));
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		if(specificheAttrezzi == null) return;

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			labirintoBuilder.addAttrezzo(nomeStanza, attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return labirintoBuilder.getStanza(nomeStanza) != null;
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		if(specificheUscite == null) return;
		
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();
					
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		labirintoBuilder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return labirintoBuilder.getStanzaIniziale();
	}

	public Stanza getStanzaVincente() {
		return labirintoBuilder.getStanzaVincente();
	}
	
	public Labirinto getLabirinto() {
		return labirintoBuilder.getLabirinto();
	}
}
