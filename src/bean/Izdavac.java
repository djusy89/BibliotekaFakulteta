package bean;

public class Izdavac {
	
	private int id;
	
	private String naziv;
	private String opis;
	private String tip_knjige;
	private String adresa_izdavaca;
	private String telefon;
	
	public Izdavac() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Izdavac(int id, String naziv, String opis, String tip_knjige, String adresa_izdavaca, String telefon) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.tip_knjige = tip_knjige;
		this.adresa_izdavaca = adresa_izdavaca;
		this.telefon = telefon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTip_knjige() {
		return tip_knjige;
	}

	public void setTip_knjige(String tip_knjige) {
		this.tip_knjige = tip_knjige;
	}

	public String getAdresa_izdavaca() {
		return adresa_izdavaca;
	}

	public void setAdresa_izdavaca(String adresa_izdavaca) {
		this.adresa_izdavaca = adresa_izdavaca;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Override
	public String toString() {
		return "Izdavac [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", tip_knjige=" + tip_knjige
				+ ", adresa_izdavaca=" + adresa_izdavaca + ", telefon=" + telefon + "]";
	}
	

}
