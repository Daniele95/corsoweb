import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.lang.Math;
import pacchetto.Grafico.*;

public class EsercizioListe {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		List< List<Integer> > laLista = new ArrayList<List<Integer>>();
		
		int n = scan.nextInt();
		
		for (int i = 0; i < n; i++ ) { // ciclo sulle righe
			List<Integer> numeriLettiRiga = new ArrayList<Integer>();
			int d = scan.nextInt();
			for(int j=0;j<d;j++) { // ciclo sui numeri della riga
				numeriLettiRiga.add(scan.nextInt());
			}
			laLista.add(numeriLettiRiga);
		}	
				
		
		
		
		System.out.println();
		int c = scan.nextInt();
		
		for(int i = 0; i<c; i++ ) {
			int q = scan.nextInt()-1;
			int f = scan.nextInt()-1;
			if(laLista.size() > q) {
				if(laLista.get(q).size() > f ) {
					System.out.println(laLista.get(q).get(f));
				}else System.out.println("error");
			}else System.out.println("error");
			
		}
		
		
		
	}
}
