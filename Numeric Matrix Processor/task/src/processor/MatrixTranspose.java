package processor;

import java.util.Scanner;

public class MatrixTranspose {
    private int choice;

    public void displayAndSetOptions(Scanner scanner) {
        System.out.println("\n1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.println("Your choice:");
        setChoice(scanner.nextInt());
    }

    private void setChoice(int choice) {
        this.choice = choice;
    }

    public void calculateTranspose(Matrix matrix) {
        Matrix result = new Matrix();
        switch (choice) {
            case 1:
                result = mainDiagonalTranspose(matrix);
                result.displayMatrix();
                break;
            case 2:
                result = sideDiagonalTranspose(matrix);
                result.displayMatrix();
                break;
            case 3:
                result = verticalLineTranspose(matrix);
                result.displayMatrix();
                break;
            case 4:
                result = horizontalLineTranspose(matrix);
                result.displayMatrix();
                break;
            default:
                break;
        }
    }

    private Matrix horizontalLineTranspose(Matrix matrix) {
        Matrix result = new Matrix(matrix.row, matrix.col);
        int totalCols = matrix.col - 1;
        for (int i = 0; i < result.row; i++) {
            for (int j = 0; j < result.col; j++) {
                result.elements[totalCols - i][j] = matrix.elements[i][j];
            }
        }
        return result;
    }

    private Matrix verticalLineTranspose(Matrix matrix) {
        Matrix result = new Matrix(matrix.row, matrix.col);
        for (int i = 0; i < result.row; i++) {
            int totalCols = result.col - 1;
            for (int j = 0; j < result.col; j++) {
                result.elements[i][j] = matrix.elements[i][totalCols - j];
            }
        }
        return result;
    }

    private Matrix sideDiagonalTranspose(Matrix matrix) {
        Matrix result = new Matrix(matrix.col, matrix.row);
        result = verticalLineTranspose(matrix);
        result = mainDiagonalTranspose(result);
        result = verticalLineTranspose(result);
        return result;
    }

    private Matrix mainDiagonalTranspose(Matrix matrix) {
        Matrix result = new Matrix(matrix.col, matrix.row);
        for (int i = 0; i < result.row; i++) {
            for (int j = 0; j < result.col; j++) {
                result.elements[j][i] = matrix.elements[i][j];
            }
        }
        return result;
    }
}
