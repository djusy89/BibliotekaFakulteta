package bean;

public class Korisnik {

	private int id;
	
	private String korisnicko_ime;
	private String lozinka;
	private String ime;
	private String prezime;
	private String tip_korisnika;
	private String telefon;
	private String e_mail;
	private String status;
	
	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(int id, String korisnicko_ime, String lozinka, String ime, String prezime, String tip_korisnika,
			String telefon, String e_mail, String status) {
		super();
		this.id = id;
		this.korisnicko_ime = korisnicko_ime;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.tip_korisnika = tip_korisnika;
		this.telefon = telefon;
		this.e_mail = e_mail;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}

	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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

	public String getTip_korisnika() {
		return tip_korisnika;
	}

	public void setTip_korisnika(String tip_korisnika) {
		this.tip_korisnika = tip_korisnika;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", korisnicko_ime=" + korisnicko_ime + ", lozinka=" + lozinka + ", ime=" + ime
				+ ", prezime=" + prezime + ", tip_korisnika=" + tip_korisnika + ", telefon=" + telefon + ", e_mail="
				+ e_mail + ", status=" + status + "]";
	}
	
	
}
