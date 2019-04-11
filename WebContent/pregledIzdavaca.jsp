<%@page import="bean.Izdavac"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utility.IParameter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>Pregled Izdavaca</title>
<style type="text/css">
#thetable {
    border-collapse: collapse;
    border: 2px solid;
    border-spacing: 10px;
    align:center;
    width:40%;
}

#thetable td {
    border: 1px solid;
}
</style>
</head>

<body>
<div id="bodyDiv" class="bodyDiv" align="center">
<div id="infoKorisniku"> Pretraga izdavaca</div>
<div id="formSifDiv" class="formSifDiv">
	<form action="KnjigaServlet" method="post" class="formDiv">
		<table>
			
			<tr>
				<td><input type="text" id="textPretrage" name="textPretrage" value=""></td>
				<td><input type="submit" id="pretragaIzdavaca" name="pretragaIzdavaca" value="&#8981 Pretrazi"></td>
				<td><input type="submit" id="nazad" name="nazad" value="Nazad" formaction="/WebBibliotekaFakulteta/mainBibliotekar.jsp"></td>
				<td><input type="submit" id="<%=IParameter.IZDAVAC_PARAM %>" name="<%=IParameter.IZDAVAC_PARAM %>" value="Dodaj Izdavaca"></td>
				
			</tr>
		</table>
	</form>
	</div>
</div>
		<%
			if(session.getAttribute(IParameter.IZMENI_KATEGORIJU) != null){

				Izdavac izmeniIzdavaca = (Izdavac)session.getAttribute(IParameter.IZMENI_IZDAVACA);
				ArrayList<Izdavac> listaIzdavaca = (ArrayList<Izdavac>)session.getAttribute(IParameter.LISTA_IZDAVACA);
			
		%>
<h3 align="center">Izmeni Izdavaca:</h3>
<div id="formDiv" class="formDiv" align="center">
<form action="KnjigaServlet" method="post">
	<fieldset style="width:1000px">
		<legend>Podaci o izdavacu</legend>
		<input type="hidden" id="<%=IParameter.ID_IZDAVACA%>" name="<%=IParameter.ID_IZDAVACA%>" value="<%=izmeniIzdavaca.getId()%>">
		<table>
			<tr>
				<td align="right">Naziv izdavaca:&nbsp; </td>
				<td><input type="text" id="nazivKategorije" name="nazivKategorije" value="<%=izmeniIzdavaca.getNaziv()%>"></td>
			</tr>
			<tr>
				<td align="right">Opis:&nbsp;</td>
				<td><textarea name="opis" id="opis" rows="10" cols="30"><%=izmeniIzdavaca.getOpis() %></textarea>></td>
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
				<td><input type="submit" id="<%=IParameter.SACUVAJ_IZMENU_IZDAVACA %>" name="<%=IParameter.SACUVAJ_IZMENU_IZDAVACA %>" value="Sacuvaj izmenu izdavaca"></td>
			</tr>
		</table>
	</fieldset>
</form>
</div>

		<%
			}
		%>
		
<h3 align="center">Spisak izdavaca:</h3>
<div id="spisakIzdavacaDiv" class="spisakIzdavacaDiv" align="center">
	<table class="tableBody" id="thetable">
		<thead>
		<tr>
			<th align="left" style="width: 5%">Sifra</th>
			<th align="left" style="width: 15%">Naziv kategorije</th>
			<th align="left" style="width: 10%">Opis</th>
			<th align="left" style="width: 10%">Tip knjige</th>
			<th align="left" style="width: 10%">Adresa</th>
			<th align="left" style="width: 10%">Telefon</th>
			<th align="left" style="width: 10%" colspan="3">Opcije</th>
		</tr>
		</thead>
		<tbody>
		<%
		if(session.getAttribute(IParameter.LISTA_IZDAVACA) != null){
			int count = 0;
		
			if(session.getAttribute(IParameter.LISTA_IZDAVACA) != null){
				ArrayList<Izdavac> listaIzdavaca = (ArrayList<Izdavac>)session.getAttribute(IParameter.LISTA_IZDAVACA);

				for (Izdavac izdavacTemp : listaIzdavaca)
				{
					count++;
		%>
				<tr>
					<td align="left"><%=izdavacTemp.getId()%></td>
					<td align="left"><%=izdavacTemp.getNaziv()%></td>
					<td align="left"><%=izdavacTemp.getOpis()%></td>
					<td align="left"><%=izdavacTemp.getTip_knjige()%></td>
					<td align="left"><%=izdavacTemp.getAdresa_izdavaca()%></td>
					<td align="left"><%=izdavacTemp.getTelefon()%></td>
					<td align="left"><a href="KnjigaServlet?akcija=izmeniKategoriju&kategorijaId=<%=izdavacTemp.getId()%>">Izmeni</a></td>
					<td align="left"><a href="KnjigaServlet?akcija=obrisiKategoriju&kategorijaId=<%=izdavacTemp.getId()%>" >Obrisi</a></td> 
					<td align="left"><a href="KnjigaServlet?akcija=detalji&kategorijaId=<%=izdavacTemp.getId()%>">Detalji</a></td> 
				</tr>
		<%						
				}	
				}
		%>				
				<tfoot>
				<tr>
					<td align="left" colspan="6">Izdavaci</td>
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
