package com.gft.architecture;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.gft.beans.Utente;
import com.sun.rowset.CachedRowSetImpl;

public class UtenteDAO implements DAOConstants {
	private Connection conn;
	// CachedRowSet è un componente sincronizzato, utile per interagire col DB
	// limita al massimo il tempo di connessione al DB:
	// si disconnette dopo averli salvati, li modifica, e si riconnette per rimandarglieli
	private CachedRowSet rowSet;
	
	public UtenteDAO() throws SQLException, ClassNotFoundException {
		conn = DBAccess.getConnection();
		// istanzio una classe che implementa CachedRowSet
		rowSet = new CachedRowSetImpl();
		rowSet.setCommand( SELECT_UTENTE );
		// cachedrowsetimpl prende come argomento una connessione
		rowSet.execute( conn );
		
		// a questo punto la connessione è chiusa, ma i miei dati sono
		// salvati nel RowSet!!		
	}
	
	public void create( Utente utente ) throws SQLException {
		// inserisco i dati nel database!!
		rowSet.moveToInsertRow();
		rowSet.updateString( 1, utente.getNome() ); // prima colonna del resultSet
		rowSet.updateString( 2, utente.getCognome() );
		rowSet.updateString( 3, utente.getUsername() );
		// inserisco la riga
		rowSet.insertRow();
		// mi sposto sulla riga non ancora inizializzata per preparare l'inserimento
		rowSet.moveToCurrentRow();
		// per poter fare il commit dei dati con il metodo
		rowSet.acceptChanges();
		
	}
	
}
