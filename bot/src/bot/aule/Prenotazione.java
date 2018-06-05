package bot.aule;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Prenotazione {

	public static String readFile(String path, Charset encoding) {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			System.out.println("impossibile leggere il file "+path);
			e.printStackTrace();
		}
		return new String(encoded, encoding);
	}

	public static String paginaNuovaPrenotazione(int aula, int anno, int mese, int giorno,int ora, int minuto) {	
		
		String orario = orarioAMillisecondi ( anno, mese, giorno,ora, minuto);
		
		return 	"https://consmilano.asimut.net/public/event.php"+
				// id=0 -> prenotazioni
				// lokid=29 indica l'aula 112 (corrisponde al rel dell'elemento HTML),
				// start -> è l'orario a cui prenoto, in millisecondi
				"?id=0&start="+orario+"&lokid="+aula+"&url="+
				// la stringa "tutte le aule" codificata
				"https%3A//consmilano.asimut.net/public/index.php%3Fdato%3D"+data(anno, mese, giorno)+"%26akt%3Dvisgruppe%26id%3D3";
	}
	public static String data (int anno,int  mese,int giorno) {		
		GregorianCalendar c = new GregorianCalendar(anno,mese-1,giorno);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	    fmt.setCalendar(c);
	    String dateFormatted = fmt.format(c.getTime());
	    return dateFormatted;
	}
	public static String  orarioAMillisecondi (int anno, int mese, int giorno,int ora, int minuto ) {		
		// per qualche motivo bisogna togliere 1 al mese per avere il giusto risultato
		GregorianCalendar c = new GregorianCalendar(anno,mese-1,giorno,ora,minuto);
		int millisecs = (int) Math.floor(c.getTimeInMillis()/1000);
		return String.valueOf(millisecs);
	}
	
	
}
