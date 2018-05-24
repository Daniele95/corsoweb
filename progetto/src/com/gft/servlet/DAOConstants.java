package com.gft.servlet;

public interface DAOConstants {
	// tutte le costanti che mi servono per interagire col database
	// per il driver, referenzio le librerie del progetto
	// Libraries (ho messo il driver sotto webContent/WEB-INF\lib
	// se scrivo male il percorso avrò ClassNotFoundExc.
	String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	String JDBC_URL = "jdbc:oracle:thin:@192.168.18.117:1521:ORCL";
	String USERNAME = "a1";
	String PASSWORD = "pass";
	
	// mi salvo anche una stringa di codice SQL
	// non ci incollo dopo la variabile per evitare sequel injection,
	// e anche perché non potrei (sono in un interfaccia)
	// metto invece il '?' che sarà tirato su dal form
	String UPDATE_MUSICA = "update statistica set voti = voti + 1 where id = ?";
	String SELECT_SOMMA = "select sum(voti) from statistica";
	// sconsigliato l'uso dell'asterisco
	// che potrebbe dare adito a istruzioni malefiche che accedano al DB
	// meglio selezionare tutti i campi che mi interessano:
	//String SELECT_MUSICA = "select * from statistica order by voti desc";
	String SELECT_MUSICA = "select scelta, voti, id from statistica order by voti desc";
	
	
	
}
