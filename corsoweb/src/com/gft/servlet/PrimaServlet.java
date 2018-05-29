package com.gft.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/url") // nome dell'URL che chiamerà la classe al server

public class PrimaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void processa(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType( "text/html" );
		// response.setBufferSize(9000);

		// catturo i parametri che arrivano da un form
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		int i = Integer.parseInt( request.getParameter("id") );
		
		PrintWriter out = response.getWriter();
		
		out.println( "<!DOCTYPE html>" );
		out.println( "<html><head><title>Primo esempio "
				+ "intercettazione richiesta</title></head>" );
		out.println( "<body>" );
		out.println( "<h3>Nome: " + nome + " Cognome: " + cognome+	
				" id: " + i + "</h3>");
		out.println( "</body>" );
		out.println( "</html>" );
		out.close();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processa(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processa(request,response);
	}
}
