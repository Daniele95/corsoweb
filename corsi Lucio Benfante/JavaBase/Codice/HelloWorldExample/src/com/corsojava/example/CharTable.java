package com.corsojava.example;

public class CharTable {

	public static void main(String[] args) {
//		solution1();
//		solution2();
		solution3();
	}

	private static void solution1() {
		for(char c = 'a'; c <= 'z'; c++) {
			int uppercase = c-'a'+'A';
			System.out.println("'"+c+"' = "+(int)c+"\t\t'"+uppercase+"' = "+(int)uppercase);
		}
	}

	private static void solution2() {
		for(char c = 'a', uppercase = 'A'; c <= 'z'; c++, uppercase++) {
			System.out.println("'"+c+"' = "+(int)c+"\t\t'"+uppercase+"' = "+(int)uppercase);
		}
	}

	private static void solution3() {
		for(char c = 'a', uppercase = 'A'; c <= 'z'; c++, uppercase++) {
			System.out.printf("'%1$c' = %1$3d\t\t'%2$c' = %2$3d\n", (int)c, (int)uppercase);
		}
	}
	
}
