package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import utility.DateUtility;

import org.apache.log4j.Logger;

import bean.ClanskaKarta;
import db.DBConnectionManager;

public class ClanskaKartaDAO {
	
	static Connection connection;
	static Statement statement;
	static PreparedStatement preparedStatement;
	static ResultSet resultSet ;
	
	static Logger logger = Logger.getLogger(KorisnikDAO.class.getName());
	
	public static ClanskaKarta napraviClanskuKartu (String ...params){
		logger.info("Entering ClanskaKarta napraviClanskuKartu()");
			
		if(params.length != 8) return null;
		
		ArrayList<String> podaci = new ArrayList<>();
		for (String param : params) {
			podaci.add(param);
		}
		
		String idClanskaKarta = params[0];
		String brojClanarine = params[1];
		String statusClanarine = params[2];
		String datPoslednjeUplate = params[3];
		String pocetakVazenja = params[4];
		String krajVazenja = params[5];
		String tipClanarine = params[6];
		int uclanjenKorisnikId = Integer.parseInt(params[7]);

		ClanskaKarta clanskaKarta = new ClanskaKarta();	
		
		Date date = new Date();
		String godinaStr = DateUtility.year(date);
		
		try 
		{
			connection = DBConnectionManager.getConnection();
			
			String query = "UPDATE korisnik SET status = 'aktivan' WHERE id_korisnik = '" + uclanjenKorisnikId + "'";

			statement = connection.createStatement();
			int count = statement.executeUpdate(query);
			
			//Otvaranje transakcije
			connection.setAutoCommit(false);
			
			query = "SELECT MAX(id_clanska_karta) as maxId FROM clanska_karta";			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			int id = 0;
			if(resultSet.next()){
				id = resultSet.getInt("maxId");
			}
			
			//insert osoba za gornji id lica
			query = "INSERT into clanska_karta (id_clanska_karta, broj_clanarine, status, dat_poslednje_uplate, pocetak_vazenja, kraj_vazenja, tip_clanarine, korisnik_id_korisnik) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			logger.info("query napravi clansku kartu" + query);
			
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, ++id);
			preparedStatement.setString(2, id + "/" + godinaStr ); // ubaciti godinu
			preparedStatement.setString(3, statusClanarine);
			preparedStatement.setString(4, datPoslednjeUplate);
			preparedStatement.setString(5, pocetakVazenja);
			preparedStatement.setString(6, krajVazenja);
			preparedStatement.setString(7, tipClanarine);
			preparedStatement.setInt(8, uclanjenKorisnikId);

			
			preparedStatement.executeUpdate();
			
			connection.commit();
			//Zatvaranje transakcije
			
			logger.info("Uspesno zavrsili insert clanska karta u bazu.");
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
		clanskaKarta = null;
	} 
	finally 
	{
		// TODO: handle finally clause
		logger.debug("end of sqls in clanskaKarta");
	}
		return clanskaKarta;
	}

}
