package com.gft.architecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// questa classe si occuperà di aprire e chiudere le connessioni col DB

public class DBAccess implements DAOConstants {
	private static Connection conn;
	
	public static synchronized Connection getConnection()
			throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection( JDBC_URL, USERNAME, PASSWORD );
		
		// cambio l'impostazione JDBC che autocommitta i dati, togliendo l'autocommit
		// il DB no, a meno che non setto di autocommittare (non è bene però
		// settare una creazione tabella che si autocommitta)
		conn.setAutoCommit( false );
		
		return conn;
	}
	
	public static void closeConnection() throws SQLException {
	// questo non è synchronized: dato che la Connection è statica,
	// è già sincronizzata dal metodo di apertura, non c'è bisogno 
	// di sincronizzarla anche qui
		if( conn != null)
			conn.close();
	}
	
}
