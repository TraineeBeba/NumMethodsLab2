package Jacobi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jacobi {
    public static double eps = 0.001;
    private final static int[][] arr = {{4, 0, 1, 1},
                                        {0, 3, 0, 1},
                                        {1, 0, 2, 0},
                                        {1, 1, 0, 5}};
    private static final double[] b = {11, 10, 7, 23};
    private static final List<Double> qList = new ArrayList<>();
    private static final List<Double> coefList = new ArrayList<>();

    /**
     * Первіряємо збіжність методу
     */
    public static boolean check() {
        for (int i = 0; i < arr.length; i++) {                      // Перевіряємо виконання умови
            double sum = 0;                                         // діагональної переваги
            for (int j = 0; j < arr[i].length; j++) {
                sum += j == i ? 0 : arr[i][j];
            }

            if (arr[i][i] < sum) {
                return false;
            }
            qList.add(sum / arr[i][i]);                             // Додаємо до списку q
            addCoef(arr[i][i]);                                     // Додаємо коефіцієнт
        }
        return true;
    }

    /**
     * Основна функція, що запускає увесь алгоритму алгоритм методу
     */
    public static void runMethod() {
        int k = iterCount();                                        // Рахуємо необхідну кілю ітерацій
        System.out.println("iterCount: " + k);

        double[] tmp = {0, 0, 0, 0};                                // Початкове наближення
        double[] res = new double[4];
        for (int i = 0; i < k; i++) {                               // Запуск ітераціного процесу
            res[0] = coefList.get(0) * (-tmp[2] - tmp[3] + b[0]);
            res[1] = coefList.get(1) * (-tmp[3] + b[1]);
            res[2] = coefList.get(2) * (-tmp[0]+ b[2]);
            res[3] = coefList.get(3) * (-tmp[0] - tmp[1] + b[3]);

            System.arraycopy(res, 0, tmp, 0, 4);
        }
        printResult(res);
    }

    /**
     * Додаємо новий коефіцієнт для рівняннь х1,...,xn
     */
    private static void addCoef(double a){
        coefList.add(1/a);
    }

    /**
     * Обираємо максимальне q для обчислення кількості ітерацій
     */
    private static double qMax() {
        return Collections.max(qList);
    }

    /**
     * Обчислюємо кількість ітерацій за формулою
     */
    private static int iterCount() {
        double q = qMax();
        for (int k = 0; true; k++) {
            if (Math.pow(q, k) / (1 - q) < eps)
                return k;
        }
    }

    /**
     * Більш детально про метод див. "printResult" в Sweep
     */
    private static void printResult(double[] res){
        System.out.println("Result:");
        for (int i = 0; i < res.length; i++) {
            System.out.printf("x%d: %.1f\n",i, res[i]);
        }
    }
}