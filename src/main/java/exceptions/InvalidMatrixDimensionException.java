package exceptions;

public class InvalidMatrixDimensionException extends MatrixException {

    public InvalidMatrixDimensionException() {
        super();
    }
    public InvalidMatrixDimensionException(String message) {
        super(message);
    }
}
