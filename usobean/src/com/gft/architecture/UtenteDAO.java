package com.gft.architecture;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;

import com.gft.beans.Utente;
import com.sun.rowset.CachedRowSetImpl;

public class UtenteDAO implements DAOConstants {
	
	public static UtenteDAO getFactory() throws SQLException, ClassNotFoundException {
		return new UtenteDAO();
	}

	private Connection conn;
	// CachedRowSet è un componente per interagire col DB in modo sincronizzato
	// (usato sopratutto in EE) -> le query di più client non possono mandare in stallo l'app. sovrapponendosi
	// limita al massimo il tempo di connessione al DB:
	// si disconnette dopo averli salvati su una tabella in CACHE,
	//li modifica, e si riconnette per rimandarglieli
	// insomma può lavorare offline (diversamente dal metterli in un resultSet,
	// che richiede di tenere aperta la connessione
	private CachedRowSet rowSet;
	
	private UtenteDAO() throws SQLException, ClassNotFoundException {
		conn = DBAccess.getConnection();
		// istanzio una classe che implementa CachedRowSet
		rowSet = new CachedRowSetImpl();
		rowSet.setCommand( SELECT_UTENTE );
		// CachedRowSetImpl prende come argomento una connessione
		rowSet.execute( conn );		
		// a questo punto la connessione è chiusa, ma i miei dati sono
		// salvati dal RowSet nella tabella in cache!!
	}
	
	// funzione: crea l'utente usando i dati passati dal form
	// che passo al metodo create sotto forma di oggetto Utente
	public void create( Utente utente ) throws SQLException {
		// inserisco i dati nel database!!
		rowSet.moveToInsertRow();
		rowSet.updateString( 1, utente.getNome() ); // prima colonna del resultSet
		rowSet.updateString( 2, utente.getCognome() );
		rowSet.updateString( 3, utente.getUsername() );
		// inserisco la riga in memoria
		rowSet.insertRow();
		// mi sposto sulla riga non ancora inizializzata per preparare l'inserimento
		rowSet.moveToCurrentRow();
		// per poter fare il commit dei dati con il metodo:
		// acceptChanges fa di nuovo la connessione, salva sul database, e la chiude
		rowSet.acceptChanges();		
	}
	
	public ArrayList<Utente> getUtenti() throws SQLException {
		ArrayList<Utente> lista = new ArrayList<Utente>();
		// mi riposiziono prima della prima riga: così posso usare questo metodo 
		// più volte senza errore
		rowSet.beforeFirst();
		while( rowSet.next() ) {
			Utente utente = new Utente();
			utente.setNome( rowSet.getString( 1 ) );
			utente.setCognome( rowSet.getString( 2 ) );
			utente.setUsername( rowSet.getString( 2 ) );
			lista.add( utente );
		}
		return lista;
	}
	
}
