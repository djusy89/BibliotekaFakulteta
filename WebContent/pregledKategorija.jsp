<%@page import="bean.Kategorija"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utility.IParameter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>Pregled Kategorija</title>
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
<div id="infoKorisniku"> Pretraga kategorija</div>
<div id="formSifDiv" class="formSifDiv">
	<form action="KnjigaServlet" method="post" class="formDiv">
		<table>
			
			<tr>
				<td><input type="text" id="textPretrage" name="textPretrage" value=""></td>
				<td><input type="submit" id="pretragaKategorija" name="pretragaKategorija" value="&#8981 Pretrazi"></td>
				<td><input type="submit" id="nazad" name="nazad" value="Nazad" formaction="/WebBibliotekaFakulteta/mainBibliotekar.jsp"></td>
				<td><input type="submit" id="<%=IParameter.KATEGORIJA_PARAM %>" name="<%=IParameter.KATEGORIJA_PARAM %>" value="Dodaj Kategoriju"></td>
				
			</tr>
		</table>
	</form>
	</div>
</div>
		<%
			if(session.getAttribute(IParameter.IZMENI_KATEGORIJU) != null){

				Kategorija izmeniKategoriju = (Kategorija)session.getAttribute(IParameter.IZMENI_KATEGORIJU);
				ArrayList<Kategorija> listaKategorija = (ArrayList<Kategorija>)session.getAttribute(IParameter.LISTA_KATEGORIJA);
			
		%>
<h3 align="center">Izmeni kategoriju:</h3>
<div id="formDiv" class="formDiv" align="center">
<form action="KnjigaServlet" method="post">
	<fieldset style="width:1000px">
		<legend>Podaci o kategoriji</legend>
		<input type="hidden" id="<%=IParameter.ID_KATEGORIJE%>" name="<%=IParameter.ID_KATEGORIJE%>" value="<%=izmeniKategoriju.getId()%>">
		<table>
			<tr>
				<td align="right">Naziv kategorije:&nbsp; </td>
				<td><input type="text" id="nazivKategorije" name="nazivKategorije" value="<%=izmeniKategoriju.getNaziv()%>"></td>
			</tr>
			<tr>
				<td align="right">Opis:&nbsp;</td>
				<td><textarea name="opis" id="opis" rows="10" cols="30"><%=izmeniKategoriju.getOpis() %></textarea>></td>
			</tr>
			<tr>
				<td><input type="submit" id="<%=IParameter.SACUVAJ_IZMENU_KATEGORIJE %>" name="<%=IParameter.SACUVAJ_IZMENU_KATEGORIJE %>" value="Sacuvaj izmenu kategorije"></td>
			</tr>
		</table>
	</fieldset>
</form>
</div>

		<%
			} // u doPost napraviti preuzimanje unetih podataka za izmenu i izmeniti u bazi
		%>
		
<h3 align="center">Spisak kategorija:</h3>
<div id="spisakKategorijaDiv" class="spisakKategorijaDiv" align="center">
	<table class="tableBody" id="thetable">
		<thead>
		<tr>
			<th align="left" style="width: 5%">Sifra</th>
			<th align="left" style="width: 15%">Naziv kategorije</th>
			<th align="left" style="width: 10%">Opis</th>
			<th align="left" style="width: 10%" colspan="3">Opcije</th>
		</tr>
		</thead>
		<tbody>
		<%
		if(session.getAttribute(IParameter.LISTA_KATEGORIJA) != null){
			int count = 0;
		
			if(session.getAttribute(IParameter.LISTA_KATEGORIJA) != null){
				ArrayList<Kategorija> listaKategorija = (ArrayList<Kategorija>)session.getAttribute(IParameter.LISTA_KATEGORIJA);

				for (Kategorija kategorijaTemp : listaKategorija)
				{
					count++;
		%>
				<tr>
					<td align="left"><%=kategorijaTemp.getId()%></td>
					<td align="left"><%=kategorijaTemp.getNaziv()%></td>
					<td align="left"><%=kategorijaTemp.getOpis()%></td>
					<td align="left"><a href="KnjigaServlet?akcija=izmeniKategoriju&kategorijaId=<%=kategorijaTemp.getId()%>">Izmeni</a></td>
					<td align="left"><a href="KnjigaServlet?akcija=obrisiKategoriju&kategorijaId=<%=kategorijaTemp.getId()%>" >Obrisi</a></td> 
					<td align="left"><a href="KnjigaServlet?akcija=detalji&kategorijaId=<%=kategorijaTemp.getId()%>">Detalji</a></td> 
				</tr>
		<%						
				}	
				}
		%>				
				<tfoot>
				<tr>
					<td align="left" colspan="3">Kategorije</td>
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
