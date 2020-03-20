 package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
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
	 * Crea un libretto nuovo (e vuoto)
	 */
	
	public Libretto() {
		super();
	}
	/**
	 * Copy Constructor
	 * "Shallow" (copia superficiale)
	 * 
	 * "Deep" (copia profonda)
	 * @param lib
	 */
	
	public Libretto (Libretto lib) {
		super();
		this.voti.addAll(lib.voti);
		
	}
	/**
	 * Aggiunge un nuovo voto al Libretto
	 * @param v Voto da  aggiungere
	 * @return 
	 */
	public boolean add(Voto v) {
		if(this.isConflitto(v) || this.isDuplicato(v)) { // this si usa soprattutto quando richiami metodi o attributi della STESSAAAA classe
			return false; // segnale al chiamante che non ha avuto successo
			// non inserire il voto 
		} else {
			// Inserisci il voto perchè non è in conflitto nè duplicato 
		this.voti.add(v);
		return true;
		}
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
/**
 * Data il  nome di un corso, ricerca se quell'esame esiste 
 * nel librettp, ed in caso affermativo restituisce l'oggetto
 * {@Voto} corrispondente.
 * Se l'esame non esistte, restituisce null
 * @param nomeCorso nomeCorso esame da cercare
 * @return il {@link Voto} corrispondente, oppure se {@null} non esiste 
 */

public Voto cercaNomeCorso(String nomeCorso) {
/*Voto result = null;
for(Voto v: this.voti) {
	if(nomeCorso.equals(v.getCorso())) {
	return v;
}
	
}
	return null;
	*/
	int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null));  //?
	if( pos != -1) {
		return this.voti.get(pos);
	}
	else {
		return null;
	}

}
/**
 * Ritorna {@code true} se il voto {@code v} 
 * esiste nel libretto,con la stessa valutazione.
 * Se non esiste,o se la valutazione è diversa, ritorna {@code false}
 * @param v il [@link Voto} da ricercare
 * @return l'esistenza di un duplicato
 */

public boolean isDuplicato(Voto v) {
	Voto esiste = this.cercaNomeCorso(v.getCorso());
	if(esiste==null) {
		return false;
	}
	/*
	if( esiste.getVoto()==v.getVoto()) {
		return true;
	}
	else {
		return false;
	} */
	return (esiste.getVoto() == v.getVoto()); 
	
}
/**
 * Determina se esiste un voto con lo stesso nome corso ma valutazione
 * diversa 
 * @param v
 * @return
 */

public boolean isConflitto(Voto v) {
	Voto esiste = this.cercaNomeCorso(v.getCorso());
	if(esiste==null) {
		return false;
	}
	return (esiste.getVoto() != v.getVoto()); 

	
}
/**
 * Restituisce un NUOVO librettp, migliorando i voti del libretto attuale.
 * 
 * @return 
 * 
 */

public Libretto creaLibrettoMigliorato(){
	Libretto nuovo = new Libretto();
	
	for(Voto v : this.voti) {
		// il problema qui è che devo creare una copia piuttosto che condividere il riferimento
		
		//Voto v2 = new Voto(v);
		
		// non è una condivisione del v che gia esisteva ma
		// è un nuovo voto che viene inizializzato con v
		// Voto g3=newVoto(v.getCorso(), v.getData(), v.getVoto())
		// puoi farlo cosi ma non mi verebbe mai in mente di passarli 
		// tutti uno alla volta perchè se aggiungo ogni volta un metodo
		// mi devo ricordare di aggiornare questa riga
		
		Voto v2= v.clone();
		if(v2.getVoto()>=24) {
			v2.setVoto(v2.getVoto()+2);
		if(v2.getVoto()>30)
			v2.setVoto(30);
		} else if (v2.getVoto()>=18) {
			v2.setVoto(v2.getVoto()+1);
		}
		nuovo.add(v2);
		
	}
	return nuovo;
	
}
/**
 * riordina i voti presentu nel libretto corrente
 * alfabeticamente per corso
 */
       public void ordinaPercorso() {
    	   Collections.sort(this.voti);
    	   
    	  }

       public void ordinaPerVoto() {
    	   Collections.sort(this.voti, new ConfrontaVotiPerValutazione());
    	 //  this.voti.sort(new ConfrontaVotiPerlaValutazione());
    	   
    	  }
       /**
        * Elimina dal libretto i voti <24
        */
       
       public void cancellaVotiScarsi() {
    	   List<Voto> daRimuovere= new ArrayList<>();
    	   for(Voto v: this.voti) {
    		   if(v.getVoto()<24) {
    			   daRimuovere.add(v);
    		   }
    	   }
    	   
    	   this.voti.removeAll(daRimuovere);
    //	   for(Voto v: daRimuovere) {
    	//	   this.voti.remove(v);
    	  // }
       }
}
