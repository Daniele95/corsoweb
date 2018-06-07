package com.corsojava.example;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

import com.corsojava.example.objects.Person;

public class EsempioIO {

	public static void withBasicStreams() throws IOException {
		InputStream is = new FileInputStream(new File("/home/lucio/tmp/corso/immagine.jpg"));
		OutputStream os = new FileOutputStream(new File("/home/lucio/tmp/corso/copia_immagine.jpg"));
		int d;
		while ((d = is.read()) != -1) {
			os.write(d);
		}
		os.close();
		is.close();
	}

	public static void withBasicStreamsAndExceptions() {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(new File("/home/lucio/tmp/corso/immagine.jpg"));
			os = new FileOutputStream(new File("/home/lucio/tmp/corso/copia_immagine.jpg"));
			int d;
			while ((d = is.read()) != -1) {
				os.write(d);
			}
		} catch (FileNotFoundException fnfe) {
			// nothing
		} catch (IOException ioe) {
			// nothing
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException ioe) {
					/* NOTHING */ }
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException ioe) {
					/* NOTHING */ }
			}
		}
	}

	public static void withBasicStreamsAndTryWithResources() throws IOException {
		try (InputStream is =
					new FileInputStream(new File("/home/lucio/tmp/corso/immagine.jpg"));
				OutputStream os =
						new FileOutputStream(new File("/home/lucio/tmp/corso/copia_immagine.jpg"));
		) {
			int d;
			while ((d = is.read()) != -1) {
				os.write(d);
			}
		}
	}

	public static void withBasicStreamsBuffered() throws IOException {
		InputStream is = new FileInputStream(new File("/home/lucio/tmp/corso/immagine.jpg"));
		OutputStream os = new FileOutputStream(new File("/home/lucio/tmp/corso/copia_immagine.jpg"));
		int n;
		byte[] buffer = new byte[1024];
		while ((n = is.read(buffer)) != -1) {
			os.write(buffer, 0, n);
		}
		os.close();
		is.close();
	}

	public static void withBufferedStreams() throws IOException {

		InputStream is = new BufferedInputStream(new FileInputStream(new File("/home/lucio/tmp/corso/immagine.jpg")));
		OutputStream os = new BufferedOutputStream(
				new FileOutputStream(new File("/home/lucio/tmp/corso/copia_immagine.jpg")));
		int d;
		while ((d = is.read()) != -1) {
			os.write(d);
		}
		os.close();
		is.close();
	}

	public static void withArrayStreams() throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(new File("/home/lucio/tmp/corso/immagine.jpg")));
		OutputStream os = new ByteArrayOutputStream();
		int d;
		while ((d = is.read()) != -1) {
			os.write(d);
		}
		byte[] immagine = ((ByteArrayOutputStream) os).toByteArray();
		os.close();
		is.close();
	}

	public static void withBasicReadersWriters() throws IOException {
		Reader r = new FileReader(new File("/home/lucio/tmp/corso/documento.txt"));
		Writer w = new FileWriter(new File("/home/lucio/tmp/corso/copia_documento.txt"));
		int d;
		while ((d = r.read()) != -1) {
			w.write(d);
		}
		w.close();
		r.close();
	}

	public static void withReadline() throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(new File("/home/lucio/tmp/corso/documento.txt")));
		String line;
		while ((line = r.readLine()) != null) {
			System.out.println(line);
		}
		r.close();
	}

	public static void scanner() throws IOException {
		Scanner s = new Scanner(new File("/home/lucio/tmp/corso/documento.txt"));
		System.out.println(s.nextLine());
	}
	
	public static void storeObject() throws IOException, ClassNotFoundException {
		ObjectOutputStream os = new ObjectOutputStream(
				new BufferedOutputStream(
				new FileOutputStream(new File("/home/lucio/tmp/corso/people.dat"))));
		Person p1 = new Person("Lucio", "Benfante", 50);
		os.writeObject(p1);
		os.close();
		
		ObjectInputStream is = new ObjectInputStream(
				new BufferedInputStream(
				new FileInputStream(new File("/home/lucio/tmp/corso/people.dat"))));
		Person p2 = (Person) is.readObject();
		is.close();
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p2.describePerson());
	}

	public static void file() throws IOException {
		File f = new File("/home/lucio/tmp/corso/documento_nuovo.txt");
		f.createNewFile();
		f.deleteOnExit();
	}

	public static void risorse() throws IOException {
		InputStream is = EsempioIO.class.getClassLoader()
				.getResourceAsStream("com/corsojava/example/configurazione/config.properties");
		BufferedReader r = new BufferedReader(new InputStreamReader(is));
		String line;
		while ((line = r.readLine()) != null) {
			System.out.println(line);
		}
		r.close();
	}

	public static void proprieta() throws IOException {
		Properties p = System.getProperties();
		p.load(EsempioIO.class.getClassLoader()
				.getResourceAsStream("com/corsojava/example/configurazione/config.properties"));
		Enumeration e = p.keys();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			System.out.println(key + " = " + p.get(key));
		}
	}

	public static void tastiera() throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = r.readLine()) != null) {
			System.out.println(line);
		}
		r.close();
	}

	public static void tastieraWithScanner() throws IOException {
		Scanner scanner = new Scanner(System.in);
		int[] numbers = new int[10];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = scanner.nextInt();
		}
		System.out.println(Arrays.toString(numbers));
		scanner.close();
	}

	public static void tastieraWithScannerDifferentTypes() throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		System.out.print("Cognome: ");
		String cognome = scanner.nextLine();
		System.out.print("Età: ");
		int eta = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Città: ");
		String citta = scanner.nextLine();
		scanner.close();
		System.out.printf("Nome: %s, Cognome: %s, Età: %d, Città: %s",
				nome, cognome, eta, citta);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
//		withBasicStreams();
		// withBasicStreamsBuffered();
		// withBufferedStreams();
		// withArrayStreams();
		// withBasicReadersWriters();
		// withReadline();
		// scanner();
		// file();
//		 risorse();
//		 proprieta();
//		 tastiera()
//		 tastieraWithScanner();
//		 storeObject();
		tastieraWithScannerDifferentTypes();
	}
}
