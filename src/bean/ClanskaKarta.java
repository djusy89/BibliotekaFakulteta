package bean;

public class ClanskaKarta {

	private int id;
	
	private String broj_clanarine;
	private String status;
	private String dat_poslednje_uplate;
	private String pocetak_vazenja;
	private String kraj_vazenja;
	private String tip_clanarine;
	
	private int id_korisnik;

	public ClanskaKarta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClanskaKarta(int id, String broj_clanarine, String status, String dat_poslednje_uplate, String pocetak_vazenja, String kraj_vazenja,
			String tip_clanarine, int id_korisnik) {
		super();
		this.id = id;
		this.broj_clanarine = broj_clanarine;
		this.status = status;
		this.dat_poslednje_uplate = dat_poslednje_uplate;
		this.pocetak_vazenja = pocetak_vazenja;
		this.kraj_vazenja = kraj_vazenja;
		this.tip_clanarine = tip_clanarine;
		this.id_korisnik = id_korisnik;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBroj_clanarine() {
		return broj_clanarine;
	}

	public void setBroj_clanarine(String broj_clanarine) {
		this.broj_clanarine = broj_clanarine;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDat_poslednje_uplate() {
		return dat_poslednje_uplate;
	}

	public void setDat_poslednje_uplate(String dat_poslednje_uplate) {
		this.dat_poslednje_uplate = dat_poslednje_uplate;
	}

	public String getPocetak_vazenja() {
		return pocetak_vazenja;
	}

	public void setPocetak_vazenja(String pocetak_vazenja) {
		this.pocetak_vazenja = pocetak_vazenja;
	}

	public String getKraj_vazenja() {
		return kraj_vazenja;
	}

	public void setKraj_vazenja(String kraj_vazenja) {
		this.kraj_vazenja = kraj_vazenja;
	}

	public String getTip_clanarine() {
		return tip_clanarine;
	}

	public void setTip_clanarine(String tip_clanarine) {
		this.tip_clanarine = tip_clanarine;
	}

	public int getId_korisnik() {
		return id_korisnik;
	}

	public void setId_korisnik(int id_korisnik) {
		this.id_korisnik = id_korisnik;
	}

	@Override
	public String toString() {
		return "ClanskaKarta [id=" + id + ", broj_clanarine=" + broj_clanarine + ", status=" + status + ", dat_poslednje_uplate=" + dat_poslednje_uplate
				+ ", pocetak_vazenja=" + pocetak_vazenja + ", kraj_vazenja=" + kraj_vazenja + ", tip_clanarine="
				+ tip_clanarine + ", id_korisnik=" + id_korisnik + "]";
	}
	
}
