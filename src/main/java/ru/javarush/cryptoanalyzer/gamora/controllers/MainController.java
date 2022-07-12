package ru.javarush.cryptoanalyzer.gamora.controllers;

import ru.javarush.cryptoanalyzer.gamora.actions.Action;
import ru.javarush.cryptoanalyzer.gamora.actions.Actions;
import ru.javarush.cryptoanalyzer.gamora.entity.Result;

public class MainController implements Controller {

    public Result execute(String command, String[] parameters) {
        Action action = Actions.find(command);
        throw new UnsupportedOperationException();
        //return action.execute(parameters);
    }

}
