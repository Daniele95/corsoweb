<%@page import="com.gft.architecture.UtenteDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- pagina d'errore: sarà mostrata nel momento in cui ho delle eccezioni -->
<%@ page errorPage="error.jsp"%>
<!-- ISTANZIO IL BEAN
nome univoco della classe che sarà creata come bean
 nell'app è "utente"  e chiama la classe, visibilità "page" necessaria per il DB,
 che deve vedere la pagina col form -->
<jsp:useBean id="utente" class="com.gft.beans.Utente" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<title>Registrazione</title>
</head>
<body>

<!-- TIRO SU I DATI DALL'HTML DENTRO ALLA CLASSE UTENTE
SETTANDO UNA PROPRIETA' DELLA CLASSE UTENTE CON SETPROPERTY
(ANZI LE SETTO TUTTE)
faccio il controllo che i dati siano stati inseriti, ma non faccio la validazione
(in generale però va fatta) -->
<jsp:setProperty name="utente" property="*"/>

<%
	if( utente.getNome() == null || utente.getCognome() == null ||
		utente.getUsername() == null ) {
%>
<!-- la graffa apre e racchiude l'HTML -->
<h3>Inserire dati per la registrazione</h3>
<!-- action="#" vuol dire ricaricare la stessa pagina -->
<form action="#" method="post">
	<table>
		<tr>
			<td style="width: 150px;">Nome</td>
			<td style="width: 150px;">			
				<!-- occhio: qui devo avere gli stessi campi della classe Utente -->
				<input type="text" name="nome" placeholder="Nome..." required>
			</td>
		</tr>
		
		<tr>
			<td>Cognome</td>
			<td><input type="text" name="cognome" placeholder="Cognome..." required></td>
		</tr>
				
		<tr>
			<td>Username</td>
			<td><input type="text" name="username" placeholder="Username..."
			 required maxlength="10"></td>
		</tr>
				
		<tr>
			<td colspan="2">
				<input type="submit" value="Registra utente">
			</td>
		</tr>
	</table>
</form>
<%
	} else {
		// passo un'istanza del DAO popolata con i dati del form!
		// praticamente cioè passo i dati al DB
		UtenteDAO.getFactory().create( utente );
		// reindirizzo. NOTA BENE response è un oggetto implicito
		response.sendRedirect( "report.jsp" );
	}
%>

</body>
</html>