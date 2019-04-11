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
</head>
<body>
<%! static Logger logger = Logger.getLogger(KnjigaServlet.class.getName()); %>
<% logger.info("Usli ste u unesiKnjigu.jsp"); %>
${cursor}
<p align="center">
<strong>Unos knjige:</strong>
<p>
<div id="errorInfo" style="color: red" align="center">
		<%
			if(session.getAttribute(IParameter.KNJIGA_UNOS_ERROR) != null){
				out.print(session.getAttribute(IParameter.KNJIGA_UNOS_ERROR));
				session.removeAttribute(IParameter.KNJIGA_UNOS_ERROR);
			}
		%>
</div>
		<% 
			
		
			if(session.getAttribute(IParameter.LISTA_KATEGORIJA) != null){
			ArrayList<Kategorija> listaKategorija = (ArrayList<Kategorija>)session.getAttribute(IParameter.LISTA_KATEGORIJA);
			ArrayList<Izdavac> listaIzdavaca = (ArrayList<Izdavac>) session.getAttribute(IParameter.LISTA_IZDAVACA);
		%>
				
<div id="formDiv" class="formDiv" align="center">
<form action="KnjigaServlet" enctype="multipart/form-data" method="post">
	<fieldset style="width:800px">
		<legend>Podaci o knjizi</legend>	
		<table>
			<tr>
				<td align="right">Naziv knjige<span style="color:red">*</span>:&nbsp;</td>
				<td><input type="text" id="nazivKnjige" name="nazivKnjige" value="" placeholder="Unesite naziv"></td>
			</tr>
			<tr>
				<td align="right">Opis knjige<span style="color:red">*</span>:&nbsp;</td>
				<td><textarea name="opis" id="opis" rows="10" cols="30" placeholder="Unesite opis knjige."></textarea></td>
			</tr>
			<tr>
				<td align="right">Link ka oficijalnom sajtu izdavaca:&nbsp;</td>
				<td><input type="text" id="linkIzdavaca" name="linkIzdavaca" value="" placeholder="http://link.com"></td>
			</tr>
			<tr>
				<td align="right">Slika naslovne strane:&nbsp;</td>
				<td><input type="file" id="slikaNaslovne" name="slikaNaslovne" value=""></td>
			</tr>
			<tr>
				<td align="right">Godina izdanja<span style="color:red">*</span>:&nbsp;</td>
				<td><input type="text" id="izdanje" name="izdanje" value="" placeholder="DD.MM.GGGG"></td>
			</tr>
			<tr>
				<td align="right">Autor<span style="color:red">*</span>:&nbsp;</td>
				<td><input type="text" id="autor" name="autor" value="" placeholder="Unesite ime autora"></td>
			</tr>
			<tr>
				<td align="right">Izdavac<span style="color:red">*</span>:&nbsp;</td>
				<td><select name="izdavac">
					  <option value="" selected>Selektujte izdavaca</option>
				        <%
				        
				        for (Izdavac izdavac : listaIzdavaca)
						{
				     
				           String izdavacStr = izdavac.getNaziv().toString();
				           int izdavacId = izdavac.getId();
				           String iId = Integer.toString(izdavacId);
				           
				        %>
				        <option value="<%=iId %>"><%=izdavacStr %></option>
				        <%} %>
					</select>
		 	 	</td>
				<td><input type="submit" id="<%=IParameter.IZDAVAC_PARAM %>" name="<%=IParameter.IZDAVAC_PARAM %>" value="Dodaj izdavaca"></td>
			</tr>
			<tr>
				<td align="right">Kategorija<span style="color:red">*</span>:&nbsp;</td>
				<td><select name="kategorije">
					  <option value="" selected>Selektujte kategoriju</option>
				        <%
				        
				        for (Kategorija kategorija : listaKategorija)
						{
				     
				           String kategorijaStr = kategorija.getNaziv().toString();
				           int kategorijaId = kategorija.getId();
				           String kId = Integer.toString(kategorijaId);
				           
				        %>
				        <option value="<%=kId %>"><%=kategorijaStr %></option>
				        <%} %>
					</select>
		 	 	</td>
		 	 	<td><input type="submit" id="<%=IParameter.KATEGORIJA_PARAM %>" name="<%=IParameter.KATEGORIJA_PARAM %>" value="Dodaj Kategoriju"></td>
			</tr>
			<tr>
				<td><p>Sva polja oznacena sa <span style="color:red">*</span> su obavezna za unos!</p></td>
			</tr>
			<tr>
				<td><input type="submit" id="<%=IParameter.UNESI_KNJIGU %>" name="<%=IParameter.UNESI_KNJIGU %>" value="Snimi"><input type="submit" formaction="mainBibliotekar.jsp" value="Pocetna strana"></td>
			</tr>
		</table>
	</fieldset>
</form>
</div>
<%
		}
%>

</body>
</html>