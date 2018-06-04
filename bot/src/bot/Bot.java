package bot;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.html.dom.HTMLBuilder;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


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
	
	public static HtmlPage submitButton( HtmlPage page, String id ) {    		
		HtmlPage p = null;
		HtmlButton button = (HtmlButton) page.getElementById(id);
		try {
			p = button.click();
		} catch (Exception e) {
			System.out.println("impossibile schiacciare il bottone "+ button.asText());
		}
		return p;
	}
	
	
	public static void submittingForm() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	    	
	        HtmlPage home = webClient.getPage("https://consmilano.asimut.net/public/login.php");	        
	        fillField(home, "authenticate-useraccount","tsimur.shved@studenti.consmilano.it");
	        ((HtmlElement) home.getElementById("authenticate-password")).type("temporanea");
			home.executeJavaScript("submitLoginForm()");
      
			

			
			
	   	    try {
	   	    	String pag = paginaNuovaPrenotazione("32", orarioAMilllisecondi ( 6, 6,2018, 8, 30 ), 2018, 6,4 );
	   	     
		    	final HtmlPage prenotazione = webClient.getPage(pag);
		    	
				fillField(prenotazione, "event-date","6/6/2018");
				fillField(prenotazione, "event-starttime","08:45");
				fillField(prenotazione, "event-endtime","09:15");
				fillField(prenotazione, "event-location","Aula114");
				
				System.out.println(((HtmlTextInput) prenotazione.getElementById("event-date")).getText());
				System.out.println(((HtmlTextInput) prenotazione.getElementById("event-starttime")).getText());
				System.out.println(((HtmlTextInput) prenotazione.getElementById("event-endtime")).getText());
				System.out.println(((HtmlTextInput) prenotazione.getElementById("event-location")).getText());
				

				home.executeJavaScript("saveEvent()");
				home.executeJavaScript("saveEvent()");
				
				DomElement button = prenotazione.getElementById("event-button-save");
				HtmlPage lastPage = button.click();
				System.out.println(lastPage.getBaseURI());
				
				 button = prenotazione.getElementById("event-button-save");
				 lastPage = button.click();
				System.out.println(lastPage.getBaseURI());
				
				
			    
		    } catch (Exception e) {
		    	System.out.println("è cambiato il link di Pagina prenotazione?");
		    }
		  
	    }
	}	
	
	
	public static String paginaNuovaPrenotazione(String aula, String orario, int anno, int mese, int giorno) {
		
	
		return 	"https://consmilano.asimut.net/public/event.php"+
				// id=0 -> prenotazioni
				// lokid=29 indica l'aula 112 (corrisponde al rel dell'elemento HTML),
				// start -> è l'orario a cui prenoto, in millisecondi
				"?id=0&start="+orario+"&lokid="+aula+"&url="+
				// la stringa "tutte le aule" codificata
				"https%3A//consmilano.asimut.net/public/index.php%3Fdato%3D"+data(anno, mese, giorno)+"%26akt%3Dvisgruppe%26id%3D3";
	}
	public static String data (int year,int  month,int giorno) {		
		GregorianCalendar c = new GregorianCalendar(year,month-1,giorno);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	    fmt.setCalendar(c);
	    String dateFormatted = fmt.format(c.getTime());
	    return dateFormatted;
	}
	public static String  orarioAMilllisecondi (int giorno,int mese, int anno, int ora, int minuto ) {		
		// per qualche motivo bisogna togliere 1 al mese per avere il giusto risultato
		GregorianCalendar c = new GregorianCalendar(anno,mese-1,giorno,ora,minuto);
		int millisecs = (int) Math.floor(c.getTimeInMillis()/1000);
		return String.valueOf(millisecs);
	}	

    public static void main(final String[] args) throws Exception {

        log.setLevel(Level.OFF);
    
        submittingForm();
    }

}