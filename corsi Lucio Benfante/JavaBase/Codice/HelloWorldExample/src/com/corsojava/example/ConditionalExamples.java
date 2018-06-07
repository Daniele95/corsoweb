package com.corsojava.example;

public class ConditionalExamples {

	public static void main(String[] args) {
		int a = 1, b=2, c = 3, d=4;
		String username = "benfante";
		
		a = (b = (c = 23));
		
		if (a == b) {
			// ...
		} else {
			//...
		}
		
		if (a ==b && b == c) {
			// ..
		} 

		if (a ==b || b == c) {
			// ..
		} 

		if (a ==b & ((d=5) == c)) {
			// ..
		} 

		if (a ==b & loadAndCheck(username)) {
			// ..
		} 

		
		String s1 = "pippo";
		String s2 = "topolino";
		String s3 = "pippo";
		String s4 = new String("pippo");
		String s5 = null;
		
		if (s1 == s3) { // true
			// ...
		}
		
		if ( s1 == s4) { // false
		}
			
		if (s1.equals(s4)) { // true
		}
			
		if (s5.equals(s1)) { // NullPointerException
		}
		
		if (s5 != null && s5.equals(s1)) {}
		
		if (s5.equals("pippo")) {} // NullPointerException
		
		if ("pippo".equals(s5)) {}  // false
	}

	private static boolean loadAndCheck(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
