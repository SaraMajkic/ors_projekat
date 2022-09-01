package application;

import java.util.ArrayList;

public class PristupniPodaci {

	private int id;
	private String korisnickoIme;   //jedinstveno
	private String sifra;
	private String email;   //jedinstveno
	private static ArrayList<PristupniPodaci> sviPristupniPodaci = new ArrayList<PristupniPodaci> ();
	
	public PristupniPodaci(int id, String korisnickoIme, String sifra, String email) {
		this.id=id;
		this.korisnickoIme=korisnickoIme;
		this.sifra=sifra;
		this.email=email;
		if(!this.postojePristupniPodaci())
			sviPristupniPodaci.add(this); 
	}
	
	private boolean postojePristupniPodaci() {
		for(PristupniPodaci pp: sviPristupniPodaci)
			if(pp.equals(this))
				return true;
		return false;
	}

	int getId() {
		return id;
	}
	
	String getKorisnickoIme() {
		return korisnickoIme;
	}

	String getSifra() {
		return sifra;
	}
	
	void setSifra(String s) {
		this.sifra = s;
	}
	
	String getEmail() {
		return email;
	}
	
	static ArrayList<PristupniPodaci> getSviPristupniPodaci() {
		return sviPristupniPodaci;
	}
	
	static PristupniPodaci getPristupniPodaci(String s) {
		for(PristupniPodaci pp: sviPristupniPodaci)
			if(pp.getKorisnickoIme().equals(s))
				return pp;
		return null;
	}
	
	static boolean postojiMejl(String s) {
		for(PristupniPodaci pp: sviPristupniPodaci)
			if(pp.getEmail().equals(s))
				return true;
		return false;
	}
	
	public String toString() {
		return "Id pristupnih podataka: " + id + ", korisnicko ime: " + korisnickoIme + ", email: " + email + ", sifra: " + sifra;
	}

	
}
