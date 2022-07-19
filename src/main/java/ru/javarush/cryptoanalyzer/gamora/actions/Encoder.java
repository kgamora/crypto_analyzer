package ru.javarush.cryptoanalyzer.gamora.actions;

import ru.javarush.cryptoanalyzer.gamora.constants.Strings;
import ru.javarush.cryptoanalyzer.gamora.entity.Result;
import ru.javarush.cryptoanalyzer.gamora.entity.ResultCode;

import java.util.*;

public class Encoder extends AbstractAction {
    @Override
    public Result execute() {
        currentText = currentText.toLowerCase();
        Map<Character, Character> charMapping = getCharacterMapping();
        currentText = encodeText(charMapping);
        writeResult();
        return new Result(ResultCode.OK);
    }

    private String encodeText(Map<Character, Character> charMapping) {
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

    private Map<Character, Character> getCharacterMapping() {
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

    public char[] rotateAlphabet(char[] oldAlphabet, int offset) {
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
}
