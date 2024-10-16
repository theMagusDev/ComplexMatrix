package exceptions;

public class InvalidDeterminantException extends MatrixException {

    public InvalidDeterminantException() {
        super();
    }
    public InvalidDeterminantException(String message) {
        super(message);
    }
}