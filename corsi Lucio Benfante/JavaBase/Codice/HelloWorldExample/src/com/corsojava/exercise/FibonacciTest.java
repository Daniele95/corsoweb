package com.corsojava.exercise;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciTest {

	@Test
	public void testFibonacci1() {
		Fibonacci app = new Fibonacci();
		assertEquals(1, app.fibonacci(1));
	}

	@Test
	public void testFibonacci2() {
		Fibonacci app = new Fibonacci();
		assertEquals(1, app.fibonacci(2));
	}

	@Test
	public void testFibonacci3() {
		Fibonacci app = new Fibonacci();
		assertEquals(2, app.fibonacci(3));
	}
	
	@Test
	public void testFibonacci7() {
		Fibonacci app = new Fibonacci();
		assertEquals(13, app.fibonacci(7));
	}

	@Test
	public void testFibonacciRecursive1() {
		Fibonacci app = new Fibonacci();
		assertEquals(1, app.fibonacciRecursive(1));
	}

	@Test
	public void testFibonacciRecursive2() {
		Fibonacci app = new Fibonacci();
		assertEquals(1, app.fibonacciRecursive(2));
	}

	@Test
	public void testFibonacciRecursive3() {
		Fibonacci app = new Fibonacci();
		assertEquals(2, app.fibonacciRecursive(3));
	}

	@Test
	public void testFibonacciRecursive7() {
		Fibonacci app = new Fibonacci();
		assertEquals(13, app.fibonacciRecursive(7));
	}

	@Test
	public void testFibonacciWithCache1() {
		Fibonacci app = new Fibonacci();
		assertEquals(1, app.fibonacciWithCache(1));
	}
	
	@Test
	public void testFibonacciWithCache2() {
		Fibonacci app = new Fibonacci();
		assertEquals(1, app.fibonacciWithCache(2));
	}
	
	@Test
	public void testFibonacciWithCache3() {
		Fibonacci app = new Fibonacci();
		assertEquals(2, app.fibonacciWithCache(3));
	}
	
	@Test
	public void testFibonacciWithCache7() {
		Fibonacci app = new Fibonacci();
		assertEquals(0, app.cache[6]);		
		assertEquals(13, app.fibonacciWithCache(7));
		assertEquals(13, app.cache[6]);
	}
	
}
