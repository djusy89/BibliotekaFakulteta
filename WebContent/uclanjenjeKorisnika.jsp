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
	<div id="infoKorisniku"> Pretraga Korisnika na cekanju</div>
	
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
<div id="spisakKorisnikaDiv" class="spisakKorisnikaDiv">

		<%
			Date date = new Date();

			String godina = DateUtility.year(date);
			String trenutniDatum = DateUtility.convertDate2String(date);
			String addYear = DateUtility.addYear(date);

			ArrayList<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
			Korisnik uclanjenKorisnik = new Korisnik();
			ClanskaKarta clanskaKarta = new ClanskaKarta();
		
		%>
	<h3>Spisak korisnika na cekanju za uclanjenje:</h3>
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
			int count = 0;
			if(session.getAttribute(IParameter.LISTA_KORISNIKA_NA_CEKANJU) != null){
				listaKorisnika = (ArrayList<Korisnik>)session.getAttribute(IParameter.LISTA_KORISNIKA_NA_CEKANJU);
				for (Korisnik korisnik : listaKorisnika)
				{
					count++;
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
				<td align="left"><a href="KorisnikServlet?akcija=uclani&korisnikId=<%=korisnik.getId()%>">Uclani</a></td>
				<td align="left"><a href="KorisnikServlet?akcija=brisanje&korisnikId=<%=korisnik.getId()%>" >Obrisi</a></td> 
				<td align="left"><a href="KorisnikServlet?akcija=detalji&korisnikId=<%=korisnik.getId()%>">Detalji</a></td> 
			</tr>
		<%
				} //session.removeAttribute(IParameter.LISTA_KORISNIKA_NA_CEKANJU);
		%>				
		<tfoot>
			<tr>
				<td align="left" colspan="9">Korisnici na cekanju</td>
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
	</table>
</div>
		<%
			}
		%>
		
		<%
			if(session.getAttribute(IParameter.CLANSKA_KARTA) != null){
				uclanjenKorisnik = (Korisnik)session.getAttribute(IParameter.CLANSKA_KARTA);
			
		%>	
<h3>Napravi clansku kartu za korisnika:</h3>
<div id="clanskaKartaDiv" class="clanskaKartaDiv">
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
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="left"><%=uclanjenKorisnik.getId()%></td>
				<td align="left"><%=uclanjenKorisnik.getKorisnicko_ime()%></td>
				<td align="left"><%=uclanjenKorisnik.getLozinka()%></td>
				<td align="left"><%=uclanjenKorisnik.getIme()%></td>
				<td align="left"><%=uclanjenKorisnik.getPrezime()%></td>
				<td align="left"><%=uclanjenKorisnik.getTip_korisnika()%></td>
				<td align="left"><%=uclanjenKorisnik.getTelefon()%></td>
				<td align="left"><%=uclanjenKorisnik.getE_mail()%></td>
				<td align="left"><%=uclanjenKorisnik.getStatus()%></td>
			</tr>
		</tbody>
	</table>
					
	<form action="KorisnikServlet" method="post">
		<fieldset>
			<legend>Clanska karta</legend>
				<input type="hidden" id="uclanjenKorisnikId" name="uclanjenKorisnikId" value="<%= uclanjenKorisnik.getId() %>">
			<table>
				<tr>
					<td align="left">Broj clanarine<span style="color:red">*</span>:&nbsp;</td>
					<td><input type="text" id="brojClanarine" name="brojClanarine" disabled="disabled"  value="<%=uclanjenKorisnik.getId() + "/" + godina%>"></td>
				</tr>
				<tr>
					<td align="left">Status<span style="color:red">*</span>:<br>
					<td>Aktivan:<input align="right" type="radio" id="aktivan" name="status" value="Aktivan" checked="checked">
					Neaktivan:<input align="right" type="radio" id="neaktivan" name="status" value="Neaktivan"></td>
				</tr>
				<tr>
					<td align="left">Datum poslednje uplate<span style="color:red">*</span>:&nbsp;</td>
					<td><input type="text" id="datPoslednjeUplate" name="datPoslednjeUplate" value="<%=trenutniDatum %>"></td>
				</tr>
				<tr>
					<td align="left">Pocetak vazenja<span style="color:red">*</span>:&nbsp;</td>
					<td><input type="text" id="pocetakVazenja" name="pocetakVazenja" value="<%=trenutniDatum %>"></td>
				</tr>
				<tr>
					<td align="left">Kraj vazenja<span style="color:red">*</span>:&nbsp;</td>
					<td><input type="text" id="krajVazenja" name="krajVazenja" value="<%=addYear %>"></td>
				</tr>
				<tr>
					<td align="left">Tip clanarine<span style="color:red">*</span>:<br>
					<td>Ograniceno:<input type="radio" id="ograniceno" name="tipClanarine" value="Ograniceno" checked="checked"></td>
					<td>Neograniceno:<input type="radio" id="neograniceno" name="tipClanarine" value="Neograniceno" ></td>
				</tr>
				<tr>
					<td><input type="submit" id="clanskaKarta" name="clanskaKarta" value="Napravi clansku kartu">
				</tr>
			</table>
		</fieldset>
	</form>		
		<%
			}session.removeAttribute(IParameter.CLANSKA_KARTA);
		%>					
</div>	
</body>
</html>