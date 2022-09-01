package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Ocjena {

	private int id;
	private Ucenik ucenik;
	private PredmetUSkoli predmetUSkoli;
	private int ocjena;
	private LocalDate datum;
	private static ArrayList<Ocjena> sveOcjene = new ArrayList<Ocjena> ();
	
	public Ocjena(int id, Ucenik ucenik, PredmetUSkoli predmetUSkoli, int ocjena, LocalDate datum) {
		
		this.id=id;
		this.ucenik=ucenik;
		this.predmetUSkoli=predmetUSkoli;
		this.ocjena=ocjena;
		this.datum=datum;
		if(!this.postojiOcjena())
			sveOcjene.add(this);
	}

	private boolean postojiOcjena() {
		for(Ocjena o: sveOcjene)
			if(o.equals(this))
				return true;
		return false;
	}

	int getId() {
		return id;
	}

	Ucenik getUcenik() {
		return ucenik;
	}

	PredmetUSkoli getPredmetUSkoli() {
		return predmetUSkoli;
	}

	int getOcjena() {
		return ocjena;
	}

	LocalDate getDatum() {
		return datum;
	}
	
	static Ocjena getOcjena(Ucenik u) {   //vraca prvu ocjenu koju je dobio ucenik, ovu metodu koristimo za trazenje mjesta, razreda itd.
		for(Ocjena o: sveOcjene)
			if(o.getUcenik().equals(u))
				return o;
		return null;
	}
	
	static ArrayList<Ocjena> getSveOcjene() {
		return sveOcjene;
	}
	
	final static Comparator<Ocjena> CompareOcjena = (o1, o2) -> porediDatume(o1, o2);
	
	private static int porediDatume(Ocjena o1, Ocjena o2) {
		/*if(o1.datum.getYear() > o2.datum.getYear())
			return -1;
		if(o1.datum.getYear() < o2.datum.getYear())
			return 1;
		else {
			if(o1.datum.getMonthValue() > o2.datum.getMonthValue())
				return -1;
			if(o1.datum.getMonthValue() < o2.datum.getMonthValue())
				return 1;
			else {
				if(o1.datum.getDayOfMonth() > o2.datum.getDayOfMonth())
					return -1;
				if(o1.datum.getDayOfMonth() < o2.datum.getDayOfMonth())
					return 1;
			}
		}	
		return 0; */ 
		return o2.datum.compareTo(o1.datum);   //vraca 1 ako je prvi datum veci tj. noviji, -1 ako je manji, 0 ako su jednaki
	}
	
	public String toString() {
		//return "Id ocjene: " + id + ", " + ucenik + ", " + predmetUSkoli + ", ocjena: " + ocjena + ", datum:" + datum;
		return "Id ocjene: " + id + ", id ucenika: " + ucenik.getId() + ", id predmeta u skoli: " + predmetUSkoli.getId() + ", ocjena: " + ocjena + ", datum: " + datum;
	}
}
