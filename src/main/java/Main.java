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

        ComplexNumber zero = new ComplexNumber(0);
//        System.out.println(z1 + " / " + zero + " = " + z1.divide(zero));
//        Unhandled exception: ComplexNumberArithmeticException. So we prevent zero division in compile time

        try {
            System.out.println(z1 + " / " + z2 + " = " + z1.divide(z2)); // (5 + 2i) / (3 + 1i) = (1,7 + 0,1i)
            System.out.println(z1 + " / " + zero + " = " + z1.divide(zero)); // [Exception here]
        } catch (ComplexNumberArithmeticException e) {
            System.out.println("Complex number exception handled. Message: " + e.getMessage());
        }
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

        System.out.println("First + first matrix result:");
        try {
            System.out.println(firstMatrix.add(firstMatrix));
        } catch (InvalidMatrixDimensionException e) {
            System.out.println("Can not do add operation for matrices with different dimensions");
        }
    }

    public static void main(String[] args) {
        testComplexNumber();
        testMatrices();
    }
}
