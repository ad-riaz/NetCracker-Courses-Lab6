import model.Matrix;
import service.MatrixMultiplicator;
import service.MatrixRandomGenerator;

public class Main {

    public static void main(String[] args) {
        double[][] matrixA = MatrixRandomGenerator.generateRandom(1000, 100);
        double[][] matrixB = MatrixRandomGenerator.generateRandom(1000, 100);
        MatrixMultiplicator multiplicationResult = new MatrixMultiplicator(matrixA, matrixB);

        System.out.println("\nFirst matrix: ");
        Matrix.showMatrix(matrixA, matrixA.length);
        System.out.println("\nSecond matrix: ");
        Matrix.showMatrix(matrixB, matrixB.length);

        System.out.println("\n\n\nStarting multiplication...");

        multiplicationResult.multiplyMultiThread();
        multiplicationResult.multiplySingleThread();

        System.out.println("\nDone!");

        multiplicationResult.showOperationDuration();
    }
}