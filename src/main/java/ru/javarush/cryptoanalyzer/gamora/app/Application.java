package ru.javarush.cryptoanalyzer.gamora.app;

import ru.javarush.cryptoanalyzer.gamora.controllers.Controller;
import ru.javarush.cryptoanalyzer.gamora.controllers.ControllerFactory;
import ru.javarush.cryptoanalyzer.gamora.entity.Result;

public abstract class Application {

    protected final Controller controller;
    protected final String[] args;

    protected Application(String[] args) {
        this.controller = getController(args);
        this.args = args;
    }

    public abstract void run();

    private Controller getController(String[] args) {
        ControllerFactory controllerFactory = new ControllerFactory(args);
        return controllerFactory.getController();
    }
}
