package classes;

import exceptions.InvalidMatrixDimensionException;

public class Matrix {
    private ComplexNumber[][] matrix;

    public Matrix() {
        this(0, 0);
    }

    public Matrix(int rowsNumber, int columnsNumber) {
        this(new ComplexNumber[rowsNumber][columnsNumber]);
    }

    public Matrix(ComplexNumber[][] matrix) {
        if (matrix.length == 0) {
            this.matrix = null;
        } else {
            int rowsNumber = matrix.length;
            int columnsNumber = matrix[0].length;
            this.matrix = new ComplexNumber[rowsNumber][columnsNumber];
            for (int i = 0; i < rowsNumber; i++) {
                for (int j = 0; j < columnsNumber; j++) {
                    if (matrix[i][j] == null) {
                        this.matrix[i][j] = new ComplexNumber(0);
                    } else {
                        this.matrix[i][j] = matrix[i][j];
                    }
                }
            }
        }
    }

    public ComplexNumber[][] getMatrix() {
        return matrix;
    }

    public int getRowsNumber() {
        if (matrix != null) {
            return matrix.length;
        } else {
            return 0;
        }
    }

    public int getColumnsNumber() {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        return matrix[0].length;
    }

    public Matrix add(Matrix other) throws InvalidMatrixDimensionException {
        if (this.getRowsNumber() != other.getRowsNumber() || this.getColumnsNumber() != other.getColumnsNumber()) {
            throw new InvalidMatrixDimensionException("Matrices must have the same size for the add operation.");
        } else {
            Matrix result = new Matrix(this.getColumnsNumber(), this.getRowsNumber());
            for (int row = 0; row < getRowsNumber(); row++) {
                for (int column = 0; column < getColumnsNumber(); column++) {
                    result.matrix[row][column] = this.matrix[row][column].add(other.matrix[row][column]);
                }
            }
            return result;
        }
    }

    public Matrix subtract(Matrix other) throws InvalidMatrixDimensionException {
        if (this.getRowsNumber() != other.getRowsNumber() || this.getColumnsNumber() != other.getColumnsNumber()) {
            throw new InvalidMatrixDimensionException("Matrices must have the same size for the subtract operation.");
        } else {
            Matrix result = new Matrix(this.getColumnsNumber(), this.getRowsNumber());
            for (int row = 0; row < getRowsNumber(); row++) {
                for (int column = 0; column < getColumnsNumber(); column++) {
                    result.matrix[row][column] = this.matrix[row][column].subtract(other.matrix[row][column]);
                }
            }
            return result;
        }
    }

    public Matrix multiply(Matrix other) throws InvalidMatrixDimensionException {
        if (this.getColumnsNumber() != other.getRowsNumber()) {
            throw new InvalidMatrixDimensionException("Matrices' dimensions must fulfil the multiplication condition: first.columnsNumber = second.rowsNumber.");
        }

        Matrix result = new Matrix(this.getRowsNumber(), other.getColumnsNumber());
        ComplexNumber tempElement;
        for (int row = 0; row < getRowsNumber(); row++) {
            for (int column = 0; column < getColumnsNumber(); column++) {
                tempElement = new ComplexNumber(0);
                tempElement.setReal(0);
                tempElement.setImaginary(0);
                for (int i = 0; i < getRowsNumber(); i++) {
                    tempElement = tempElement.add(matrix[row][i].add(other.matrix[i][column]));
                }
                result.matrix[row][column] = tempElement;
            }
        }

        return result;
    }

    public ComplexNumber calculateDeterminant() throws InvalidMatrixDimensionException {
        if (getRowsNumber() != getColumnsNumber()) {
            throw new InvalidMatrixDimensionException("Not square matrix can not have a determinant.");
        }

        return calculateNxNDeterminant();
    }

//    private classes.ComplexNumber calculate2x2Determinant() {
//        return matrix[0][0].multiply(matrix[1][1]).subtract(matrix[0][1].multiply(matrix[1][0]));
//    }
//
//    private classes.ComplexNumber calculate3x3Determinant() {
//        classes.ComplexNumber first = matrix[0][0].multiply(matrix[1][1]).multiply(matrix[2][2])
//                .add(matrix[0][1].multiply(matrix[1][2]).multiply(matrix[2][0]))
//                .add(matrix[0][2].multiply(matrix[1][0]).multiply(matrix[2][1]));
//        classes.ComplexNumber second = matrix[0][2].multiply(matrix[1][1]).multiply(matrix[2][0])
//                .add(matrix[0][1].multiply(matrix[1][0]).multiply(matrix[2][2]))
//                .add(matrix[0][0].multiply(matrix[1][2]).multiply(matrix[2][1]));
//        return first.subtract(second);
//    }
    // #todo decide if 2x2 and 3x3 are necessary or general case NxN is better

