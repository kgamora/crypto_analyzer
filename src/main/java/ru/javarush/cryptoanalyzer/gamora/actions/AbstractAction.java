package ru.javarush.cryptoanalyzer.gamora.actions;

import ru.javarush.cryptoanalyzer.gamora.constants.Strings;
import ru.javarush.cryptoanalyzer.gamora.exception.CryptoanalyzerApplicationException;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public abstract class AbstractAction implements Action {

    protected Path readPath;
    protected Path writePath;
    protected int key;
    protected String currentText;

    protected AbstractAction() {

    }

    public abstract void build(String[] args);

    protected Map<Character, Character> getCharacterMapping(int key) {
        char[] oldAlphabet = Strings.strings.ALPHABET.toCharArray();
        char[] newAlphabet = rotateAlphabet(oldAlphabet, key);
        Map<Character, Character> charMapping = new HashMap<>();
        for (int i = 0; i < oldAlphabet.length; i++) {
            char oldChar = oldAlphabet[i];
            char newChar = newAlphabet[i];
            charMapping.put(oldChar, newChar);
        }
        return charMapping;
    }

    protected char[] rotateAlphabet(char[] oldAlphabet, int offset) {
        char[] newAlphabet = new char[oldAlphabet.length];
        offset = offset % oldAlphabet.length;
        List<Character> newCharacters = new ArrayList<>();
        for (char character : oldAlphabet) {
            newCharacters.add(character);
        }
        Collections.rotate(newCharacters, -offset);
        for (int i = 0; i < newCharacters.size(); i++) {
            newAlphabet[i] = newCharacters.get(i);
        }
        return newAlphabet;
    }

    protected void readFromFile(Path readPath) {
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

    protected String getTextAlphabet() {
        Set<Character> characterSet = new HashSet<>();
        for (int i = 0; i < currentText.length(); i++) {
            Character character = currentText.charAt(i);
            characterSet.add(character);
        }
        char[] chars = new char[characterSet.size()];
        int i = 0;
        for (Character character : characterSet) {
            chars[i++] = character;
        }
        return new String(chars);
    }

    protected String mapText(Map<Character, Character> charMapping) {
        char[] text = currentText.toCharArray();
        for (int i = 0; i < text.length; i++) {
            Character currentChar = text[i];
            if (charMapping.containsKey(currentChar)) {
                char newChar = charMapping.get(currentChar);
                text[i] = newChar;
            }
        }
        return new String(text);
    }

    protected void writeResult() {
        try (FileWriter fileWriter = new FileWriter(writePath.toFile())) {
            fileWriter.write(currentText.toCharArray());
        } catch (IOException e) {
            throw new CryptoanalyzerApplicationException(e);
        }
    }
}
