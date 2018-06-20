package progettoSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrimaConnessione {

	public static void main(String[] args) throws SQLException {
		
		// Connection implementa AutoCloseable (viene chiusa in automatico) 
		//per questo posso  dichiararne una dentro le parentesi del try
		try ( 	Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/sakila?serverTimezone=UTC",
				"root", "root"); 
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT film_id, title FROM film");	
			)  
		{	
			if(conn != null) {
				System.out.println("connessione effettuata!");
				if( conn.isValid(10))
					System.out.println(" e valida");
				System.out.println(conn.getClass().getName());
			} 
			while(rs.next()) {
				int id = rs.getInt("film_id");
				// varchar dell'sql -> String del Java
				String title = rs.getString(2);
				System.out.println(id+" "+title);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
