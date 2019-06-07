package de.haw.javanisten.complex;


/**
 * @author Roman Schmidt, Stanislaw Brug
 * <p>
 * Class for mathematical calculation of a complex class which should never force any changes to the Complex object
 */
public abstract class MathUtils {

    public static Cartesian getCartesianFromPolar(Polar polar) {
        double radiant = polar.getAlpha() * (Math.PI / 180);
        double real = polar.getAbs() * Math.cos(radiant);
        double imag = polar.getAbs() * Math.sin(radiant);
        return new Cartesian(real, imag);
    }

    public static Polar getPolarFromCartesian(Cartesian cartesian) {
        Complex complex = new Complex(cartesian);
        return new Polar(MathUtils.angleDegree(complex), complex.getAbsolute());
    }

    public static Complex exp(Complex complex) {
        Cartesian cart = new Cartesian(Math.exp(complex.getReal()) * Math.cos(complex.getImag()), Math.exp(complex.getReal()) * Math.sin(complex.getImag()));
        return new Complex(cart, complex.getType());
    }

    public static Complex sin(Complex complex) {
        Cartesian cart = new Cartesian(Math.sin(complex.getReal()) * Math.cosh(complex.getImag()), Math.cos(complex.getReal()) * Math.sinh(complex.getImag()));
        return new Complex(cart, complex.getType());
    }

    public static Complex cos(Complex complex) {
        Cartesian cart = new Cartesian(Math.cos(complex.getReal()) * Math.cosh(complex.getImag()), -Math.sin(complex.getReal()) * Math.sinh(complex.getImag()));
        return new Complex(cart, complex.getType());
    }

    public static Complex tan(Complex complex) {
        return MathUtils.sin(complex).divides(MathUtils.cos(complex));
    }

    public static double angleRadiant(Complex complex) {
        return Math.atan(complex.getImag() / complex.getReal());
    }

    public static double angleDegree(Complex complex) {
        return MathUtils.angleRadiant(complex) * (180 / Math.PI);
    }

    public static Complex reciprocal(Complex complex) {
        double scale = complex.getReal() * complex.getReal() + complex.getImag() * complex.getImag();
        Cartesian cart = new Cartesian(complex.getReal() / scale, -complex.getImag() / scale);
        return new Complex(cart, complex.getType());
    }

    /**
     * source: https://google.github.io/guava/releases/17.0/api/docs/com/google/common/math/DoubleMath.html
     */
    public static boolean fuzzyEquals(double a, double b, double tolerance) {
        return Math.copySign(a - b, 1.0) <= Math.abs(tolerance)
                || (a == b) // needed to ensure that infinities equal themselves
                || (Double.isNaN(a) && Double.isNaN(b));
    }

    public static double phase(Complex complex) {
        return Math.atan2(complex.getImag(), complex.getReal());
    }
}
