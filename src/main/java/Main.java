import classes.ComplexNumber;
import classes.Matrix;
import exceptions.InvalidMatrixDimensionException;

public class Main {

    static void testComplexNumber() {
        ComplexNumber z1 = new ComplexNumber(5, 2);
        ComplexNumber z2 = new ComplexNumber(3, 1);
        System.out.println("z1 = " + z1); // z1 = (5 + 2i)
        System.out.println("z2 = " + z2); // z2 = (3 + 1i)
        System.out.println(z1 + " + " + z2 + " = " + z1.add(z2)); // (5 + 2i) + (3 + 1i) = (8 + 3i)
        System.out.println(z1 + " - " + z2 + " = " + z1.subtract(z2)); // (5 + 2i) - (3 + 1i) = (2 + 1i)
        System.out.println(z1 + " * " + z2 + " = " + z1.multiply(z2)); // (5 + 2i) * (3 + 1i) = (13 + 11i)
        System.out.println(z1 + " / " + z2 + " = " + z1.divide(z2)); // (5 + 2i) / (3 + 1i) = (1,7 + 0,1i)
    }

    static void testMatrices() {
        Matrix firstMatrix = new Matrix(3, 3); // is zero-matrix by default
        System.out.println(firstMatrix);
        /*
         (0 + 0i) (0 + 0i) (0 + 0i)
         (0 + 0i) (0 + 0i) (0 + 0i)
         (0 + 0i) (0 + 0i) (0 + 0i)
         */

        // fill first matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                firstMatrix.getMatrix()[i][j] = new ComplexNumber(i+1, j+2);
            }
        }

        // fill second matrix
        Matrix secondMatrix = new Matrix(3, 4);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                secondMatrix.getMatrix()[i][j] = new ComplexNumber(i+10, j+20);
            }
        }

        System.out.println("First matrix is:");
        System.out.println(firstMatrix);

        System.out.println("Second matrix is:");
        System.out.println(secondMatrix);

        System.out.println("First matrix size is: " + firstMatrix.getRowsNumber() + 'x' + firstMatrix.getColumnsNumber());
        System.out.println("Second matrix size is: " + secondMatrix.getRowsNumber() + 'x' + secondMatrix.getColumnsNumber());

        System.out.println("Second matrix transposed is:");
        System.out.println(secondMatrix.getTransposed());

        System.out.println("First + first matrix result:");
        try {
            System.out.println(firstMatrix.add(firstMatrix)); // OK
            System.out.println(firstMatrix.add(secondMatrix)); // [exception here]
        } catch (InvalidMatrixDimensionException e) {
            System.out.println(e.getMessage()); // Matrices must have the same size for the add operation.
        }

        try {
            System.out.println(secondMatrix.subtract(secondMatrix)); // OK, result is zero-matrix
            System.out.println(firstMatrix.multiply(secondMatrix)); // OK
        } catch (InvalidMatrixDimensionException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(firstMatrix.calculateDeterminant()); // (0 + 0i)
        } catch (InvalidMatrixDimensionException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(firstMatrix.getInverse());
        } catch (InvalidMatrixDimensionException | IllegalCallerException e) {
            System.out.println(e.getMessage()); // Determinant is equal to zero, matrix does not have its inverse.
        }

        Matrix thirdMatrix = new Matrix(3, 3);
        thirdMatrix.getMatrix()[0][0] = new ComplexNumber(1, 5);
        thirdMatrix.getMatrix()[0][1] = new ComplexNumber(5, -7);
        thirdMatrix.getMatrix()[0][2] = new ComplexNumber(11, -1);
        thirdMatrix.getMatrix()[1][0] = new ComplexNumber(-7, 2);
        thirdMatrix.getMatrix()[1][1] = new ComplexNumber(3, -4);
        thirdMatrix.getMatrix()[1][2] = new ComplexNumber(6, 8);
        thirdMatrix.getMatrix()[2][0] = new ComplexNumber(-9, 3);
        thirdMatrix.getMatrix()[2][1] = new ComplexNumber(4, -6);
        thirdMatrix.getMatrix()[2][2] = new ComplexNumber(2, 7);

        try {
            System.out.println(thirdMatrix.getInverse());;
        } catch (InvalidMatrixDimensionException | IllegalCallerException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        testComplexNumber();
        testMatrices();
    }
}
