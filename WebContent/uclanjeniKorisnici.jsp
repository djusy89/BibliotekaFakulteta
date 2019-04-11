<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="utility.*" %>
<%@ page import="vo.*" %>
<%@ page import="bean.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="bodyDiv" class="bodyDiv">
	<div id="infoKorisniku"> Pretraga aktivnih korisnika</div>
	
	<div id="formSifDiv" class="formSifDiv">

	<form action="KorisnikServlet" method="post" class="formDiv">
		<table>
			
			<tr>
				<td><input type="text" id="textPretrage" name="textPretrage" value=""></td>
				<td><input type="submit" id="pretragaKorisnika" name="pretragaKorisnika" value="&#8981 Pretrazi"></td>
				<td><input type="submit" id="nazad" name="nazad" value="Nazad" formaction="/WebBibliotekaFakulteta/mainBibliotekar.jsp"></td>
			</tr>
		</table>
	</form>
	</div>
</div>
<p>
<h3>Spisak korisnika:</h3>
<div id="spisakKorisnikaDiv" class="spisakKorisnikaDiv">
	<table class="tableBody" border="1" cellspacing="0" width="100%">
		<thead>
		<tr>
			<th align="left" style="width: 5%">Sifra</th>
			<th align="left" style="width: 15%">Korisnicko Ime</th>
			<th align="left" style="width: 10%">Lozinka</th>
			<th align="left" style="width: 10%">Ime</th>
			<th align="left" style="width: 10%">Prezime</th>
			<th align="left" style="width: 10%">Tip Korisnika</th>
			<th align="left" style="width: 10%">Telefon</th>
			<th align="left" style="width: 10%">E-mail</th>
			<th align="left" style="width: 10%">Status</th>
			<th align="left" style="width: 10%" colspan="3">Opcije</th>
		</tr>
		</thead>
		<tbody>
		<%
			ArrayList<Korisnik> listaAktivnihKorisnika = new ArrayList<Korisnik>();
			int count = 0;
			if(session.getAttribute(IParameter.LISTA_AKTIVNIH_KORISNIKA) != null){
				listaAktivnihKorisnika = (ArrayList<Korisnik>)session.getAttribute(IParameter.LISTA_AKTIVNIH_KORISNIKA);
				for (Korisnik korisnik : listaAktivnihKorisnika)
				{
					count++;
				//TODO for petlja
		%>
				<tr>
					<td align="left"><%=korisnik.getId()%></td>
					<td align="left"><%=korisnik.getKorisnicko_ime()%></td>
					<td align="left"><%=korisnik.getLozinka()%></td>
					<td align="left"><%=korisnik.getIme()%></td>
					<td align="left"><%=korisnik.getPrezime()%></td>
					<td align="left"><%=korisnik.getTip_korisnika()%></td>
					<td align="left"><%=korisnik.getTelefon()%></td>
					<td align="left"><%=korisnik.getE_mail()%></td>
					<td align="left"><%=korisnik.getStatus()%></td>
					<td align="left"><a href="KorisnikServlet?akcija=produziClanarinu&korisnikId=<%=korisnik.getId()%>">Izmeni</a></td>
					<td align="left"><a href="KorisnikServlet?akcija=brisanje&korisnikId=<%=korisnik.getId()%>" >Obrisi</a></td> 
					<td align="left"><a href="KorisnikServlet?akcija=detalji&korisnikId=<%=korisnik.getId()%>">Detalji</a></td> 
				</tr>
		<%
				}//session.removeAttribute(IParameter.LISTA_KORISNIKA_NA_CEKANJU);
		%>				
				<tfoot>
				<tr>
					<td align="left" colspan="9">Korisnici</td>
					<td align="left" colspan="3">Ukupno rezultata: <%=count%></td>
				</tr>
				</tfoot>
		<%
			} else {
		%>
		</tbody>
		<tfoot>
		<tr>
			<td align="center" colspan="11">Nema rezultata pretrage</td>
		</tr>
		</tfoot>
		<%
			}
		%>				
	</table>	
</div>	
</body>
</html>