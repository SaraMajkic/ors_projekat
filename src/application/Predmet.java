package application;

import java.util.ArrayList;

public class Predmet {

	private int id;
	private String naziv;
	private int razred;
	private static ArrayList<Predmet> sviPredmeti = new ArrayList<Predmet> ();
	
	public Predmet(int id, String naziv, int razred) {
		this.id=id;
		this.naziv=naziv;
		this.razred=razred;
		if(!this.postojiPredmet())
			sviPredmeti.add(this);
	}

	private boolean postojiPredmet() {
		for(Predmet p: sviPredmeti)
			if(p.equals(this))
				return true;
		return false;
	}

	int getId() {
		return id;
	}

	String getNaziv() {
		return naziv;
	}

	int getRazred() {
		return razred;
	}
	
	static ArrayList<Predmet> getSviPredmeti() {
		return sviPredmeti;
	}
	
	/*public static Predmet getPredmet(String s) {   //vraca predmet po nazivu predmeta
		for(Predmet p: sviPredmeti)
			if(p.getNaziv().equals(s))
				return p;
		return null;
	}*/
	
	static Predmet getPredmet(String s, Ucenik u) { //vraca predmet po nazivu predmeta i po uceniku koji ga slusa jer imamo 2x matematika
		for(Predmet p: u.getPredmetiByIzostanci())
			if(p.getNaziv().equals(s))
				return p;
		for(Predmet p: u.getPredmetiByOcjena())
			if(p.getNaziv().equals(s))
				return p;
		return null;
	}
	
	static Predmet getPredmet(int id) {
		for(Predmet p: sviPredmeti)
			if(p.getId()==id)
				return p;
		return null;
	}
	
	/*public static Predmet getPredmet(String s, Ucenik u) {   //vraca predmet po nazivu predmeta
													// i po uceniku jer imamo dvije matematike, pa je to pravilo problem, uvijek vracalo samo prvu mat
		for(Predmet p: sviPredmeti) {
			if(u.getSviIzostanci()!=null)
				if(p.getNaziv().equals(s))
					return p;
			if(u.getSveOcjene()!=null)
				if(p.getNaziv().equals(s))
					return p;
		}
		return null;
	}*/
	
	/*public ArrayList<Profesor> getProfesori() {  //vraca profesore zaduzene za taj predmet
		ArrayList<Profesor> l = new ArrayList<Profesor>();
		for(PredmetUSkoli ps: PredmetUSkoli.getSviPredmetiUSkoli())
			if(!l.contains(ps.getProfesor()))
				l.add(ps.getProfesor());
		return l;
	}*/
	
	boolean postojiOcjenaPredmeta(Ucenik u, Profesor p) {       //provjerava da li postoji ocjena za dati predmet tj za profesora
		for(OcjenaPredmeta o: OcjenaPredmeta.getSveOcjenePredmeta())
			if(o.getPredmetUSkoli().getPredmet().equals(this)  && o.getUcenik().equals(u) && o.getPredmetUSkoli().getProfesor().equals(p))
				return true;
		return false;
	}
	
	double getOcjenaPredmeta(Ucenik u, Profesor p) {            //vraca prosjecnu ocjenu koju je jedan ucenik dao za dati predmet tj za profesora
		double prosjek = 0;
		int zbir = 0;
		for(OcjenaPredmeta o: OcjenaPredmeta.getSveOcjenePredmeta())
			if(o.getPredmetUSkoli().getPredmet().equals(this) && o.getUcenik().equals(u) && o.getPredmetUSkoli().getProfesor().equals(p))
				zbir += o.getOcjena();
		prosjek = (zbir+0.0)/4;
		return prosjek;
	}
	
	PredmetUSkoli getPredmetUSkoli(Skola s) {  //mora biti parametar skole, jer isti predmet moze biti u vise skola
		for(PredmetUSkoli pus: PredmetUSkoli.getSviPredmetiUSkoli())
			if(pus.getPredmet().equals(this) && pus.getSkola().equals(s))
				return pus;
		return null;
	}
	
	ArrayList<Ucenik> getUcenici(Profesor p, Skola s) {   //moramo proslijediti i parametar profesor, jer vise profesora moze predavati jedan predmet i parametar skolu jer jedan predmet moze biti u vise skola
		ArrayList<Ucenik> l = new ArrayList<Ucenik>();
		for(Ucenik u: Ucenik.getSviUcenici()) {
			for(Izostanci i: u.getSviIzostanci())
				if(i.getPredmetUSkoli().getPredmet().equals(this) && !l.contains(u) && i.getPredmetUSkoli().getProfesor().equals(p) && i.getPredmetUSkoli().getSkola().equals(s))
					l.add(u);
			for(Ocjena o: u.getSveOcjene())
				if(o.getPredmetUSkoli().getPredmet().equals(this) && !l.contains(u) && o.getPredmetUSkoli().getProfesor().equals(p) && o.getPredmetUSkoli().getSkola().equals(s))
					l.add(u);
		}
			
		return l;
	}
	
	static boolean postojiPredmet(String naziv, int razred) {
		for(Predmet p: sviPredmeti)
			if(p.getNaziv().equals(naziv) && p.getRazred()==razred)
				return true;
		return false;
	}
	
	public String toString() {
		return "Id predmeta: " + id + ", naziv: " + naziv + ", razred: " + razred;
	}
}
