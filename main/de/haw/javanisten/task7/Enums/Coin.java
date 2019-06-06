package de.haw.javanisten.task7.Enums;

import org.jetbrains.annotations.Contract;

public enum Coin {
    nickel("Nickel", 5),
    dime("Dime", 10),
    quarter("Quarter", 25),
    halfDollar("Half Dollar", 50),
    dollar("Dollar", 100);

    public final String name;
    public final int value;

    @Contract(pure = true)
    private Coin(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
