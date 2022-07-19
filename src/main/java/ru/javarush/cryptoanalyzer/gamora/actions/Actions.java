package ru.javarush.cryptoanalyzer.gamora.actions;

import java.util.Locale;

public enum Actions {

    ENCODE(new Encoder()),
    ENCRYPT(new Encrypt()),
    DECODE(new Decoder());

    public final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        return Actions.valueOf(command.toUpperCase(Locale.ROOT)).action;
    }
}
