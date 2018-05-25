package com.gft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// creo una classe che mi connette al DB.
// ciò non va mai fatto in una JSP, ma sempre nel Java

public class MusicServlet extends HttpServlet implements DAOConstants {
	private static final long serialVersionUID = 1L;
	// questo oggetto mi permette di tenere traccia della conn.
	private Connection conn;
	// lo Statement è l'oggetto che mantiene le istruzioni SQL
	// da inviare al database
	private Statement stmt;
	private PreparedStatement ps;
	
	@Override
	public void destroy() {
		// chiudo la connessione col DB
		try {
			if( conn != null ) {
				stmt.close();
				ps.close();
				conn.close();
			}
		} catch( SQLException sql ) {
			System.out.println( "Motivo: " + sql.getMessage() );
			System.out.println( "Codice errore: " );
			sql.printStackTrace();
		}
	}

	@Override
	// questo metodo apre la connessione al DB e interroga 
	// le celle che ho scritto nel database
	// mediante il file SQL\dati.sql
	public void init( ServletConfig config ) throws ServletException {
		try {
			// carico il driver che sta sotto lib\ojdbc8.jar
			// il cui indirizzo l'ho salvato nell'interfaccia DAOConstants
			//Class.forName( JDBC_DRIVER );

			// oppure passo i parametri da config xml:
			Class.forName( config.getInitParameter("JDBC_DRIVER"));
			
			
			// getConnection metodo generico, se non carico un driver
			// non so come aprire un database specifico
			conn = DriverManager.getConnection( JDBC_URL, USERNAME, PASSWORD );
			// creo l'istanza dello statement sulla connection

			// oppure passo i parametri da config xml:
			conn = DriverManager.getConnection( 
					config.getInitParameter("JDBC_URL"),
					config.getInitParameter("USERNAME"),
					config.getInitParameter("PASSWORD")
					);
			// In questo caso i parametri definiti nell'interfaccia DAO
			// diventano inutili
			
			stmt = conn.createStatement();
			
		} catch( ClassNotFoundException exc ) {
			System.out.println( "Impossibile caricare il driver" );
			exc.printStackTrace();
		} catch( SQLException sql ) {
			// motivi: errore credenziali, database non raggiungibile,...
			System.out.println( "Motivo: " + sql.getMessage() );
			System.out.println( "Codice errore: " );
			sql.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// metodo più articolato, decide COSA SI DEVE FARE nel momento in cui
		// arriva la richiesta dalla pagina web
		// 1. dico come dev'essere implementata la risposta:
		response.setContentType( "text/html" );
		// "text/html" è il MIME, multipurpose internet main extension
		// 2. catturo la richiesta:
		PrintWriter out = response.getWriter();
		
		// leggo un context parametr da xml: questo è accessibile qualsiasi server
	//	String param = this.getServletContext().getInitParameter( "test" );
		
		// 3. prendo il valore che arriva dal form jsp
		// nome= "music", valore va da 1 a 5
		int valore = Integer.parseInt( request.getParameter( "music" ) );
		
		// creo la pagina di risposta, in HTML5		
		out.println( "<!DOCTYPE html>" );
		out.println( "<html>" );
		out.println( "<head>" );
		
		// voglio aggiornare la tabella statistica settando
		// voti = voti+1 all'id catturato dall'utente (index.jsp)
		// e salvato in 'int valore'
		// per eseguire le istruzioni salvate in DAOConstants
		// (UPDATE_MUSICA e SELECT_SOMMA), cioè istruz senza parametri, si usa Statement
		// invece per eseguire istruzioni variabili, cioè parametrizzate,
		// si usa PreparedStatement
		// voglio anche stampare nell'html l'eccezione restituita dal DB

		try {
			ps = conn.prepareStatement( UPDATE_MUSICA );
			// i punti domanda che metto nella stringa SQL (UPDATE_MUSICA)
			// vengono indicizzati. se chiamo il set con indice 1,
			// mi va a settare il primo ?
			ps.setInt( 1, valore );
			// uso executeUpdate se faccio un aggiornamento,
			ps.executeUpdate();
			// altrimenti farei executeQuery se dovessi fare una richiesta
			// qui uso uno statement 'stmt' poiché è statica
			ResultSet rs = stmt.executeQuery( SELECT_SOMMA );
			// il ResultSet ha il cursore di spostamento
			// posizionato prima della prima riga di dati
			// quindi non posso fare ora rs.get() perché
			// prima devo inizializzare alla riga giusta il ResultSet
			rs.next();
			// gli indici di colonna partono da 1, quindi in questo caso
			// indice numerico e indice colonna coincidono
			int totale = rs.getInt( 1 );
			// usando più ResultSet contemporaneamente, rischio di ottenere
			// l'eccezione ResultSet esaurito, quindi sempre chiuderli
			// dopo che finisco di usarli
			rs.close();
			ResultSet dati = stmt.executeQuery( SELECT_MUSICA );
			
			out.println( "<title>Analisi dati</title>" );
			out.println( "</head>" );
			out.println( "<body>" );
			out.println( "<h3>Risultati statistica</h3>" );
			// ora di dati ne ho più di uno -> li scorro
			// SELECT_MUSICA = select scelta, voti, id from statistica order by voti desc
			int voti;
			// leggo i dati dal db (le righe nel resultset) e li formatto
			while( dati.next() ) {
				out.print( dati.getString( 1 ) );
				out.print(": ");
				voti = dati.getInt( 2 );
				out.printf( "%.2f", (double)voti/totale * 100 );
				out.print( "% risposte: ");
				out.println( voti );
			}
			out.print( "<br>Totale risposte: ");
			out.println( totale );
			out.println("<br><a href=\"index.jsp\">Torna alla scelta</a>");
		} catch( SQLException sql ) {
			// mostro un titolo differente nel caso si registri un'eccezione
			out.println( "<title>Analisi dati</title>" );
			out.println( "</head>" );
			out.println( "<body>" );
			out.println( "<h3>Eccezione durante l'esecuzione</h3>" );
			out.println( "<p>" + sql.getMessage() + "</p>" );
			out.println( "<p>" + sql.getErrorCode() + "</p>" );
		}	
		out.println( "</body>" );
		out.println( "</html>" );
		out.close();	
		
	}

}
