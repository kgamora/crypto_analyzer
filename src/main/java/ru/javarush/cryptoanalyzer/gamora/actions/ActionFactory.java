package ru.javarush.cryptoanalyzer.gamora.actions;

public class ActionFactory {

    public ActionFactory() {
    }

    public Action getAction(String [] args) {
        //TODO: логика выбора действия
        String command = args[0];
        Action action = parseAction(command);
        action.build(args);
        return action;
    }

    private Action parseAction(String command) {
        String enumCommand = command.toUpperCase();
        return Actions.valueOf(enumCommand).action;
    }
}
