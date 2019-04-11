package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import bean.Izdavac;
import bean.Kategorija;
import bean.Knjiga;
import dao.KnjigaDAO;
import dao.SifarnikController;
import utility.IParameter;
import utility.IResource;
import utility.StringUtility;

/**
 * Servlet implementation class KnjigaServlet
 */
@WebServlet("/KnjigaServlet")
@MultipartConfig(maxFileSize = 16177215) 
public class KnjigaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		static Logger logger = Logger.getLogger(KnjigaServlet.class.getName());
		
		HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KnjigaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		session = request.getSession();
		logger.info("Entering in doGet method KnjigaServlet");
		String akcija = request.getParameter("akcija");
	
		ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
		ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
		ArrayList<Knjiga> listaKnjiga = SifarnikController.preuzmiKnjige();
		// akcije za upravljenje sa Objektima tipa Knjiga
		
		if(akcija.equalsIgnoreCase("unesiKnjigu")){
			logger.info("Vratili listu kategorija: " + listaKategorija);
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);

			response.sendRedirect("unesiKnjigu.jsp");
			logger.info("Response : " + response);
		}
		
		if(akcija.equalsIgnoreCase("pregledKnjiga")){
			String startString = request.getParameter("startniRed");
			int startniRed = Integer.parseInt(startString);
			int brojPikazanihRedova = 5;
			int maxBrRez = KnjigaDAO.preuzmiMaxRedovaKnjiga();
			int ukupanBrStranica=((int)(Math.ceil((double)maxBrRez/brojPikazanihRedova)));
			int startniRedTemp= 0;
			if(startniRed==0)
	    	{
			startniRedTemp=0;
	    	}else{
	    		startniRedTemp=Math.abs((startniRed-1)*brojPikazanihRedova);
	    	}
			ArrayList<Knjiga> listaKnjigaPag = SifarnikController.preuzmiKnjige(startniRedTemp, brojPikazanihRedova);
			
			logger.info("maxBrRez: " + maxBrRez + "startniRed:" + startniRed);
			
			session.setAttribute(IParameter.LISTA_KNJIGA, listaKnjigaPag);
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);
			session.setAttribute("startniRed", startniRedTemp);
			session.setAttribute("maxBrojRedova", maxBrRez);
			session.setAttribute("ukupanBrStranica", ukupanBrStranica);
			response.sendRedirect("pregledKnjiga.jsp");
			logger.info("Response : " + response);
		}
		
		if(akcija.equalsIgnoreCase("izmeniKnjigu")){
			int knjigaId = Integer.parseInt(request.getParameter("knjigaId"));
			Knjiga knjiga = SifarnikController.izmeniKnjigu(knjigaId);
			
			logger.info("Vratili listu knjiga: " + listaKnjiga);
			session.setAttribute(IParameter.IZMENI_KNJIGA, knjiga);
			session.setAttribute(IParameter.LISTA_KNJIGA, listaKnjiga);
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);

			response.sendRedirect("pregledKnjiga.jsp");
			logger.info("Response : " + response);
		}
		if(akcija.equalsIgnoreCase("obrisiKnjigu")){
			int knjigaId = Integer.parseInt(request.getParameter("knjigaId"));
			Knjiga knjiga = SifarnikController.obrisiKnjigu(knjigaId);
			
			session.setAttribute(IParameter.LISTA_KNJIGA, listaKnjiga);
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);

			response.sendRedirect("pregledKnjiga.jsp");
			logger.info("Response : " + response);
		}
		
		if(akcija.equalsIgnoreCase("sortirajKnjigu")){
			String sortiranje = request.getParameter("sortiranje");
			ArrayList<Knjiga> listaSortiranihKnjiga = new ArrayList<>();
			if(sortiranje.equalsIgnoreCase("nazivKnjige") ){
				listaSortiranihKnjiga = SifarnikController.sortirajKnjige(sortiranje);
			}else if (sortiranje.equalsIgnoreCase("autorKnjige")) {
				listaSortiranihKnjiga = SifarnikController.sortirajKnjige(sortiranje);
			}
			
			session.setAttribute(IParameter.LISTA_KNJIGA, listaSortiranihKnjiga);
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);
			
			response.sendRedirect("pregledKnjiga.jsp");
			logger.info("Response : " + response);
		}
		
		// akcije za upravljenje sa Objektima tipa Kategorija
		
		if(akcija.equalsIgnoreCase("preuzmiKategorije")){
			
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			response.sendRedirect("pregledKategorija.jsp");
			logger.info("Response : " + response);
		}
		if(akcija.equalsIgnoreCase("izmeniKategoriju")){
			int kategorijaId = Integer.parseInt(request.getParameter("kategorijaId"));
			Kategorija kategorija = SifarnikController.izmeniKategoriju(kategorijaId);
			
			session.setAttribute(IParameter.IZMENI_KATEGORIJU, kategorija);
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			response.sendRedirect("pregledKategorija.jsp");
			logger.info("Response : " + response);
		}
		if(akcija.equalsIgnoreCase("obrisiKategoriju")){
			int kategorijaId = Integer.parseInt(request.getParameter("kategorijaId"));
			Kategorija kategorija = SifarnikController.obrisiKategoriju(kategorijaId);
			
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);

			response.sendRedirect("pregledKategorija.jsp");
			logger.info("Response : " + response);
		}
		// akcije za upravljenje sa Objektima tipa Izdavac
		
		if(akcija.equalsIgnoreCase("preuzmiIzdavace")){			
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);
			response.sendRedirect("pregledIzdavaca.jsp");
			logger.info("Response : " + response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		logger.info("Entering doPost() method KnjigaServlet.");
		
		session = request.getSession();
		
		// Kategorija:
		
		if(request.getParameter(IParameter.KATEGORIJA_PARAM) != null)
		{
			String kategorija = request.getParameter(IParameter.KATEGORIJA_PARAM);
			ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			logger.info("Lista kategorija koja se prosledjuje dodajKategoriju" + listaKategorija );
			response.sendRedirect("dodajKategoriju.jsp");
		}
			
		if(request.getParameter(IParameter.KATEGORIJA_UNOS_PARAM) != null)
		{
			String nazivKategorije = request.getParameter(IParameter.NAZIV_KATEGORIJE);
			String opisKategorije = request.getParameter(IParameter.OPIS_KATEGORIJE);
			
			if (StringUtility.isEmptyOrNull(nazivKategorije) || StringUtility.isEmptyOrNull(opisKategorije)){
				ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
				session.setAttribute(IParameter.KATEGORIJA_UNOS_ERROR, IResource.USER_LOGIN_INSERT_ERROR_INFO);
				session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
				response.sendRedirect("dodajKategoriju.jsp");
			}else {
				logger.info("Vrednosti naziva i opisa kategorije" + nazivKategorije + ", " + opisKategorije);
				Kategorija kategorijaTemp = SifarnikController.napraviKategoriju(nazivKategorije, opisKategorije);
				if (kategorijaTemp != null) {
					ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
					ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
					session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
					session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);
					response.sendRedirect("unesiKnjigu.jsp");
					
					logger.info("Vratili listu kategorija: " + listaKategorija);
				}else {
					ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
					session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
					session.setAttribute(IParameter.KATEGORIJA_DUPLICATE_ERROR, IResource.KATEGORIJA_DUPLICATE_ERROR_INFO);
					response.sendRedirect("dodajKategoriju.jsp");
				}				
			}			
		}
		
		if(request.getParameter(IParameter.SACUVAJ_IZMENU_KATEGORIJE) != null){
			// TODO: IZMENA KATEGORIJE
			
		}
		
		if(request.getParameter(IParameter.PRETRAGA_KATEGORIJA) != null){
			String tekstPretrage = request.getParameter("textPretrage");
			ArrayList<Kategorija> listaKategorija = SifarnikController.pretragaKategorije(tekstPretrage);
			
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			response.sendRedirect("pregledKategorija.jsp");
			logger.info("Response : " + response);
		}
		
		// Knjiga:
		
		if(request.getParameter(IParameter.KNJIGA_STRANA) != null){
			ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
			ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);

			response.sendRedirect("unesiKnjigu.jsp");			
		}
		
		if(request.getParameter(IParameter.UNESI_KNJIGU) != null)
		{
			logger.info("Entering doPost() method UNESI_KNJIGU");
			String nazivKnjige = request.getParameter(IParameter.NAZIV_KNJIGE);
			String opis = request.getParameter(IParameter.OPIS_KNJIGE);
			String linkIzdavaca = request.getParameter(IParameter.LINK_IZDAVACA);
			Part slikaNaslovne = request.getPart(IParameter.SLIKA_NASLOVNE);
			String izdanje = request.getParameter(IParameter.IZDANJE_KNJIGE);
			String autor = request.getParameter(IParameter.AUTOR_KNJIGE);
			String izdavac = request.getParameter(IParameter.IZDAVAC_KNJIGE);
			String kategorije = request.getParameter(IParameter.KATEGORIJA_KNJIGE);
			
			if (StringUtility.isEmptyOrNull(nazivKnjige) || StringUtility.isEmptyOrNull(opis) || 
					StringUtility.isEmptyOrNull(izdanje) || StringUtility.isEmptyOrNull(autor) || StringUtility.isEmptyOrNull(izdavac) || StringUtility.isEmptyOrNull(kategorije)){
				session.setAttribute(IParameter.KNJIGA_UNOS_ERROR, IResource.USER_LOGIN_INSERT_ERROR_INFO);
				ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
				ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
				session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
				session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);
				response.sendRedirect("unesiKnjigu.jsp");
			}else {
				Knjiga knjiga = SifarnikController.unesiKnjigu(nazivKnjige, opis, linkIzdavaca, slikaNaslovne, izdanje, autor, izdavac, kategorije);
				ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
				ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
				session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
				session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);
				logger.info("unesiKnjigu() - " + knjiga );
				response.sendRedirect("unesiKnjigu.jsp");
			}
		}
		
		if(request.getParameter(IParameter.SACUVAJ_IZMENU_KNJIGE) != null)
		{
			logger.info("Entering doPost() method SACUVAJ_IZMENU_KNJIGE");
			int knjigaId = Integer.parseInt(request.getParameter(IParameter.ID_KNJIGE));
			String nazivKnjige = request.getParameter(IParameter.NAZIV_KNJIGE);
			String opis = request.getParameter(IParameter.OPIS_KNJIGE);
			String linkIzdavaca = request.getParameter(IParameter.LINK_IZDAVACA);
			Part slikaNaslovne = request.getPart(IParameter.SLIKA_NASLOVNE);
			String izdanje = request.getParameter(IParameter.IZDANJE_KNJIGE);
			String autor = request.getParameter(IParameter.AUTOR_KNJIGE);
			String izdavac = request.getParameter(IParameter.IZDAVAC_KNJIGE);
			String kategorije = request.getParameter(IParameter.KATEGORIJA_KNJIGE);
			
			Knjiga knjiga = SifarnikController.izmeniKnjigu(knjigaId, nazivKnjige, opis, linkIzdavaca, slikaNaslovne, izdanje, autor, izdavac, kategorije);
			ArrayList<Knjiga> listaKnjiga = SifarnikController.preuzmiKnjige();
			ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
			ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
			session.setAttribute(IParameter.LISTA_KNJIGA, listaKnjiga);
			session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);
			response.sendRedirect("pregledKnjiga.jsp");
		}
		
		// Izdavac:
		
		if(request.getParameter(IParameter.IZDAVAC_PARAM) != null)
		{
			ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
			session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);
			logger.info("Lista kategorija koja se prosledjuje dodajKategoriju" + listaIzdavaca );
			response.sendRedirect("dodajIzdavaca.jsp");
		}
		
		if(request.getParameter(IParameter.IZDAVAC_UNOS_PARAM) != null) // napraviti preusmerenje na dodajKategoriju.jsp i poslati atribut listuKategorija
		{
			String nazivIzdavaca = request.getParameter(IParameter.NAZIV_IZDAVACA);
			String opisIzdavaca = request.getParameter(IParameter.OPIS_IZDAVACA);
			String tipKnjige = request.getParameter(IParameter.TIP_KNJIGE_IZDAVACA);
			String adresaIzdavaca = request.getParameter(IParameter.ADRESA_IZDAVACA);
			String telefonIzdavaca = request.getParameter(IParameter.TELEFON_IZDAVACA);
			
			if (StringUtility.isEmptyOrNull(nazivIzdavaca) || StringUtility.isEmptyOrNull(opisIzdavaca) || 
					StringUtility.isEmptyOrNull(tipKnjige) || StringUtility.isEmptyOrNull(adresaIzdavaca) || StringUtility.isEmptyOrNull(telefonIzdavaca)){
				
				ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
				session.setAttribute(IParameter.LINK_IZDAVACA, listaIzdavaca);
				session.setAttribute(IParameter.IZDAVAC_UNOS_ERROR, IResource.USER_LOGIN_INSERT_ERROR_INFO);

				response.sendRedirect("dodajIzdavaca.jsp");
			}else {
				Izdavac izdavacTemp = SifarnikController.unesiIzdavaca(nazivIzdavaca, opisIzdavaca, tipKnjige, adresaIzdavaca, telefonIzdavaca);
				ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
				session.setAttribute(IParameter.LINK_IZDAVACA, listaIzdavaca);
				response.sendRedirect("unesiKnjigu.jsp");
				
				logger.info("Vratili listu kategorija: " + listaIzdavaca);
			}			
		}		// TO DO: uraditi funkcionalnosti brisanje i izmena
		if(request.getParameter(IParameter.TEKST_PRETRAGE) != null){
			String tekstPretrage = request.getParameter(IParameter.TEKST_PRETRAGE);
			String pretragaKnjige = request.getParameter("pretragaKnjige");
			
			logger.info("Tekst pretrage: " + tekstPretrage + " select: " + pretragaKnjige);
			
			if(!StringUtility.isEmptyOrNull(tekstPretrage)){
				ArrayList<Knjiga> listaKnjigaPretraga =SifarnikController.pretragaKnjiga(tekstPretrage, pretragaKnjige);				
				ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
				ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
				
				session.setAttribute(IParameter.LISTA_KNJIGA, listaKnjigaPretraga);
				session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
				session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);

				response.sendRedirect("pregledKnjiga.jsp");
			}else {
				ArrayList<Knjiga> listaKnjiga = SifarnikController.preuzmiKnjige();
				ArrayList<Kategorija> listaKategorija = SifarnikController.preuzmiKategorije();
				ArrayList<Izdavac> listaIzdavaca = SifarnikController.preuzmiIzdavace();
				
				session.setAttribute(IParameter.LISTA_KNJIGA, listaKnjiga);
				session.setAttribute(IParameter.LISTA_KATEGORIJA, listaKategorija);
				session.setAttribute(IParameter.LISTA_IZDAVACA, listaIzdavaca);

				response.sendRedirect("pregledKnjiga.jsp");
			}
			
		}
	}
}
