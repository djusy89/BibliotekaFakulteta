package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import bean.Korisnik;
import db.DBConnectionManager;

public class KorisnikDAO {

	static Connection connection;
	static Statement statement;
	static PreparedStatement preparedStatement;
	static ResultSet resultSet ;
	
	static Logger logger = Logger.getLogger(KorisnikDAO.class.getName());

	/**
	 * Unos novog korisnika
	 */

	public static Korisnik dodajNovogKorisnika(String ...params){
		
		if(params.length != 8) return null;
		
		ArrayList<String> podaci = new ArrayList<>();
		for (String param : params) {
			podaci.add(param);
		}
		String ime = params[0];
		String prezime = params[1];
		String telefon = params[2];
		String email = params[3];
		String korisnickoIme = params[4];
		String lozinka = params[5];
		String tipKorisnika = params[6];
		String status = params[7];
		
		Korisnik korisnik = new Korisnik();	
		
		try 
		{
			connection = DBConnectionManager.getConnection();
			
			//Otvaranje transakcije
			connection.setAutoCommit(false);
			
			String query = "SELECT MAX(id_korisnik) as maxId FROM korisnik";			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			int id = 0;
			if(resultSet.next()){
				id = resultSet.getInt("maxId");
			}

			query = "INSERT into korisnik (id_korisnik, korisnicko_ime, lozinka, ime, prezime, tip_korisnika, telefon, e_mail, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, ++id);
			preparedStatement.setString(2, korisnickoIme);
			preparedStatement.setString(3, lozinka);
			preparedStatement.setString(4, ime);
			preparedStatement.setString(5, prezime);
			preparedStatement.setString(6, tipKorisnika);
			preparedStatement.setString(7, telefon);
			preparedStatement.setString(8, email);
			preparedStatement.setString(9, status);
			
			preparedStatement.executeUpdate();
			
			connection.commit();
			//Zatvaranje transakcije
			
			logger.info("Uspesno zavrsili insert korisnika u bazu.");
		
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
		korisnik = null;
	} 
	finally 
	{
		// TODO: handle finally clause
		logger.debug("end of sqls");
	}
		return korisnik;
	}
	
	public static ArrayList<Korisnik> preuzmiKorisnikeNaCekanju()
	{
		logger.info("Entering TipKorisnikaDAO.preuzmiKorisnikeNaCekanju()");
		
		Korisnik korisnik = null;
		ArrayList<Korisnik> listaKorisnika = new ArrayList<>();
		try {
			connection = DBConnectionManager.getConnection();
			String query = "";
			
			StringBuffer tempQuery = new StringBuffer();
			
			tempQuery.append("SELECT id_korisnik, korisnicko_ime, lozinka, ime, prezime, tip_korisnika, telefon, e_mail, status FROM korisnik WHERE status = 'neaktivan' ");
			
			logger.info("Query: " + tempQuery.toString());
			
			query = tempQuery.toString();
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				korisnik = new Korisnik();
				korisnik.setId(resultSet.getInt("id_korisnik"));
				korisnik.setKorisnicko_ime(resultSet.getString("korisnicko_ime"));
				korisnik.setLozinka(resultSet.getString("lozinka"));
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setTip_korisnika(resultSet.getString("tip_korisnika"));
				korisnik.setTelefon(resultSet.getString("telefon"));
				korisnik.setE_mail(resultSet.getString("e_mail"));
				korisnik.setStatus(resultSet.getString("status"));
				
				
				listaKorisnika.add(korisnik);
			}
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaKorisnika;
	}
	public static ArrayList<Korisnik> preuzmiAktivneKorisnike()
	{
		logger.info("Entering TipKorisnikaDAO.preuzmiAktivneKorisnike()");
		
		Korisnik korisnik = null;
		ArrayList<Korisnik> listaKorisnika = new ArrayList<>();
		try {
			connection = DBConnectionManager.getConnection();
			String query = "";
			
			StringBuffer tempQuery = new StringBuffer();
			
			tempQuery.append("SELECT id_korisnik, korisnicko_ime, lozinka, ime, prezime, tip_korisnika, telefon, e_mail, status FROM korisnik WHERE status = 'aktivan' ");
			
			logger.info("Query: " + tempQuery.toString());
			
			query = tempQuery.toString();
			
			preparedStatement = connection.prepareStatement(query);		
			resultSet = preparedStatement.executeQuery();
			
			int count = 0;
			
			while (resultSet.next()) 
			{
				count++;
				korisnik = new Korisnik();
				korisnik.setId(resultSet.getInt("id_korisnik"));
				korisnik.setKorisnicko_ime(resultSet.getString("korisnicko_ime"));
				korisnik.setLozinka(resultSet.getString("lozinka"));
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setTip_korisnika(resultSet.getString("tip_korisnika"));
				korisnik.setTelefon(resultSet.getString("telefon"));
				korisnik.setE_mail(resultSet.getString("e_mail"));
				korisnik.setStatus(resultSet.getString("status"));
				
				
				listaKorisnika.add(korisnik);
			}
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return listaKorisnika;
	}
	
	public static Korisnik uclaniKorisnika (int id)
	{
		logger.info("Entering Korisnik uclaniKorisnika()");
		
		Korisnik korisnik = null;
		
		try {
			connection = DBConnectionManager.getConnection();
			
			String tempQuery = ("SELECT id_korisnik, korisnicko_ime, lozinka, ime, prezime, tip_korisnika, telefon, e_mail, status FROM korisnik WHERE id_korisnik = '" + id + "'");
			
			logger.info("Query: " + tempQuery.toString());
			
			statement = connection.createStatement();		
			resultSet = preparedStatement.executeQuery(tempQuery);
			
			
			while (resultSet.next()) 
			{
				korisnik = new Korisnik();
				korisnik.setId(resultSet.getInt("id_korisnik"));
				korisnik.setKorisnicko_ime(resultSet.getString("korisnicko_ime"));
				korisnik.setLozinka(resultSet.getString("lozinka"));
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setTip_korisnika(resultSet.getString("tip_korisnika"));
				korisnik.setTelefon(resultSet.getString("telefon"));
				korisnik.setE_mail(resultSet.getString("e_mail"));
				korisnik.setStatus(resultSet.getString("status"));
			}
			
			logger.info("Prosledjuje se korisnik za pravljenje clanske karte");
			
			
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
		{
			//TODO
		}
		return korisnik;
	}
	
	public static Korisnik obrisiKorisnika (int id)
	{
		logger.info("Entering Korisnik obrisiKorisnika()");
		
		Korisnik korisnik = null;
		
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = ("DELETE FROM clanska_karta WHERE korisnik_id_korisnik= '" + id + "'");
			
			logger.info("Query: " + query.toString());
			
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
			query = ("DELETE FROM korisnik WHERE id_korisnik= '" + id + "'");
			
			
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
		return korisnik;
	}
	
}
