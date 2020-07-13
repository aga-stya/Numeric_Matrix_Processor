package processor;

import java.util.Scanner;
import static java.lang.Math.pow;

public class MatrixOperations {
    public void matrixAddition(Scanner scanner) {
        Matrix matrixA = new Matrix("Enter size of first matrix:", "Enter first matrix:");
        Matrix matrixB = new Matrix("Enter size of second matrix:", "Enter second matrix:");
        //check if 2 matrices can be added
        if (matrixA.row != matrixB.row) {
            System.out.println("ERROR");
            return;
        }
        if (matrixA.col != matrixB.col) {
            System.out.println("ERROR");
            return;
        }
        //matrix addition
        for (int i = 0; i < matrixA.row; i++) {
            for (int j = 0; j < matrixA.col; j++) {
                System.out.printf("%f ", (matrixA.elements[i][j] + matrixB.elements[i][j]));
            }
            System.out.println();
        }
    }

    public void matrixScalarMultiplication(Scanner scanner) {
        Matrix matrix = new Matrix("Enter matrix size:", "Enter matrix:");
        System.out.println("Enter the scalar:");
        int scalar = scanner.nextInt();
        //Scalar matrix multiplication
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.col; j++) {
                System.out.printf("%f ", (matrix.elements[i][j] * scalar));
            }
            System.out.println();
        }
    }

    public void matrixMultiplication(Scanner scanner) {
        Matrix matrixA = new Matrix("Enter size of first matrix:", "Enter first matrix:");
        Matrix matrixB = new Matrix("Enter size of second matrix:", "Enter second matrix:");
        //check if the matrices can be multiplied
        if (matrixA.col != matrixB.row) {
            System.out.println("ERROR");
            return;
        }
        //Matrix multiplication
        for (int aRow = 0; aRow < matrixA.row; aRow++) {
            for (int bCol = 0; bCol < matrixB.col; bCol++) {
                double currSum = 0;
                for (int aCol = 0; aCol < matrixA.col; aCol++) {
                    currSum += matrixA.elements[aRow][aCol] * matrixB.elements[aCol][bCol];
                }
                System.out.printf("%f ", currSum);
            }
            System.out.println();
        }
    }

    public void matrixTranspose(Scanner scanner) {
        MatrixTranspose matTranspose = new MatrixTranspose();
        matTranspose.displayAndSetOptions(scanner);
        Matrix matrix = new Matrix("Enter matrix size:", "Enter matrix:");
        matTranspose.calculateTranspose(matrix);
    }

    public int getSumMainDiagonal(int determinantRow, int determinantCol, Matrix m) {
        int result = 0;
        for (int i = 0; i < m.row && i != determinantRow; i++) {
            for (int j = 0; j < m.col && j != determinantCol; j++) {
                result += m.elements[i][j];
            }
        }
        return result;
    }

    private double matrixDeterminant(Matrix matrix, int rowsCols) {
        //matrix.displayMatrix();
        //Matrix tempMatrix = new Matrix (rowsCols - 1, rowsCols - 1);
        double result = 0;
        if (rowsCols == 1) {
            return matrix.elements[0][0];
        } else if (rowsCols == 2) {
            result = (matrix.elements[0][0] * matrix.elements[1][1] - matrix.elements[0][1] * matrix.elements[1][0]);
            return result;
        } else {
            for (int detCol = 0; detCol < rowsCols; detCol++) {
                Matrix cofactor = getCofactor(matrix, 0, detCol, rowsCols);
                double temp = matrixDeterminant(cofactor,rowsCols - 1);
                //System.out.printf("scalar:%f \n", matrix.elements[0][detCol]);
                temp = pow(-1, detCol) * matrix.elements[0][detCol] * temp;
                result += temp;
            }
            return result;
        }
    }

    public void matrixDeterminant(Scanner scanner) {
        Matrix matrix = new Matrix("Enter matrix size", "Enter matrix");
        double result = matrixDeterminant(matrix, matrix.row);
        System.out.printf("The result is: \n%f\n", result);
    }

    public Matrix getCofactor(Matrix matrix, int p, int q, int rowCols) {
        int i = 0;
        int j = 0;
        Matrix result = new Matrix(rowCols - 1, rowCols - 1);
        for (int row = 0; row < rowCols; row++) {
            for (int col = 0; col < rowCols; col++) {
                if (row != p && col != q) {
                    result.elements[i][j++] = matrix.elements[row][col];
                    if (j == rowCols - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return result;
    }

    public Matrix matrixAdjoint(Matrix matrix) {
        Matrix result = new Matrix(matrix.row, matrix.col);
        if (matrix.row == 1) {
            result.elements[0][0] = matrix.elements[0][0];
            return result;
        }
        int sign = 1;
        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.col; j++) {
                Matrix temp = getCofactor(matrix, i, j, matrix.row);
                sign = ((i + j) % 2 == 0) ? 1 : -1;
                result.elements[j][i] = (sign) * (matrixDeterminant(temp, temp.row));
            }
        }
        return result;
    }

    private Matrix matrixInverse(Matrix matrix, int rowCols) {
        Matrix result = new Matrix(rowCols, rowCols);
        double determinant = matrixDeterminant(matrix, rowCols);
        if (determinant == 0) {
            System.out.println("Error:");
            return result;
        }

        Matrix temp = matrixAdjoint(matrix);

        for (int i = 0; i < matrix.row; i++) {
            for (int j = 0; j < matrix.row; j++) {
                result.elements[i][j] = temp.elements[i][j] / determinant;
            }
        }
        return result;
    }

    public void matrixInverse(Scanner scanner) {
        Matrix matrix = new Matrix("Enter matrix size", "Enter matrix");
        Matrix result = matrixInverse(matrix, matrix.row);
        result.displayMatrix();
    }
}

