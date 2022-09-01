package application;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
/*import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
import static org.junit.Assert.assertTrue;
import org.junit.Assert.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;*/


public class Konekcija {

	public Connection connection;
	private String DB_user = "root";
	private String DB_password = "";
	private String connectionUrl = "jdbc:mysql://localhost";
	private int port = 3306;
	private String DB_name = "ors1_opp_2021_2022";
	private Statement statement;
	private ResultSet resultSet = null;
	
	
	public Konekcija() {
		
		
		
		try {
	
			connectionUrl = connectionUrl + ":" + port + "/" + DB_name;
			
			connection = DriverManager.getConnection(connectionUrl, DB_user, DB_password);
			
			statement = connection.createStatement();

			/*String selectSql = "SELECT * FROM ucenik";
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString("prezime") + " " + resultSet.getString(2));
            }*/
			
			String s1 = "SELECT * FROM pristupni_podaci";
			resultSet = statement.executeQuery(s1);
			while(resultSet.next()) {
				PristupniPodaci pp = new PristupniPodaci(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(4), resultSet.getString(3));
				//System.out.println(pp);
				//System.out.println(pp.getKorisnickoIme());
			}  
		/*	for(PristupniPodaci pp: PristupniPodaci.getSviPristupniPodaci())
				System.out.println(pp); */ //lijepo cita pristupne podatke
			
	
			String s2 = "SELECT * FROM ucenik";
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				String ime = resultSet.getString(2);
				String prezime = resultSet.getString(3);
				for(PristupniPodaci pp: PristupniPodaci.getSviPristupniPodaci()) {
					if(ime.equalsIgnoreCase(pp.getKorisnickoIme().split("\\.")[0].trim()) && prezime.equalsIgnoreCase(pp.getKorisnickoIme().split("\\.")[1].trim())) {
						Ucenik u = new Ucenik(Integer.parseInt(resultSet.getString(1)), ime, prezime, Integer.parseInt(resultSet.getString(4)), pp);
						//System.out.println(u);
					}
				}
			}   //cita sve ucenike 
			
			
			String s3 = "SELECT * FROM profesor";
			resultSet = statement.executeQuery(s3);
			while(resultSet.next()) {
				String ime = resultSet.getString(2);
				String prezime = resultSet.getString(3);
				for(PristupniPodaci pp: PristupniPodaci.getSviPristupniPodaci()) {
					if(ime.equalsIgnoreCase(pp.getKorisnickoIme().split("\\.")[0].trim()) && prezime.equalsIgnoreCase(pp.getKorisnickoIme().split("\\.")[1].trim())) {
						Profesor p = new Profesor(Integer.parseInt(resultSet.getString(1)), ime, prezime, Integer.parseInt(resultSet.getString(4)), pp);
						//System.out.println(p);
					}
				}
			}   //cita sve profesore
			
			String s4 = "SELECT * FROM skola";
			resultSet = statement.executeQuery(s4);
			while(resultSet.next()) {
				Skola s = new Skola(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
				//System.out.println(s);
			}  //cita sve skole 
			
			String s5 = "SELECT * FROM predmet";
			resultSet = statement.executeQuery(s5);
			while(resultSet.next()) {
				Predmet p = new Predmet(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), Integer.parseInt(resultSet.getString(3)));
				//System.out.println(p);
			}  //cita sve predmete
			
			String s6 = "SELECT * FROM predmet_u_skoli";
			resultSet = statement.executeQuery(s6);
			while(resultSet.next()) {
				int idP = Integer.parseInt(resultSet.getString(2));
				int idS = Integer.parseInt(resultSet.getString(3));
				int idPr = Integer.parseInt(resultSet.getString(4));
				//System.out.println("" + idP + idS + idPr);
				for(Predmet p: Predmet.getSviPredmeti()) 
					for(Skola s: Skola.getSveSkole()) 
						for(Profesor pr: Profesor.getSviProfesori()) 
							if(p.getId()==idP && s.getId()==idS && pr.getId()==idPr) {
								PredmetUSkoli pus = new PredmetUSkoli(Integer.parseInt(resultSet.getString(1)), p, s, pr);
								//System.out.println(pus);
							} 
			} //ispisuje sve predmete u skoli
		
			String s7 = "SELECT * FROM izostanci";
			resultSet = statement.executeQuery(s7);
			while(resultSet.next()) {
				int idU = Integer.parseInt(resultSet.getString(2));
				int idP = Integer.parseInt(resultSet.getString(3));
				for(Ucenik u: Ucenik.getSviUcenici()) {
					for(PredmetUSkoli p: PredmetUSkoli.getSviPredmetiUSkoli()) 
						if(idU==u.getId() && idP==p.getId()) {
							Izostanci i = new Izostanci(Integer.parseInt(resultSet.getString(1)), u, p, LocalDate.parse(resultSet.getString(4)));
							//System.out.println(i);
							//System.out.println(i.getDatum());
						}
				}
			} //ispisuje sve izostanke
			
			String s8 = "SELECT * FROM ocjena";
			resultSet = statement.executeQuery(s8);
			while(resultSet.next()) {
				int idU = Integer.parseInt(resultSet.getString(2));
				int idP = Integer.parseInt(resultSet.getString(3));
				for(Ucenik u: Ucenik.getSviUcenici())
					for(PredmetUSkoli pus: PredmetUSkoli.getSviPredmetiUSkoli())
						if(u.getId()==idU && pus.getId()==idP) {
							Ocjena o = new Ocjena(Integer.parseInt(resultSet.getString(1)), u, pus, Integer.parseInt(resultSet.getString(4)), LocalDate.parse(resultSet.getString(5)));
							//System.out.println(o);
						}		
			}  //ispisuje sve ocjene
			
			String s9 = "SELECT * FROM pitanje";
			resultSet = statement.executeQuery(s9);
			while(resultSet.next()) {
				Pitanje p = new Pitanje(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2));
				//System.out.println(p);
			} //cita sva pitanja
			
