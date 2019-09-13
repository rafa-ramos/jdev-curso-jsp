<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="myprefix" uri="WEB-INF/testtag.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<h1>Hello world JSP</h1>
	<%
		out.print("seu sucesso garantido");
	%>

	<form action="receber-nome-2.jsp" method="get">
		<input type="text" id="nome" name="nome"/> 
		<input type="submit" value="Enviar"/>
	</form>
	<% session.setAttribute("usuarioLogado", "Rafael Ramos"); %>

	<%!/*tag declarativa*/
	int cont = 2;

	public int retorna(int n) {
		return n * 3;
	}%>

	<%=cont%>
	<br>
	<%=retorna(8)%>
	<br>
	<%=application.getInitParameter("estado")%>
	
	
	<%@ include file="pagina-include.jsp" %>
	
	<myprefix:minhatag/>
</body>
</html>