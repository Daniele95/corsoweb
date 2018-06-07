package com.corsojava.example;

public class PrintMatrix {

	public int[][] buildTable(int dim) {
		int[][] result = new int[dim][dim];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = (i+1)*(j+1);
			}
		}
		return result;
	}
	
	public void print(int table[][]) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				System.out.printf("%3d\t", table[i][j]);
			}
			System.out.println();
		}
	}
	
	public void printLinearized(int table[][]) {
		int rowNum = table.length;
		int colNum = table[0].length;
		int totalElements = rowNum*colNum;
		for (int k = 0; k < totalElements; k++) {
			int i = k / colNum;
			int j = k % colNum;
			System.out.printf("%3d\t", table[i][j]);
			if (j == colNum-1) {
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args) {
		PrintMatrix app = new PrintMatrix();
		int[][] table = app.buildTable(10);
//		app.print(table);
		app.printLinearized(table);
	}

}
