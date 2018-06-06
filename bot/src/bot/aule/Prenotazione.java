package bot.aule;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Prenotazione {

	public static String readFile(String path) {
		File pathParziale = new File("src/bot/files/check.js");	
		
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			System.out.println("impossibile leggere il file "+pathParziale.getAbsolutePath());
			e.printStackTrace();
		}
		return new String(encoded, Charset.defaultCharset());
	}

	public static String linkNuovaPrenotazione(int aula, GregorianCalendar cal ) {	
		
		// per qualche motivo le due funzioni seguenti aggiungono un mese!!
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String orario = orarioAMillisecondi ( cal);
		String data = data(cal);
		
		return 	"https://consmilano.asimut.net/public/event.php"+
				// id=0 -> prenotazioni
				// lokid=29 indica l'aula 112 (corrisponde al rel dell'elemento HTML),
				// start -> è l'orario a cui prenoto, in millisecondi
				"?id=0&start="+orario+"&lokid="+aula+"&url="+
				// la stringa "tutte le aule" codificata
				"https%3A//consmilano.asimut.net/public/index.php%3Fdato%3D"+data+"%26akt%3Dvisgruppe%26id%3D3";
	}
	public static String data (GregorianCalendar c) {	
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	    fmt.setCalendar(c);
	    String dateFormatted = fmt.format(c.getTime());
	    return dateFormatted;
	}
	public static String  orarioAMillisecondi (GregorianCalendar c ) {		
		int millisecs = (int) Math.floor(c.getTimeInMillis()/1000);
		return String.valueOf(millisecs);
	}
	
	
}
