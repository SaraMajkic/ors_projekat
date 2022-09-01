package application;
import java.math.BigInteger;
import java.security.*;

public class LoginKontroler {

	static boolean Login(String korisnickoIme, String sifra, Konekcija konekcija) throws Exception {
		
		String dbMD5Hash = konekcija.postojiKorisnik(korisnickoIme);
		if (dbMD5Hash == null) {   //vraca null ako nema sifre, tj. nema tog korisnika
			return false;
		}
		
		MessageDigest md5hash = MessageDigest.getInstance("MD5");  //rezultat hesiranja, instanca md5 algoritma
		md5hash.update(sifra.getBytes("UTF-8"));
		return konekcija.ispravnaLozinka(String.format("%032x", new BigInteger(1, md5hash.digest())), dbMD5Hash);  //md5hash.digest je binaran niz
		//%032x formatiranje u heksadecimalni zapis (x)
		//32 cifre dobijemo, ako ne dobijemo dodaje se vodeca nula
		//ako se bajt niz ne moze pretvorit u Integer zbog ogranicenja, koristi se BigInteger
		//1 oznacava pozitivan broj predstavljen nizom
	}
	
	
}
