import java.text.DecimalFormat;

public class ComplexNumber {

    private double real;
    private double imaginary;

    public ComplexNumber() {
        this(0.0, 0.0);
    }

    public ComplexNumber(double real) {
        this(real, 0.0);
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(int imaginary) {
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber other) {
        double resultingReal, resultingImaginary;
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
        double resultingReal, resultingImaginary;
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
        double resultingReal, resultingImaginary;
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
        double resultingReal = getReal() * other;
        double resultingImaginary = getImaginary() * other;
        return new ComplexNumber(resultingReal, resultingImaginary);
    }

    @Override
    public String toString() {
        DecimalFormat decFormat = new DecimalFormat("#.##");
        return "(" + decFormat.format(getReal())
                + " + "
                + decFormat.format(getImaginary()) + "i)";
    }
}
