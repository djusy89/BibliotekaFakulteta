<!DOCTYPE html>

<%@page import="java.util.Base64.Encoder"%>
<%@page import="dao.SifarnikController"%>
<%@page import="servlet.KnjigaServlet"%>
<%@ page import="utility.*" %>
<%@ page import="vo.*" %>
<%@ page import="bean.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.sql.Blob"%>
<html>
<head>
<meta charset="${encoding}">
<title>Insert title here</title>
</head>
<body>
<%! static Logger logger = Logger.getLogger(KnjigaServlet.class.getName()); %>
<% logger.info("Usli ste u unesiKnjigu.jsp"); %>
${cursor}
<div id="bodyDiv" class="bodyDiv" align="center">
<div id="infoKorisniku"> <h3>Pretraga knjiga:</h3></div>
<div id="formSifDiv" class="formSifDiv">
	<form action="KnjigaServlet" method="post" class="formDiv">
		<table>
			
			<tr>
				<td><input type="text" id=<%=IParameter.TEKST_PRETRAGE %> name=<%=IParameter.TEKST_PRETRAGE %> value=""></td>
				<td align="right">Kriterijum Pretrage:&nbsp;</td>
				<td><select name="pretragaKnjige">
					  <option value="pretragaNazivKnjige" selected>Naziv Knjige</option>				       
				      <option value="pretragaAutor">Autor</option>
				      <option value="pretragaKategorija" disabled="disabled">Kategorija</option>
				      <option value="pretragaIzdavac" disabled="disabled">Izdavac</option>			      
					</select>
				</td>				
			</tr>
			<tr>
				<td><input type="submit" id="pretragaKnjiga" name="pretragaKnjiga" value="&#8981 Pretrazi"></td>
				<td><input type="submit" id="nazad" name="nazad" value="Nazad" formaction="/WebBibliotekaFakulteta/mainBibliotekar.jsp"></td>
			</tr>
		</table>
	</form>
	</div>
