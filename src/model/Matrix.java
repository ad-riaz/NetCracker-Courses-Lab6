package model;

public class Matrix extends Thread {

    private double[][] matrix;
    private double[] row;
    private double[] result;

    public Matrix(double[][] matrix, double[] row) {
        this.matrix = matrix;
        this.row = row;
    }

    @Override
    public void run() {
        result = multiplyLine(matrix, row);
    }

    public static double[] multiplyLine(double[][] matrix, double[] row) {
        double result[] = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i] += row[j] * matrix[j][i];
            }
        }
        return result;
    }

    public static void showMatrix(double[][] matrix, double matrixSize) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public double[] getResult() {
        return result;
    }
}