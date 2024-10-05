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

    public void changeMatrix(int raw, int column, ComplexNumber value) {
        if (raw < rawsAmount && column < columnsAmount && value != null) {
            matrix[raw][column] = value;
        }
    }

    public int getRawsAmount() {
        return rawsAmount;
    }

    public void setRawsAmount(int rawsAmount) {
        this.rawsAmount = rawsAmount;
    }

    public int getColumnsAmount() {
        return columnsAmount;
    }

    public void setColumnsAmount(int columnsAmount) {
        this.columnsAmount = columnsAmount;
    }
}
