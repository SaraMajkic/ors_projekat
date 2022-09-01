package application;

import java.util.ArrayList;

public class Pitanje {

	private int id;
	private String pitanje;
	private static ArrayList<Pitanje> svaPitanja = new ArrayList<Pitanje> ();
	
	public Pitanje(int id, String pitanje) {
		
		this.id=id;
		this.pitanje=pitanje;
		if(!this.postojiPitanje())
			svaPitanja.add(this);
	}
	
	private boolean postojiPitanje() {
		for(Pitanje p: svaPitanja)
			if(p.equals(this))
				return true;
		return false;
	}
	
	int getId() {
		return id;
	}
	
	String getPitanje() {
		return pitanje;
	}
	
	static ArrayList<Pitanje> getSvaPitanja() {
		return svaPitanja;
	}
	
	static Pitanje getPitanje(int id) {
		for(Pitanje p: svaPitanja)
			if(p.getId()==id)
				return p;
		return null;
	}

	public String toString() {
		return "Id pitanja: " + id + ", tekst pitanja: " + pitanje;
	}
	
}
