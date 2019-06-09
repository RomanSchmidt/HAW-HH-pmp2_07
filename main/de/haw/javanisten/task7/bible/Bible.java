package de.haw.javanisten.task7.bible;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Bible {
    private final String _deVersionFile = "de.txt";
    private final Charset _deVersionCharset = Charset.forName("windows-1252");
    private final String _enVersionFile = "en.txt";
    private final Charset _enVersionCharset = Charset.forName("utf-8");
    private HashMap<String, HashMap<Integer, HashMap<Integer, String>>> _indexDe = new HashMap<>();
    private HashMap<String, HashMap<Integer, HashMap<Integer, String>>> _indexEn = new HashMap<>();

    private HashMap<String, String> _bookMap = new HashMap<>();

    Bible() throws IOException {
        this._init();
    }

    private void _readIndex(String filName, HashMap<String, HashMap<Integer, HashMap<Integer, String>>> map, Charset charset) throws IOException {
        BufferedReader fileHandler = this._getReadHandler(filName, charset);
        if (null != fileHandler) {
            fileHandler.lines().forEach(line -> {
                String[] splitted1 = line.split("( |\t)\\d+:\\d+( |\t)+", 2);
                Pattern pattern = Pattern.compile("[ \t](\\d+:\\d+)[ \t]");
                Matcher matcher = pattern.matcher(line);
                if (splitted1.length == 2 && matcher.find()) {
                    String[] sectionVerse = matcher.group(1).split(":");

                    String mappedBook = this._bookMap.getOrDefault(splitted1[0], splitted1[0]);
                    HashMap<Integer, HashMap<Integer, String>> byBool = map.computeIfAbsent(mappedBook, k -> new HashMap<>());
                    HashMap<Integer, String> bySection = byBool.computeIfAbsent(Integer.parseInt(sectionVerse[0]), k -> new HashMap<>());
                    bySection.put(Integer.parseInt(sectionVerse[1]), splitted1[1]);
                }
            });
            fileHandler.close();
        }
    }

    @Nullable
    private BufferedReader _getReadHandler(String fileName, Charset charset) {
        URL filePath = this.getClass().getResource(fileName);
        try {
            return Files.newBufferedReader(Paths.get(filePath.toExternalForm().replace("file:/", "")), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void _init() throws IOException {
        this._fillBookMap();
        this._readIndex(this._deVersionFile, this._indexDe, this._deVersionCharset);
        this._readIndex(this._enVersionFile, this._indexEn, this._enVersionCharset);
    }

    public HashMap<String, String> get(String book, int section, int verse) {
        book = this._bookMap.getOrDefault(book, book);
        HashMap<String, String> output = new HashMap<>();
        output.put(Language.de.toString(), _getByLanguage(this._indexDe, book, section, verse));
        output.put(Language.en.toString(), _getByLanguage(this._indexEn, book, section, verse));

        return output;
    }

    private String _getByLanguage(@NotNull HashMap<String, HashMap<Integer, HashMap<Integer, String>>> languageMap, String book, int section, int verse) {
        String defaultNotFound = "not Found!";
        HashMap<Integer, HashMap<Integer, String>> bookMap = languageMap.getOrDefault(book, null);
        if (null == bookMap) {
            return defaultNotFound;
        }
        HashMap<Integer, String> sectionMap = bookMap.getOrDefault(section, null);
        if (null == sectionMap) {
            return defaultNotFound;
        }
        String value = sectionMap.getOrDefault(verse, null);
        if (null == value) {
            return defaultNotFound;
        }
        return value;
    }

    private void _fillBookMap() {
        this._bookMap.put("Gen", "Genesis");
        this._bookMap.put("Exo", "Exodus");
        this._bookMap.put("Lev", "Leviticus");
        this._bookMap.put("Num", "Numbers");
        this._bookMap.put("Deu", "Deuteronomy");
        this._bookMap.put("Jos", "Joshua");
        this._bookMap.put("Jdg", "Judges");
        this._bookMap.put("Rut", "Ruth");
        this._bookMap.put("1Sa", "1 Samuel");
        this._bookMap.put("2Sa", "2 Samuel");
        this._bookMap.put("1Ki", "1 Kings");
        this._bookMap.put("2Ki", "2 Kings");
        this._bookMap.put("1Ch", "1 Chronicles");
        this._bookMap.put("2Ch", "2 Chronicles");
        this._bookMap.put("Ezr", "Ezra");
        this._bookMap.put("Neh", "Nehemiah");
        this._bookMap.put("Est", "Esther");
        this._bookMap.put("Psa", "Psalms");
        this._bookMap.put("Pro", "Proverbs");
        this._bookMap.put("Ecc", "Ecclesiastes");
        this._bookMap.put("Sol", "Song of Solomon");
        this._bookMap.put("Isa", "Isaiah");
        this._bookMap.put("Jer", "Jeremiah");
        this._bookMap.put("Lam", "Lamentations");
        this._bookMap.put("Eze", "Ezekiel");
        this._bookMap.put("Dan", "Daniel");
        this._bookMap.put("Hos", "Hosea");
        this._bookMap.put("Joe", "Joel");
        this._bookMap.put("Amo", "Amos");
        this._bookMap.put("Abd", "Obadiah");
        this._bookMap.put("Jon", "Jonah");
        this._bookMap.put("Mic", "Micah");
        this._bookMap.put("Nah", "Nahum");
        this._bookMap.put("Hab", "Habakkuk");
        this._bookMap.put("Zep", "Zephaniah");
        this._bookMap.put("Hag", "Haggai");
        this._bookMap.put("Zec", "Zechariah");
        this._bookMap.put("Mal", "Malachi");
        this._bookMap.put("Mat", "Matthew");
        this._bookMap.put("Mar", "Mark");
        this._bookMap.put("Luk", "Luke");
        this._bookMap.put("Joh", "John");
        this._bookMap.put("Act", "Acts");
        this._bookMap.put("Rom", "Romans");
        this._bookMap.put("1Co", "1 Corinthians");
        this._bookMap.put("2Co", "2 Corinthians");
        this._bookMap.put("Gal", "Galatians");
        this._bookMap.put("Eph", "Ephesians");
        this._bookMap.put("Phi", "Philippians");
        this._bookMap.put("Col", "Colossians");
        this._bookMap.put("1Th", "1 Thessalonians");
        this._bookMap.put("2Th", "2 Thessalonians");
        this._bookMap.put("1Ti", "1 Timothy");
        this._bookMap.put("2Ti", "2 Timothy");
        this._bookMap.put("Tit", "Titus");
        this._bookMap.put("Phm", "Philemon");
        this._bookMap.put("Heb", "Hebrews");
        this._bookMap.put("Jam", "James");
        this._bookMap.put("1Pe", "1 Peter");
        this._bookMap.put("2Pe", "2 Peter");
        this._bookMap.put("1Jo", "1 John");
        this._bookMap.put("2Jo", "2 John");
        this._bookMap.put("3Jo", "3 John");
        this._bookMap.put("Jud", "Jude");
        this._bookMap.put("Rev", "Revelation");
    }
}
