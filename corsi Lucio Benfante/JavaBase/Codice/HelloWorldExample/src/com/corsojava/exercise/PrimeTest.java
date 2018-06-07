package com.corsojava.exercise;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimeTest {

	@Test
	public void testIsPrime() {
		Prime app = new Prime();
		assertEquals(true, app.isPrime(2));
		assertEquals(true, app.isPrime(3));
		assertEquals(true, app.isPrime(5));
		assertEquals(true, app.isPrime(7));
		assertEquals(true, app.isPrime(11));
		assertEquals(true, app.isPrime(13));
		assertEquals(true, app.isPrime(17));
		assertEquals(true, app.isPrime(19));
		assertEquals(true, app.isPrime(23));
		assertEquals(true, app.isPrime(19));
		assertEquals(true, app.isPrime(29));
		assertEquals(true, app.isPrime(31));
		assertEquals(true, app.isPrime(37));
		assertEquals(true, app.isPrime(8191));
		assertEquals(true, app.isPrime(2147483647L));
	}

	@Test
	public void testIsNotPrime() {
		Prime app = new Prime();
		assertEquals(false, app.isPrime(4));
		assertEquals(false, app.isPrime(6));
		assertEquals(false, app.isPrime(8));
		assertEquals(false, app.isPrime(9));
		assertEquals(false, app.isPrime(10));
		assertEquals(false, app.isPrime(12));
		assertEquals(false, app.isPrime(16));
		assertEquals(false, app.isPrime(18));
		assertEquals(false, app.isPrime(22));
		assertEquals(false, app.isPrime(28));
		assertEquals(false, app.isPrime(30));
		assertEquals(false, app.isPrime(32));
		assertEquals(false, app.isPrime(38));
		assertEquals(false, app.isPrime(8197));
		assertEquals(false, app.isPrime(2147483649L));
	}
	
}
