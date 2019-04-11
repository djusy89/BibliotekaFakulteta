<!DOCTYPE html>

<%@ page import="utility.*" %>
<%@ page import="vo.*" %>
<%@ page import="bean.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta charset="${encoding}">
<title>Insert title here</title>
</head>
<body>
<h3 align="center">Spisak Izdavaca u bazi:</h3>
<div align="center">
	<table class="tableBody" border="1" cellspacing="0" width="50%">
		<thead>
			<tr>
				<th align="left" style="width: 10%">Naziv izdavaca</th>
				<th align="left" style="width: 15%">Opis izdavaca</th>
				<th align="left" style="width: 5%">Tip knjge</th>
				<th align="left" style="width: 5%">Adresa izdavaca</th>
				<th align="left" style="width: 5%">Telefon</th>
				<th align="left" style="width: 10%" colspan="3">Opcije</th>
			</tr>
		</thead>
		<tbody>
		<%		
			if(session.getAttribute(IParameter.LISTA_IZDAVACA) != null){
				ArrayList<Izdavac> listaIzdavaca = (ArrayList<Izdavac>)session.getAttribute(IParameter.LISTA_IZDAVACA);
				int count = 0;
				for (Izdavac izdavacTemp : listaIzdavaca)
				{
					count++;
		%>
			<tr>
				<td align="left"><%=izdavacTemp.getNaziv()%></td>
				<td align="left"><%=izdavacTemp.getOpis()%></td>
				<td align="left"><%=izdavacTemp.getTip_knjige()%></td>
				<td align="left"><%=izdavacTemp.getAdresa_izdavaca()%></td>
				<td align="left"><%=izdavacTemp.getTelefon()%></td>
				<td align="left"><a href="KnjigaServlet?akcija=izmeni&izdavacId=<%=izdavacTemp.getId()%>">Izmeni</a></td>
				<td align="left"><a href="KnjigaServlet?akcija=brisanje&izdavacId=<%=izdavacTemp.getId()%>" >Obrisi</a></td> 
				<td align="left"><a href="KnjigaServlet?akcija=detalji&izdavacId=<%=izdavacTemp.getId()%>">Detalji</a></td> 
			</tr>
		<%
				} session.removeAttribute(IParameter.LINK_IZDAVACA);
		%>				
		<tfoot>
			<tr>
				<td align="left" colspan="1">Izdavaca:</td>
				<td align="right" colspan="7">Ukupno rezultata: <%=count%></td>
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
<br>
<div id="errorInfo" style="color: red" align="center">
		<%
			if(session.getAttribute(IParameter.IZDAVAC_UNOS_ERROR) != null){
				out.print(session.getAttribute(IParameter.IZDAVAC_UNOS_ERROR));
				session.removeAttribute(IParameter.IZDAVAC_UNOS_ERROR);
			}
		%>
</div>
<br>
<div id="formDiv" class="formDiv" align="center">
<form action="KnjigaServlet" method="post">
	<fieldset>
		<legend>Podaci o izdavacu</legend>
		<table>
			<tr>
				<td align="right">Naziv izdavaca<span style="color:red">*</span>:&nbsp;</td>
				<td><input type="text" id="nazivIzdavaca" name="nazivIzdavaca" value=""></td>
			</tr>
			<tr>
				<td align="right">Opis<span style="color:red">*</span>:&nbsp;</td>
				<td><textarea name="opisIzdavaca" id="opisIzdavaca" rows="10" cols="30">Unesite opis.</textarea></td>
			</tr>
			<tr>
				<td align="right">Tip knjige<span style="color:red">*</span>:&nbsp;</td>
				<td><input type="text" id="tipKnjige" name="tipKnjige" value=""></td>
			</tr>
			<tr>
				<td align="right">Adresa izdavaca<span style="color:red">*</span>:&nbsp;</td>
				<td><input type="text" id="adresaIzdavaca" name="adresaIzdavaca" value=""></td>
			</tr>
			<tr>
				<td align="right">Telefon izdavaca<span style="color:red">*</span>:&nbsp;</td>
				<td><input type="text" id="telefonIzdavaca" name="telefonIzdavaca" value=""></td>
			</tr>
			<tr>
				<td><input type="submit" id="<%=IParameter.IZDAVAC_UNOS_PARAM %>" name="<%=IParameter.IZDAVAC_UNOS_PARAM %>" value="Snimi"></td>
				<td><input type="submit" id="<%=IParameter.KNJIGA_STRANA %>" name="<%=IParameter.KNJIGA_STRANA %>" value="Unesi knjigu strana"><input type="submit" formaction="mainBibliotekar.jsp" value="Pocetna strana"></td>
				
			</tr>
		</table>
	</fieldset>
</form>
</div>
</body>
</html>