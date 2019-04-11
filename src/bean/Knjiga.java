package bean;

import com.mysql.jdbc.Blob;

public class Knjiga {

	private int id;
	
	private String naziv;
	private String opis;
	private String link_izdavaca;
	private Blob slika_naslovne_strane;
	private String izdanje;
	private String autor;
	
	private int id_kategorija;
	private int id_izdavac;
	
	public Knjiga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Knjiga(int id, String naziv, String opis, String link_izdavaca, Blob slika_naslovne_strane, String izdanje,
			String autor, int id_kategorija, int id_izdavac) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.link_izdavaca = link_izdavaca;
		this.slika_naslovne_strane = slika_naslovne_strane;
		this.izdanje = izdanje;
		this.autor = autor;
		this.id_kategorija = id_kategorija;
		this.id_izdavac = id_izdavac;
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

	public String getLink_izdavaca() {
		return link_izdavaca;
	}

	public void setLink_izdavaca(String link_izdavaca) {
		this.link_izdavaca = link_izdavaca;
	}

	public Blob getSlika_naslovne_strane() {
		return slika_naslovne_strane;
	}

	public void setSlika_naslovne_strane(Blob slika_naslovne_strane) {
		this.slika_naslovne_strane = slika_naslovne_strane;
	}

	public String getIzdanje() {
		return izdanje;
	}

	public void setIzdanje(String izdanje) {
		this.izdanje = izdanje;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
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
		return "Knjiga [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", link_izdavaca=" + link_izdavaca
				+ ", slika_naslovne_strane=" + slika_naslovne_strane + ", izdanje=" + izdanje + ", autor=" + autor
				+ ", id_kategorija=" + id_kategorija + ", id_izdavac=" + id_izdavac + "]";
	}
	
	
}
