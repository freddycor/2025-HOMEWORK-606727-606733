package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	String direzioneBloccata;
	String nomeChiave;

	public StanzaBloccata(String nome, String direzioneBloccata, String nomeChiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.nomeChiave = nomeChiave;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
        if(!super.hasAttrezzo(nomeChiave)) return (Stanza)this;
        else return super.getStanzaAdiacente(direzione);
        
	}
	
	@Override
	public String getDescrizione() {
		StringBuilder output = new StringBuilder();
		
		output.append("Direzione Bloccata: " + direzioneBloccata);
		output.append("Oggetto Chiave: " + nomeChiave);
		output.append(super.toString());
		
		return output.toString();
	}
	
	

}
