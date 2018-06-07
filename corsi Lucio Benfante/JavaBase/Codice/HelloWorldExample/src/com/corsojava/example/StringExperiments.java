package com.corsojava.example;

public class StringExperiments {

	public static void main(String[] args) {
		System.out.println("Lunghezza: "+ args[0].length());
		System.out.println("Primo carattere: "+ args[0].charAt(0));
		System.out.println("Ultimo carattere: "+ args[0].charAt(args[0].length()-1));
		System.out.println("Maiuscolo: "+ args[0].toUpperCase());
		

	}

}
