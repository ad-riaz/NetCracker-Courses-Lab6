package service;

public abstract class MatrixRandomGenerator {

    public static double[][] generateRandom(int size, int maxElementValue) {
        double[][] matrix = new double[size][size];
        Thread[] threads = new Thread[size];

        for (int i = 0; i < size; i++) {
            int finalI = i;
            threads[i] = new Thread() {
                public void run() {
                    for (int j = 0; j < size; j++) {
                        matrix[finalI][j] = (Math.random() * maxElementValue);
                    }

                }
            };
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return matrix;
    }
}
