package de.haw.javanisten.task1.complex;

import org.jetbrains.annotations.Contract;

/**
 * @author Roman Schmidt, Stanislaw Brug
 * <p>
 * Handle and scope the polar attributes and give convert method to cartesian
 */
public class Polar {
    private double _alpha;
    private double _abs;

    @Contract(pure = true)
    public Polar(double alpha, double abs) {
        this._alpha = alpha;
        this._abs = abs;
    }

    public double getAlpha() {
        return _alpha;
    }

    public double getAbs() {
        return _abs;
    }

    public Cartesian getCartesian() {
        return MathUtils.getCartesianFromPolar(this);
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Polar) {
            return
                    MathUtils.fuzzyEquals(this._abs, ((Polar) other)._abs, 0.1) &&
                            MathUtils.fuzzyEquals(this._alpha, ((Polar) other)._alpha, 0.1)
                    ;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("abs: ");
        builder.append(this._abs);
        builder.append("; alpha: ");
        builder.append(this._alpha);
        return builder.toString();
    }
}
