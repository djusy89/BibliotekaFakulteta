package bean;

public class IzdataKnjiga {

	private int id;
	
	private String dat_zaduzenja;
	private String rok_za_vracanje;
	private String br_zaduzenih_knjiga;
	
	private int id_korisnik;
	private int id_knjiga;
	private int id_kategorija;
	private int id_izdavac;
	
	public IzdataKnjiga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IzdataKnjiga(int id, String dat_zaduzenja, String rok_za_vracanje, String br_zaduzenih_knjiga,
			int id_korisnik, int id_knjiga, int id_kategorija, int id_izdavac) {
		super();
		this.id = id;
		this.dat_zaduzenja = dat_zaduzenja;
		this.rok_za_vracanje = rok_za_vracanje;
		this.br_zaduzenih_knjiga = br_zaduzenih_knjiga;
		this.id_korisnik = id_korisnik;
		this.id_knjiga = id_knjiga;
		this.id_kategorija = id_kategorija;
		this.id_izdavac = id_izdavac;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDat_zaduzenja() {
		return dat_zaduzenja;
	}

	public void setDat_zaduzenja(String dat_zaduzenja) {
		this.dat_zaduzenja = dat_zaduzenja;
	}

	public String getRok_za_vracanje() {
		return rok_za_vracanje;
	}

	public void setRok_za_vracanje(String rok_za_vracanje) {
		this.rok_za_vracanje = rok_za_vracanje;
	}

	public String getBr_zaduzenih_knjiga() {
		return br_zaduzenih_knjiga;
	}

	public void setBr_zaduzenih_knjiga(String br_zaduzenih_knjiga) {
		this.br_zaduzenih_knjiga = br_zaduzenih_knjiga;
	}

	public int getId_korisnik() {
		return id_korisnik;
	}

	public void setId_korisnik(int id_korisnik) {
		this.id_korisnik = id_korisnik;
	}

	public int getId_knjiga() {
		return id_knjiga;
	}

	public void setId_knjiga(int id_knjiga) {
		this.id_knjiga = id_knjiga;
	}

	public int getId_kategorija() {
		return id_kategorija;
	}

	public void setId_kategorija(int id_kategorija) {
		this.id_kategorija = id_kategorija;
	}

	public int getId_izdavac() {
		return id_izdavac;
	}

	public void setId_izdavac(int id_izdavac) {
		this.id_izdavac = id_izdavac;
	}

	@Override
	public String toString() {
		return "IzdateKnjige [id=" + id + ", dat_zaduzenja=" + dat_zaduzenja + ", rok_za_vracanje=" + rok_za_vracanje
				+ ", br_zaduzenih_knjiga=" + br_zaduzenih_knjiga + ", id_korisnik=" + id_korisnik + ", id_knjiga="
				+ id_knjiga + ", id_kategorija=" + id_kategorija + ", id_izdavac=" + id_izdavac + "]";
	}
	
	
	
}
