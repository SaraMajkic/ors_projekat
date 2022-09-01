package application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Ucenik {

	private int id;
	private String ime;
	private String prezime;
	private int pol;
	private PristupniPodaci pristupniPodaci;
	private static ArrayList<Ucenik> sviUcenici = new ArrayList<Ucenik>();
	
	public Ucenik(int id, String ime, String prezime, int pol, PristupniPodaci pristupniPodaci) {
		
		this.id=id;
		this.ime=ime;
		this.prezime=prezime;
		this.pol=pol;
		this.pristupniPodaci=pristupniPodaci;
		if(!this.postojiUcenik())
			sviUcenici.add(this);
	}

	private boolean postojiUcenik() {
		for(Ucenik u: sviUcenici)
			if(u.equals(this))
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
	
	static ArrayList<Ucenik> getSviUcenici() {
		return sviUcenici;
	}

	PristupniPodaci getPristupniPodaci() {
		return pristupniPodaci;
	}
	
	/*public static Ucenik getUcenik(int id) {  //vraca ucenika po pristupni_podaci_id
		for(Ucenik u: sviUcenici)
			if(u.getPristupniPodaci().getId()==id)
				return u;
		return null;
	}*/
	
	static Ucenik getUcenik(String korisnickoIme) { //vraca ucenika po korisnickom imenu iz pristupnih podataka
		for(Ucenik u: sviUcenici)
			if(u.getPristupniPodaci().getKorisnickoIme().equals(korisnickoIme))
				return u;
		return null;
	}
	  
	static Ucenik getUcenik(int id) {     //vraca ucenika po id-u
		for(Ucenik u: sviUcenici)
			if(u.getId()==id)
				return u;
		return null;
	}
	
	Skola getSkola() {
		if(Izostanci.getIzostanak(this)!=null)
			return Izostanci.getIzostanak(this).getPredmetUSkoli().getSkola();
		else if(Ocjena.getOcjena(this)!=null)
			return Ocjena.getOcjena(this).getPredmetUSkoli().getSkola();
		return null;
	}
	
	String getMjesto() {
		if(Izostanci.getIzostanak(this)!=null)
			return Izostanci.getIzostanak(this).getPredmetUSkoli().getSkola().getMjesto();
		else if(Ocjena.getOcjena(this)!=null)
			return Ocjena.getOcjena(this).getPredmetUSkoli().getSkola().getMjesto();
		return null;
	}
	
	String getGrad() {
		if(Izostanci.getIzostanak(this)!=null)
			return Izostanci.getIzostanak(this).getPredmetUSkoli().getSkola().getGrad();
		else if(Ocjena.getOcjena(this)!=null)
			return Ocjena.getOcjena(this).getPredmetUSkoli().getSkola().getGrad();
		return null;
	}
	
	int getRazred() {
		if(Izostanci.getIzostanak(this)!=null)
			return Izostanci.getIzostanak(this).getPredmetUSkoli().getPredmet().getRazred();
		else if(Ocjena.getOcjena(this)!=null)
			return Ocjena.getOcjena(this).getPredmetUSkoli().getPredmet().getRazred();
		return 0;
	}
	
	ArrayList<Predmet> getPredmetiByOcjena() {  //vraca sve predmete iz kojih ima ocjenu
		ArrayList<Predmet>l = new ArrayList<Predmet>();
		for(Ocjena o: Ocjena.getSveOcjene()) {
			if(o.getUcenik().equals(this))
				if(!l.contains(o.getPredmetUSkoli().getPredmet()))
						l.add(o.getPredmetUSkoli().getPredmet());
		}
		return l;
	}
	
	ArrayList<Predmet> getPredmetiByIzostanci() {  //vraca sve predmete iz kojih ima izostanak
		ArrayList<Predmet>l = new ArrayList<Predmet>();
		for(Izostanci i: Izostanci.getSviIzostanci()) {
			if(i.getUcenik().equals(this))
				if(!l.contains(i.getPredmetUSkoli().getPredmet()))
					l.add(i.getPredmetUSkoli().getPredmet());
		}
		return l;
	}
	
	ArrayList<Ocjena> getSveOcjene() {   //vraca sve ocjene koje ima ucenik
		ArrayList<Ocjena>l = new ArrayList<Ocjena>();
		for(Ocjena o: Ocjena.getSveOcjene()) {
			if(o.getUcenik().equals(this))
				l.add(o);
		}
		return l;
	}
	
	private Predmet getPredmet(String s) {   
		if(Izostanci.getIzostanak(this)!=null)
			for(Izostanci i: this.getSviIzostanci())
				if(i.getPredmetUSkoli().getPredmet().getNaziv().equals(s))
					return i.getPredmetUSkoli().getPredmet();
		if(Ocjena.getOcjena(this)!=null)
			for(Ocjena o: Ocjena.getSveOcjene())
				if(o.getPredmetUSkoli().getPredmet().getNaziv().equals(s))
					return o.getPredmetUSkoli().getPredmet();
		return null;		
	}
	
	ArrayList<Ocjena> getOcjeneIzPredmeta(String predmet) {  //vraca sve ocjene ucenika iz odredjenog predmeta po nazivu predmeta
		ArrayList<Ocjena>l = new ArrayList<Ocjena>();
		//for(Ocjena o: Ocjena.getSveOcjene())   
		for(Ocjena o: this.getSveOcjene())
			//if(o.getPredmetUSkoli().getPredmet().equals(getPredmet(predmet)) && o.getUcenik().equals(this))
			if(o.getPredmetUSkoli().getPredmet().getNaziv().equals(predmet))
				l.add(o);
		return l;
	}
	 
	ArrayList<Izostanci> getSviIzostanci() {   //vraca sve izostanke koje ima ucenik
		ArrayList<Izostanci>l = new ArrayList<Izostanci>();
		for(Izostanci i: Izostanci.getSviIzostanci())
			if(i.getUcenik().equals(this))
				l.add(i);
		return l;
	}
	
	ArrayList<Izostanci> getIzostanciIzPredmeta(String predmet) {  //vraca sve izostanke ucenika iz odredjenog predmeta po nazivu predmeta
		ArrayList<Izostanci> l = new ArrayList<Izostanci>();
		for(Izostanci i: Izostanci.getSviIzostanci())
			if(i.getPredmetUSkoli().getPredmet().getNaziv().equals(predmet) && i.getUcenik().equals(this))
				l.add(i);
		return l;
	}
	
	ArrayList<Profesor> getProfesori() {  //vraca profesore koji predaju datom uceniku
		ArrayList<Profesor> l = new ArrayList<Profesor>();
		for(PredmetUSkoli ps: PredmetUSkoli.getSviPredmetiUSkoli()) {
			for(Izostanci i: this.getSviIzostanci())
				if(i.getPredmetUSkoli().equals(ps) && !l.contains(ps.getProfesor()))
					l.add(ps.getProfesor());
			for(Ocjena o: this.getSveOcjene())
				if(o.getPredmetUSkoli().equals(ps) && !l.contains(ps.getProfesor()))
					l.add(ps.getProfesor());
		}
		return l;
	}
	
	Profesor getProfesor(Predmet p) {  //vraca profesora koji predaje ovom uceniku dati predmet, tj kod koga ima ili ocjenu ili izostanak
		for(Izostanci i: this.getSviIzostanci())
			if(i.getPredmetUSkoli().getPredmet().equals(p))
				return i.getPredmetUSkoli().getProfesor();
		for(Ocjena o: this.getSveOcjene())
			if(o.getPredmetUSkoli().getPredmet().equals(p))
				return o.getPredmetUSkoli().getProfesor();
		return null;
	}
	
	int brojDnevnihOcjena(LocalDate datum) {
		int br=0;
		for(Ocjena o: Ocjena.getSveOcjene())
			if(o.getDatum().equals(datum) && o.getUcenik().equals(this))
				br++;
		return br;
	}
	
	LocalDate datumPosljednjeOcjene(Predmet pr) {   //vraca datum posljednje ocjene iz posmatranog predmeta
		LocalDate datum = LocalDate.parse("2010-10-10");
		for(Ocjena o: Ocjena.getSveOcjene())
			if(o.getUcenik().equals(this) && o.getPredmetUSkoli().getPredmet().equals(pr) && o.getDatum().compareTo(datum)>0)
				datum=o.getDatum();
		return datum;
	}
	
	int razlikaUDanima(LocalDate date1, LocalDate date2) {  //kao date1 cu u main-u proslijediti novi datum, a date2 posljednji datum iz tog predmeta
		
		/*if(date1.compareTo(date2)<0)       //compareTo vraca 1 ako je prvi datum veci tj. noviji, -1 ako je drugi veci, 0 ako su jednaki
			return -1;         //vracamo -1 ako je prvi datum bar za dan stariji od drugog
		if(date1.getYear()==date2.getYear() && date1.getMonth()==date2.getMonth())
			return (date1.getDayOfMonth()-date2.getDayOfMonth());     //ako ovo vrati nesto manje od 7, znamo da se ne moze upisati ocjena u Main
		return 7;   //8 jer trazimo da je razlika izmedju datuma barem 7, pa u obzir dolaze sve razlike 7 i iznad*/
		
		return (int) ChronoUnit.DAYS.between(date2, date1);   //vratice - ako je novi uneseni datum(date1) stariji od posljednjeg datuma koji gledamo
															//gledamo da ova razlika bude >= 7, ali da datum nije noviji od sadasnjeg
	}
	
	boolean postojiIzostanak(Predmet pr, LocalDate datum) {  //provjerava da li postoji izostanak ucenika na dati datum sa datog predmeta
		for(Izostanci i: Izostanci.getSviIzostanci())
			if(i.getPredmetUSkoli().getPredmet().equals(pr) && i.getDatum().equals(datum) && i.getUcenik().equals(this))
				return true;
		return false;
	}
	
	static ArrayList<Ucenik> getUceniciBezOcjeneBezIzostanka() {   //vraca ucenike koji nemaju ni ocjenu ni izostanak ni iz jednog predmeta
		ArrayList<Ucenik> l = new ArrayList<Ucenik>();
		for(Ucenik u : Ucenik.getSviUcenici()) 
			if(u.getSviIzostanci().isEmpty() && u.getSveOcjene().isEmpty())
				l.add(u);	
		return l;
	}
	
	public String toString() {
		return "Id ucenika: " + id + ", ime: " + ime + ", prezime: " + prezime + ", pol: " + pol + ", " + pristupniPodaci;
	}

	
}
