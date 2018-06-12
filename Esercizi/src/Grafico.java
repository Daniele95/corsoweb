
import java.util.Random;

public class Grafico {

	static int[] gaussiana;
	static int N;
	Random rand = new Random();
	static int max;
	static int min;
	static int range = 3;
	
	
	public Grafico(int Min, int Max, int Num) {
		max = Max;
		min = Min;
		gaussiana = new int[max*range];
		N = Num;
		
		for (int i = 0; i<N; i++) {
			int myRand = (int) (rand.nextGaussian()*max);
			if(Math.abs(myRand)< max)
				gaussiana[myRand+max*(range/2)] ++ ;
		}		
	}

	public static void stampa() {

		for (int i =0; i<max*range;i++) {
			System.out.print(i+ " ");
			for (int j = 0; j<gaussiana[i]; j++)
				System.out.print('*');
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int min = 0;
		int max = 30;
		int Num = 1900;
		new Grafico(min, max, Num);
		stampa();
	}
	
}
