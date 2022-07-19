package ru.javarush.cryptoanalyzer.gamora;

import ru.javarush.cryptoanalyzer.gamora.actions.Action;
import ru.javarush.cryptoanalyzer.gamora.actions.ActionFactory;

public class Runner {
    public static void main(String[] args) {
        ActionFactory actionFactory = new ActionFactory();
        Action action = actionFactory.getAction(args);
        action.execute();
    }
}
