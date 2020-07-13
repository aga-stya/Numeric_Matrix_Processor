package processor;

import java.util.Scanner;

public class Matrix {
    int row;
    int col;
    double[][] elements;

    Matrix() {
    }

    Matrix(String msg1, String msg2) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg1);
        this.row = scanner.nextInt();
        this.col = scanner.nextInt();
        System.out.println(msg2);
        this.elements = new double[row][col];
        this.addElements(scanner);
    }

    Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        elements = new double[row][col];
    }

    public void displayMatrix() {
        System.out.println("display matrix");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%f ",elements[i][j]);
            }
            System.out.println();
        }
    }

    public void addElements(Scanner scanner) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                elements[i][j] = scanner.nextDouble();
            }
        }
    }
}
