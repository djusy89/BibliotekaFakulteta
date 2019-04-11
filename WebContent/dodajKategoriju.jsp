<!DOCTYPE html>

<%@page import="dao.SifarnikController"%>
<%@page import="servlet.KnjigaServlet"%>
<%@ page import="utility.*" %>
<%@ page import="vo.*" %>
<%@ page import="bean.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.log4j.Logger"%>
<html>
<head>
<meta charset="${encoding}">
<title>Insert title here</title>
<style type="text/css">
#thetable {
    border-collapse: collapse;
    border: 1px solid;
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
<%! static Logger logger = Logger.getLogger(KnjigaServlet.class.getName()); %>
<% logger.info("Usli ste u dodajKategoriju.jsp"); %>
<div>
<h3 align="center">Spisak Kategorija u bazi:</h3>
<div align="center">
	<table class="tableBody" id="thetable">
		<thead>
			<tr>
				<th align="left" style="width: 20%">Naziv kategorije</th>
				<th align="left" style="width: 35%">Opis kategorije</th>

			</tr>
		</thead>
		<tbody>
		<%		
		if(session.getAttribute(IParameter.LISTA_KATEGORIJA) != null){
			int count = 0;
		
			if(session.getAttribute(IParameter.LISTA_KATEGORIJA) != null){
				ArrayList<Kategorija> listaKategorija = (ArrayList<Kategorija>)session.getAttribute(IParameter.LISTA_KATEGORIJA);
				logger.info("Vratili listu kategorija na jsp: " + listaKategorija);
				for (Kategorija kategorijaTemp : listaKategorija)
				{
					count++;
		%>
			<tr>
				<td align="left"><%=kategorijaTemp.getNaziv()%></td>
				<td align="left"><%=kategorijaTemp.getOpis()%></td>
			</tr>
		<%
				}
		%>				
		<tfoot>
			<tr>
				<td align="left">Kategorije:</td>
				<td align="right">Ukupno rezultata: <%=count%></td>
			</tr>
		</tfoot>
		<%
			} else {
		%>
		</tbody>
			<tfoot>
				<tr>
					<td align="center" colspan="2">Nema rezultata pretrage</td>
				</tr>
			</tfoot>
			<%
			}
		}
		%>
	</table>
</div>
<br>
<div id="errorInfo" style="color: red" align="center">
		<%
			if(session.getAttribute(IParameter.KATEGORIJA_UNOS_ERROR) != null){
				out.print(session.getAttribute(IParameter.KATEGORIJA_UNOS_ERROR));
				session.removeAttribute(IParameter.KATEGORIJA_UNOS_ERROR);
			}
			if(session.getAttribute(IParameter.KATEGORIJA_DUPLICATE_ERROR) != null){
				out.print(session.getAttribute(IParameter.KATEGORIJA_DUPLICATE_ERROR));
				session.removeAttribute(IParameter.KATEGORIJA_DUPLICATE_ERROR);
			}
		%>
</div>
<br>
<div id="formDiv" class="formDiv" align="center">
<form action="KnjigaServlet" method="post">
	<fieldset style="width:500px">
		<legend>Podaci o kategoriji</legend>
		<table>
			<tr>
				<td align="right">Naziv kategorije<span style="color:red">*</span>:&nbsp;</td>
				<td><input type="text" id="nazivKategorije" name="nazivKategorije" value="" placeholder="Unesite naziv"></td>
			</tr>
			<tr>
				<td align="right">Opis kategorije<span style="color:red">*</span>:&nbsp;</td>
				<td><textarea name="opisKategorije" id="opisKategorije" rows="10" cols="30" placeholder="Unesite opis kategorije."></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" id="<%=IParameter.KATEGORIJA_UNOS_PARAM %>" name="<%=IParameter.KATEGORIJA_UNOS_PARAM %>" value="Dodaj kategoriju"></td>
				<td><input type="submit" id="<%=IParameter.KNJIGA_STRANA %>" name="<%=IParameter.KNJIGA_STRANA %>" value="Unesi knjigu strana"><input type="submit" formaction="mainBibliotekar.jsp" value="Pocetna strana"></td>
			</tr>
		</table>
	</fieldset>
</form>
</div>
</div>
</body>
</html>