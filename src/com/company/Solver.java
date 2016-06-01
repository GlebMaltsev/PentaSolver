package com.company;

public class Solver {

    public static double[] solvePentaDiagonalMatrix(double[][] matr, double[] f) {

        // Инициализация массивов для хранения прогоночных коэффицентов и результата
        int N = f.length - 1;
        double[] result = new double[N + 1];
        double[] p = new double[N + 1];
        double[] q = new double[N + 1];
        double[] delta = new double[N + 1];
        double[] r = new double[N + 2];

        // Прямой ход метода прогонки - вычисление прогоночных коэффицентов
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


        // Обратный ход метода прогонки - нахождение решения по реккурентным формулам
        result[N] = r[N + 1];
        result[N - 1] = p[N] * result[N] + r[N];
        for (int i = N - 2; i > -1; i--) {
            result[i] = p[i + 1] * result[i + 1] - q[i + 1] * result[i + 2] + r[i + 1];
        }
        return result;
    }
}
