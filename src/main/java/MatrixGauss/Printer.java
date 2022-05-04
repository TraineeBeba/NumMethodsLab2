package MatrixGauss;

import java.util.List;

import static MatrixGauss.MatrixGauss.mtr;

class Printer {
    /**
     * Вивід матриці
     */
    static final void printMatrix(double[][] m) {
        for (double[] doubles : m) {
            for (double aDouble : doubles) {
                System.out.printf("%8.3f", aDouble);
            }
            System.out.println();
        }
        System.out.println("______________________________");
    }

    /**
     * Вивід стовпчику
     */
    static final void printColumn(double[] m) {
        for (double v : m) {
            System.out.printf("%8.3f", v);
            System.out.println();
        }
    }

    /**
     * Вивід списку M або Р
     */
    static final void printList(List<double[][]> list){
        for (double[][] doubles : list) {
            printMatrix(doubles);
            System.out.println("______________________________");
        }
    }

    /**
     * Вивід фінальних результатів
     */
    public static void showResult(double[] x, double det, double[][] invMtr) {
        System.out.println("Matrix is:");
        printMatrix(mtr);

        System.out.printf("Determinant: %.1f\n",det);
        System.out.println("______________________________");

        for (int i = 0; i < x.length; i++) {
            System.out.printf("x%d: %6.3f\n",i, x[i]);
        }
        System.out.println("______________________________");

        System.out.println("Inversed matrix is:");
        printMatrix(invMtr);
    }
}