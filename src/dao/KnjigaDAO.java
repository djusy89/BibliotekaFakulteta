package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Blob;

import bean.Izdavac;
import bean.Kategorija;
import bean.Knjiga;
import db.DBConnectionManager;
import utility.IConstant;

public class KnjigaDAO {

	
	static Connection connection;
	static Statement statement;
	static PreparedStatement preparedStatement;
	static ResultSet resultSet ;
	
	static Logger logger = Logger.getLogger(KnjigaDAO.class.getName());
	
	public static Kategorija napraviKategoriju (String nazivKategorije, String opisKategorije){ // proveriti unos u bazu i resiti redirekciju nakon unosa podataka
		logger.info("Entering KnjigaDAO napraviKategoriju()");

		Kategorija kategorija = new Kategorija();	
		String query = null;
		
		try 
		{
			connection = DBConnectionManager.getConnection();
			
			//Otvaranje transakcije
			connection.setAutoCommit(false);
			
			query = "SELECT naziv FROM kategorija WHERE naziv= '" + nazivKategorije + "'";
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.wasNull()) {
				query = "SELECT MAX(id_kategorija) as maxId FROM kategorija";			
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				
				int idKategorija = 0;
				if(resultSet.next()){
					idKategorija = resultSet.getInt("maxId");
				}
				
				//insert osoba za gornji id lica
				query = "INSERT into kategorija (id_kategorija, naziv, opis) VALUES (?, ?, ?)";
				logger.info("query napravi kategoriju" + query);
				
				statement = connection.createStatement();
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, ++idKategorija);
				preparedStatement.setString(2, nazivKategorije);
				preparedStatement.setString(3, opisKategorije);
				
				preparedStatement.executeUpdate();
				
				connection.commit();
				//Zatvaranje transakcije
				
				logger.info("Uspesno zavrsili insert kategorije u bazu.");
			}else {
				kategorija = null;
			}
			
			
		}catch (SQLException e) 
		{
		// TODO: handle exception
		e.printStackTrace();
		try {
			connection.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		kategorija = null;
	} 
	finally 
	{
		// TODO: handle finally clause
	}
		return kategorija;
	}
	
	public static ArrayList<Kategorija> preuzmiKategorije(){
		
		logger.info("Entering KnjigaDAO.preuzmiKategorije()");
		
		Kategorija kategorijaTemp = null;
		ArrayList<Kategorija> listaKategorija = new ArrayList<>();
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = "";
			
			StringBuffer tempQuery = new StringBuffer();
			tempQuery.append("SELECT * FROM kategorija");
			
			query = tempQuery.toString();
			
			logger.info("Query: " + query);
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				kategorijaTemp = new Kategorija();
				kategorijaTemp.setId(resultSet.getInt("id_kategorija"));
				kategorijaTemp.setNaziv(resultSet.getString("naziv"));
				kategorijaTemp.setOpis(resultSet.getString("opis"));
				
				listaKategorija.add(kategorijaTemp);
				
			}
			logger.info("Lista kategorija KnjigaDAO: " + listaKategorija);
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaKategorija;
	}
	
	public static ArrayList<Kategorija> pretragaKategorije(String tekstPretrage){
		
		logger.info("Entering KnjigaDAO.pretragaKategorije()");
		
		Kategorija kategorijaTemp = null;
		ArrayList<Kategorija> listaKategorija = new ArrayList<>();
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = "";
			
			StringBuffer tempQuery = new StringBuffer();
			tempQuery.append("SELECT * FROM kategorija WHERE naziv LIKE '"+ tekstPretrage +"%'");
			
			query = tempQuery.toString();
			
			logger.info("Query: " + query);
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				kategorijaTemp = new Kategorija();
				kategorijaTemp.setId(resultSet.getInt("id_kategorija"));
				kategorijaTemp.setNaziv(resultSet.getString("naziv"));
				kategorijaTemp.setOpis(resultSet.getString("opis"));
				
				listaKategorija.add(kategorijaTemp);
				
			}
			logger.info("Lista kategorija KnjigaDAO: " + listaKategorija);
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaKategorija;
	}

	public static Kategorija obrisiKategoriju(int kategorijaId){
		
		logger.info("Entering Korisnik obrisiKategoriju()");
		
		Kategorija kategorija = null;
		
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = ("DELETE FROM kategorija WHERE id_kategorija= '" + kategorijaId + "'");
			
			logger.info("Query: " + query.toString());
			
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return kategorija;
	}
	
	public static Kategorija izmeniKategoriju(int kategorijaId){
		
		Kategorija kategorija = new Kategorija();	
		
		try 
		{
			connection = DBConnectionManager.getConnection();
			
			connection.setAutoCommit(false);
			
			String query = "SELECT * FROM kategorija WHERE id_kategorija ='" + kategorijaId + "'";			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) 
			{
				kategorija.setId(resultSet.getInt("id_kategorija"));
				kategorija.setNaziv(resultSet.getString("naziv"));
				kategorija.setOpis(resultSet.getString("opis"));
				
			}
			logger.info("Kategorija izmeniKAtegoriju KnjigaDAO: " + kategorija);
		
	}catch (SQLException e) 
		{
		// TODO: handle exception
		e.printStackTrace();
		try {
			connection.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		kategorija = null;
	} 
	finally 
	{
		// TODO: handle finally clause
		logger.debug("end of sqls");
	}
		return kategorija;
	}
	
	public static Knjiga unesiKnjigu(String nazivKnjige, String opis, String linkIzdavaca, Part slikaNaslovne, String izdanje, String autor, String izdavac, String kategorije) throws IOException {
		
				int izdavacId = Integer.parseInt(izdavac);
				int kategorijeId = Integer.parseInt(kategorije);
				
				Knjiga knjiga = new Knjiga();	
				InputStream inputStream = slikaNaslovne.getInputStream();

				logger.info("Inputstream: " + inputStream);
				
				if (slikaNaslovne.getSize() == 0) {
					inputStream = new FileInputStream(new File("C:\\workspace-jee\\WebBibliotekaFakulteta\\WebContent\\images\\no_images.png"));
				}
				try 
				{
					connection = DBConnectionManager.getConnection();
					
					//Otvaranje transakcije
					connection.setAutoCommit(false);
					
					String query = "SELECT MAX(id_knjiga) as maxId FROM knjiga";			
					statement = connection.createStatement();
					resultSet = statement.executeQuery(query);
					
					int id = 0;
					if(resultSet.next()){
						id = resultSet.getInt("maxId");
					}

					query = "INSERT into knjiga (id_knjiga, naziv, opis, link_izdavaca, slika_naslovne, izdanje, autor, kategorija_id_kategorija, izdavac_id_izdavac) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					statement = connection.createStatement();
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, ++id);
					preparedStatement.setString(2, nazivKnjige);
					preparedStatement.setString(3, opis);
					preparedStatement.setString(4, linkIzdavaca);
					preparedStatement.setBlob(5, inputStream);
					preparedStatement.setString(6, izdanje);
					preparedStatement.setString(7, autor);
					preparedStatement.setInt(8,  kategorijeId);
					preparedStatement.setInt(9, izdavacId);
					
					preparedStatement.executeUpdate();
					
					connection.commit();
					//Zatvaranje transakcije
					
					logger.info("Uspesno zavrsili insert knjige u bazu.");
				
			}catch (SQLException e) 
				{
				// TODO: handle exception
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				knjiga = null;
			} 
			finally 
			{
				// TODO: handle finally clause
				logger.debug("end of sqls");
			}
				return knjiga;
			}
	public static Knjiga izmeniKnjigu(int knjigaId, String nazivKnjige, String opis, String linkIzdavaca, Part slikaNaslovne, String izdanje, String autor, String izdavac, String kategorije) throws IOException {
		
		int izdavacId = Integer.parseInt(izdavac);
		int kategorijeId = Integer.parseInt(kategorije);
		
		Knjiga knjiga = new Knjiga();	
		InputStream inputStream = slikaNaslovne.getInputStream();
		
		
		try 
		{
			connection = DBConnectionManager.getConnection();
			
			//Otvaranje transakcije
			connection.setAutoCommit(false);
			
			String query = ("UPDATE knjiga SET id_knjiga=?, naziv=?, opis=?, link_izdavaca=?, slika_naslovne=?, izdanje=?, autor=?, izdavac_id_izdavac=?, kategorija_id_kategorija=? WHERE id_knjiga='" + knjigaId + "'");		
			String query1 = ("UPDATE knjiga SET id_knjiga=?, naziv=?, opis=?, link_izdavaca=?, izdanje=?, autor=?, izdavac_id_izdavac=?, kategorija_id_kategorija=? WHERE id_knjiga='" + knjigaId + "'");
			
			if (slikaNaslovne.getSize() == 0){
				logger.info("Usli ste u if blok" );
				statement = connection.createStatement();
				preparedStatement = connection.prepareStatement(query1);
				
				preparedStatement.setInt(1, knjigaId);
				preparedStatement.setString(2, nazivKnjige);
				preparedStatement.setString(3, opis);
				preparedStatement.setString(4, linkIzdavaca);
				preparedStatement.setString(5, izdanje);
				preparedStatement.setString(6, autor);
				preparedStatement.setInt(7,  izdavacId);
				preparedStatement.setInt(8, kategorijeId);
				
				preparedStatement.executeUpdate();
			}else {
				logger.info("Usli ste u else blok" );
				statement = connection.createStatement();
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, knjigaId);
				preparedStatement.setString(2, nazivKnjige);
				preparedStatement.setString(3, opis);
				preparedStatement.setString(4, linkIzdavaca);
				preparedStatement.setBlob(5, inputStream);
				preparedStatement.setString(6, izdanje);
				preparedStatement.setString(7, autor);
				preparedStatement.setInt(8,  izdavacId);
				preparedStatement.setInt(9, kategorijeId);
				
				preparedStatement.executeUpdate();
			}
			
			
			connection.commit();
			
			logger.info("Uspesno zavrsili izmenu knjige u bazi.");
		
	}catch (SQLException e) 
		{
		// TODO: handle exception
		e.printStackTrace();
		try {
			connection.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		knjiga = null;
	} 
	finally 
	{
		// TODO: handle finally clause
		logger.debug("end of sqls");
	}
		return knjiga;
	}
	
	public static int preuzmiMaxRedovaKnjiga(){
		int maxBrRez = 0;
		try {
			connection = DBConnectionManager.getConnection();
			
			String queryMax = "SELECT COUNT(*) AS maxRes FROM knjiga;";
			preparedStatement = connection.prepareStatement(queryMax);		
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				maxBrRez = resultSet.getInt("maxRes");
			}
			}catch (SQLException e) 
		{
				e.printStackTrace();
			} finally 
			{
				//TODO
			}
			return maxBrRez;		
	}
	
	public static ArrayList<Knjiga> preuzmiKnjige(int startniRed, int brojPikazanihRedova){
		
		logger.info("Entering KnjigaDAO.preuzmiKnjige()");
		
		Knjiga knjigaTemp = null;
		ArrayList<Knjiga> listaKnjiga = new ArrayList<>();
		int maxRes = 0;
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = "SELECT SQL_CALC_FOUND_ROWS * FROM knjiga limit "+ startniRed +"," + brojPikazanihRedova;
			
			logger.info("Query: " + query);
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				knjigaTemp = new Knjiga();
				knjigaTemp.setId(resultSet.getInt("id_knjiga"));
				knjigaTemp.setNaziv(resultSet.getString("naziv"));
				knjigaTemp.setOpis(resultSet.getString("opis"));
				knjigaTemp.setLink_izdavaca(resultSet.getString("link_izdavaca"));
				knjigaTemp.setSlika_naslovne_strane((Blob) resultSet.getBlob("slika_naslovne"));
				knjigaTemp.setIzdanje(resultSet.getString("izdanje"));
				knjigaTemp.setAutor(resultSet.getString("autor"));
				knjigaTemp.setId_kategorija(resultSet.getInt("kategorija_id_kategorija"));
				knjigaTemp.setId_izdavac(resultSet.getInt("izdavac_id_izdavac"));
				
				listaKnjiga.add(knjigaTemp);
				
			}
			logger.info("Lista kategorija KnjigaDAO: " + listaKnjiga);
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaKnjiga;
	}
	
	public static ArrayList<Knjiga> preuzmiKnjige(){
		
		logger.info("Entering KnjigaDAO.preuzmiKnjige()");
		
		Knjiga knjigaTemp = null;
		ArrayList<Knjiga> listaKnjiga = new ArrayList<>();
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = "SELECT * FROM knjiga";
			
			logger.info("Query: " + query);
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				knjigaTemp = new Knjiga();
				knjigaTemp.setId(resultSet.getInt("id_knjiga"));
				knjigaTemp.setNaziv(resultSet.getString("naziv"));
				knjigaTemp.setOpis(resultSet.getString("opis"));
				knjigaTemp.setLink_izdavaca(resultSet.getString("link_izdavaca"));
				knjigaTemp.setSlika_naslovne_strane((Blob) resultSet.getBlob("slika_naslovne"));
				knjigaTemp.setIzdanje(resultSet.getString("izdanje"));
				knjigaTemp.setAutor(resultSet.getString("autor"));
				knjigaTemp.setId_kategorija(resultSet.getInt("kategorija_id_kategorija"));
				knjigaTemp.setId_izdavac(resultSet.getInt("izdavac_id_izdavac"));
				
				listaKnjiga.add(knjigaTemp);
				
			}
			logger.info("Lista kategorija KnjigaDAO: " + listaKnjiga);
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaKnjiga;
	}
	
	public static Knjiga izmeniKnjigu(int knjigaId){
		
				Knjiga knjigaTemp = new Knjiga();	
				
				try 
				{
					connection = DBConnectionManager.getConnection();
					
					connection.setAutoCommit(false);
					
					String query = "SELECT * FROM knjiga WHERE id_knjiga ='" + knjigaId + "'";			
					statement = connection.createStatement();
					resultSet = statement.executeQuery(query);
					
					while (resultSet.next()) 
					{
						knjigaTemp = new Knjiga();
						knjigaTemp.setId(resultSet.getInt("id_knjiga"));
						knjigaTemp.setNaziv(resultSet.getString("naziv"));
						knjigaTemp.setOpis(resultSet.getString("opis"));
						knjigaTemp.setLink_izdavaca(resultSet.getString("link_izdavaca"));
						knjigaTemp.setSlika_naslovne_strane((Blob) resultSet.getBlob("slika_naslovne"));
						knjigaTemp.setIzdanje(resultSet.getString("izdanje"));
						knjigaTemp.setAutor(resultSet.getString("autor"));
						knjigaTemp.setId_kategorija(resultSet.getInt("kategorija_id_kategorija"));
						knjigaTemp.setId_izdavac(resultSet.getInt("izdavac_id_izdavac"));
						
					}
					logger.info("Knjiga izmeniKnjigu KnjigaDAO: " + knjigaTemp);
				
			}catch (SQLException e) 
				{
				// TODO: handle exception
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				knjigaTemp = null;
			} 
			finally 
			{
				// TODO: handle finally clause
				logger.debug("end of sqls");
			}
				return knjigaTemp;
			}
	
	public static Knjiga obrisiKnjigu(int knjigaId) throws IOException {
		
			logger.info("Entering Korisnik obrisiKorisnika()");
			
			Knjiga knjiga = null;
			
			try {
				connection = DBConnectionManager.getConnection();
				
				String query = ("DELETE FROM knjiga WHERE id_knjiga= '" + knjigaId + "'");
				
				logger.info("Query: " + query.toString());
				
				statement = connection.createStatement();
				statement.executeUpdate(query);
				
			}catch (SQLException e) 
			{
				e.printStackTrace();
			} finally 
			{
				//TODO
			}
			return knjiga;
		}
	
	public static ArrayList<Knjiga> pretragaKnjiga(String tekstPretrage, String pretragaKnjige){ 
		
		String searchSQL = "";
		Knjiga knjigaTemp = null;
		Kategorija kategorijaTemp = new Kategorija();
		ArrayList<Knjiga> listaKnjigaPretraga = new ArrayList<>();
		ArrayList<Kategorija> listaPretrageKategorija = new ArrayList<>();
		
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = "SELECT * FROM knjiga WHERE ";
			String queryKategorija = "SELECT id_kategorija FROM kategorija WHERE ";
			
			String nazivPretraga = "naziv like '%" + tekstPretrage + "%'";
			String autorPretraga = "autor like '%" + tekstPretrage + "%'";
			
			String izdavacPretraga = "izdavac_id_izdavac like '%" + tekstPretrage + "%'";
			
			if (pretragaKnjige.equals("pretragaIzdavac")) {
				searchSQL = izdavacPretraga;
			}
			else if (pretragaKnjige.equals("pretragaNazivKnjige")) {
				searchSQL = nazivPretraga;
			}
			else if (pretragaKnjige.equals("pretragaAutor")) {
				searchSQL = autorPretraga;
			}
			else if (pretragaKnjige.equals("pretragaKategorija")) {
				String kategorijaPretragaTemp = "naziv like '%" + tekstPretrage + "%'"; // pretraga po kategorijama ne radi unosom naziva, samo unosom id_kategorija
				String kategorijaPretraga = null;
				queryKategorija += kategorijaPretragaTemp;
				
				preparedStatement = connection.prepareStatement(queryKategorija);		
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) 
				{				
				kategorijaTemp.setId(resultSet.getInt("id_kategorija"));
				kategorijaPretraga = "kategorija_id_kategorija ='" + kategorijaTemp.getId() + "'";
				logger.info("KategorijaTemp = " + kategorijaTemp.getId());
				}
				
				searchSQL = kategorijaPretraga;
			}
			
			query += searchSQL;
			
			logger.info("Query pretraga: " + query);
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				knjigaTemp = new Knjiga();
				knjigaTemp.setId(resultSet.getInt("id_knjiga"));
				knjigaTemp.setNaziv(resultSet.getString("naziv"));
				knjigaTemp.setOpis(resultSet.getString("opis"));
				knjigaTemp.setLink_izdavaca(resultSet.getString("link_izdavaca"));
				knjigaTemp.setSlika_naslovne_strane((Blob) resultSet.getBlob("slika_naslovne"));
				knjigaTemp.setIzdanje(resultSet.getString("izdanje"));
				knjigaTemp.setAutor(resultSet.getString("autor"));
				knjigaTemp.setId_kategorija(resultSet.getInt("kategorija_id_kategorija"));
				knjigaTemp.setId_izdavac(resultSet.getInt("izdavac_id_izdavac"));
				
				listaKnjigaPretraga.add(knjigaTemp);
				
			}
			preparedStatement.close();
			resultSet.close();
			
			logger.info("Lista kategorija KnjigaDAO: " + listaKnjigaPretraga);
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaKnjigaPretraga;	
	}
	
	public static ArrayList<Knjiga> sortirajKnjige(String sortiranje){
		String searchSQL = "";
		Knjiga knjigaTemp = null;
		ArrayList<Knjiga> listaSortiranihKnjiga = new ArrayList<>();
		
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = "SELECT * FROM knjiga ";
			
			String nazivSortiranje = "order by naziv ASC;";
			String autorSortiranje = "order by autor ASC;";;
			
			if (sortiranje.equals("nazivKnjige")) {
				searchSQL = nazivSortiranje;
			}
			else if (sortiranje.equals("autorKnjige")) {
				searchSQL = autorSortiranje;
			}				
			
			query += searchSQL;
			
			logger.info("Query sortiranje: " + query);
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				knjigaTemp = new Knjiga();
				knjigaTemp.setId(resultSet.getInt("id_knjiga"));
				knjigaTemp.setNaziv(resultSet.getString("naziv"));
				knjigaTemp.setOpis(resultSet.getString("opis"));
				knjigaTemp.setLink_izdavaca(resultSet.getString("link_izdavaca"));
				knjigaTemp.setSlika_naslovne_strane((Blob) resultSet.getBlob("slika_naslovne"));
				knjigaTemp.setIzdanje(resultSet.getString("izdanje"));
				knjigaTemp.setAutor(resultSet.getString("autor"));
				knjigaTemp.setId_kategorija(resultSet.getInt("kategorija_id_kategorija"));
				knjigaTemp.setId_izdavac(resultSet.getInt("izdavac_id_izdavac"));
				
				listaSortiranihKnjiga.add(knjigaTemp);
				
			}
			preparedStatement.close();
			resultSet.close();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaSortiranihKnjiga;	
		
	}
	
	public static ArrayList<Izdavac> preuzmiIzdavace(){
			
		logger.info("Entering KnjigaDAO.preuzmiIzdavace()");
		
		Izdavac izdavacTemp = null;
		ArrayList<Izdavac> listaIzdavaca = new ArrayList<>();
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = "SELECT * FROM izdavac";
						
			logger.info("Query: " + query);
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				izdavacTemp = new Izdavac();
				izdavacTemp.setId(resultSet.getInt("id_izdavac"));
				izdavacTemp.setNaziv(resultSet.getString("naziv"));
				izdavacTemp.setOpis(resultSet.getString("opis"));
				izdavacTemp.setTip_knjige(resultSet.getString("tip_knjige"));
				izdavacTemp.setAdresa_izdavaca(resultSet.getString("adresa_izdavaca"));
				izdavacTemp.setTelefon(resultSet.getString("telefon"));
				
				listaIzdavaca.add(izdavacTemp);
				
			}
			logger.info("Lista kategorija KnjigaDAO: " + listaIzdavaca);
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaIzdavaca;
	}
	
	public static Izdavac unesiIzdavaca(String ...params) {
		
		if(params.length != 5) return null;
				
				ArrayList<String> podaci = new ArrayList<>();
				for (String param : params) {
					podaci.add(param);
				}
				String nazivIzdavaca = params[0];
				String opis = params[1];
				String tipKnjige = params[2];
				String adresaIzdavaca = params[3];
				String telefon = params[4];

				
				Izdavac izdavac = new Izdavac();
				
				if(params != null){
				
				try 
				{
					connection = DBConnectionManager.getConnection();
					
					//Otvaranje transakcije
					connection.setAutoCommit(false);
					
					String query = "SELECT MAX(id_izdavac) as maxId FROM izdavac";			
					statement = connection.createStatement();
					resultSet = statement.executeQuery(query);
					
					int id = 0;
					if(resultSet.next()){
						id = resultSet.getInt("maxId");
					}

					query = "INSERT into izdavac (id_izdavac, naziv, opis, tip_knjige, adresa_izdavaca, telefon) VALUES (?, ?, ?, ?, ?, ?)";
					
					statement = connection.createStatement();
					preparedStatement = connection.prepareStatement(query);
					
					preparedStatement.setInt(1, ++id);
					preparedStatement.setString(2, nazivIzdavaca);
					preparedStatement.setString(3, opis);
					preparedStatement.setString(4, tipKnjige);
					preparedStatement.setString(5, adresaIzdavaca);
					preparedStatement.setString(6, telefon);
					
					preparedStatement.executeUpdate();
					
					connection.commit();
					//Zatvaranje transakcije
					
					logger.info("Uspesno zavrsili insert izdavaca u bazu.");
				
			}catch (SQLException e) 
				{
				// TODO: handle exception
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				izdavac = null;
			} 
			finally 
			{
				// TODO: handle finally clause
				logger.debug("end of sqls");
			}
				}
				return izdavac;
			}
	
}
