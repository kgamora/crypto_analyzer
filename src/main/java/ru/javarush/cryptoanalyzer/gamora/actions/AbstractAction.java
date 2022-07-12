package ru.javarush.cryptoanalyzer.gamora.actions;

import ru.javarush.cryptoanalyzer.gamora.exception.CryptoanalyzerApplicationException;
import ru.javarush.cryptoanalyzer.gamora.util.PathFinder;

import java.io.*;
import java.nio.file.Path;

public abstract class AbstractAction implements Action {

    protected Path readPath;
    protected Path writePath;
    protected int key;
    protected String currentText;

    protected AbstractAction() {

    }

    public void build(String[] args) {
        readPath = PathFinder.pathFinder.getFilePath(args, 1);
        writePath = PathFinder.pathFinder.getFilePath(args, 2);
        readFromFile(readPath);
        key = Integer.parseInt(args[3]);
    }

    private void readFromFile(Path readPath) {
        int bufferSize = 1024 * 1024;
        StringBuilder textBuilder = new StringBuilder(bufferSize);
        try (Reader fileReader = new FileReader(readPath.toFile())) {
            char[] charBuffer = new char[bufferSize];
            int read = fileReader.read(charBuffer);
            while (read > 0) {
                textBuilder.append(charBuffer, 0, read);
                read = fileReader.read(charBuffer);
            }
        } catch (IOException e) {
            throw new CryptoanalyzerApplicationException(e);
        }
        currentText = textBuilder.toString();
    }

    public void writeResult() {
        try (FileWriter fileWriter = new FileWriter(writePath.toFile())) {
            fileWriter.write(currentText.toCharArray());
        } catch (IOException e) {
            throw new CryptoanalyzerApplicationException(e);
        }
    }
}