			String s10 = "SELECT * FROM ocjena_predmeta";
			resultSet = statement.executeQuery(s10);
			while(resultSet.next()) {
				int idU = Integer.parseInt(resultSet.getString(3));
				int idPr = Integer.parseInt(resultSet.getString(2));
				int idP = Integer.parseInt(resultSet.getString(4));
				for(Ucenik u: Ucenik.getSviUcenici())
					for(PredmetUSkoli pus: PredmetUSkoli.getSviPredmetiUSkoli())
						for(Pitanje p: Pitanje.getSvaPitanja())
							if(u.getId()==idU && pus.getId()==idPr && p.getId()==idP) {
								OcjenaPredmeta op = new OcjenaPredmeta(Integer.parseInt(resultSet.getString(1)), u, pus, p, Integer.parseInt(resultSet.getString(5)));
								//System.out.println(op);
							}			
			} //cita sve ocjene predmeta
			
			
			
			/*if(Ucenik.getUcenik("vukasin.markovic")!=null)
				System.out.println(Ucenik.getUcenik("vukasin.markovic"));
			else
				System.out.println("Err");*/ 
			
		/*	for(Ocjena o: Ocjena.getSveOcjene())
				System.out.println(o + "\n");*/
			
			/*for(Ocjena o: Ucenik.getUcenik("vukasin.markovic").getSveOcjene())
				System.out.println(o + "\n");*/
		
			/*for(Ocjena o: Ucenik.getUcenik("vukasin.markovic").getOcjeneIzPredmeta("Matematika"))
				System.out.println(o + "\n");
			System.out.println();*/
			//System.out.println(Predmet.getPredmet("Fizika"));
			//System.out.println(Ucenik.getUcenik("vukasin.markovic").getSviIzostanci());
			
			/*String s11 = "INSERT INTO ocjena_predmeta VALUES () ";
			resultSet = statement.executeQuery(s11);*/
			
			/*String s11 = "SELECT MAX(id) FROM ocjena_predmeta";
			resultSet = statement.executeQuery(s11);
			while(resultSet.next()) {
				System.out.println(Integer.parseInt(resultSet.getString(1)));
			}   //ovo vraca posljednji id pitanja, da znam u main-u koji je id nove ocjene profesora tj ocjena_predmeta*/
			
