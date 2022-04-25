package Sweep;

class Printer {
    /**
     * Див. наступну функцію
     */
    static void printLemmaCheck() {
        if (Sweep.lemmaCheck()){
            System.out.println("Lemma is true :)");
        } else {
            System.out.println("Lemma is not true :(");
        }
    }

    /**
     * Певен що цій функції треба приділити побільше уваги,
     * адже не зовсім очевидно яким чином і що вона робить...
     */
    static void printResult(double[] x){
        for (int i = 0; i < x.length; i++) {
            System.out.printf("x%d: %.1f%n", i, x[i]);
        }
    }
}
