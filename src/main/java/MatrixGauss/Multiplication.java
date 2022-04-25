package MatrixGauss;

class Multiplication {
    /**
     * Множення матриці на матрицю
     */
    static double[][] multiply(double[][] mtr1, double[][] mtr2) {
        double[][] res = new double[mtr1.length][mtr2[0].length];
        for (int i = 0; i < mtr1.length; i++) {
            for (int u = 0; u < mtr2.length; u++) {
                for (int j = 0; j < mtr2[i].length; j++) {
                    res[i][u] += mtr1[i][j] * mtr2[j][u];
                }
            }
        }
        return res;
    }

    /**
     * Множення матриці на стовпчик
     */
    static double[] multiply(double[][] mtr1, double[] mtr2) {
        double[] res = new double[mtr2.length];
        for (int i = 0; i < mtr1[0].length; i++) {
            for (int j = 0; j < mtr2.length; j++) {
                res[i] += mtr1[i][j] * mtr2[j];
            }
        }
        return res;
    }
}