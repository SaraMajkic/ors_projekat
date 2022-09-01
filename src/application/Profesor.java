package application;

import java.util.ArrayList;

public class Profesor {

	private int id;
	private String ime;
	private String prezime;
	private int pol;
	private PristupniPodaci pristupniPodaci;
	private static ArrayList<Profesor> sviProfesori = new ArrayList<Profesor>();
	
	public Profesor(int id, String ime, String prezime, int pol, PristupniPodaci pristupniPodaci) {
		this.id=id;
		this.ime=ime;
		this.prezime=prezime;
		this.pol=pol;
		this.pristupniPodaci=pristupniPodaci;
		if(!this.postojiProfesor())
			sviProfesori.add(this);
	}

	private boolean postojiProfesor() {
		for(Profesor p: sviProfesori)
			if(p.equals(this))
				return true;
		return false;
	}

	int getId() {
		return id;
	}

	String getIme() {
		return ime;
	}

	String getPrezime() {
		return prezime;
	}

	int getPol() {
		return pol;
	}

	PristupniPodaci getPristupniPodaci() {
		return pristupniPodaci;
	}
	
	static ArrayList<Profesor> getSviProfesori() {
		return sviProfesori;
	}
	
	/*public Profesor getProfesor() {  //vraca profesora po pristupni_podaci_id
		for(Profesor p: sviProfesori)
			if(p.getPristupniPodaci().getId()==this.getPristupniPodaci().getId())
				return p;
		return null;
	}*/
	
	static Profesor getProfesor(String korisnickoIme) { //vraca profesora po korisnickom imenu iz pristupnih podataka
		for(Profesor p: sviProfesori)
			if(p.getPristupniPodaci().getKorisnickoIme().equals(korisnickoIme))
				return p;
		return null;
	}
	
	ArrayList<Skola> getSkole() {    //vraca sve skole u kojima radi profesor
		ArrayList<Skola> l = new ArrayList<Skola>();
		for(PredmetUSkoli p: PredmetUSkoli.getSviPredmetiUSkoli())
			if(p.getProfesor().equals(this) && !l.contains(p.getSkola()))
				l.add(p.getSkola());
		return l;
	}
	
	ArrayList<Predmet> getPredmeti(Skola skola) {    //vraca sve predmete za koje je zaduzen profesor po odredjenoj skoli
		ArrayList<Predmet> l = new ArrayList<Predmet>();
		for(PredmetUSkoli p: PredmetUSkoli.getSviPredmetiUSkoli())
			if(p.getProfesor().equals(this) && p.getSkola().equals(skola) && !l.contains(p.getPredmet()))
				l.add(p.getPredmet());
		return l;
	}
	
	private boolean postojiPredmetUSkoli(Skola s, Predmet p) {  //provjerava da li prof predaje taj predmet u toj skoli
		for(PredmetUSkoli pus : PredmetUSkoli.getSviPredmetiUSkoli())
			if(pus.getSkola().equals(s) && pus.getPredmet().equals(p) && pus.getProfesor().equals(this))
				return true;
		return false;
	}
	
	ArrayList<Predmet> getMoguciPredmeti(Skola skola) {
		ArrayList<Predmet> l = new ArrayList<Predmet>();
		for(Predmet p : Predmet.getSviPredmeti())
			if(!postojiPredmetUSkoli(skola, p))
				l.add(p);
		return l;
	}
	
	/*private boolean imaZaduzenje(PredmetUSkoli ps) { //vraca tacno ako je dati prof zaduzen za dati predmet u skoli
		for(PredmetUSkoli p : PredmetUSkoli.getSviPredmetiUSkoli())
			if(p.getProfesor().equals(this) && p.equals(ps))
				return true;
		return false;
	}*/
	
	/*ArrayList<Predmet> getMoguciPredmeti(Skola skola) { //vraca sve predmete za koje prof nije zaduzen, da bi mogao dobiti novo zaduzenje po potrebi
	ArrayList<Predmet> l = new ArrayList<Predmet>(); 
	for(PredmetUSkoli p: PredmetUSkoli.getSviPredmetiUSkoli())
			//if(p.getSkola().equals(skola) && !this.imaZaduzenje(p) && !l.contains(p.getPredmet()))
			if(p.getSkola().equals(skola) && !this.imaZaduzenje(p) && !l.contains(p.getPredmet()))
				l.add(p.getPredmet());
	return l;
	}*/
	
	/*private boolean istaSkolaIPredmet(PredmetUSkoli p1, PredmetUSkoli p2) {
		if(p1.getSkola().equals(p2.getSkola()) && p1.getPredmet().equals(p2.getPredmet()))
			return true;
		return false;
	}*/
	
	/*ArrayList<Predmet> getMoguciPredmeti(Skola skola) { //vraca sve predmete za koje prof nije zaduzen, da bi mogao dobiti novo zaduzenje po potrebi
		ArrayList<Predmet> l = new ArrayList<Predmet>(); 
		for(PredmetUSkoli p: PredmetUSkoli.getSviPredmetiUSkoli())
			for(Predmet pr: this.getPredmeti(skola))
				if(!istaSkolaIPredmet(p, pr.getPredmetUSkoli(skola)) && !p.getProfesor().equals(this) && !this.getPredmeti(skola).contains(p.getPredmet()) && !l.contains(p.getPredmet()))
					l.add(p.getPredmet());
		return l;
	}*/
	
	
	
	boolean postojiOcjenaProfesora(Ucenik u) {    //provjera da li postoji ocjena datog profesora za datog ucenika
		for(OcjenaPredmeta o: OcjenaPredmeta.getSveOcjenePredmeta())
			if(o.getPredmetUSkoli().getProfesor().equals(this) && o.getUcenik().equals(u))
				return true;
		return false;
	}
	
	public String toString() {
		return "Id profesora: " + id + ", ime: " + ime + ", prezime: " + prezime + ", pol: " + pol + ", " + pristupniPodaci;
	}
}
