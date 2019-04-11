<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ page import="java.util.*" %>
<%@ page import="utility.*" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registracija korisnika</title>
<link rel="stylesheet" type="text/css" href="css/mainstyle.css">
</head>

<body>
<div id="infoKorisniku" style="color: red">
<%
	if(session.getAttribute(IParameter.USER_LOGIN_ERROR) != null){
		out.println(session.getAttribute(IParameter.USER_LOGIN_ERROR));
	}

	out.println(" ");
	
	if(session.getAttribute(IParameter.USER_LOGIN_SUCCESS) != null){
		out.println(session.getAttribute(IParameter.USER_LOGIN_SUCCESS));
	}
%>
</div>
<p>
Unos korisnika
<p>
<div id="formDiv" class="formDiv">
<form action="LoginServlet" method="post">
	<fieldset>
		<legend>Podaci o korisniku</legend>
			<input type="hidden" id="<%=IParameter.TIP_LOGINA_PARAM%>" name="<%=IParameter.TIP_LOGINA_PARAM%>" value="<%=IConstant.MOD_UNOS%>">
	
		<table>
			<tr>
				<td align="right">Ime korisnika<span style="color:red">*</span>:&nbsp;<input type="text" id="imeKorisnika" name="imeKorisnika" value="" placeholder="Unesite ime"></td>
			</tr>
			<tr>
				<td align="right">Prezime korisnika<span style="color:red">*</span>:&nbsp;<input type="text" id="prezimeKorisnika" name="prezimeKorisnika" value=""></td>
			</tr>
			<tr>
				<td align="right">Korisnicko ime<span style="color:red">*</span>:&nbsp;<input type="text" id="korisnickoIme" name="korisnickoIme" value=""></td>
			</tr>
			<tr>
				<td align="right">Lozinka<span style="color:red">*</span>:&nbsp;<input type="password" id="lozinka" name="lozinka" value=""></td>
			</tr>
			<tr>
				<td align="right">Lozinka (ponovljena):&nbsp;<input type="password" id="lozinka1" name="lozinka1" value=""></td>
			</tr>
			<tr>
				<td><input type="submit" id="submitDugme" name="submitDugme" value="Snimi"><input type="submit" formaction="login.jsp" value="Login stranica"></td>
			</tr>
		</table>
	</fieldset>
</form>
</div>
</body>
</html>