    private ComplexNumber calculateNxNDeterminant() {
        if (getRowsNumber() == 1) {
            return new ComplexNumber(
                    matrix[0][0].getReal(),
                    matrix[0][0].getImaginary()
            );
        }
        ComplexNumber determinant = new ComplexNumber(0);
        // decomposition of the determinant by the first column
        int column = 0;
        Matrix minor;
        for (int row = 0; row < getRowsNumber(); row++) {
            minor = new Matrix(getMatrix());
            minor.removeRow(row);
            minor.removeColumn(column);
            if ((row + column) % 2 == 0) { // sign depends on the row and column index
                determinant = determinant.add(minor.calculateNxNDeterminant());
            } else {
                determinant = determinant.add(minor.calculateNxNDeterminant()).multiply(-1);
            }
        }

        return determinant;
    }

    private void removeRow(int rowToDelete) {
        if (rowToDelete < 0) {
            throw new IllegalArgumentException("Row index can not be negative.");
        }
        if (rowToDelete >= getRowsNumber()) {
            return;
        }

        ComplexNumber[][] newMatrix = new ComplexNumber[getRowsNumber()-1][getColumnsNumber()];
        byte skippedRow = 0;
        for (int row = 0; row < getRowsNumber(); row++) {
            if (row == rowToDelete) {
                skippedRow = 1;
                continue;
            }
            for (int column = 0; column < getColumnsNumber(); column++) {
                newMatrix[row - skippedRow][column] = matrix[row][column];
            }
        }
        matrix = newMatrix;
    }

    private void removeColumn(int columnToDelete) {
        if (columnToDelete < 0) {
            throw new IllegalArgumentException("Column index can not be negative.");
        }
        if (columnToDelete >= getRowsNumber()) {
            return;
        }

        ComplexNumber[][] newMatrix = new ComplexNumber[getRowsNumber()][getColumnsNumber()-1];
        byte skippedColumn = 0;
        for (int row = 0; row < getRowsNumber(); row++) {
            if (row == columnToDelete) {
                skippedColumn = 1;
                continue;
            }
            for (int column = 0; column < getColumnsNumber(); column++) {
                newMatrix[row][column - skippedColumn] = matrix[row][column];
            }
        }
        matrix = newMatrix;
    }

    public Matrix getTransposed() {
        Matrix transposedMatrix = new Matrix(getColumnsNumber(), getRowsNumber());
        for (int row = 0; row < getRowsNumber(); row++) {
            for (int column = 0; column < getColumnsNumber(); column++) {
                transposedMatrix.matrix[column][row] = new ComplexNumber(
                        this.matrix[row][column].getReal(),
                        this.matrix[row][column].getImaginary()
                );
            }
        }
        return transposedMatrix;
    }

    public Matrix getInverse() throws InvalidMatrixDimensionException {
        if (getRowsNumber() != getColumnsNumber()) {
            throw new InvalidMatrixDimensionException("Not square matrix can not have its reverse.");
        }
        ComplexNumber determinant = this.calculateDeterminant();
        if (determinant.getReal() == 0 && determinant.getImaginary() == 0) {
            throw new IllegalCallerException("Determinant is equal to zero, matrix does not have its inverse.");
        }

        Matrix transposed = this.getTransposed();
        Matrix minor;
        Matrix inversed = new Matrix(getRowsNumber(), getColumnsNumber());
        for (int row = 0; row < getRowsNumber(); row++) {
            for (int column = 0; column < getColumnsNumber(); column++) {
                minor = new Matrix(transposed.matrix);
                minor.removeRow(row);
                minor.removeColumn(column);
                if ((row + column) % 2 == 0) {
                    inversed.matrix[row][column] = minor.calculateDeterminant();
                } else {
                    inversed.matrix[row][column] = minor.calculateDeterminant().multiply(-1);
                }
            }
        }

        return inversed;
    }

    public Matrix divide(Matrix other) throws InvalidMatrixDimensionException {
        if (this.getColumnsNumber() != other.getColumnsNumber()) {
            throw new InvalidMatrixDimensionException(
                    "Matrices can not be divided: first matrix's column number ("
                            + this.getColumnsNumber() + ") is not equal to second matrix's ("
                            + other.getColumnsNumber() + ").");
        }
        return this.multiply(other.getInverse());
    }

    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
       for (int row = 0; row < getRowsNumber(); row++) {
           for (int column = 0; column < getColumnsNumber(); column++) {
               stringBuilder.append(matrix[row][column]).append(' ');
           }
           stringBuilder.append('\n');
       }
       return stringBuilder.toString();
    }
}
