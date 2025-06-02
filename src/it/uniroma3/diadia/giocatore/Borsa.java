package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
    public final static int DEFAULT_PESO_MAX_BORSA = 10;
    private Map<String, Attrezzo> attrezzi;
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
        this.attrezzi = new HashMap<>();
    }
    
    /**
     * Aggiunge un attrezzo nella borsa, controllando
     * se non si sono superati i limiti di quest'ultima.
     * 
     * @param attrezzo da mettere nella borsa
     * @return vero se l'operazione è stata completata con successo
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) return false;
        
        this.attrezzi.put(attrezzo.getNome(), attrezzo);
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
       
    	return this.attrezzi.get(nomeAttrezzo);
    }

    /**
     * Ritorna il peso attuale 
     * del contenuto della borsa
     * 
     * @return peso totale della borsa
     */
    public int getPeso() {
        int peso = 0;
        for (Attrezzo a : attrezzi.values())
            peso += a.getPeso();

        return peso;
    }
    
    /**
     * Ritorna vero se la borsa non contiene oggetti
     * @return vero se la borsa è vuota
     */
    public boolean isEmpty() {
        return this.attrezzi.isEmpty();
    }
    
    /**
     * Ritorna vero se la borsa contiene l'attrezzo specificato
     * @param nome dell'attrezzo
     * @return vero se la borsa contiene l'attrezzo
     */
    public boolean hasAttrezzo(String nomeAttrezzo) {
        return this.attrezzi.containsKey(nomeAttrezzo);
    }
    
    /**
     * Rimuove l'attrezzo specificato dalla borsa
     * e ritorna l'oggetto rimosso
     * 
     * @param nome dell'attrezzo
     * @return l'oggetto attrezzo appena rimosso dalla borsa
     */
    public Attrezzo removeAttrezzo(String nomeAttrezzo) {
    	
    	return this.attrezzi.remove(nomeAttrezzo);
   }
        
    
    /**
     * Ritorna la descrizione completa
     * della borsa e del suo contenuto
     */
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (!this.isEmpty()) {
            s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
            for (Attrezzo a : getContenutoOrdinatoPerPeso())
                s.append(a.toString()+" ");
        }
        else
            s.append("Borsa vuota");
        return s.toString();
    }
    
    public List<Attrezzo> getContenutoOrdinatoPerPeso() {
    	List<Attrezzo> ordinata = new ArrayList<>(this.attrezzi.values());
    	
    	ordinata.sort(new Comparator<Attrezzo>() {

			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				int cmp = o1.getPeso() - o2.getPeso();
				if(cmp == 0) return o1.getNome().compareTo(o2.getNome());
				return cmp;
			}
		});
    	
    	return ordinata;
    }
    
    public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
    	SortedSet<Attrezzo> ordinati = new TreeSet<Attrezzo>(new Comparator<Attrezzo>() {

			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				return o1.getNome().compareTo(o2.getNome());
			}
    		
    	});
    	ordinati.addAll(this.attrezzi.values());
    	
    	return ordinati;
    }
    
    SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
    	SortedSet<Attrezzo> ordinati = new TreeSet<Attrezzo>(new Comparator<Attrezzo>() {

			@Override
			public int compare(Attrezzo o1, Attrezzo o2) {
				int cmp = o1.getPeso() - o2.getPeso();
				if(cmp == 0) return o1.getNome().compareTo(o2.getNome());
				return cmp;
			}
    		
    	}); 
    	ordinati.addAll(this.attrezzi.values());
    	return ordinati;
    }
    
    Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
    	Map<Integer,Set<Attrezzo>> raggruppati = new HashMap<>();
    	
    	for(Attrezzo a : this.attrezzi.values()) {
    		Set<Attrezzo> set = raggruppati.get(a.getPeso());
    		if(set == null) {
    			set = new HashSet<Attrezzo>();
    			raggruppati.put(a.getPeso(), set);
    		}
    		set.add(a);
    	}
    	
    	return raggruppati;
    }
}