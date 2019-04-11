package vo;

import utility.StringUtility;

/**
 * Wrapper View object na klijentskoj strani .jsp u session object
 * @author dsaro
 *
 */
public class KorisnikVO {

	private static final long serialVersionUID = 1L;
	//private Korisnik korisnik;
	
	//Podaci o ulogovanom korisniku
	private int id; //nevidljivo polje/invisible! id korisnika
	
	private String ime;
	private String prezime;
	private String tipKorisnika;
	
	private String korisnikOutput;
	
	public KorisnikVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KorisnikVO(int id, String ime, String prezime, String tipKorisnika) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.tipKorisnika = tipKorisnika;
		//TODO:
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getTipKorisnika() {
		return tipKorisnika;
	}
	public void setTipKorisnika(String tipKorisnika) {
		this.tipKorisnika = tipKorisnika;
	}
	
	public String getKorisnikOutput() {
		return korisnikOutput;
		//
	}
	public void setKorisnikOutput() {
		this.korisnikOutput = StringUtility.napraviFormatPrikaza(getIme(), getPrezime(), getTipKorisnika());
		//
	}
	
	@Override
	public String toString() {
		return "KorisnikVO [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", tipKorisnika=" + tipKorisnika
				+ "]";
	}
	
	
}
