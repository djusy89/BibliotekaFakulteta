package bean;

public class Kategorija {

	private int id;
	
	private String naziv;
	private String opis;
	
	public Kategorija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kategorija(int id, String naziv, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
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

	@Override
	public String toString() {
		return "Kategorija [id=" + id + ", naziv=" + naziv + ",  opis=" + opis + "]";
	}
	
	
}
