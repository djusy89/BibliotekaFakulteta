package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import bean.Korisnik;
import db.DBConnectionManager;

public class LoginDAO {
	
	static Connection connection;
	static Statement statement;
	static PreparedStatement preparedStatement;
	static ResultSet resultSet ;
	
	static Logger logger = Logger.getLogger(KorisnikDAO.class.getName());

	public static boolean getByUserNamePassword(String user, String password)
	{
		//logger
		boolean isIn = false;
		//TODO: select into korisnik tbl
		
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = 
					"SELECT * FROM korisnik WHERE korisnicko_ime = '" + user.trim() + "' and lozinka= '" + password.toLowerCase() + "'";
			
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				isIn = true;
			}			
		}catch (SQLException e) 
		{
			// TODO: handle exception
		} finally 
		{
			//TODO
		}
		return isIn;
	}
	
	public static Korisnik getAllByUserNamePassword(String userName, String password)
	{

		Korisnik korisnik = null;
		try {
			connection = DBConnectionManager.getConnection();
			
			String query = 
					"SELECT * FROM korisnik WHERE korisnicko_ime = '" + userName.trim() + "' and lozinka= '" + password.toLowerCase() + "'";
			

			System.out.println("query: " + query);
			
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
				
				korisnik.setLozinka(
						resultSet.getString("lozinka") != null ? (String)resultSet.getString("lozinka") : "");
				
				korisnik.setIme(resultSet.getString("ime"));
				korisnik.setPrezime(resultSet.getString("prezime"));
				korisnik.setTip_korisnika(resultSet.getString("tip_korisnika"));
				korisnik.setTelefon(resultSet.getString("telefon"));
				korisnik.setE_mail(resultSet.getString("e_mail"));
				korisnik.setStatus(resultSet.getString("status"));
			}	
			
			if(count>1) return null; //error, ne mozemo da imamo vise korisnika sa istim login-om!
			
		}catch (SQLException e) 
		{
			// TODO: handle exception
		} finally 
		{
			//TODO
		}
		return korisnik;
	}
}
