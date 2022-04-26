import Jacobi.Jacobi;
import MatrixGauss.MatrixGauss;
import Sweep.Sweep;

import java.util.Scanner;

public class Main {
    private static final String METHOD_CHOICE = """
            Which method example do you want to run?
            1: Gauss method with matrices
            2: Sweep method
            3: Jacobi method
                        
            Let's try this one:""";
    public static void main(String[] args) {
        System.out.print(METHOD_CHOICE);
        System.out.println("_____________________________");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice) {
            case (1) -> MatrixGauss.runMethod();
            case (2) -> Sweep.runMethod();
            case (3) -> {
                if(!Jacobi.check()){
                    System.out.println("Не виконується умова диагональної переваги :(");
                    System.exit(0);
                }
                Jacobi.runMethod();
            }
        }
    }
}
