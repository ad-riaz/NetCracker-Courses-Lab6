package service;

import model.Matrix;

public class MatrixMultiplicator {
    long startOperationTime;
    long endOperationTime;
    long multiThreadDuration;
    long singleThreadDuration;

    double[][] matrixA;
    double[][] matrixB;
    double[][] matrixMultiplication;
    final Matrix[] matrices;

    public MatrixMultiplicator(double[][] matrixA, double[][] matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrices = new Matrix[matrixA.length];
        this.matrixMultiplication = new double[matrixA.length][matrixB[0].length];
    }

    public double[][] multiplyMultiThread() {
        startOperationTime = System.nanoTime();

        for (int i = 0; i < matrices.length; i++) {
            matrices[i] = new Matrix(matrixB, matrixA[i]);
            matrices[i].start();
        }

        try {
            for (int l = 0; l < matrices.length; l++) {
                matrices[l].join();
                matrixMultiplication[l] = matrices[l].getResult();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        endOperationTime = System.nanoTime();
        multiThreadDuration = endOperationTime - startOperationTime;

        System.out.println("\nmodel.Matrix multiplication result in multithread mode: ");
        Matrix.showMatrix(matrixMultiplication, matrixMultiplication.length);

        return matrixMultiplication;
    }

    public double[][] multiplySingleThread() {
        startOperationTime = System.nanoTime();

        for (int j = 0; j < matrixA.length; j++) {
            matrixMultiplication[j] = Matrix.multiplyLine(matrixB, matrixA[j]);
        }

        endOperationTime = System.nanoTime();
        singleThreadDuration = endOperationTime - startOperationTime;

        System.out.println("\nmodel.Matrix multiplication result in singlethread mode: ");
        Matrix.showMatrix(matrixMultiplication, matrixMultiplication.length);

        return matrixMultiplication;
    }

    public void showOperationDuration() {
        System.out.println();
        System.out.println("Multithread mode. Multiplication took " + multiThreadDuration / 1000000 + " ms");
        System.out.println("Singlethread mode. Multiplication took " + singleThreadDuration / 1000000 + " ms");
    }
}