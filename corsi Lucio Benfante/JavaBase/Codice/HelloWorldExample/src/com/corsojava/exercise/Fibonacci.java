package com.corsojava.exercise;

import java.util.Scanner;

public class Fibonacci {
	long[] cache = new long[1000];

	public long fibonacci(int n) {
		long result, previous;
		result = previous = 1;
		for (int i = 3; i <= n; i++) {
			long current = previous + result;
			previous = result;
			result = current;
		}
		return result;
	}

	public long fibonacciRecursive(int n) {
		long result = 1;
		if (n >= 3) {
			result = fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
		}
		return result;
	}

	public long fibonacciWithCache(int n) {
		long result, previous;
		if (cache[n - 1] > 0) {
			result = cache[n - 1];
		} else {
			result = previous = 1;
			for (int i = 3; i <= n; i++) {
				long current = previous + result;
				previous = result;
				result = current;
			}
			cache[n - 1] = result;
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Fibonacci app = new Fibonacci();
		int n = 0;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		do {
			for (int i = 1; i <= n; i++) {
				System.out.printf("%d ", app.fibonacci(i));
			}
			System.out.println();
			System.out.print("n = ");
			n = scanner.nextInt();
		} while (n > 0);
		scanner.close();
	}

}
