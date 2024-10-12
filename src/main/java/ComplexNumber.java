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
            resultingReal = this.real + other.real;
            resultingImaginary = this.imaginary + other.imaginary;
        }
        return new ComplexNumber(resultingReal, resultingImaginary);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        double resultingReal, resultingImaginary;
        if (other == null) {
            resultingReal = 0;
            resultingImaginary = 0;
        } else {
            resultingReal = this.real - other.real;
            resultingImaginary = this.imaginary - other.imaginary;
        }
        return new ComplexNumber(resultingReal, resultingImaginary);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double resultingReal, resultingImaginary;
        if (other == null) {
            resultingReal = 0;
            resultingImaginary = 0;
        } else {
            resultingReal = this.real * other.real - this.imaginary * other.imaginary;
            resultingImaginary = this.real * other.imaginary + this.imaginary * other.real;
        }
        return new ComplexNumber(resultingReal, resultingImaginary);
    }

    public ComplexNumber multiply(int other) {
        double resultingReal = this.real * other;
        double resultingImaginary = this.imaginary * other;
        return new ComplexNumber(resultingReal, resultingImaginary);
    }

    public ComplexNumber divide(ComplexNumber other) {
        if (other == null || (other.real == 0 && other.imaginary == 0)) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        // z1 = a+bi, z2 = c+di
        double a = this.real;
        double b = this.imaginary;
        double c = other.real;
        double d = other.imaginary;

        double denominator = c*c + d*d;
        double resultingReal = (a*c + b*d) / denominator;
        double resultingImaginary = (b*c - a*d) / denominator;
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
