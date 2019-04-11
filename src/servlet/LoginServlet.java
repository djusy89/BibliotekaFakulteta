package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bean.Korisnik;
import dao.SifarnikController;
import utility.IConstant;
import utility.IParameter;
import utility.IResource;
import utility.StringUtility;
import vo.KorisnikVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static Logger logger = Logger.getLogger(LoginServlet.class.getName());
	
	HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		session = request.getSession(false);

		if(request.getParameter("odjavaKorisnika") != null)
		{
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		logger.info("Entering doPost() method.");
		
		session = request.getSession(true);
		
		if(request.getParameter(IParameter.TIP_LOGINA_PARAM) != null && ((String)request.getParameter(IParameter.TIP_LOGINA_PARAM)).equals(IConstant.MOD_PREGLED) )
		{
			//Logovanje korisnika
			prijavaKorisnika(request, response);
			
		}
//		else if(request.getParameter(IParameter.TIP_LOGINA_PARAM) != null && ((String)request.getParameter(IParameter.TIP_LOGINA_PARAM)).equals(IConstant.MOD_UNOS) )
//		{
//			//Unos novog korisnika
//			unosKorisnika(request, response);
//		}
		else if(request.getParameter(IParameter.TIP_LOGINA_PARAM) != null && ((String)request.getParameter(IParameter.TIP_LOGINA_PARAM)).equals(IConstant.MOD_PRIJAVA) )
		{
			//Unos novog korisnika
			prijavaNovogKorisnika(request, response);
		}
		else{
			//nemamo tipLogina u requestu
			//session.setAttribute("infoKorisniku", IConstant.ERR_LOGIN);
			response.sendRedirect("login.jsp");
		}
		
	}
	private void prijavaKorisnika(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String korisnickoIme = request.getParameter(IParameter.USER_NAME_PARAM);
		String lozinka = request.getParameter(IParameter.PASSWORD_PARAM);
		
		logger.info("Pokusaj prijave korisnika: " + korisnickoIme + ", " + lozinka);
		
		session = request.getSession(true);
		
		//TODO: validacija ulaznih podataka
		if(StringUtility.isEmptyOrNull(korisnickoIme) 
				|| StringUtility.isEmptyOrNull(lozinka))
		{
			logger.error("Validacija je failed.");
			
			session.setAttribute(IParameter.USER_VALIDATION_ERROR, IResource.USER_VALIDATION_ERROR_INFO);
			response.sendRedirect("login.jsp");
		} 
		else 
		{
			logger.info("Validacija je success.");
			//TODO: provera kroz bazu da li postoji taj logovan korisnik...
			boolean jesteUBazi = SifarnikController.proveriLogovanjeKorisnika(korisnickoIme, lozinka);
			
			if(jesteUBazi)
			{
				logger.info("Jeste korisnik je u bazi.");
				//TODO: punjenje VO sa podacima o korisniku, smestanje u session object, 
				//redirekcija na main page
				//TODO: tip logovanog korisnika, da se odredi gde ide redirekcija... admin...
				Korisnik korisnik = SifarnikController.preuzmiKorisnikaIzBaze(korisnickoIme, lozinka);
				
				if(korisnik != null)
				{
					//TODO: kako iz korisnik objekta pokupiti sled. podatke
					KorisnikVO korisnikVO = new KorisnikVO(korisnik.getId(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getTip_korisnika());
					//napuniti sa metodom sa delimiterima za ispis podataka o ulogovanom korisniku:
					korisnikVO.setKorisnikOutput();
					if (korisnik.getTip_korisnika().equalsIgnoreCase("obican korisnik")) {
						session.setAttribute(IParameter.USER_VO, korisnikVO);
						response.sendRedirect("main.jsp");
					}
					if (korisnik.getTip_korisnika().equalsIgnoreCase("administrator")) {
						session.setAttribute(IParameter.USER_VO, korisnikVO);
						response.sendRedirect("mainAdmin.jsp");
					}
					if (korisnik.getTip_korisnika().equalsIgnoreCase("bibliotekar")) {
						session.setAttribute(IParameter.USER_VO, korisnikVO);
						response.sendRedirect("mainBibliotekar.jsp");
					}
					
				} 
				else
				{
					session.setAttribute(IParameter.USER_LOGIN_ERROR, IResource.USER_LOGIN_DUPLICATE_ERROR_INFO);
					response.sendRedirect("login.jsp");
				}
			}
			else
			{
				//TODO: redirekcija na login page, sa info korisniku
				logger.error("Korisnik nije u bazi!");
				
				session.setAttribute(IParameter.USER_LOGIN_ERROR, IResource.USER_LOGIN_ERROR_INFO);
				response.sendRedirect("login.jsp");
			}
		}		
	}

	private void prijavaNovogKorisnika(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String imeKorisnika = request.getParameter(IParameter.FIST_NAME_PARAM);
		String prezimeKorisnika = request.getParameter(IParameter.LAST_NAME_PARAM);		
		String telefon = request.getParameter(IParameter.TELEFON_PARAM);
		String email = request.getParameter(IParameter.EMAIL_PARAM);	
		String korisnickoIme = request.getParameter(IParameter.USER_NAME_PARAM);
		String lozinka = request.getParameter(IParameter.PASSWORD_PARAM);
		String lozinka1 = request.getParameter(IParameter.PASSWORD_PARAM1);
		String tipKorisnika = request.getParameter(IParameter.TIP_KORISNIKA_PARAM);	
		String status = IParameter.NEAKTIVAN_KORISNIK;
		
		if(StringUtility.isEmptyOrNull(lozinka) || StringUtility.isEmptyOrNull(lozinka1) || !lozinka.equals(lozinka1)){
			//return; //TODO
			session.setAttribute(IParameter.USER_LOGIN_ERROR, IResource.USER_LOGIN_INSERT_ERROR_INFO);
			response.sendRedirect("loginRequestAdd.jsp");
		} else{
			//TODO ostale validacije
			Korisnik novKorisnik = SifarnikController.zahtevZaUnosKorisnika(imeKorisnika, prezimeKorisnika, telefon, email,  korisnickoIme, lozinka, tipKorisnika, status);
			
			if(novKorisnik == null){
				//return; //TODO
				session.setAttribute(IParameter.USER_LOGIN_ERROR, IResource.USER_LOGIN_INSERT_ERROR_INFO);
				response.sendRedirect("loginRequestAdd.jsp");
			}
			else{
				//session.setAttribute(IParameter.USER_VO, korisnikVO);
				session.setAttribute(IParameter.USER_LOGIN_SUCCESS, IResource.USER_LOGIN_INSERT_SUCCESS_INFO);
				response.sendRedirect("obavestenjeKorisniku.html");
			}
			}
		}
	}


