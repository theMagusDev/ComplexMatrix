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

    public int getRawsAmount() {
        return rawsAmount;
    }

    public int getColumnsAmount() {
        return columnsAmount;
    }

    public Matrix add(Matrix other) {
        if (this.rawsAmount != other.rawsAmount || this.columnsAmount != other.columnsAmount) {
            throw new IllegalArgumentException("Matrices must have the same size for the add operation.");
        } else {
            Matrix result = new Matrix(this.columnsAmount, this.rawsAmount);
            for (int raw = 0; raw < rawsAmount; raw++) {
                for (int column = 0; column < columnsAmount; column++) {
                    result.getMatrix()[raw][column] = this.getMatrix()[raw][column].add(other.getMatrix()[raw][column]);
                }
            }
            return result;
        }
    }

    public Matrix subtract(Matrix other) {
        if (this.rawsAmount != other.rawsAmount || this.columnsAmount != other.columnsAmount) {
            throw new IllegalArgumentException("Matrices must have the same size for the subtract operation.");
        } else {
            Matrix result = new Matrix(this.columnsAmount, this.rawsAmount);
            for (int raw = 0; raw < rawsAmount; raw++) {
                for (int column = 0; column < columnsAmount; column++) {
                    result.getMatrix()[raw][column] = this.getMatrix()[raw][column].subtract(other.getMatrix()[raw][column]);
                }
            }
            return result;
        }
    }
}
