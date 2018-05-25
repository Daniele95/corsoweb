package com.gft.architecture;

public interface DAOConstants {
	// tutte le costanti che mi servono per interagire col database
	// per il driver, referenzio le librerie del progetto
	// Libraries (ho messo il driver sotto webContent/WEB-INF\lib
	// se scrivo male il percorso avrò ClassNotFoundExc.
	String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	// url = ip:portaDB:nomeDatabase
	String JDBC_URL = "jdbc:oracle:thin:@192.168.18.117:1521:ORCL";
	String USERNAME = "a1";
	String PASSWORD = "pass";
	
	String SELECT_UTENTE = "select nome, cognome, username from utente";
}
