package MatrixGauss;

import java.util.Arrays;

import static MatrixGauss.MatrixGauss.*;

public class MPOperations {
    static void addMPToList() {
        MList.add(M);
        PList.add(P);
    }

    /**
     * Записуємо в Р та М одиничну матрицю
     */
    static void clearMP() {
        M = Arrays.stream(E).map(double[]::clone).toArray(double[][]::new);
        P = Arrays.stream(E).map(double[]::clone).toArray(double[][]::new);
    }

    /**
     * Знаходимо матрицю маштабування
     */
    static void calcM(int i) {
        M[i][i] = 1 / mtr[i][i];                        // Обчислюємо перший елмемент стовбчику в М
        double[] column = new double[LENGTH];
        for (int j = i; j < LENGTH; j++) {
            column[j] = mtr[j][i];                      // Записуємо в масив стовбчик
        }                                               // основної матриці із яким працюємо

        for (int u = i + 1; u < M.length; u++) {
            M[u][i] = -column[u] / column[i];           // Обчислюємо інші елмементи стовбчику в М
        }
    }

    /**
     * Знаходимо матрицю перестановок,
     * змінючи рядки на позиціях іnd та і
     */
    static void calcP(int i, int ind) {
        double[] tmp = P[ind];
        P[ind] = P[i];
        P[i] = tmp;
    }
}
