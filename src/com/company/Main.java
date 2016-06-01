package com.company;

public class Main {

    public static void main(String[] args) {
        TestProvider.verifyPentaDiagonalSolver();
    }

    private static double[][] initMatrix(int N) {
        return new double[N + 1][N + 1];
    }


    private static double[] initF(int N) {
        return new double[N + 1];
    }
}
