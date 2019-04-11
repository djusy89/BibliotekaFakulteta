<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="utility.*" %>
<%@ page import="vo.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main page</title>
<link rel="stylesheet" type="text/css" href="css/mainstyle.css" />
</head>
<body>

<div id="container" class="mainContainer">
	<div id="headerDiv" class="headerDiv">
		<div id="logoDiv" class="logoDiv"><a class='jwprimer-logo' href='http://localhost:8080/JavaWebPrimer/mainPage.jsp'>JavaWebPrimer<span class='dotcom'>.com</span></a></div>
		
		<div id="pretragaDiv" class="pretragaDiv">
			<form action="SearchServlet">
				Pretraga: 
				<input type="text" id="pretragaKrit" name="pretragaKrit" value="">
				<input type="submit" id="submitDugme" name="submitDugme" value="Pretrazi">
			</form>
		</div>
		<div id="clearDiv" class="clearDiv"></div>
	</div>
	<div id="navigationDiv" class="navigationDiv">
	<div id="korisnikDiv">
		<img src="images/Biblioteka.jpg" alt="Biblioteka" >&nbsp;
		<br>
		<strong>Prijavljen korisnik:</strong>
		<br>
		<%
		if(session.getAttribute(IParameter.USER_VO) != null)
		{
			//System.out.print(session.getAttribute(IParameter.USER_VO));
			//KorisnikVO korisnikVo = new KorisnikVO();
			//korisnikVo = (KorisnikVO)session.getAttribute(IParameter.USER_VO);
			//out.println(korisnikVo.getIme());
			out.println(((KorisnikVO)session.getAttribute(IParameter.USER_VO)).getKorisnikOutput());
		}	
		else{
			response.sendRedirect("login.jsp");
			//TODO: redirekcija na logovanje!!!!
		}
		%>
	<br>
	<!-- <%=session.getAttribute(IParameter.USER_VO)!=null?((KorisnikVO)session.getAttribute(IParameter.USER_VO)).getKorisnikOutput():""%> -->
	</div>
	
	<a href="LoginServlet?odjavaKorisnika=yes" id="odjavaKorisnika" name="odjavaKorisnika" >Odjava korisnika</a>	

	<h3>Meni:</h3>
	<ol class="listaGlavna">
		<li>Lista opcija
			<ul class="podListaSifra">
				<li><a href="mainPregledKniga.jsp" id="pregledknjiga" name="pregledknjiga">Pregled knjiga</a></li> <!--  -->
				<li><a href="#"  id="iznajmljivanjeknjiga" name="iznajmljivanjeknjiga">Iznajmljivanje knjiga</a></li>
				<li><a href="#"  id="nabavkaknjige" name="nabavkaknjige">Zahtev za nabavku knjige</a></li>
				<li><a href="#"  id="produzenjeroka" name="produzenjeroka">Produzenje roka vracanja knjige</a></li>
			</ul>
		</li>
	</ol>
</div>
	<div id="bodyDiv" class="bodyDiv">body</div>
	<div id="footerDiv" class="footerDiv">Copyright@rajak.rs</div>
</div>
</body>
</html>