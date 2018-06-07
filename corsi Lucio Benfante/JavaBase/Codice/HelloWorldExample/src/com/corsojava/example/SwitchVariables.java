package com.corsojava.example;

public class SwitchVariables {

	public static void main(String[] args) {
		int a = 12 ,b = 3;
		print(a, b);
		
		/* NOOO!
		 * 
		 * a = b;
		 * b = a;
		 * 
		 */
		
		// Con variabile temporanea aggiuntiva
		int tmp = a;
		a = b;
		b = tmp;
		
		print(a, b);

		// Senza variabile aggiuntiva
		a = a + b;
		b = a - b;
		a = a - b;
		
		print(a, b);

	}

	private static void print(int a, int b) {
		System.out.println("a = "+a+", b = "+b);
	}

}
