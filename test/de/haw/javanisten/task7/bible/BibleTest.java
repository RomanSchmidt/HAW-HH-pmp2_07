package de.haw.javanisten.task7.bible;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BibleTest {

    private final String _deMatch = "Und zum Weibe sprach er: Ich will dir viel Schmerzen schaffen, wenn du schwanger wirst; du sollst mit Schmerzen Kinder gebären; und dein Verlangen soll nach deinem Manne sein, und er soll dein Herr sein.";
    private final String _enMatch = "Unto the woman he said, I will greatly multiply thy sorrow and thy conception; in sorrow thou shalt bring forth children; and thy desire shall be to thy husband, and he shall rule over thee.";
    private final String _defaultNotFound = "not Found!";

    @Test
    void getFull() {
        assertDoesNotThrow(() -> {
            Bible bible = new Bible();
            HashMap<String, String> map = bible.get("Genesis", 3, 16);
            assertTrue(map.containsKey(Language.de.getName()));
            assertTrue(map.containsKey(Language.en.getName()));
            assertEquals(map.get(Language.de.getName()), this._deMatch);
            assertEquals(map.get(Language.en.getName()), this._enMatch);
        });
    }

    @Test
    void getShort() {
        assertDoesNotThrow(() -> {
            Bible bible = new Bible();
            HashMap<String, String> map = bible.get("Gen", 3, 16);
            assertTrue(map.containsKey(Language.de.getName()));
            assertTrue(map.containsKey(Language.en.getName()));
            assertEquals(map.get(Language.de.getName()), this._deMatch);
            assertEquals(map.get(Language.en.getName()), this._enMatch);
        });
    }

    @Test
    void getEmpty() {
        assertDoesNotThrow(() -> {
            Bible bible = new Bible();
            HashMap<String, String> map = bible.get("blah", 3, 16);
            assertTrue(map.containsKey(Language.de.getName()));
            assertTrue(map.containsKey(Language.en.getName()));
            assertEquals(map.get(Language.de.getName()), this._defaultNotFound);
            assertEquals(map.get(Language.en.getName()), this._defaultNotFound);
        });
    }
}