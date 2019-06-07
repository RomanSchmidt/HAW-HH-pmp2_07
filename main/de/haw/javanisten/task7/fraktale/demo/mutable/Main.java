package de.haw.javanisten.task7.fraktale.demo.mutable;

public class Main extends de.haw.javanisten.task7.fraktale.demo.parallel.Main {
    /**
     * change the boolean to see a difference
     */
    private final boolean _MUTABLE = false;

    /**
     * Java main for when running without JavaFX launcher
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    protected MandelbrotSetTask _createNewTask(boolean parallel, double minX, double minY, double maxX, double maxY, boolean fast) {
        return new MandelbrotSetTask(parallel, wiOffscreen.getPixelWriter(),
                (int) winWidth, (int) winHeight,
                position.getMinReal(), position.getMinImg(),
                position.getMaxReal(), position.getMaxImg(),
                minX, minY, maxX, maxY, fast, this._MUTABLE);
    }
}
