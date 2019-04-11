<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*" %>
<%@ page import="utility.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=IResource.INDEX_TITLE%></title>
</head>
<body>

<div id="wrapperDiv">Wrapper div!
<div id="headerDiv" >Header div!</div>
<div id="errorInfo" style="color: red">
<%
	if(session.getAttribute(IParameter.USER_VALIDATION_ERROR) != null)
	{
		out.print(session.getAttribute(IParameter.USER_VALIDATION_ERROR));
		session.removeAttribute(IParameter.USER_VALIDATION_ERROR);
	}
	if(session.getAttribute(IParameter.USER_LOGIN_ERROR) != null)
	{
	out.print(session.getAttribute(IParameter.USER_LOGIN_ERROR));
	session.removeAttribute(IParameter.USER_LOGIN_ERROR);
	}
%>
</div>
<center>
<div id="mainDiv">
<h2><%=IResource.WELCOME%></h2>
<h3><%=IResource.LOGIN%></h3>
<br>
<div id="formDiv" class="formDiv">
<form action="LoginServlet" method="POST">

	<input type="hidden" id="<%=IParameter.TIP_LOGINA_PARAM%>" name="<%=IParameter.TIP_LOGINA_PARAM%>" value="<%=IConstant.MOD_PREGLED%>">
	
	<table>
		<tr>
			<td align="right"><%=IResource.LOGIN_USER_NAME%>&nbsp;<input type="text" id="<%=IParameter.USER_NAME_PARAM%>" name="<%=IParameter.USER_NAME_PARAM%>" value="bato"></td>
		</tr>
		<tr>
			<td align="right"><%=IResource.LOGIN_PASSWORD%>&nbsp;<input type="password" id="<%=IParameter.PASSWORD_PARAM%>" name="<%=IParameter.PASSWORD_PARAM%>" value="bato"></td>
		</tr>
		<tr>
			<td align="right"><input type="submit" id="<%=IParameter.SUBMIT_PARAM%>" name="<%=IParameter.SUBMIT_PARAM%>" value="<%=IResource.PRIJAVA%>"></td>
		</tr>
	</table>
</form>
</div>
<div id="registracijaDiv">
<a href="loginRequestAdd.jsp"><strong><%=IResource.LOGIN_NEW_USER%></strong></a>
</div>
</div>
</center>
<div id="footerDiv" >Footer div!</div>
</div>


</body>
</html>