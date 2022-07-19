package ru.javarush.cryptoanalyzer.gamora.app;

import ru.javarush.cryptoanalyzer.gamora.entity.Result;

public class CommandLineApplication extends Application {

    public CommandLineApplication(String[] args) {
        super(args);
    }

    @Override
    public void run() {
        Result execute = controller.execute(args[0], args);
        System.out.println(execute.resultCode);
    }
}
