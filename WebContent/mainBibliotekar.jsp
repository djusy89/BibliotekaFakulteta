<!DOCTYPE html>

<%@ page import="utility.*" %>
<%@ page import="vo.*" %>
<%@ page import="java.util.*" %>

<html>
<head>
<meta charset="${encoding}">
<title>Main page</title>
</head>
<body>
${cursor}
<div id="container" class="mainContainer" style="width:1000px">
	<div id="headerDiv" class="headerDiv" align="center">
		<img src="images/Biblioteka.jpg" alt="Biblioteka" align="middle" >&nbsp;
	</div>
	<div id="navigationDiv" class="navigationDiv">
	<div id="korisnikDiv">
		
		<br>
		<strong>Prijavljen korisnik:</strong>
		<br>
		<%
		if(session.getAttribute(IParameter.USER_VO) != null)
		{
			out.println(((KorisnikVO)session.getAttribute(IParameter.USER_VO)).getKorisnikOutput());
		}	
		else{
			response.sendRedirect("login.jsp");
		}
		%>
	<br>

	</div>
	
	<a href="LoginServlet?odjavaKorisnika=yes" id="odjavaKorisnika">Odjava korisnika</a>	

	<h3>Meni:</h3>
	<ol class="listaGlavna">
		<li>Lista opcija
			<ul class="podListaSifra">
				<li><a href="KorisnikServlet?akcija=uclaniKorisnika" id="uclaniKorisnika">Uclani novog korisnika</a></li>
				<li><a href="KorisnikServlet?akcija=preuzmiKorisnike" id="preuzmiKorisnike">Uclanjeni korisnici</a></li>
				<li><a href="KnjigaServlet?akcija=unesiKnjigu"  id="unesiKnjigu">Unesi knjigu</a></li>
				<li><a href="KnjigaServlet?akcija=preuzmiKategorije"  id="preuzmiKategorije" >Pregled i izmene kategorija</a></li>
				<li><a href="KnjigaServlet?akcija=preuzmiIzdavace"  id="preuzmiIzdavace" >Pregled i izmene izdavaca</a></li>
				<li><a href="KnjigaServlet?akcija=pregledKnjiga&startniRed=0"  id="pregledKnjiga">Pregled i izmene knjiga</a></li>
				<li><a href="#"  id="izdajknjigu">Izdaj knjigu</a></li>
				<li><a href="#"  id="nalogzaostecenuknjigu">Nalog za ostecenu knjigu</a></li>
				<li><a href="#"  id="zaduzenjastudenata">Zaduzenja knjiga korisnika</a></li>
				<li><a href="#"  id="opomenazaknjigu">Slanje opomene za zaduzenu knjigu</a></li>
				<li><a href="#"  id="trebovanjezaprimanje">Trebovanje i zaprimanje knjiga</a></li>
				<li><a href="#"  id="produzenjeroka">Produzenje roka vracanja knjige</a></li>
			</ul>
		</li>
	</ol>
</div>
	<div id="bodyDiv" class="bodyDiv">body</div>
	<div id="footerDiv" class="footerDiv">Copyright@marko.rs</div>
</div>
</body>
</html>