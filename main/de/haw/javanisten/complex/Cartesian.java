package de.haw.javanisten.complex;

/**
 * @author Roman Schmidt, Stanislaw Brug
 * <p>
 * Handle and scope the cartesian attributes and give convert method to polar
 */
public class Cartesian {
    private double _imag;
    private double _real;

    public Cartesian(double real, double imag) {
        this._imag = imag;
        this._real = real;
    }

    public double getImag() {
        return _imag;
    }

    public double getReal() {
        return _real;
    }

    public Polar getPolar() {
        return MathUtils.getPolarFromCartesian(this);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Cartesian) {
            return
                    MathUtils.fuzzyEquals(this._imag, ((Cartesian) other)._imag, 0.1) &&
                            MathUtils.fuzzyEquals(this._real, ((Cartesian) other)._real, 0.1)
                    ;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("imag: ");
        builder.append(this._imag);
        builder.append("; real: ");
        builder.append(this._real);
        return builder.toString();
    }
}