</div>
		<%
			if(session.getAttribute(IParameter.IZMENI_KNJIGA) != null){
				Knjiga izmeniKnjigu = (Knjiga)session.getAttribute(IParameter.IZMENI_KNJIGA);
				ArrayList<Kategorija> listaKategorija = (ArrayList<Kategorija>)session.getAttribute(IParameter.LISTA_KATEGORIJA);
				ArrayList<Izdavac> listaIzdavaca = (ArrayList<Izdavac>)session.getAttribute(IParameter.LISTA_IZDAVACA);
				
				Blob blobTemp = izmeniKnjigu.getSlika_naslovne_strane();
				byte[] slikaPrikaz = blobTemp.getBytes(1, (int) blobTemp.length());
	            
	            String encoded = Base64.getEncoder().encodeToString(slikaPrikaz);
	            String source = "data:image/jpg;base64,";
	            source = source.concat(encoded.toString());
	            
				String kategorijaNaziv = null;
				String izdavacNaziv = null;
				for(Kategorija kategorija : listaKategorija){
					if(kategorija.getId() == izmeniKnjigu.getId_kategorija())
						kategorijaNaziv = kategorija.getNaziv();
				}
				for(Izdavac izdavac : listaIzdavaca){
					if(izdavac.getId() == izmeniKnjigu.getId_izdavac())
						izdavacNaziv = izdavac.getNaziv();
				}
			
		%>
<h3>Izmeni knjigu:</h3>
<div id="formDiv" class="formDiv" align="center">
<form action="KnjigaServlet" enctype="multipart/form-data" method="post">
	<fieldset style="width:1000px">
		<legend>Podaci o knjizi</legend>
		<input type="hidden" id="<%=IParameter.ID_KNJIGE%>" name="<%=IParameter.ID_KNJIGE%>" value="<%=izmeniKnjigu.getId()%>">
		<table>
			<tr>
				<td align="right">Naziv knjige:&nbsp; </td>
				<td><input type="text" id="nazivKnjige" name="nazivKnjige" value="<%=izmeniKnjigu.getNaziv()%>"></td>
			</tr>
			<tr>
				<td align="right">Opis:&nbsp;</td>
				<td><textarea name="opis" id="opis" rows="10" cols="30"><%=izmeniKnjigu.getOpis() %></textarea>></td>
			</tr>
			<tr>
				<td align="right">Link izdavaca:&nbsp;</td>
				<td><input type="url" id="linkIzdavaca" name="linkIzdavaca" value="<%=izmeniKnjigu.getLink_izdavaca()%>"></td>
			</tr>
			<tr>
				<td align="right">Slika naslovne strane:&nbsp;</td>
				<td><img src="<%=source %>" align="middle" width="100" height="100"/></td>
				<td><input type="file" id="slikaNaslovne" name="slikaNaslovne"></td>
			</tr>
			<tr>
				<td align="right">Godina izdanja:&nbsp;</td>
				<td><input type="text" id="izdanje" name="izdanje" value="<%=izmeniKnjigu.getIzdanje()%>"></td>
			</tr>
			<tr>
				<td align="right">Autor:&nbsp;</td>
				<td><input type="text" id="autor" name="autor" value="<%=izmeniKnjigu.getAutor()%>"></td>
			</tr>
			<tr>
				<td align="right">Izdavac:&nbsp;</td>
				<td><select name="izdavac"  size="3" >
					  <option value="<%=izmeniKnjigu.getId_izdavac() %>" selected><%=izdavacNaziv %></option>
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
				<td align="right">Kategorija:&nbsp;</td>
				<td><select name="kategorije"  size="3" >
					  <option value="<%=izmeniKnjigu.getId_kategorija() %>"selected><%=kategorijaNaziv %></option>
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
				<td><input type="submit" id="<%=IParameter.SACUVAJ_IZMENU_KNJIGE %>" name="<%=IParameter.SACUVAJ_IZMENU_KNJIGE %>" value="Sacuvaj izmenu knjige"></td>
			</tr>
		</table>
	</fieldset>
</form>
</div>

		<%
			} session.removeAttribute(IParameter.IZMENI_KNJIGA);
		%>	
		<%
			ArrayList<Knjiga> listaKnjiga = (ArrayList<Knjiga>)session.getAttribute(IParameter.LISTA_KNJIGA);
			ArrayList<Kategorija> listaKategorija = (ArrayList<Kategorija>)session.getAttribute(IParameter.LISTA_KATEGORIJA);
			ArrayList<Izdavac> listaIzdavaca = (ArrayList<Izdavac>)session.getAttribute(IParameter.LISTA_IZDAVACA);	       
			logger.info("Lista knjiga: " + listaKnjiga);	
			String kategorijaNaziv = null;
			String izdavacNaziv = null;
			int count = 0;
			if(session.getAttribute(IParameter.LISTA_KNJIGA) != null){
				
				int brojPikazanihRedova=5;  
				int brojIndexaStrana=5; 
				
				int maxBrRez=(int)session.getAttribute("maxBrojRedova");
				int ukupanBrStranica= (int)session.getAttribute("ukupanBrStranica");;
				int startniRed = (int)session.getAttribute("startniRed"); // 5, 10, 15... pocetni index 
				int cPagNo=StringUtility.Converter(request.getParameter("cPagNo")); // ne korisna promenjiva
				logger.info("maxBrRez" + maxBrRez + " ,startniRed"+ startniRed + " ,ukupanBrStranica: "+ukupanBrStranica);
				int iStartRezultata=0;
				int iKrajRezultata=0;
			%>
			
		
<h3 align="center">Spisak knjiga:</h3>
<div id="spisakKorisnikaDiv" class="spisakKorisnikaDiv" align="center">
	<form action="KnjigaServlet" >
		<input type="hidden" name="startniRed" value="<%=startniRed%>">
		<input type="hidden" name="cPagNo" value="<%=cPagNo%>">
		<input type="hidden" name="brojPikazanihRedova" value="<%=brojPikazanihRedova%>">
	<table class="tableBody" border="1" cellspacing="0" width="80%">
		<thead>
		<tr>
			<th align="left" style="width: 3%">Sifra</th>
			<th align="left" style="width: 10%"><a href="KnjigaServlet?akcija=sortirajKnjigu&sortiranje=nazivKnjige">Naziv knjige</a></th>
			<th align="left" style="width: 17%">Opis</th>
			<th align="left" style="width: 10%">Link izdavaca</th>
			<th align="left" style="width: 10%">Slika naslovne strane</th>
			<th align="left" style="width: 10%">Izdanje</th>
			<th align="left" style="width: 10%"><a href="KnjigaServlet?akcija=sortirajKnjigu&sortiranje=autorKnjige">Autor</a></th>
			<th align="left" style="width: 10%">Kategorija</th>
			<th align="left" style="width: 10%">Izdavac</th>
			<th align="left" style="width: 10%" colspan="3">Opcije</th>
		</tr>
		</thead>
		<tbody>
		<%
				for (Knjiga knjiga : listaKnjiga)
				{
					Blob blobTemp = knjiga.getSlika_naslovne_strane();
					byte[] slikaPrikaz = blobTemp.getBytes(1, (int) blobTemp.length());
		            
		            String encoded = Base64.getEncoder().encodeToString(slikaPrikaz);
		            String source = "data:image/jpg;base64,";
		            source = source.concat(encoded.toString());


					for(Kategorija kategorija : listaKategorija)
					{
						if(kategorija.getId() == knjiga.getId_kategorija()){
							kategorijaNaziv = kategorija.getNaziv();
							
							for(Izdavac izdavac : listaIzdavaca){
								if(izdavac.getId() == knjiga.getId_izdavac()){
									izdavacNaziv = izdavac.getNaziv();
					
					count++;
			%>
		
				<tr>
					<td align="left"><%=knjiga.getId()%></td>
					<td align="left"><%=knjiga.getNaziv()%></td>
					<td align="left"><%=knjiga.getOpis()%></td>
					<td align="left"><%=knjiga.getLink_izdavaca()%></td>
					<td align="center"><img src="<%=source %>" width="100" height="100"/></td> 
					<td align="left"><%=knjiga.getIzdanje()%></td>
					<td align="left"><%=knjiga.getAutor()%></td>
					<td align="left"><%=kategorijaNaziv%></td>
					<td align="left"><%=izdavacNaziv%></td>
					<td align="left"><a href="KnjigaServlet?akcija=izmeniKnjigu&knjigaId=<%=knjiga.getId()%>">Izmeni</a></td>
					<td align="left"><a href="KnjigaServlet?akcija=obrisiKnjigu&knjigaId=<%=knjiga.getId()%>" >Obrisi</a></td> 
					<td align="left"><a href="KnjigaServlet?akcija=detaljiKnjigu&knjigaId=<%=knjiga.getId()%>">Detalji</a></td> 
				</tr>		
				
				<%
								}
							}
						}	
					}
				}
				%>
				<tfoot>
				<tr>
					<td align="left" colspan="9">Knjige</td>
					<td align="left" colspan="3">Ukupno rezultata: <%=count%></td>
				</tr>
				<tr>
					<td align="center" colspan="6">
					<%						
						try{
							if(maxBrRez<(startniRed+brojPikazanihRedova))
					            {
								iKrajRezultata=maxBrRez;
					            }
							else
					            {
								iKrajRezultata=(startniRed+brojPikazanihRedova);
					            }

							iStartRezultata=(startniRed+1);
							
					       	}
						catch(Exception e)
					        {
									e.printStackTrace();
					        }
						
					int i = 0;
					int trenutnaStranaInt = 0;

						if(maxBrRez!=0){
							trenutnaStranaInt=((int)(Math.ceil((double)iKrajRezultata/(brojIndexaStrana*brojPikazanihRedova))));
							int prethodnaStranica = (trenutnaStranaInt*brojIndexaStrana)-((brojIndexaStrana-1) + brojIndexaStrana);
							logger.info("trenutnaStranaInt : " + trenutnaStranaInt + " , prethodnaStrana: " + prethodnaStranica);	
							if((trenutnaStranaInt*brojIndexaStrana)-(brojIndexaStrana) > 0){
					%>
							<a href="KnjigaServlet?akcija=pregledKnjiga&startniRed=<%=prethodnaStranica%>" >&#8678;</a>
					<%
							}
						
							for(i=((trenutnaStranaInt*brojIndexaStrana)-(brojIndexaStrana-1));i<=(trenutnaStranaInt*brojIndexaStrana);i++){
							
								if(i==((startniRed/brojPikazanihRedova)+1)){
					%>
							<a href="KnjigaServlet?akcija=pregledKnjiga&startniRed=<%=i%>"style="cursor:pointer;color:red"><b><%=i%></b></a>
					<%
							}else if(i<=ukupanBrStranica){
					%>
							<a href="KnjigaServlet?akcija=pregledKnjiga&startniRed=<%=i%>" ><%=i %></a>
					<%			
							}
							}logger.info("ukupanBrStranica : " + ukupanBrStranica + " , brojIndexaStrana: " + brojIndexaStrana + "i " + i);	
							if(ukupanBrStranica>brojIndexaStrana && i-1 <ukupanBrStranica){
								logger.info("ukupanBrStranica : " + ukupanBrStranica + " , brojIndexaStrana: " + brojIndexaStrana);	
					%>
							<a href="KnjigaServlet?akcija=pregledKnjiga&startniRed=<%=i%>" >&#8680;</a>
					<%			
							}
						}
					%>
					</td>
					<td align="center" colspan="6">
						<b>Red: <%=iStartRezultata%> - <%=iKrajRezultata%>   Ukupno rezultata:  <%=maxBrRez%></b>
					</td>
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
	</form>	
</div>	
</body>
</html>