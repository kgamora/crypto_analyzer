package ru.javarush.cryptoanalyzer.gamora.exception;

public enum ExceptionMessages {
    BRUTE_FAILED("Wasn't able to decode text with brute force");

    public final String exceptionMessage;

    ExceptionMessages(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
