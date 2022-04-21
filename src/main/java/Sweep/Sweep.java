package Sweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class Sweep {
    private final static int[][] arr = {{2, 4, 0},
                                        {4, 1, 5},
                                        {0, 5, 2}};
    private static final List<Integer> a = new ArrayList<>();
    private static final List<Integer> c = new ArrayList<>();
    private static final List<Integer> b = new ArrayList<>();
    private static final List<Integer> f = new ArrayList<>(Arrays.asList(20, 37, 30));
    private static final List<Double> alpha = new ArrayList<>();
    private static final List<Double> beta = new ArrayList<>();
    private static final double[] x = new double[3];

    /**
     * Основна функція, що запускає увесь алгоритм методу
     */
    public static void runMethod() {
        for (int i = 0; i < arr.length; i++) {              // Записуємо в масиви коефіцієнти матриці
            if (i > 0) a.add(arr[i][i - 1]);                // Все що нижче діагоналі - а і-ті
            c.add(-arr[i][i]);                              // Все що на діагоналі - с і-ті
            if (i != arr.length - 1) b.add(arr[i][i + 1]);  // Все що вище діагоналі - а і-ті
        }
//        if (!lemaСheck()){
//            System.out.println("Oops!");
//            System.exit(0);
//        }
        findSweepCoefs();
        findResults();
        printResult();
    }
// !!!!!!!!!!!!!!!!!ЛЕМА НА ВИКОНУЄТЬСЯ, ТРЕБА ЗАПИТАТИ!!!!!!!!!!!!!!!!!!!
//    /**
//     * Лема 3.1
//     * Лема за якою визначаємо стійкість методу для а, с, в - і-тих
//     */
//    private static boolean lemaСheck(){
//        for (int i = 0; i < arr.length - 1; i++) {
//            if (a.get(i) == 0 || b.get(i) == 0)              // Частина умови леми
//                return false;
//        }
//        if (c.get(arr.length - 1) == 0 || c.get(0) == 0)     // Ще частина умови леми
//            return false;
//        a.add(0,0);                            // Теж частина умови леми
//        b.add(arr.length - 1, 0);
//
//        for (int i = 0; i < arr.length; i++) {               // Основна первірка умови
//            if (abs(c.get(i)) < abs(a.get(i)) + abs(b.get(i)))
//                return false;
//        }
//
//        a.remove(0);                                   // Видаляємо, бо для роботи алгоритму 0-ві
//        b.remove(arr.length - 1);                      // коефіцієнти не потрібні
//        return true;
//    }

    /**
     * 1 пукнт алгоритму
     * Знаходимо прогонкові коефійієнти Альфа та Бета
     */
    private static void findSweepCoefs(){
        alpha.add((double) b.get(0) / c.get(0));
        beta.add((double) f.get(0) / c.get(0));

        for (int i = 1; i < arr.length - 1; i++) {
            double z = c.get(i) - alpha.get(i - 1) * a.get(i - 1);
            alpha.add(b.get(i) / z);
            beta.add((f.get(i) + a.get(i - 1) * beta.get(i - 1)) / z);
        }
    }

    /**
     * 2 пукнт алгоритму
     * Визначаємо останній х за відповідною формулою
     * ____________________________________________
     * 3 пукнт алгоритму
     * Обчислюємо в зворотньому напрямку усі інші x і-ті
     */
    private static void findResults(){                       // Індекси доволі заплутані, але див. теор. відомості
        x[2] = (f.get(2) + a.get(1)*beta.get(1))/            // 2 пункт
                (c.get(2) - alpha.get(1)*a.get(1));
        for (int i = arr.length-2; i > -1; i--) {            // 3 пункт
            x[i] = alpha.get(i) * x[i+1] + beta.get(i);
        }
    }

    /**
     * Певен що цій функції треба приділити побільше уваги,
     * адже не зовсім очевидно яким чином і що вона робить...
     */
    private static void printResult(){
        for (int i = 0; i < x.length; i++) {
            System.out.printf("x%d: %.1f%n", i, x[i]);
        }
    }
}