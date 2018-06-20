
public class Logica {
	
	public static int D(int a, int b) {
		return Math.abs(a-b);
	}
		
	
	public static boolean vicini(int a, int b, int c) {
		return (D(a,b)<10);
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(vicini(1,2, 0));
	}
}
