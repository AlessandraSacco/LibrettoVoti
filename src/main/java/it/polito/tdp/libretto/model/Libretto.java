package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Memorizza e gestisce un insieme di voti superati
 * 
 * @author Alessandra
 *
 */

public class Libretto {
	
	private List<Voto> voti = new ArrayList<>();
	/**
	 * Aggiunge un nuovo voto al Libretto
	 * @param v Voto da  aggiungere
	 */
	public void add(Voto v) {
		this.voti.add(v);
		}
	
	/**
	 * Dato un libretto, restituisce una stringa nella quale
	 * vi sono solamente i voti pari a l valore specificato 
	 * @param voto
	 * @return stringa formattata per visualizzare il sotto-libretto
	 */
	
	public String stampaVotiUguali(int voto) {

//	public String toString() { se volevo stampare tutti i voti 
		String s = "";
		for ( Voto v: this.voti) {
			if(v.getVoto()== voto) {
				s += v.toString()+"\n";
			}
			
		}
		return s;
	}
	
	/**
	 * Genera un nuovo Libretto, a partire da quello esistente
	 * che conterrà esclusivamente i voti con votazione pari a quella specificata
	 * @param voto votazione specificata
	 * @return nuovo Libretto "ridotto"
	 */
	
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo = new Libretto();
		for(Voto v: this.voti) {
			if(v.getVoto()==voto) {
				nuovo.add(v);
			}
		
		}
		return nuovo;
	}
public String toString() { 
	String s = "";
	for ( Voto v: this.voti) {
		
		s += v.toString()+"\n";
	}
	return s;
	
	
}
	

}
