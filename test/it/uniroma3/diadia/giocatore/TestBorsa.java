package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestBorsa {
	
	private Borsa borsa;
	private Attrezzo martello;
	
	@BeforeEach
	public void setUp() throws Exception {
		borsa = new Borsa();
		martello = new Attrezzo("Martello", 1);
	}

	@Test
	public void testHasAttrezzoFalse() {
		assertFalse(borsa.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testAddAttrezzo() {
		borsa.addAttrezzo(martello);
		
		assertTrue(borsa.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testAddAttrezzoPrimaEDopo() {
		assertFalse(borsa.hasAttrezzo("Martello"));
		borsa.addAttrezzo(martello);
		
		assertTrue(borsa.hasAttrezzo("Martello"));
	}
	
	@Test
	public void testGetAttrezzoNull() {
		borsa.addAttrezzo(martello);
		
		assertNull(borsa.getAttrezzo("Trapano"));
	}
	
	@Test
	public void testGetAttrezzo() {
		borsa.addAttrezzo(martello);
		
		assertSame(martello, borsa.getAttrezzo("Martello"));
	}
	
	@Test
	public void testGetAttrezzoTraMolti() {
		borsa.addAttrezzo(new Attrezzo("Trapano", 1));
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(new Attrezzo("Livella", 1));
		
		assertSame(martello, borsa.getAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		borsa.addAttrezzo(martello);
		borsa.removeAttrezzo("Martello");
		
		assertNull(borsa.getAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoPrimaEDopo() {
		borsa.addAttrezzo(martello);
		assertSame(martello, borsa.getAttrezzo("Martello"));
		borsa.removeAttrezzo("Martello");
		
		assertNull(borsa.getAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoTraMolti() {
		borsa.addAttrezzo(new Attrezzo("Trapano", 1));
		borsa.addAttrezzo(martello);
		borsa.addAttrezzo(new Attrezzo("Livella", 1));
		borsa.removeAttrezzo("Martello");
		
		assertNull(borsa.getAttrezzo("Martello"));
	}
	
	@Test
	public void testStessoPesoOrdinatiSortedSet() {
		borsa.addAttrezzo(new Attrezzo("Trapano", 1));
		borsa.addAttrezzo(martello);
		
		assertEquals("[Martello:1, Trapano:1]", borsa.getContenutoOrdinatoPerNome().toString());
	}
	
	@Test
	public void testStessoPesoOrdinatiLista() {
		borsa.addAttrezzo(new Attrezzo("Trapano", 1));
		borsa.addAttrezzo(martello);
		
		assertEquals("[Martello:1, Trapano:1]", borsa.getContenutoOrdinatoPerPeso().toString());
	}
	
	@Test
	public void testOrdinatiPerNomeSortedSet() {
		borsa.addAttrezzo(new Attrezzo("Trapano", 1));
		borsa.addAttrezzo(martello);
		
		assertEquals("[Martello:1, Trapano:1]", borsa.getContenutoOrdinatoPerNome().toString());
	}
	
	@Test
	public void testStessoPesoOrdinatiMappa() {
		borsa.addAttrezzo(new Attrezzo("Trapano", 1));
		borsa.addAttrezzo(martello);
		
		assertEquals("{1=[Martello:1, Trapano:1]}", borsa.getContenutoRaggruppatoPerPeso().toString());
	}

}
