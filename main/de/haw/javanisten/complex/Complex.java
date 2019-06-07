package de.haw.javanisten.complex;

import java.util.Objects;

/**
 * @author Roman Schmidt, Stanislaw Brug
 * <p>
 * This class holds the main logic for the calculation with Complex numbers.
 * It uses the MathUtils to
 * <p>
 * inspired by: https://introcs.cs.princeton.edu/java/32class/Complex.java.html
 */
public class Complex {
    private static final Type _defaultType = Type.cartesian;
    private static final boolean _defaultMutable = true;
    private final Type _type;
    private final boolean _isMutable;
    private double _imag;
    private double _real;

    public Complex(Cartesian cartesian) {
        this(cartesian.getReal(), cartesian.getImag(), Type.cartesian, Complex._defaultMutable);
    }

    public Complex(Polar polar) {
        this(polar.getCartesian(), Type.polar, Complex._defaultMutable);
    }

    public Complex(Polar polar, boolean mutable) {
        this(polar.getCartesian(), Type.polar, mutable);
    }

    public Complex(Cartesian cartesian, boolean mutable) {
        this(cartesian.getReal(), cartesian.getImag(), Type.cartesian, mutable);
    }

    public Complex(Cartesian cart, Type type, boolean mutable) {
        this(cart.getReal(), cart.getImag(), type, mutable);
    }

    public Complex(Polar polar, Type type, boolean mutable) {
        this(polar.getCartesian(), type, mutable);
    }

    public Complex(Cartesian cart, Type type) {
        this(cart.getReal(), cart.getImag(), type, Complex._defaultMutable);
    }

    public Complex(Polar polar, Type type) {
        this(polar.getCartesian(), type, Complex._defaultMutable);
    }

    private Complex(double real, double imag, Type type, boolean mutable) {
        this._type = type;
        this._isMutable = mutable;
        this._real = real;
        this._imag = imag;
    }

    public Cartesian getCartesian() {
        return new Cartesian(this._real, this._imag);
    }

    public Polar getPolar() {
        return this.getCartesian().getPolar();
    }

    public Type getType() {
        return this._type;
    }

    private Complex _applyChange(double real, double imag) {
        if (this._isMutable) {
            this._real = real;
            this._imag = imag;
            return this;
        }
        return new Complex(new Cartesian(real, imag), this._type, false);
    }

    @Override
    public Complex clone() {
        return this._applyChange(this._real, this._imag);
    }

    public double getImag() {
        return this._imag;
    }

    public double getReal() {
        return this._real;
    }

    @Override
    public String toString() {
        return this.toString(this._type);
    }

    public String toString(Type type) {
        return type == Type.cartesian ? this._toStringCartesian() : this._toStringPolar();
    }

    private String _toStringCartesian() {
        StringBuilder builder = new StringBuilder();
        String real = this.getReal() == 0 ? "0" : Double.toString(this.getReal());
        String imag = this.getImag() == 0 ? "0" : Double.toString(Math.abs(this.getImag()));

        builder.append(real);
        if (this.getImag() >= 0) {
            builder.append(" + ");
        } else {
            builder.append(" - ");
        }
        builder.append(imag);
        builder.append("i");
        return builder.toString();
    }

    private String _toStringPolar() {
        StringBuilder builder = new StringBuilder();

        builder.append("absolute: ");
        builder.append(this.getAbsolute());
        builder.append("; angle: ");
        builder.append(MathUtils.angleDegree(this));

        return builder.toString();
    }

    public Complex plus(Complex otherAComplex) {
        double real = this.getReal() + otherAComplex.getReal();
        double imag = this.getImag() + otherAComplex.getImag();
        return this._applyChange(real, imag);
    }

    public Complex minus(Complex otherAComplex) {
        double real = this.getReal() - otherAComplex.getReal();
        double imag = this.getImag() - otherAComplex.getImag();
        return this._applyChange(real, imag);
    }

    /**
     * make sure the other complex will not be changed any way.
     * multiply after reciprocal the other complex;
     */
    public Complex divides(Complex otherAComplex) {
        return this.multiply(MathUtils.reciprocal(otherAComplex));
    }

    public Complex multiply(Complex otherAComplex) {
        double real = this.getReal() * otherAComplex.getReal() - this.getImag() * otherAComplex.getImag();
        double imag = this.getReal() * otherAComplex.getImag() + this.getImag() * otherAComplex.getReal();
        return this._applyChange(real, imag);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Complex) {
            return (this.getReal() == ((Complex) other).getReal()) && (this.getImag() == ((Complex) other).getImag());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getReal(), this.getImag());
    }

    public double getAbsolute() {
        return Math.hypot(this.getReal(), this.getImag());
    }

    public Complex scale(double alpha) {
        return this._applyChange(alpha * this.getReal(), alpha * this.getImag());
    }

    /**
     * Square of Complex object's length, we're using square of length to
     * eliminate the computation of square root
     * @return square of length
     */
    public double lengthSQ() {
        return this.getReal() * this.getReal() + this.getImag() * this.getImag();
    }

    /**
     * invert imag
     */
    public Complex conjugate() {
        return this._applyChange(this.getReal(), -this.getImag());
    }
}