			/*Ucenik u = Ucenik.getUcenik("tijana.boskovic");  //provjera ocjena ucenika
			for(Ocjena o: u.getSveOcjene())
				System.out.println(o);
			for(Ocjena o : u.getOcjeneIzPredmeta("Matematika")) {
				System.out.println(o);	
				System.out.println("ispis");
			}*/
			
			/*Ucenik u = Ucenik.getUcenik("tina.golubovic");  //provjera izostanaka ucenika
			for(Izostanci i : u.getSviIzostanci())
				System.out.println(i);
			System.out.println();
			for(Izostanci i: u.getIzostanciIzPredmeta("Matematika")) {
				System.out.println(i);
				System.out.println("ispis poslije izostanka");
			}*/
			
			/*Ucenik u = Ucenik.getUcenik("dusko.tadic");    //provjera ocjene predmeta tj profesora
			Predmet p = Predmet.getPredmet("Hemija", u);
			System.out.println(p);
			System.out.println(p.getOcjenaPredmeta(u, u.getProfesor(p)));
			System.out.println(u.getProfesor(p));*/
			
			//probaUcenika(12, 1);
			//novaOcjenaPredmeta(17, 5, 5, 4, 5);
		/*	Profesor p = Profesor.getProfesor("radomir.jankovic");
			for(Skola s: p.getSkole()) {
				System.out.println(s);
				System.out.println("dgag");
				for(Predmet pr: p.getPredmeti(s))
					System.out.println(pr);
			}*/
			
			//novaOcjenaUcenika(Ocjena.getSveOcjene().size()+1, 2, 2, 5, "2022-02-03");  //provjera nove ocjene, dodaje i u bazu i u kolekciju
			/*for(Ocjena o: Ocjena.getSveOcjene()) {
				System.out.println(o);
			}*/
			
			for(Ucenik u: Ucenik.getSviUcenici()) 
				System.out.println(u);
			
			for(Profesor p: Profesor.getSviProfesori())
				System.out.println(p);
			
			for(PristupniPodaci pp: PristupniPodaci.getSviPristupniPodaci())   //provjera promjene sifre, mijenja u bazi i u kolekciji objekat
				System.out.println(pp);
			
			System.out.println("Broj pp: " + PristupniPodaci.getSviPristupniPodaci().size());
			System.out.println("Broj ucenika: " + Ucenik.getSviUcenici().size());
			System.out.println("Broj profesora: " + Profesor.getSviProfesori().size());
			//System.out.println(postojiKorisnik("sarasara")); //vraca sifru
			//System.out.println(LoginKontroler.Login("sara.majkic", "sara.majkic123", this));
			//System.out.println(Ucenik.getUcenik("sarasara"));
			//System.out.println(Ucenik.getUcenik("vukasin.markovic")); //
			//System.out.println(Ucenik.getUcenik("mara.majkic"));  
			//System.out.println(Profesor.getProfesor("saricaa"));
			//System.out.println(Profesor.getProfesor("radomir.jankovic"));  //
			//System.out.println(Profesor.getProfesor("sara.majkic"));  
			
			for(OcjenaPredmeta o: OcjenaPredmeta.getSveOcjenePredmeta())
				System.out.println(o);
			
			for(Skola s: Skola.getSveSkole())
				System.out.println(s);
			
			for(Predmet p: Predmet.getSviPredmeti())
				System.out.println(p);
			
			
			/*noviIzostanakUcenika(Izostanci.getSviIzostanci().size()+1, 2, 2, "2022-02-03"); //provjera novog izostanka, dodaje i u bazu i kolekciju
			for(Izostanci i: Izostanci.getSviIzostanci())
				System.out.println(i);*/
			
			/*Ucenik u = Ucenik.getUcenik("marko.knezevic");
			System.out.println(u.datumPosljednjeOcjene(Predmet.getPredmet("Matematika", u)));  //provjera pronalazenja posljednjeg datuma iz predmeta za datog ucenika
			//novaOcjenaUcenika(Ocjena.getSveOcjene().size()+1, 8, 1, 5, "2021-10-22");
			for(Ocjena o:u.getOcjeneIzPredmeta("Matematika"))
				System.out.println(o);
			System.out.println();
			System.out.println(u.datumPosljednjeOcjene(Predmet.getPredmet("Matematika", u)));*/
			/*LocalDate l = LocalDate.now();
			LocalDate l2 = LocalDate.of(2021, 10, 22);
			System.out.println(u.razlikaUDanima(l2, u.datumPosljednjeOcjene(Predmet.getPredmet("Matematika", u))));*/
			
