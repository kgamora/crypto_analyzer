package ru.javarush.cryptoanalyzer.gamora.constants;

public class Strings {
    public final static Strings strings;

    private Strings() {
    }

    static {
        strings = new Strings();
    }

    public final String RU_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщьыъэюя";
    public final String SPECIAL_SYMBOLS = ".,””:-!? ";
    public final String ALPHABET = RU_ALPHABET + SPECIAL_SYMBOLS;
}
