package com.corsojava.example;
/**
 * 
 */

import com.corsojava.example.data.Person;

/**
 * @author student
 *
 */
public class HelloWorld {
	
	static int a2 = 2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean b1 = true;
		
		int a;
		
		if (b1) {
			a = 1;
		} else {
			a = 2;
		}
		
		float f = (float) 3.14;
		long l = 2L;
		int i = (int) (12 + l);
		long l2 = 10;
		double d = 3.14F;
		d = 25L;
		int i2 = (int) 12.34;
		
		int b = (int)(5 + (100L + 100)/2);
		
		System.out.println(a);
		System.out.println(a2);
		
		String firstName;
		String lastName = System.getProperty("lastName");
		PersonMoreAccessible p = new PersonMoreAccessible();
		p.setFirstName(args[0]);
		p.setLastName(System.getProperty("lastName"));
		p.printFullName();

		if (lastName.length() < 30) {
			firstName ="Maria Giovanna";
		} else {
			firstName = null;
		}
		
		PersonMoreAccessible p2 = new PersonMoreAccessible();
		p2.setFirstName(firstName);
		p2.setLastName("Verdi");
		p2.printFullName();
	}

}
