public class Matrix {
    private int rawsAmount;
    private int columnsAmount;
    private ComplexNumber[][] matrix;

    public Matrix() {
        this(0, 0);
    }

    public Matrix(int columnsAmount, int rawsAmount) {
        this.columnsAmount = columnsAmount;
        this.rawsAmount = rawsAmount;
    }

    public Matrix(int columnsAmount, int rawsAmount, ComplexNumber[][] matrix) {
        this(columnsAmount, rawsAmount);
        this.matrix = matrix;
    }

    public ComplexNumber[][] getMatrix() {
        return matrix;
    }

    public ComplexNumber getElement(int raw, int column) {
        if (raw < rawsAmount && column < columnsAmount) {
            return matrix[raw][column];
        } else {
            return null;
        }
    }

    public void setElement(int raw, int column, ComplexNumber value) {
        if (raw < rawsAmount && column < columnsAmount && value != null) {
            matrix[raw][column] = value;
        }
    }

    public int getRawsAmount() {
        return rawsAmount;
    }

    public int getColumnsAmount() {
        return columnsAmount;
    }

    public Matrix add(Matrix other) {
        if (rawsAmount != other.rawsAmount || columnsAmount != other.columnsAmount) {
            throw new IllegalArgumentException("Matrices must have the same size for the add operation.");
        } else {
            Matrix result = new Matrix(this.columnsAmount, this.rawsAmount);
            for (int raw = 0; raw < rawsAmount; raw++) {
                for (int column = 0; column < columnsAmount; column++) {
                    result.setElement(raw, column, this.getElement(raw, column).add(other.getElement(raw, column)));
                }
            }
            return result;
        }
    }

    public Matrix subtract(Matrix other) {
        if (rawsAmount != other.rawsAmount || columnsAmount != other.columnsAmount) {
            throw new IllegalArgumentException("Matrices must have the same size for the subtract operation.");
        } else {
            Matrix result = new Matrix(this.columnsAmount, this.rawsAmount);
            for (int raw = 0; raw < rawsAmount; raw++) {
                for (int column = 0; column < columnsAmount; column++) {
                    result.setElement(raw, column, this.getElement(raw, column).subtract(other.getElement(raw, column)));
                }
            }
            return result;
        }
    }
}
