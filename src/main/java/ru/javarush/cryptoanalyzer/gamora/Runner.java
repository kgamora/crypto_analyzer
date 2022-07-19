package ru.javarush.cryptoanalyzer.gamora;

import ru.javarush.cryptoanalyzer.gamora.app.CommandLineApplication;

public class Runner {
    public static void main(String[] args) {
        CommandLineApplication commandLineApplication = new CommandLineApplication(args);
        commandLineApplication.run();
    }
}
