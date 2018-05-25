package com.gft.beans;

// questa classe si chiama come la tabella del database
// ecco il bean, è molto semplice: variabili con getter e setter

public class Utente {	
	private String nome;
	private String cognome;
	private String username;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	
}
