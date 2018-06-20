package servlet;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import bot.aule.Aule;
import bot.aule.Prenotazione;

public class Bot {

	static WebClient webClient = new WebClient();
	static HtmlPage paginaPrenotazione = null;
	
	static Logger log = Logger.getLogger("com.gargoylesoftware");

	public Bot(String nomeAula, GregorianCalendar cal, int oraFine, int minutoFine) {
		
	    login();	
	    
	    /* iteratore
		Iterator<Entry<Integer, String>> it = Aule.getMap().entrySet().iterator();
		
	    while (it.hasNext()) {
	    	Map.Entry<Integer,String> pair = (Map.Entry<Integer,String>)it.next();
			fillField(paginaPrenotazione, "event-location", pair.getValue().toString());
	    	
			boolean possoSalvare = possoSalvare();
			if (possoSalvare) {
				
	//			pair.getKey() + "\t" + pair.getValue().toString());
				
			} else System.out.println();
	    }
		it.remove();
	    */
	    
        boolean prenotazioneRiuscita = provaAEffettuarePrenotazione(nomeAula, cal, oraFine, minutoFine);
        System.out.println(prenotazioneRiuscita);
        System.out.println("----------------------------------------------");
       
	   
	}

	
	public static void login() {
		HtmlPage home;
		try {
			home = (HtmlPage) webClient.getPage("https://consmilano.asimut.net/public/login.php");   
		    fillField(home, "authenticate-useraccount","tsimur.shved@studenti.consmilano.it");
		    ((HtmlElement) home.getElementById("authenticate-password")).type("temporanea");			
			home.executeJavaScript("submitLoginForm()");
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}	     
	}
	
	public static boolean provaAEffettuarePrenotazione(String nomeAula, GregorianCalendar cal, int oraFine, int minutoFine) {
		nuovaPrenotazione(nomeAula, cal);
		riempiCampi( nomeAula, cal, oraFine, minutoFine);
		boolean possoSalvare = possoSalvare();
		if(possoSalvare)
			prenota();
		return possoSalvare;
	}
	
	
	public static void nuovaPrenotazione(String nomeAula, GregorianCalendar cal) {
		// classe ausiliaria per creare il link di prenotazione:
		new Prenotazione();	
		// e per trovare l'id corrispondente all'aula:
		new Aule("src/bot/aule/aule");
		
	    int aula = Aule.IDAula(nomeAula);
	   	String pag = Prenotazione.linkNuovaPrenotazione(aula,cal );	   	  
		try {
			paginaPrenotazione = webClient.getPage(pag);			
		} catch (Exception e) {e.printStackTrace();} 		
	}
	
	public static void riempiCampi (String nomeAula,GregorianCalendar cal, int oraFine, int minutoFine) {
		fillField(paginaPrenotazione, "event-date",cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR));
		fillField(paginaPrenotazione, "event-starttime",cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
		fillField(paginaPrenotazione, "event-endtime",oraFine+":"+minutoFine);
		fillField(paginaPrenotazione, "event-location", nomeAula);
	}
	
	public static boolean possoSalvare () {
		paginaPrenotazione.executeJavaScript(  Prenotazione.readFile("src/bot/files/check.js")   );
		webClient.waitForBackgroundJavaScript(1000);
		String result = paginaPrenotazione.getElementById("form-messages").asText();
		System.out.println(result);
		return result.contains("Posso salvare: true");
	}
	public static String prenota () {
		paginaPrenotazione.executeJavaScript(  Prenotazione.readFile("src/bot/files/saveEvent.js")   );				
		webClient.waitForBackgroundJavaScript(1000);
		return paginaPrenotazione.asText();
	}
	
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

	public static void waitUntil (int myDay, int myHour, int myMinute) {
		  	Calendar calendar = Calendar.getInstance();        
	        
	        int minute = 0;
	        int hour = 0;
	        int day = 0;
	    	while (day < myDay || hour < myHour || minute<myMinute) {
	            calendar.setTime(new Date());
	            day = calendar.get(Calendar.DAY_OF_MONTH);
	            hour = calendar.get(Calendar.HOUR_OF_DAY);
	            minute = calendar.get(Calendar.MINUTE);
	    		try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {e.printStackTrace();}
	    		System.out.println(calendar.get(Calendar.YEAR) + "/"+calendar.get(Calendar.MONTH) + "/" + day + " - " + hour + ":" + minute);
	    	}	    	
	}
	
	/*
    public static void main(final String[] args) {
    	
    //	log.setLevel(Level.OFF);
      
    //	waitUntil(7,16,01);
    	new Bot("Aula 114",new GregorianCalendar(2018,6,13,14,00),16,00);
        
    	
    }
*/
}