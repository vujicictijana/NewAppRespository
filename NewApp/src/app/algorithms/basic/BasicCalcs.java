package app.algorithms.basic;

import Jama.Matrix;

public class BasicCalcs {

	public static double[] vectorMinusVector(double[] first, double[] second) {
		double[] firstMinusSecond = new double[first.length];

		for (int i = 0; i < firstMinusSecond.length; i++) {
			firstMinusSecond[i] = first[i] - second[i];
		}
		return firstMinusSecond;
	}

	public static double[] vectorPlusVector(double[] first, double[] second) {
		double[] firstPlusSecond = new double[first.length];

		for (int i = 0; i < firstPlusSecond.length; i++) {
			firstPlusSecond[i] = first[i] + second[i];
		}
		return firstPlusSecond;
	}

	public static double multiplyTwoVectors(double[] first, double[] second) {
		double sum = 0;
		for (int i = 0; i < first.length; i++) {
			sum += first[i] * second[i];
		}
		return sum;
	}

	public static double trace(double[][] matrix) {
		// sum of the elements on the main diagonal
		Matrix m1 = new Matrix(matrix);
		return m1.trace();
	}

	public static double[] multiplyVectorByANumber(double[] vector,
			double number) {
		double[] result = new double[vector.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = number * vector[i];
		}
		return result;
	}

	public static double[][] multiplyMatrixByANumber(double[][] matrix,
			double number) {
		Matrix m1 = new Matrix(matrix);
		Matrix res = m1.times(number);
		return res.getArray();
	}

	public static double[][] multiplyTwoMatrices(double[][] matrix1,
			double[][] matrix2) {
		Matrix m1 = new Matrix(matrix1);
		Matrix m2 = new Matrix(matrix2);
		Matrix res = m1.times(m2);
		return res.getArray();
	}

	public static double[][] matrixMinusMatrix(double[][] matrix1,
			double[][] matrix2) {
		Matrix m1 = new Matrix(matrix1);
		Matrix m2 = new Matrix(matrix2);
		Matrix res = m1.minus(m2);
		return res.getArray();
	}

	public static double[][] matrixPlusMatrix(double[][] matrix1,
			double[][] matrix2) {
		Matrix m1 = new Matrix(matrix1);
		Matrix m2 = new Matrix(matrix2);
		Matrix res = m1.plus(m2);
		return res.getArray();
	}

	public static double[] multiplyMatrixByAColumnVector(double[][] matrix,
			double[] vector) {
		double[] result = new double[matrix.length];
		double temp = 0;
		for (int i = 0; i < matrix.length; i++) {
			temp = 0;
			for (int j = 0; j < matrix.length; j++) {
				temp += matrix[i][j] * vector[j];
			}
			result[i] = temp;
		}
		return result;
	}

	public static double[] rowSum(double[][] matrix) {
		double[] rowSum = new double[matrix.length];
		double sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			sum = 0;
			for (int j = 0; j < matrix.length; j++) {
				sum += matrix[i][j];
			}
			rowSum[i] = sum;
		}
		return rowSum;
	}

	public static double[] colSum(double[][] matrix) {
		double[] colSum = new double[matrix.length];
		double sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			sum = 0;
			for (int j = 0; j < matrix.length; j++) {
				sum += matrix[j][i];
			}
			colSum[i] = sum;
		}
		return colSum;
	}

	public static double[][] diag(double[] vector) {
		double[][] diag = new double[vector.length][vector.length];
		for (int i = 0; i < diag.length; i++) {
			diag[i][i] = vector[i];
		}
		return diag;
	}

	public static double[][] identityMatrix(int n) {
		return Matrix.identity(n, n).getArray();
	}

	public static double[][] inverse(double[][] matrix) {
		Matrix m = new Matrix(matrix);
		Matrix inverse = m.inverse();
		return inverse.getArray();
	}

	private static int degree(int row, double[][] matrix) {
		int degree = 0;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[row][i] != 0) {
				degree++;
			}
		}
		return degree;
	}

	public static double[][] degreeMatrix(double[][] matrix) {
		double[][] degreeMatrix = new double[matrix.length][matrix.length];
		for (int i = 0; i < degreeMatrix.length; i++) {
			degreeMatrix[i][i] = degree(i, matrix);
		}
		return degreeMatrix;
	}

}