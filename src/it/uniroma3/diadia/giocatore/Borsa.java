package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
    public final static int DEFAULT_PESO_MAX_BORSA = 10;
    private Attrezzo[] attrezzi;
    private int numeroAttrezzi;
    private int pesoMax;
    
    /**
     * Crea una borsa con il peso massimo predefinito
     */
    public Borsa() {
        this(DEFAULT_PESO_MAX_BORSA);
    }
    
    /**
     * Crea una borsa
     * @param peso massimo del contenuto
     */
    public Borsa(int pesoMax) {
        this.pesoMax = pesoMax;
        this.attrezzi = new Attrezzo[10]; // speriamo bastino...
        this.numeroAttrezzi = 0;
    }
    
    /**
     * Aggiunge un attrezzo nella borsa, controllando
     * se non si sono superati i limiti di quest'ultima.
     * 
     * @param attrezzo da mettere nella borsa
     * @return vero se l'operazione è stata completata con successo
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
            return false;
        if (this.numeroAttrezzi==10)
            return false;
        this.attrezzi[this.numeroAttrezzi] = attrezzo;
        this.numeroAttrezzi++;
        return true;
    }
    
    /**
     * Ritorna il peso massimo 
     * che può supportare la borsa
     * 
     * @return peso massimo nella borsa
     */
    public int getPesoMax() {
        return pesoMax;
    }
    
    /**
     * Ritorna un attrezzo contenuto nella borsa
     * @param nome dell'attrezzo
     * @return vero se l'operazione è stata completata con successo
     */
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
        Attrezzo a = null;
        for (int i= 0; i<this.numeroAttrezzi; i++)
            if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
                a = attrezzi[i];

        return a;
    }

    /**
     * Ritorna il peso attuale 
     * del contenuto della borsa
     * 
     * @return peso totale della borsa
     */
    public int getPeso() {
        int peso = 0;
        for (int i= 0; i<this.numeroAttrezzi; i++)
            peso += this.attrezzi[i].getPeso();

        return peso;
    }
    
    /**
     * Ritorna vero se la borsa non contiene oggetti
     * @return vero se la borsa è vuota
     */
    public boolean isEmpty() {
        return this.numeroAttrezzi == 0;
    }
    
    /**
     * Ritorna vero se la borsa contiene l'attrezzo specificato
     * @param nome dell'attrezzo
     * @return vero se la borsa contiene l'attrezzo
     */
    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.getAttrezzo(nomeAttrezzo)!=null;
    }
    
    /**
     * Rimuove l'attrezzo specificato dalla borsa
     * e ritorna l'oggetto rimosso
     * 
     * @param nome dell'attrezzo
     * @return l'oggetto attrezzo appena rimosso dalla borsa
     */
    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
        Attrezzo a = null;
        int indiceAttrezzo = -1;
        
        for (int i= 0; i<this.numeroAttrezzi; i++) {
        	if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
        		a = this.attrezzi[i];
        		indiceAttrezzo = i;
        	}
        }
        
        if(a==null) return null;
        
        this.attrezzi[indiceAttrezzo] = null;
        this.numeroAttrezzi--;
        
        for (int i=indiceAttrezzo; i<this.numeroAttrezzi; i++) {
        	this.attrezzi[i] = this.attrezzi[i+1];
        }
        
        return a;
    }
    
    /**
     * Ritorna la descrizione completa
     * della borsa e del suo contenuto
     */
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (!this.isEmpty()) {
            s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
            for (int i= 0; i<this.numeroAttrezzi; i++)
                s.append(attrezzi[i].toString()+" ");
        }
        else
            s.append("Borsa vuota");
        return s.toString();
    }
}