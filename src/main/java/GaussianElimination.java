import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GaussianElimination {
    public static void main(String[] args) {
        try {
            // Чтение данных из файла
            Scanner scanner = new Scanner(new File("../GaussMethod/src/main/resources/input.txt"));
            int n = scanner.nextInt(); // количество уравнений
            double[][] matrix = new double[n][n+1]; // матрица коэффициентов

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n+1; j++) {
                    matrix[i][j] = scanner.nextDouble();
                }
            }
            scanner.close();

            // Приведение к верхнетреугольному виду
            for (int i = 0; i < n - 1; i++) {
                for (int k = i + 1; k < n; k++) {
                    double coeff = matrix[k][i] / matrix[i][i];
                    for (int j = i; j < n + 1; j++) {
                        matrix[k][j] -= coeff * matrix[i][j];
                    }
                }
            }

            // Обратный ход
            double[] solution = new double[n];
            for (int i = n - 1; i >= 0; i--) {
                solution[i] = matrix[i][n] / matrix[i][i];
                for (int k = i - 1; k >= 0; k--) {
                    matrix[k][n] -= matrix[k][i] * solution[i];
                }
            }

            // Вывод результатов в файл
            FileWriter writer = new FileWriter("../GaussMethod/src/main/resources/output.txt");
            for (int i = 0; i < n; i++) {
                writer.write("x" + (i+1) + " = " + solution[i] + "\n");
            }
            writer.close();

            System.out.println("Результаты записаны в файл output.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}