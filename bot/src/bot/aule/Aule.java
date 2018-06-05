package bot.aule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Aule {

	private static HashMap<Integer, String> map = new HashMap<Integer, String>();
	
	public Aule() {
		File aule = new File("src/bot/aule/aule");
		map = new HashMap<Integer, String>();
		String line;
		
		try {
			FileReader fileReader = new FileReader(aule);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null) {
			    Integer key = Integer.parseInt(line.substring(0, line.indexOf("\t")));
				String aula = line.substring(line.indexOf("\t"),line.length());
				aula = aula.replace("\t", "");
			    map.put(key, aula);
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static int IDAula(String nomeAula) {
		return getKeyByValue(map,nomeAula);
	}
	
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}	

	// questo metodo produce un file 'aule' a cui devo cancellare le ultime due righe
	// altrimenti ottengo errore
	String ricaricaFileAule() {
		File aule = new File("src/bot/auleHTML");
		String line;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			FileReader fileReader = new FileReader(aule);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				line = line.substring(line.indexOf("rel"),line.length());			
				line = line.substring(0, line.indexOf("'>"));				
				line = line.replace("' title='", "\t");			
				line = line.replace("rel='", "");
				stringBuffer.append(line);
				stringBuffer.append("\n");
				
			}
			fileReader.close();
			PrintWriter out = new PrintWriter("src/bot/aule/aule");
			out.close();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
}
