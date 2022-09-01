package application;

import java.util.ArrayList;

public class Skola {

	private int id;
	private String naziv;
	private String grad;
	private String mjesto;
	private String drzava;
	private static ArrayList<Skola> sveSkole = new ArrayList<Skola> ();
	
	public Skola(int id, String naziv, String grad, String mjesto, String drzava) {
		
		this.id=id;
		this.naziv=naziv;
		this.grad=grad;
		this.mjesto=mjesto;
		this.drzava=drzava;
		if(!this.postojiSkola())
			sveSkole.add(this);
	}

	private boolean postojiSkola() {
		for(Skola s: sveSkole)
			if(s.equals(this))
				return true;
		return false;
	}

	int getId() {
		return id;
	}

	String getNaziv() {
		return naziv;
	}

	String getGrad() {
		return grad;
	}

	String getMjesto() {
		return mjesto;
	}

	String getDrzava() {
		return drzava;
	}
	
	static ArrayList<Skola> getSveSkole() {
		return sveSkole;
	}
	
	static boolean postojiSkola(String naziv, String grad, String mjesto, String drzava) {
		for(Skola s: sveSkole)
			if(s.getNaziv().equals(naziv) && s.getGrad().equals(grad) && s.getMjesto().equals(mjesto) && s.getDrzava().equals(drzava))
				return true;
		return false;
	}
	
	public String toString() {
		return "Id skole: " + id + ", naziv: " + naziv + ", grad: " + grad + ", mjesto: " + mjesto + ", drzava: " + drzava;
	}
	
}
