package com.company;

public class Main {

    public static void main(String[] args) {
        solvePentaDiagonalMatrix(initTestMatrix(), initTestF());
        System.out.print("Success!");
    }

    private static double[][] initMatrix(int N) {
        return new double[N + 1][N + 1];
    }

    private static double[][] initTestMatrix() {
        return new double[][]{
                {10, 3, 2, 0, 0},
                {2, 15, -1, 2, 0},
                {3, 1, 18, 6, -2},
                {0, 2, 3, 20, 8},
                {0, 0, 3, 4, 15}
        };
    }

    private static double[] initF(int N) {
        return new double[N + 1];
    }

    private static double[] initTestF() {
        return new double[]{2, 3, 4, 5, 6};
    }

    private static double[] getTestRealSolve() {
        return new double[]{0.0998816829, 0.1924625558, 0.211897752, 0.0625980244, 0.3409276431};
    }

    private static double[] solvePentaDiagonalMatrix(double[][] matr, double[] f) {
        int N = f.length - 1;
        double[] result = new double[N + 1];
        double[] p = new double[N + 1];
        double[] q = new double[N + 1];
        double[] delta = new double[N + 1];
        double[] r = new double[N + 2];

        p[1] = -matr[0][1] / matr[0][0];
        delta[1] = matr[1][1] + matr[1][0] * p[1];
        q[1] = matr[0][2] / matr[0][0];
        r[1] = f[0] / matr[0][0];

        p[2] = (q[1] * matr[1][0] - matr[1][2]) / delta[1];
        delta[2] = matr[2][2] - matr[2][0] * q[1] + p[2] * (matr[2][0] * p[1] + matr[2][1]);
        q[2] = matr[1][3] / delta[1];
        r[2] = (f[1] - matr[1][0] * r[1]) / delta[1];

        for (int i = 2; i < N - 1; i++) {
            p[i + 1] = (q[i] * (matr[i][i - 2] * p[i - 1] + matr[i][i - 1]) - matr[i][i + 1]) / delta[i];
            delta[i + 1] = matr[i + 1][i + 1] - matr[i + 1][i - 1] * q[i] + p[i + 1] * (matr[i + 1][i - 1] * p[i] + matr[i + 1][i]);
            q[i + 1] = matr[i][i + 2] / delta[i];
            r[i + 1] = (f[i] - matr[i][i - 2] * r[i - 1] - r[i] * (matr[i][i - 2] * p[i - 1] + matr[i][i - 1])) / delta[i];
        }

        p[N] = (q[N - 1] * (matr[N - 1][N - 3] * p[N - 2] + matr[N - 1][N - 2]) - matr[N - 1][N]) / delta[N - 1];
        delta[N] = matr[N][N] - matr[N][N - 2] * q[N - 1] + p[N] * (matr[N][N - 2] * p[N - 1] + matr[N][N - 1]);
        r[N] = (f[N - 1] - matr[N - 1][N - 3] * r[N - 2] - r[N - 1] * (matr[N - 1][N - 3] * p[N - 2] + matr[N - 1][N - 2])) / delta[N - 1];

        r[N + 1] = (f[N] - matr[N][N - 2] * r[N - 1] - r[N] * (matr[N][N - 2] * p[N - 1] + matr[N][N - 1])) / delta[N];

        return result;
    }
}
