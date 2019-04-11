package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bean.ClanskaKarta;
import bean.Korisnik;
import dao.SifarnikController;
import utility.IParameter;

/**
 * Servlet implementation class KorisnikServlet
 */
@WebServlet("/KorisnikServlet")
public class KorisnikServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(KorisnikServlet.class.getName());	
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KorisnikServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		logger.info("Entering in doGet method");
		String akcija = request.getParameter("akcija");
		logger.info("Entering in doGet method akcija = " + akcija);
		String korisnikId = request.getParameter("korisnikId");
				
		if(akcija.equalsIgnoreCase("uclani")){ // pokrenuti akciju na dugme
			Korisnik korisnik = SifarnikController.uclaniKorisnika(Integer.parseInt(korisnikId));
			session.setAttribute(IParameter.CLANSKA_KARTA, korisnik);
			response.sendRedirect("uclanjenjeKorisnika.jsp");	
		}
		if(akcija.equalsIgnoreCase("brisanje")){ // pokrenuti akciju na dugme
			Korisnik korisnik = SifarnikController.obrisiKorisnika(Integer.parseInt(korisnikId));
			ArrayList<Korisnik> listaAktivnihKorisnika = SifarnikController.preuzmiAktivneKorisnike();
			session.setAttribute(IParameter.LISTA_AKTIVNIH_KORISNIKA, listaAktivnihKorisnika);
			response.sendRedirect("uclanjeniKorisnici.jsp");
			
		}
		if(akcija.equalsIgnoreCase("uclaniKorisnika")){
			ArrayList<Korisnik> listaKorisnika = SifarnikController.preuzmiKorisnikeNaCekanju();
			
			logger.info("Vratili listu korisnika: " + listaKorisnika);

			session.setAttribute(IParameter.LISTA_KORISNIKA_NA_CEKANJU, listaKorisnika);
			response.sendRedirect("uclanjenjeKorisnika.jsp");
			
		}
		if(akcija.equalsIgnoreCase("preuzmiKorisnike")){
			ArrayList<Korisnik> listaAktivnihKorisnika = SifarnikController.preuzmiAktivneKorisnike();
			
			logger.info("Vratili listu aktivnih korisnika: " + listaAktivnihKorisnika);

			session.setAttribute(IParameter.LISTA_AKTIVNIH_KORISNIKA, listaAktivnihKorisnika);
			response.sendRedirect("uclanjeniKorisnici.jsp");			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		doGet(request, response);
		session = request.getSession(true);

		if (request.getParameter("clanskaKarta") != null) {
			
			String idClanskaKarta = request.getParameter(IParameter.CLANSKA_KARTA);
			String brojClanarine = request.getParameter(IParameter.BROJ_CLANARINE);
			String statusClanarine = request.getParameter(IParameter.STATUS_CLANARINE);
			String datPoslednjeUplate = request.getParameter(IParameter.DAT_POS_UPLATE);
			String pocetakVazenja = request.getParameter(IParameter.POCETAK_VAZENJA);
			String krajVazenja = request.getParameter(IParameter.KRAJ_VAZENJA);
			String tipClanarine = request.getParameter(IParameter.TIP_CLANARINE);
			String uclanjenKorisnikId = request.getParameter("uclanjenKorisnikId".toString());
			
			ClanskaKarta clanskaKarta = SifarnikController.napraviClanskuKartu(idClanskaKarta, brojClanarine, statusClanarine, datPoslednjeUplate,pocetakVazenja,krajVazenja, tipClanarine, uclanjenKorisnikId);
			logger.info("After SifarnikController.napraviClanskuKartu() - uclanjen korisnik id = " + uclanjenKorisnikId +" clanska karta: " + clanskaKarta);
			//session.setAttribute(IParameter.AKTIVNA_CLANSKA_KARTA, clanskaKarta);
			response.sendRedirect("uclanjenjeKorisnika.jsp");
		}
		
	}
}
