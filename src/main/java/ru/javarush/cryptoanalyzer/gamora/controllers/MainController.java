package ru.javarush.cryptoanalyzer.gamora.controllers;

import ru.javarush.cryptoanalyzer.gamora.actions.Action;
import ru.javarush.cryptoanalyzer.gamora.actions.ActionFactory;
import ru.javarush.cryptoanalyzer.gamora.entity.Result;

public class MainController implements Controller {

    public Result execute(String command, String[] parameters) {
        ActionFactory actionFactory = new ActionFactory();
        Action action = actionFactory.getAction(parameters);
        return action.execute();
    }

}
