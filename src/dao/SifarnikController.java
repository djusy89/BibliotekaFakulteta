package dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import bean.ClanskaKarta;
import bean.Izdavac;
import bean.Kategorija;
import bean.Knjiga;
import bean.Korisnik;

public class SifarnikController {
	
	static Logger logger = Logger.getLogger(SifarnikController.class.getName());

	public SifarnikController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// Korisnici:
	
	public static boolean proveriLogovanjeKorisnika(String korisnickoIme, String lozinka)
	{
		return LoginDAO.getByUserNamePassword(korisnickoIme, lozinka);
	}
	
	public static Korisnik preuzmiKorisnikaIzBaze(String korisnickoIme, String lozinka)
	{
		return LoginDAO.getAllByUserNamePassword(korisnickoIme, lozinka);
	}
	
	public static Korisnik zahtevZaUnosKorisnika(String ...podaci)
	{
		return KorisnikDAO.dodajNovogKorisnika(podaci);
	}

	public static Korisnik uclaniKorisnika(int id)
	{
		logger.info("Entering SifarnikController.uclaniKorisnika()");
		
		return KorisnikDAO.uclaniKorisnika(id);	
	}
	
	public static Korisnik obrisiKorisnika(int id)
	{
		logger.info("Entering SifarnikController.obrisiKorisnika()");
		
		return KorisnikDAO.obrisiKorisnika(id);	
	}
	
	public static ArrayList<Korisnik> preuzmiKorisnikeNaCekanju()
	{
		logger.info("Entering SifarnikController.preuzmiKorisnikeNaCekanju()");
		
		return KorisnikDAO.preuzmiKorisnikeNaCekanju();
	}
	public static ArrayList<Korisnik> preuzmiAktivneKorisnike()
	{
		logger.info("Entering SifarnikController.preuzmiAktivneKorisnike()");
		
		return KorisnikDAO.preuzmiAktivneKorisnike();
	}
	
	// Clanska karta:
	
	public static ClanskaKarta napraviClanskuKartu(String ...podaci)
	{
		logger.info("Entering SifarnikController.napraviClanskuKartu(int id)");
		
		return ClanskaKartaDAO.napraviClanskuKartu(podaci);
	}
	
	// Kategorija:
	
	public static Kategorija napraviKategoriju(String nazivKategorije, String opisKategorije)
	{
		logger.info("Entering SifarnikController.napraviKategoriju");
		
		return KnjigaDAO.napraviKategoriju(nazivKategorije, opisKategorije);
	}
	
	public static ArrayList<Kategorija> preuzmiKategorije()
	{
		logger.info("Entering SifarnikController.preuzmiKategorije()");
		
		return KnjigaDAO.preuzmiKategorije();
	}
	public static ArrayList<Kategorija> pretragaKategorije(String tekstPretrage)
	{
		logger.info("Entering SifarnikController.preuzmiKategorije()");
		
		return KnjigaDAO.pretragaKategorije(tekstPretrage);
	}
	public static Kategorija izmeniKategoriju(int kategorijaId)
	{
		logger.info("Entering SifarnikController.izmeniKategoriju()");
		
		return KnjigaDAO.izmeniKategoriju(kategorijaId);
	}
	public static Kategorija obrisiKategoriju(int kategorijaId)
	{
		logger.info("Entering SifarnikController.obrisiKategoriju()");
		
		return KnjigaDAO.obrisiKategoriju(kategorijaId);
	}
	
	// Knjiga:
	
	public static Knjiga unesiKnjigu(String nazivKnjige, String opis, String linkIzdavaca, Part slikaNaslovne, String izdanje, String autor, String izdavac, String kategorije) throws IOException
	{
		logger.info("Entering SifarnikController.unesiKnjigu()");
		
		return KnjigaDAO.unesiKnjigu(nazivKnjige, opis, linkIzdavaca, slikaNaslovne, izdanje, autor, izdavac, kategorije);
	}
	
	public static Knjiga izmeniKnjigu(int knjigaId, String nazivKnjige, String opis, String linkIzdavaca, Part slikaNaslovne, String izdanje, String autor, String izdavac, String kategorije) throws IOException
	{
		logger.info("Entering SifarnikController.izmeniKnjigu()");
		
		return KnjigaDAO.izmeniKnjigu(knjigaId, nazivKnjige, opis, linkIzdavaca, slikaNaslovne, izdanje, autor, izdavac, kategorije);
	}
	
	public static Knjiga obrisiKnjigu(int knjigaId) throws IOException
	{
		logger.info("Entering SifarnikController.obrisiKnjigu()");
		
		return KnjigaDAO.obrisiKnjigu(knjigaId);
	}
	
	public static ArrayList<Knjiga> preuzmiKnjige(int startniRed, int brojPikazanihRedova)
	{
		logger.info("Entering SifarnikController.preuzmiKnjige()");
		
		return KnjigaDAO.preuzmiKnjige(startniRed, brojPikazanihRedova);
	}
	public static ArrayList<Knjiga> preuzmiKnjige()
	{
		logger.info("Entering SifarnikController.preuzmiKnjige()");
		
		return KnjigaDAO.preuzmiKnjige();
	}
	
	public static Knjiga izmeniKnjigu(int knjigaId)
	{
		logger.info("Entering SifarnikController.unesiKnjigu()");
		
		return KnjigaDAO.izmeniKnjigu(knjigaId);
	}
	public static ArrayList<Knjiga> pretragaKnjiga (String tekstPretrage, String pretragaKnjige)
	{
		logger.info("Entering SifarnikController.pretragaKnjiga()");
		
		return KnjigaDAO.pretragaKnjiga(tekstPretrage, pretragaKnjige);
	}
	public static ArrayList<Knjiga> sortirajKnjige (String sortiranje)
	{
		logger.info("Entering SifarnikController.sortirajKnjige");
		
		return KnjigaDAO.sortirajKnjige(sortiranje);
	}
	
	// Izdavaci:
	
	public static ArrayList<Izdavac> preuzmiIzdavace()
	{
		logger.info("Entering SifarnikController.preuzmiIzdavace()");
		
		return KnjigaDAO.preuzmiIzdavace();
	}
	
	public static Izdavac unesiIzdavaca(String ...podaci)
	{
		logger.info("Entering SifarnikController.unesiIzdavaca()");
		
		return KnjigaDAO.unesiIzdavaca(podaci);
	}
}
