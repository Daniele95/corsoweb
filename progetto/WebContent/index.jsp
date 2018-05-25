<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Richiesta</title>
</head>
<body>


<h1>
Qual'è il tuo genere musicale preferito?
</h1>
<!-- 
getServletContextName è un metodo dell'oggetto anonimo 
application (che è automaticamente definito). 
questo metodo mi ritorna dinamicamente
il nome del servlet che ha chiamato l'applicazione
aggiungo l'URL che deve fare la chiamata da server
 -->
<form action="<%=application.getServletContextName()%>/musica" 
method="post">
<input type="radio" name="music" value="1">Rock<br>
<input type="radio" name="music" value="2">Ambient<br>
<input type="radio" name="music" value="3">Progressive<br>
<input type="radio" name="music" value="4">Pop<br>
<input type="radio" name="music" value="5" checked>Nessuno<br>
<input type="submit" value="Invia scelta">
</form>


</body>
</html>