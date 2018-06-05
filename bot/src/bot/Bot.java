package bot;
import java.io.File;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import bot.aule.Aule;
import bot.aule.Prenotazione;

public class Bot {

	static Logger log = Logger.getLogger("com.gargoylesoftware");
	
	public static void fillField( HtmlPage page, String id, String content ) {
		final HtmlTextInput field = (HtmlTextInput) page.getElementById(id);
		field.setText("");
		try {
			field.type(content);
		} catch (Exception e) {
			System.out.println("impossibile riempire il campo " + field.getId());
			e.printStackTrace();
		}
	}
	
	public static void submittingForm(String nomeAula, int anno, int mese, int giorno,
			int oraInizio, int minutoInizio, int oraFine, int minutoFine) throws Exception {
		
	    try (final WebClient webClient = new WebClient()) {
	    	
	        HtmlPage home = webClient.getPage("https://consmilano.asimut.net/public/login.php");	        
	        fillField(home, "authenticate-useraccount","tsimur.shved@studenti.consmilano.it");
	        ((HtmlElement) home.getElementById("authenticate-password")).type("temporanea");			
			home.executeJavaScript("submitLoginForm()");			
			
	   	    try {
	   	    	// classi ausiliarie:
	   	        new Aule();	new Prenotazione();
	   	        
	   	        int aula = Aule.IDAula(nomeAula);
	   	    	String pag = Prenotazione.paginaNuovaPrenotazione(aula,anno, mese,giorno,oraInizio,minutoInizio );
	   	    	
	   	    	//String pag = paginaNuovaPrenotazione("32", orarioAMilllisecondi ( 6, 6,2018, 8, 15 ), 2018, 6,4 );
	   	    	//String pag = "https://consmilano.asimut.net/public/event.php?id=0&start=1528265541&lokid=32&url=https%3A//consmilano.asimut.net/public/index.php%3Fdato%3D20180202%26akt%3Dvisgruppe%26id%3D3";
	   	    	
	   	    	final HtmlPage prenotazione = webClient.getPage(pag);
				
				fillField(prenotazione, "event-date",giorno+"/"+mese+"/"+anno);
				fillField(prenotazione, "event-starttime",oraInizio+":"+minutoInizio);
				fillField(prenotazione, "event-endtime",oraFine+":"+minutoFine);
				fillField(prenotazione, "event-location", nomeAula);				
	

				File aule = new File("src/bot/files/saveEvent.js");
				String saveEvent = Prenotazione.readFile(aule.getAbsolutePath(), Charset.defaultCharset());
				prenotazione.executeJavaScript(saveEvent);
				
				//prenotazione.executeJavaScript("saveEvent()");
				webClient.waitForBackgroundJavaScript(10000);				
				System.out.println(prenotazione.asText());				
			    
		    } catch (Exception e) {
		    	System.out.println("è cambiato il link di Pagina prenotazione?");
		    }
		  
	    }
	}	
	
	

    public static void main(final String[] args) throws Exception {

        log.setLevel(Level.OFF);
		submittingForm( "Aula 114",2018,6,7, 		8, 15, 		8,45);
       
    }

}