package MatrixGauss;

import java.util.*;

import static MatrixGauss.MPOperations.*;
import static MatrixGauss.Multiplication.multiply;

public class MatrixGauss {
    static final double[][] E = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    };
    static double[][] mtr = {
            {5, 2, 1, 0},
            {1, 3, 2, 8},
            {4, -6, 1, 0},
            {5, 0, 3, 2}
    };
    static final int LENGTH = mtr.length;
    private static double[] b = {14, 65, -3, 32};
    private static double det;
    private static double[][] InvMtr;
    private static double[] x = new double[LENGTH];

    private static double[][] Ei = E;
    private static int prmtCount = 0;                   // Каунтер необхідних перестановок
    private static final double[] leadElem = new double[LENGTH];

    static double[][] M;
    static double[][] P;
    static final List<double[][]> MList = new ArrayList<>();
    static final List<double[][]> PList = new ArrayList<>();

    /**
    * Основна функція, що запускає увесь алгоритм методу
    */
    public static void runMethod() {
        for (int i = 0; i < LENGTH; i++) {
            clearMP();                                  // Записуємо в Р та М одиничну матрицю
            int ind = findLeadElem(i);                  // Знаходимо індекс ведучого елменту

            if (ind != i) {                             // Якщо макс. елемент стовпчика не діагональний ->
                calcP(i, ind);                          // Обчислюємо матрицю перестановки
                prmtCount++;                            // +1 до кількості необхідних перестановок
            }
            calcM(i);                                   // Обчислюємо матрицю маштабування

            addMPToList();                              // Додаємо матриці М та Р до списків
            calcNewMtr();                               // Обчислємо нову матрицю
            calcNewB();                                 // Обчислюємо новий стовпчик в
        }
        calcX();                                        // Обчислюємо розв'язки матриці
        calcDet();                                      // Обчислюємо визначник
        calcInvMtr();                                   // Обчислюємо обернену матрицю
        Printer.showResult(x, det, InvMtr);             // Вивід результатів
    }

    /**
    * Знаходимо розв'язок матриці
    */
    private static void calcX() {
    x = backStroke(mtr, b);
    }

    /**
    * Знаходимо визначник за формулою
    */
    private static void calcDet() {
        det = Math.pow(-1, prmtCount);
        for (double v : leadElem) {
            det *= v;
        }
    }

    /**
    * Знаходимо обернену матрицю
    */
    private static void calcInvMtr() {
        for (int i = 0; i < PList.size(); i++) {        // Знаходимо матрицю Ei
            Ei = multiply(PList.get(i), Ei);
            Ei = multiply(MList.get(i), Ei);
        }

        InvMtr = new double[LENGTH][LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            double[] b1 = new double[LENGTH];
            for (int j = 0; j < LENGTH; j++) {          // Виписуємо стовпчик з матриці Ei
                b1[j] = Ei[j][i];
            }
            b1 = backStroke(mtr, b1);                   // Знаходимо стовбчик матриці InvMtr
            for (int j = 0; j < LENGTH; j++) {
                InvMtr[j][i] = b1[j];                   // Записуємо цей стовбчик в матрицю InvMtr
            }
        }
    }

    /**
    * Знаходимо наступну матрицю
    */
    private static void calcNewMtr() {
        mtr = multiply(P, mtr);                         // Множимо основну матрицю на матрицю перестановки
        mtr = multiply(M, mtr);                         // Множимо основну матрцию на матрицю маштабування
    }

    /**
    * Знаходимо наступний стовбчик в
    */
    private static void calcNewB() {
        b = multiply(P, b);                             // Множимо стовбчик в на матрицю перестановки
        b = multiply(M, b);                             // Множимо стовбчик в на матрицю маштабування
    }

    /**
    * Знаходимо ведучий елемент
    */
    private static int findLeadElem(int i) {
        int ind = i;                                    // Діагональний елемент -> поч. макс. значення
        double max = mtr[i][i];                         // Індекс цього елемнту
        for (int j = i; j < LENGTH; j++) {              // Шукаємо в стовпчику максимальне значення
            if (max < mtr[j][i]) {                      // Знайшли більше ніж початкове ->
                max = mtr[j][i];                        // перезаписуємо його
                ind = j;                                // Перезаписуємо його індекс
            }
        }
        leadElem[i] = max;                              // Додаємо до списку ведучих елементів
        return ind;                                     // Повертаємо його індекс
    }

    /**
    * Запускаэмо зворотній хід для
    * знаходження розв'язків матриці
    */
        private static double[] backStroke(double[][] m, double[] b1) {
        double[] res = new double[b1.length];
        for (int i = m.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < m.length; j++) {
                b1[i] = b1[i] - m[i][j] * res[j];
            }
            res[i] = b1[i];
        }
        return res;
    }
}