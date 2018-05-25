<%@page import="com.gft.architecture.UtenteDAO"%>
<%@page import="com.gft.beans.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Report dati</title>
</head>
<body>
<h3>Lista utenti</h3>
<table>
	<tr>
		<td style="width: 150px;">Nome</td>
		<td style="width: 150px;">Cognome</td>
		<td style="width: 150px;">Username</td>
	</tr>
	
<%
	ArrayList<Utente> lista = UtenteDAO.getFactory().getUtenti();
	for( Utente utente : lista ) {		
%>

	<tr>
		<td><%= utente.getNome() %></td>
		<td><%= utente.getCognome() %></td>
		<td><%= utente.getUsername() %></td>
	</tr>
<%
	}
%>

</table>
<br>
<a href="index.jsp">Torna all'inserimento</a>
</body>
</html>