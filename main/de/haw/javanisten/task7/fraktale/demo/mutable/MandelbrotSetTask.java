package de.haw.javanisten.task7.fraktale.demo.mutable;

import de.haw.javanisten.complex.Cartesian;
import de.haw.javanisten.complex.Complex;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MandelbrotSetTask extends de.haw.javanisten.task7.fraktale.demo.parallel.MandelbrotSetTask {
    private final boolean _mutable;

    public MandelbrotSetTask(boolean parallel, PixelWriter pixelWriter, int width, int height, double minR, double minI, double maxR, double maxI, double minX, double minY, double maxX, double maxY, boolean fast, boolean mutable) {
        super(parallel, pixelWriter, width, height, minR, minI, maxR, maxI, minX, minY, maxX, maxY, fast);
        this._mutable = mutable;
    }

    protected int calc(Complex comp) {
        int count = 0;
        Complex c = new Complex(new Cartesian(0, 0), this._mutable);
        do {
            c = c.multiply(c).plus(comp);
            count++;
        } while (count < CAL_MAX_COUNT && c.lengthSQ() < LENGTH_BOUNDARY);
        return count;
    }

    protected Color calcPixel(double x, double y) {
        double re = (minR * (width - x) + x * maxR) / width;
        double im = (minI * (height - y) + y * maxI) / height;
        Complex calPixel = new Complex(new Cartesian(re, im), this._mutable);
        return getColor(calc(calPixel));
    }
}