			/*noviPristupniPodaci(PristupniPodaci.getSviPristupniPodaci().size()+1, "sar", "sar"); //provjera unosa pristupnih podataka i ucenika
			PristupniPodaci pp = PristupniPodaci.getPristupniPodaci("sar");
			System.out.println(pp);
			noviUcenik(Ucenik.getSviUcenici().size()+1, "sara", "saraaa", 0, pp);*/
			
		/*	for(Ocjena o :Ocjena.getSveOcjene())
				System.out.println(o);*/
	
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	//-----------------------------------------------------------------log in-------------------------------------------------------------------------
	//Vraca MD5 hash kod za trazenog korisnika ako postoji u bazi ili null ako nema korisnika ili je sifra polje prazno.
	
	String postojiKorisnik(String korisnickoIme) throws Exception {

		//Statement statement = connection.createStatement();
		String selectSql = "SELECT sifra FROM pristupni_podaci WHERE korisnicko_ime LIKE '" + korisnickoIme + "';";
		ResultSet resultSet = statement.executeQuery(selectSql);  //sadrzi rezultat izvrsavanja kverija

		if (resultSet.next() == false) {
			return null;
		}

		return resultSet.getString("sifra");
	}

	boolean ispravnaLozinka(String hashLozinke, String dbHashLozinke) {
		return hashLozinke.equals(dbHashLozinke); 
	}
	
