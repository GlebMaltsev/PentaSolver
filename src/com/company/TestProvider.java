package com.company;

public class TestProvider {

    private static final int REAL_SOLUTION_DECIMAL_PLACES = 15;
    private static final double EPSILON = Math.pow(10, -REAL_SOLUTION_DECIMAL_PLACES);

    public static void verifyPentaDiagonalSolver() {
        System.out.println("Start Testing...\n");
        boolean simpleTestResults = runTest(getSimpleTestMatrix(), getSimpleTestF(), getSimpleTestRealSolution());
        System.out.println("Simple test " + (simpleTestResults ? "success" : "failed"));
        boolean complexTestResults = runTest(getComplexTestMatrix(), getComplexTestF(), getComplexTestRealSolve());
        System.out.println("Complex test " + (complexTestResults ? "success" : "failed"));
        System.out.println("\nTesting completed!");
    }

    private static boolean runTest(double[][] matr, double[] f, double[] realSolution) {
        double[] calculatedSolution = Main.solvePentaDiagonalMatrix(matr, f);
        for (int i = 0; i < calculatedSolution.length; i++) {
            if (Math.abs(calculatedSolution[i] - realSolution[i]) > EPSILON) {
                return false;
            }
        }
        return true;
    }

    private static double[][] getSimpleTestMatrix() {
        return new double[][]{
                {10, 3, 2, 0, 0},
                {2, 15, -1, 2, 0},
                {3, 1, 18, 6, -2},
                {0, 2, 3, 20, 8},
                {0, 0, 3, 4, 15}
        };
    }

    private static double[] getSimpleTestF() {
        return new double[]{2, 3, 4, 5, 6};
    }

    private static double[] getSimpleTestRealSolution() {
        return new double[]{0.099881682855020, 0.192462555833769, 0.211897751974245, 0.062598024378835, 0.340927643104128};
    }

    private static double[][] getComplexTestMatrix() {
        return new double[][]{
                {2, 1, -1, 0, 0, 0, 0, 0, 0, 0},
                {1, 5, 3, -1, 0, 0, 0, 0, 0, 0},
                {-1, 2, 6, 2, 0, 0, 0, 0, 0, 0},
                {0, 2, 3, 12, 4, 1, 0, 0, 0, 0},
                {0, 0, 1, 2, 18, 5, 6, 0, 0, 0},
                {0, 0, 0, 2, 1, 4, -1, -1, 0, 0},
                {0, 0, 0, 0, 3, 2, 10, 2, 2, 0},
                {0, 0, 0, 0, 0, -1, 0, 6, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 2, 9}
        };
    }

    private static double[] getComplexTestF() {
        return new double[]{4, 5, -1, -3, 2, 8, 19, 33, 2, 0};
    }

    private static double[] getComplexTestRealSolve() {
        return new double[]{1.770481939395210, 0.530747979796656, 0.071711858587076, -0.360642585860280, -0.987820853897951,
                4.002362910560624, 0.069755688415579, 6.230589928208408, 0.400000000000000, -0.781176658689823};
    }
}
