public class ComplexNumber {

    private int real;
    private int imaginary;

    public ComplexNumber() {
        this(0, 0);
    }

    public ComplexNumber(int real) {
        this(real, 0);
    }

    public ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public int getImaginary() {
        return imaginary;
    }

    public void setImaginary(int imaginary) {
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber other) {
        int resultingReal, resultingImaginary;
        if (other == null) {
            resultingReal = 0;
            resultingImaginary = 0;
        } else {
            resultingReal = getReal() + other.getReal();
            resultingImaginary = getImaginary() + other.getImaginary();
        }
        return new ComplexNumber(resultingReal, resultingImaginary);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        int resultingReal, resultingImaginary;
        if (other == null) {
            resultingReal = 0;
            resultingImaginary = 0;
        } else {
            resultingReal = getReal() + other.getReal();
            resultingImaginary = getImaginary() - other.getImaginary();
        }
        return new ComplexNumber(resultingReal, resultingImaginary);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        int resultingReal, resultingImaginary;
        if (other == null) {
            resultingReal = 0;
            resultingImaginary = 0;
        } else {
            resultingReal = getReal() * other.getReal() - getImaginary() * other.getImaginary();
            resultingImaginary = getReal() * other.getImaginary() + getImaginary() * other.getReal();
        }
        return new ComplexNumber(resultingReal, resultingImaginary);
    }

    public ComplexNumber multiply(int other) {
        int resultingReal = getReal() * other;
        int resultingImaginary = getImaginary() * other;
        return new ComplexNumber(resultingReal, resultingImaginary);
    }
}
