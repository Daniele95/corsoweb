package bot;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


public class Test {

	static Logger log = Logger.getLogger("com.gargoylesoftware");
	
	public static String dataOdierna () {
		
		// dal file js/global.js?r17125-52-2
		// vedo che l'elemento html event-locationgroup, se cliccato,
		// mi restituisce un link con la data 20180531
		
		String t = LocalDateTime.now().toString();
	    t = t.substring(0, 10);
	    t = t.replace("-", "");
	    System.out.println(t);
	    return t;
	}
	
	public static String tutteLeAule() {
		// L'id "3" indica "tutte le aule" nei gruppi di aule (visualizzazione "visgruppe")
		return "index.php?dato=" + dataOdierna() + "&akt=visgruppe&id=3";		
	}
	
	static String readFile(String path, Charset encoding) {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			System.out.println("impossibile leggere il file "+path);
			e.printStackTrace();
		}
		return new String(encoded, encoding);
	}
	
	public static String paginaNuovaPrenotazione(String aula, String orario) {
		
		aula = "29"; // corrisponde alla 112, vedi
		// view-source:https://consmilano.asimut.net/public/index.php?dato=20180531&akt=visgruppe&id=3
		orario = "1527785788";
		
		return 	"https://consmilano.asimut.net/public/event.php"+
				// id=0 -> prenotazioni
				// lokid=29 indica l'aula 112 (corrisponde al rel dell'elemento HTML),
				// start -> è l'orario a cui prenoto, in millisecondi
				"?id=0&start="+orario+"&lokid="+aula+"&url="+
				// la stringa "tutte le aule" codificata
				"https%3A//consmilano.asimut.net/public/index.php%3Fdato%3D20180531%26akt%3Dvisgruppe%26id%3D3";
	}
	
	public static String  orarioAMilllisecondi (int giorno,int mese, int anno, int ora, int minuto ) {		
		// per qualche motivo bisogna togliere 1 al mese per avere il giusto risultato
		GregorianCalendar c = new GregorianCalendar(anno,mese-1,giorno,ora,minuto);
		return String.valueOf(c.getTimeInMillis());
	}
	
	public static void submittingForm() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	    	
	        // Get the first page
	        final HtmlPage page1 = webClient.getPage("https://consmilano.asimut.net/public/login.php");
	        
	        // Get the form that we are dealing with and within that form, 
	        // find the submit button and the field that we want to change.
	        final HtmlForm form = page1.getHtmlElementById("login");

	        // create a submit button - it doesn't work with 'input'
	        HtmlElement button = (HtmlElement) page1.createElement("button");
	        button.setAttribute("type", "submit");
		    // append the button to the form
		    form.appendChild(button);	
		    // submit the form

	        final HtmlTextInput usernameField = form.getInputByName("authenticate-useraccount");
	        final HtmlInput passwordField = form.getInputByName("authenticate-password");
	        // Change the value of the text field
	        usernameField.type("tsimur.shved@studenti.consmilano.it");
	        passwordField.type("temporanea");
	        
		    // final HtmlElement button = page1.getHtmlElementById("authenticate-submit");
		    //final HtmlPage page2 = button.click();        
 
	        // prendo il link "Tutte le aule" non cliccando il bottone
		    // ma scrivendo direttamente il link (infatti è generato da Javascript)
		    // ho più problemi che il link cambi ma non mi rompo le palle
		    try {

		        final HtmlPage page3 = webClient.getPage("https://consmilano.asimut.net/public/"+
		        		tutteLeAule() );
		        
		        System.out.println(page3.getBaseURI());	        
				System.out.println(page3.asText());
		        
				// eseguo codice Javascript nella pagina:
				/*String javaScriptCode = readFile("C:\\Users\\Administrator\\eclipse-workspace\\bot\\src\\bot\\newReservation.js",Charset.defaultCharset());
				ScriptResult result = page3.executeJavaScript(javaScriptCode);
				result.getJavaScriptResult();
				System.out.println("result: "+ result.toString());*/
				
		    }catch (Exception e) {
		    	System.out.println("è cambiato il link di Tutte le Aule?");
		    }
	        
		    try {

		        final HtmlPage page4 = webClient.getPage( paginaNuovaPrenotazione("29", 
		        		orarioAMilllisecondi(31, 5, 2018, 17, 35) ) );
		        
		        System.out.println(page4.getBaseURI());	        
				System.out.println(page4.asText());
		        
				// eseguo codice Javascript nella pagina:
				/*String javaScriptCode = readFile("C:\\Users\\Administrator\\eclipse-workspace\\bot\\src\\bot\\saveEvent.js",Charset.defaultCharset());
				ScriptResult result = page3.executeJavaScript(javaScriptCode);
				result.getJavaScriptResult();
				System.out.println("result: "+ result.toString());*/
				
		    }catch (Exception e) {
		    	System.out.println("è cambiato il link di Pagina prenotazione?");
		    }
		    
		    
		    
	        
	    }
	}	
	
	
	public static void homePage() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	    	
	    	
	        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	        Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());
	
	        final String pageAsXml = page.asXml();
	        Assert.assertTrue(pageAsXml.contains("<body class=\"topBarDisabled\">"));
	
	        final String pageAsText = page.asText();
	        Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	        
	        
	    }
	}
	
    public static void main(final String[] args) throws Exception {

        log.setLevel(Level.OFF);

        submittingForm();
    }

}