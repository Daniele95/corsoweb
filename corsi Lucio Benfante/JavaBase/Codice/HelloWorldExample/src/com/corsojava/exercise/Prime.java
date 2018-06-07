package com.corsojava.exercise;

public class Prime {
	public boolean isPrime(long n) {
		boolean result = true;
		long maxDivisor = (long) Math.sqrt(n);
		for (long i = 2; i <= maxDivisor; i++) {
			if(n % i == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