	//----------------------------------------------------------------ostalo--------------------------------------------------------------------------
	/*void novaOcjenaPredmeta(int id, Ucenik u, PredmetUSkoli pus, Pitanje p, int ocjena) {
		int idu = u.getId();
		int idpus = pus.getId();
		int idp = p.getId();
		String s = "INSERT INTO ocjena_predmeta VALUES (id, idu, 'idpus', 'idp', ocjena)";
		try {
			statement.executeUpdate(s);
			new OcjenaPredmeta(id, u, pus, p, ocjena);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	void novaOcjenaPredmeta(int idU, int idPus, int idP, int ocjena) {
		String s = "INSERT INTO ocjena_predmeta(predmet_u_skoli_id, ucenik_id, pitanje_id, ocjena) VALUES (" + idPus + ", " + idU + ", " + idP + "," + ocjena + ")";
		String s2 = "SELECT MAX(id) FROM ocjena_predmeta";
		int id = 0;
		try {
			statement.executeUpdate(s);
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				id = Integer.parseInt(resultSet.getString(1));
			}
			new OcjenaPredmeta(id, Ucenik.getUcenik(idU), PredmetUSkoli.getPredmetUSkoli(idPus), Pitanje.getPitanje(idP), ocjena);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void setSifra(String korisnickoIme, String lozinka) throws Exception{
		MessageDigest md5hash;
		md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(lozinka.getBytes("UTF-8"));
		String s = "UPDATE ors1_opp_2021_2022.pristupni_podaci SET sifra = '" + String.format("%032x", new BigInteger(1, md5hash.digest())) + "' WHERE korisnicko_ime = '" + korisnickoIme + "';";
		try {
			statement.executeUpdate(s);
			PristupniPodaci.getPristupniPodaci(korisnickoIme).setSifra("String.format(\"%032x\", new BigInteger(1, md5hash.digest()))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void novaOcjenaUcenika(int idU, int idPus, int ocjena, String s) {
	/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = sdf.parse(s);*/
	String str = "INSERT INTO ocjena(ucenik_id, predmet_u_skoli_id, ocjena, datum) VALUES (" + idU + ", " + idPus + "," + ocjena + ", '" + s + "')";
	String s2 = "SELECT MAX(id) FROM ocjena";
	int id = 0;
	try {
		statement.executeUpdate(str);
		resultSet = statement.executeQuery(s2);
		while(resultSet.next()) {
			id = Integer.parseInt(resultSet.getString(1));
		}
		new Ocjena(id, Ucenik.getUcenik(idU), PredmetUSkoli.getPredmetUSkoli(idPus), ocjena, LocalDate.parse(s));
	} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	
	void noviIzostanakUcenika(int idU, int idPus, String str) {
		String s = "INSERT INTO izostanci(ucenik_id, predmet_u_skoli_id, datum) VALUES (" + idU + ", " + idPus + ", '" + str + "')";
		String s2 = "SELECT MAX(id) FROM izostanci";
		int id = 0;
		try {
			statement.executeUpdate(s);
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				id = Integer.parseInt(resultSet.getString(1));
			}
			new Izostanci(id, Ucenik.getUcenik(idU), PredmetUSkoli.getPredmetUSkoli(idPus), LocalDate.parse(str));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void noviPristupniPodaci(String korIme, String mejl) throws Exception {
		String s2 = "SELECT MAX(id) FROM pristupni_podaci";
		int id = 0;
		String sifra = korIme + "123";
		MessageDigest md5hash;
		md5hash = MessageDigest.getInstance("MD5");
		md5hash.update(sifra.getBytes("UTF-8"));
		String s = "INSERT INTO pristupni_podaci(korisnicko_ime, email, sifra) VALUES ('" + korIme + "', '" + mejl +  "', '" + String.format("%032x", new BigInteger(1, md5hash.digest())) + "')";
		try {
			statement.executeUpdate(s);
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				id = Integer.parseInt(resultSet.getString(1));
			}
			new PristupniPodaci(id, korIme, sifra, mejl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void noviUcenik(String ime, String prezime, int pol, PristupniPodaci pp) {
		String s = "INSERT INTO ucenik(ime, prezime, pol, pristupni_podaci_id) VALUES ('" + ime + "', '" + prezime + "', " + pol + ", " + pp.getId() + ")";
		String s2 = "SELECT MAX(id) FROM ucenik";
		int id = 0;
		try {
			statement.executeUpdate(s);
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				id = Integer.parseInt(resultSet.getString(1));
			}
			new Ucenik(id, ime, prezime, pol, pp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void noviPredmet(String naziv, int razred) {
		String s = "INSERT INTO predmet(naziv, razred) VALUES ('" + naziv + "', " + razred + ")";
		String s2 = "SELECT MAX(id) FROM predmet";
		int id = 0;
		try {
			statement.executeUpdate(s);
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				id = Integer.parseInt(resultSet.getString(1));
			}
			new Predmet(id, naziv, razred);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void novaSkola(String naziv, String grad, String mjesto, String drzava) {
		String s = "INSERT INTO skola(naziv, grad, mjesto, drzava) VALUES ('" + naziv + "', '" + grad + "', '" + mjesto + "', '" + drzava + "')";
		String s2 = "SELECT MAX(id) FROM skola";
		int id = 0;
		try {
			statement.executeUpdate(s);
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				id = Integer.parseInt(resultSet.getString(1));
			}
			new Skola(id, naziv, grad, mjesto, drzava);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void noviProfesor(String ime, String prezime, int pol, PristupniPodaci pp) {
		String s = "INSERT INTO profesor(ime, prezime, pol, pristupni_podaci_id) VALUES ('" + ime + "', '" + prezime + "', " + pol + ", " + pp.getId() + ")";
		String s2 = "SELECT MAX(id) FROM profesor";
		int id = 0;
		try {
			statement.executeUpdate(s);
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				id = Integer.parseInt(resultSet.getString(1));
			}
			new Profesor(id, ime, prezime, pol, pp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void novoZaduzenjeProfesora(Predmet p, Skola sk, Profesor prof) {
		String s = "INSERT INTO predmet_u_skoli(predmet_id, skola_id, profesor_id) VALUES(" + p.getId() + ", " + sk.getId() + ", " + prof.getId() + ")";
		String s2 = "SELECT MAX(id) FROM predmet_u_skoli";
		int id = 0;
		try {
			statement.executeUpdate(s);
			resultSet = statement.executeQuery(s2);
			while(resultSet.next()) {
				id = Integer.parseInt(resultSet.getString(1));
			}
			new PredmetUSkoli(id, p, sk, prof);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*private static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}

	void testUsingStrictRegex() {
	    String emailAddress = "username@domain.com";
	    String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
	        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	    Assert.assertTrue(patternMatches(emailAddress, regexPattern));
	}*/
	
}