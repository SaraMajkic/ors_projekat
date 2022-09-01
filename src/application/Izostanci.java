package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Izostanci {

	private int id;
	private Ucenik ucenik;
	private PredmetUSkoli predmetUSkoli;
	private LocalDate datum;
	private static ArrayList<Izostanci> sviIzostanci = new ArrayList<Izostanci> ();
	
	public Izostanci(int id, Ucenik ucenik, PredmetUSkoli predmetUSkoli, LocalDate datum) {
		
		this.id=id;
		this.ucenik=ucenik;
		this.predmetUSkoli=predmetUSkoli;
		this.datum=datum;
		if(!this.postojiIzostanak())
			sviIzostanci.add(this);
	}

	private boolean postojiIzostanak() {
		for(Izostanci i: sviIzostanci)
			if(i.equals(this))
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

	LocalDate getDatum() {
		return datum;
	}

	static ArrayList<Izostanci> getSviIzostanci() {
		return sviIzostanci;
	}
	
	static Izostanci getIzostanak(Ucenik u) {  //vraca izostanak po uceniku
		for(Izostanci i: sviIzostanci)
			if(i.getUcenik().equals(u))
				return i;
		return null;
	}
	
	static boolean postojiIz(LocalDate datum, PredmetUSkoli pr, Ucenik u) {
		for(Izostanci i: sviIzostanci)
			if(i.getDatum().equals(datum) && i.getPredmetUSkoli().equals(pr) && i.getUcenik().equals(u))
				return true;
		return false;
	}
	
	public String toString() {
		return "Id izostanka: " + id + ", " + ucenik + ", " + predmetUSkoli + ", datum: " + datum;
	}
	
}
