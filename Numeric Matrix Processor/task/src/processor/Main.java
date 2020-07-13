package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            System.out.printf("Your choice: %d", choice);
            MatrixOperations operations = new MatrixOperations();
            switch (choice) {
                case 1:
                    operations.matrixAddition(scanner);
                    break;
                case 2:
                    operations.matrixScalarMultiplication(scanner);
                    break;
                case 3:
                    operations.matrixMultiplication(scanner);
                    break;
                case 4:
                    operations.matrixTranspose(scanner);
                    break;
                case 5:
                    operations.matrixDeterminant(scanner);
                    break;
                case 6:
                    operations.matrixInverse(scanner);
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }
    }
}
