package ru.javarush.cryptoanalyzer.gamora.actions;
import ru.javarush.cryptoanalyzer.gamora.entity.Result;
import ru.javarush.cryptoanalyzer.gamora.entity.ResultCode;
import ru.javarush.cryptoanalyzer.gamora.exception.CryptoanalyzerApplicationException;
import ru.javarush.cryptoanalyzer.gamora.util.PathFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Encrypt extends AbstractAction {
    private final Path dictPath = PathFinder.pathFinder.getFilePath("dict.txt");
    private Map<Character, Character> map;

    @Override
    public Result execute() {
        currentText = currentText.toLowerCase();
        String oldAlphabet = getTextAlphabet();
        map = getNewAlphabet(oldAlphabet);
        currentText = mapText(map);
        writeResult();
        return new Result(ResultCode.OK);
    }

    @Override
    protected void writeResult() {
        super.writeResult();
        writeDictionary();
    }

    private void writeDictionary() {
        try (FileWriter fileWriter = new FileWriter(dictPath.toFile())) {
            fileWriter.write(map.toString().toCharArray());
        } catch (IOException e) {
            throw new CryptoanalyzerApplicationException(e);
        }
    }

    @Override
    public void build(String[] args) {
        readPath = PathFinder.pathFinder.getFilePath(args, 1);
        writePath = PathFinder.pathFinder.getFilePath(args, 2);
        readFromFile(readPath);
    }

    private Map<Character, Character> getNewAlphabet(String oldAlphabet) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < oldAlphabet.length(); i++) {
            char charAt = oldAlphabet.charAt(i);
            Character newChar = getNewChar(charAt);
            while (map.containsValue(newChar)) {
                newChar = getNewChar(charAt);
            }
            map.put(charAt, newChar);
        }
        return map;
    }

    private Character getNewChar(char charAt) {
        int newCharPosition = charAt + new Random().nextInt();
        return (Character) (char) newCharPosition;
    }
}
