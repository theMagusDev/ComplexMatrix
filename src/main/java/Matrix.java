public class Matrix {
    private ComplexNumber[][] matrix;

    public Matrix() {
        this(0, 0);
    }

    public Matrix(int rawsNumber, int columnsNumber) {
        this(new ComplexNumber[rawsNumber][columnsNumber]);
    }

    public Matrix(ComplexNumber[][] matrix) {
        if (matrix.length == 0) {
            this.matrix = null;
        } else {
            int rawsNumber = matrix.length;
            int columnsNumber = matrix[0].length;
            this.matrix = new ComplexNumber[rawsNumber][columnsNumber];
            for (int i = 0; i < rawsNumber; i++) {
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

    public int getRawsNumber() {
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

    public Matrix add(Matrix other) {
        if (this.getRawsNumber() != other.getRawsNumber() || this.getColumnsNumber() != other.getColumnsNumber()) {
            throw new IllegalArgumentException("Matrices must have the same size for the add operation.");
        } else {
            Matrix result = new Matrix(this.getColumnsNumber(), this.getRawsNumber());
            for (int raw = 0; raw < getRawsNumber(); raw++) {
                for (int column = 0; column < getColumnsNumber(); column++) {
                    result.matrix[raw][column] = this.matrix[raw][column].add(other.matrix[raw][column]);
                }
            }
            return result;
        }
    }

    public Matrix subtract(Matrix other) {
        if (this.getRawsNumber() != other.getRawsNumber() || this.getColumnsNumber() != other.getColumnsNumber()) {
            throw new IllegalArgumentException("Matrices must have the same size for the subtract operation.");
        } else {
            Matrix result = new Matrix(this.getColumnsNumber(), this.getRawsNumber());
            for (int raw = 0; raw < getRawsNumber(); raw++) {
                for (int column = 0; column < getColumnsNumber(); column++) {
                    result.matrix[raw][column] = this.matrix[raw][column].subtract(other.matrix[raw][column]);
                }
            }
            return result;
        }
    }

    public Matrix multiplicate(Matrix other) {
        if (this.getColumnsNumber() != other.getRawsNumber()) {
            throw new IllegalArgumentException("Matrices' dimensions must fulfil the multiplication condition.");
        }

        Matrix result = new Matrix(this.getRawsNumber(), other.getColumnsNumber());
        ComplexNumber tempElement;
        for (int raw = 0; raw < getRawsNumber(); raw++) {
            for (int column = 0; column < getColumnsNumber(); column++) {
                tempElement = new ComplexNumber(0);
                tempElement.setReal(0);
                tempElement.setImaginary(0);
                for (int i = 0; i < getRawsNumber(); i++) {
                    tempElement = tempElement.add(matrix[raw][i].add(other.matrix[i][column]));
                }
                result.matrix[raw][column] = tempElement;
            }
        }

        return result;
    }

    public ComplexNumber calculateDeterminant() {
        if (getRawsNumber() != getColumnsNumber()) {
            throw new IllegalCallerException("Not square matrix can not have a determinant.");
        }

        if (getRawsNumber() == 1) {
            return new ComplexNumber(
                    matrix[0][0].getReal(),
                    matrix[0][0].getImaginary()
            );
        }
        if (getRawsNumber() == 2) {
            return calculate2x2Determinant();
        }
        if (getRawsNumber() == 3) {
            return calculate3x3Determinant();
        }
        return calculateNxNDeterminant();
    }

    private ComplexNumber calculate2x2Determinant() {
        return matrix[0][0].multiply(matrix[1][1]).subtract(matrix[0][1].multiply(matrix[1][0]));
    }

    private ComplexNumber calculate3x3Determinant() {
        ComplexNumber first = matrix[0][0].multiply(matrix[1][1]).multiply(matrix[2][2])
                .add(matrix[0][1].multiply(matrix[1][2]).multiply(matrix[2][0]))
                .add(matrix[0][2].multiply(matrix[1][0]).multiply(matrix[2][1]));
        ComplexNumber second = matrix[0][2].multiply(matrix[1][1]).multiply(matrix[2][0])
                .add(matrix[0][1].multiply(matrix[1][0]).multiply(matrix[2][2]))
                .add(matrix[0][0].multiply(matrix[1][2]).multiply(matrix[2][1]));
        return first.subtract(second);
    }

    private ComplexNumber calculateNxNDeterminant() {
        ComplexNumber determinant = new ComplexNumber(0);
        // decomposition of the determinant by the first column
        int column = 0;
        Matrix minor;
        for (int raw = 0; raw < getRawsNumber(); raw++) {
            minor = new Matrix(getMatrix());
            minor.removeRaw(raw);
            minor.removeColumn(column);
            if ((raw + column) % 2 == 0) { // sign depends on the raw and column index
                determinant = determinant.add(minor.calculateDeterminant());
            } else {
                determinant = determinant.add(minor.calculateDeterminant()).multiply(-1);
            }
        }

        return determinant;
    }

    private void removeRaw(int rawToDelete) {
        if (rawToDelete < 0) {
            throw new IllegalArgumentException("Raw index can not be negative.");
        }
        if (rawToDelete >= getRawsNumber()) {
            return;
        }

        ComplexNumber[][] newMatrix = new ComplexNumber[getRawsNumber()-1][getColumnsNumber()];
        byte skippedRaw = 0;
        for (int raw = 0; raw < getRawsNumber(); raw++) {
            if (raw == rawToDelete) {
                skippedRaw = 1;
                continue;
            }
            for (int column = 0; column < getColumnsNumber(); column++) {
                newMatrix[raw - skippedRaw][column] = matrix[raw][column];
            }
        }
        matrix = newMatrix;
    }

    private void removeColumn(int columnToDelete) {
        if (columnToDelete < 0) {
            throw new IllegalArgumentException("Column index can not be negative.");
        }
        if (columnToDelete >= getRawsNumber()) {
            return;
        }

        ComplexNumber[][] newMatrix = new ComplexNumber[getRawsNumber()][getColumnsNumber()-1];
        byte skippedColumn = 0;
        for (int raw = 0; raw < getRawsNumber(); raw++) {
            if (raw == columnToDelete) {
                skippedColumn = 1;
                continue;
            }
            for (int column = 0; column < getColumnsNumber(); column++) {
                newMatrix[raw][column - skippedColumn] = matrix[raw][column];
            }
        }
        matrix = newMatrix;
    }

    public Matrix getTransposed() {
        Matrix transposedMatrix = new Matrix(getColumnsNumber(), getRawsNumber());
        for (int i = 0; i < getRawsNumber(); i++) {
            for (int j = 0; j < getColumnsNumber(); j++) {
                transposedMatrix.matrix[j][i] = new ComplexNumber(
                        this.matrix[i][j].getReal(),
                        this.matrix[i][j].getImaginary()
                );
            }
        }
        return transposedMatrix;
    }

    public Matrix getInverse() {
        if (getRawsNumber() != getColumnsNumber()) {
            throw new IllegalCallerException("Not square matrix can not have its reverse.");
        }
        ComplexNumber determinant = this.calculateDeterminant();
        if (determinant.getReal() == 0 && determinant.getImaginary() == 0) {
            throw new IllegalCallerException("Determinant is equal to zero, matrix does not have its inverse.");
        }

        Matrix transposed = this.getTransposed();
        Matrix minor;
        Matrix reversed = new Matrix(getRawsNumber(), getColumnsNumber());
        for (int i = 0; i < getRawsNumber(); i++) {
            for (int j = 0; j < getColumnsNumber(); j++) {
                minor = new Matrix(transposed.matrix);
                minor.removeRaw(i);
                minor.removeColumn(j);
                if ((i + j) % 2 == 0) {
                    reversed.matrix[i][j] = minor.calculateDeterminant();
                } else {
                    reversed.matrix[i][j] = minor.calculateDeterminant().multiply(-1);
                }
            }
        }

        return reversed;
    }

    public Matrix divide(Matrix other) {

    }
    // #todo multiplicate and division methods. Do not forget to make dimension check
}
