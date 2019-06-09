package de.haw.javanisten.task7.bible;

import org.jetbrains.annotations.Contract;

public enum Language {
    de("De"),
    en("En");

    private final String _name;

    @Contract(pure = true)
    Language(String name) {
        this._name = name;
    }

    @Contract(pure = true)
    public String getName() {
        return this._name;
    }

    @Contract(pure = true)
    @Override
    public String toString() {
        return this.getName();
    }
}
