package application;

import java.util.ArrayList;

public class PredmetUSkoli {

	private int id;
	private Predmet predmet;
	private Skola skola;
	private Profesor profesor;
	private static ArrayList<PredmetUSkoli> sviPredmetiUSkoli= new ArrayList<PredmetUSkoli> ();
	
	public PredmetUSkoli(int id, Predmet predmet, Skola skola, Profesor profesor) {
		
		this.id=id;
		this.predmet=predmet;
		this.skola=skola;
		this.profesor=profesor;
		if(!this.postojiPredmetUSkoli()) 
			sviPredmetiUSkoli.add(this);
	}

	private boolean postojiPredmetUSkoli() {
		for(PredmetUSkoli p: sviPredmetiUSkoli)
			if(p.equals(this))
				return true;
		return false;
	}

	int getId() {
		return id;
	}

	Predmet getPredmet() {
		return predmet;
	}

	Skola getSkola() {
		return skola;
	}

	Profesor getProfesor() {
		return profesor;
	}
	
	static ArrayList<PredmetUSkoli> getSviPredmetiUSkoli() {
		return sviPredmetiUSkoli;
	}
	
	static PredmetUSkoli getPredmetUSkoli(Predmet p) {  //vraca predmet u skoli, po predmetu
		for(PredmetUSkoli pus: sviPredmetiUSkoli)
			if(pus.getPredmet().equals(p))
				return pus;
		return null;
	}
	
	static PredmetUSkoli getPredmetUSkoli(int id) {  //vraca predmet u skoli, po id-u
		for(PredmetUSkoli pus: sviPredmetiUSkoli)
			if(pus.getId()==id)
				return pus;
		return null;
	}
	
	public String toString() {
		return "Id predmeta u skoli: " + id + ", " + predmet + ", " + skola + ", " + profesor;
	}
	
}
