package application;

import java.util.ArrayList;

public class OcjenaPredmeta {

	private int id;
	private Ucenik ucenik;
	private PredmetUSkoli predmetUSkoli;
	private Pitanje pitanje;
	private int ocjena;
	private static ArrayList<OcjenaPredmeta> sveOcjenePredmeta = new ArrayList<OcjenaPredmeta> ();
	
	public OcjenaPredmeta(int id, Ucenik ucenik, PredmetUSkoli predmetUSkoli, Pitanje pitanje, int ocjena) {
		
		this.id=id;
		this.ucenik=ucenik;
		this.predmetUSkoli=predmetUSkoli;
		this.pitanje=pitanje;
		this.ocjena=ocjena;
		if(!this.postojiOcjenaPredmeta())
			sveOcjenePredmeta.add(this);
	}

	private boolean postojiOcjenaPredmeta() {
		for(OcjenaPredmeta op: sveOcjenePredmeta)
			if(op.equals(this))
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

	Pitanje getPitanje() {
		return pitanje;
	}

	int getOcjena() {
		return ocjena;
	}

	static ArrayList<OcjenaPredmeta> getSveOcjenePredmeta() {
		return sveOcjenePredmeta;
	}
	
	
	public String toString() {
		return "Id ocjene predmeta: " + id +", " + ucenik + ", " + predmetUSkoli + ", " + pitanje + ", ocjena: " + ocjena;
	}
	
}
