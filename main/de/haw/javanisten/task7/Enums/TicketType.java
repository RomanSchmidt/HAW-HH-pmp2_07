package de.haw.javanisten.task7.Enums;

import org.jetbrains.annotations.Contract;

public enum TicketType {
    ToHell(100, "To Hell!"),
    FromHell(Integer.MAX_VALUE, "From Hell!");

    private final int _price;
    private final String _name;

    @Contract(pure = true)
    TicketType(int price, String name) {
        this._price = price;
        this._name = name;
    }

    @Contract(pure = true)
    public int getPrice() {
        return this._price;
    }

    @Contract(pure = true)
    public String getName() {
        return this._name;
    }
}
