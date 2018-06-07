package com.corsojava.example;

public class PrintMatrix2 {
	
	int[][] matrix;
	
	public PrintMatrix2(int dim) {
		matrix = buildTable(dim);
	}
	
	public void print() {
		print(matrix);
	}
	
	private int[][] buildTable(int dim) {
		int[][] result = new int[dim][dim];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = (i+1)*(j+1);
			}
		}
		return result;
	}
	
	private void print(int table[][]) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				System.out.printf("%3d\t", table[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		PrintMatrix2 app = new PrintMatrix2(10);
		app.print();
	}

}
