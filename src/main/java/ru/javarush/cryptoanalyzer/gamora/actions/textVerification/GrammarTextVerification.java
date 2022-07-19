package ru.javarush.cryptoanalyzer.gamora.actions.textVerification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarTextVerification implements TextVerification {
    @Override
    public boolean verify(String text) {
        return isSpacesAfterCommas(text) && isWhitespacesAfterSentenceEndingMarks(text);
    }

    /*
     * We know that there's always a space after comma
     * @param text - text to test
     * @return boolean - all commas are guided by spaces
     */
    private boolean isSpacesAfterCommas(String text) {
        String commaRegEx = "[,][\\S\\n]";
        return isAllMatch(text, commaRegEx);
    }

    /*
     * We know that there's always a whitespace char (end of line, space, \n) after sentence ending marks (.,...?!)
     * @param text - text to test
     * @return boolean - all commas are guided by spaces
     */
    private boolean isWhitespacesAfterSentenceEndingMarks(String text) {
        String sentenceEndingMarksRegEx = "[.\\u2026?!][\\n $]";
        return isAllMatch(text, sentenceEndingMarksRegEx);
    }

    private boolean isAllMatch(String text, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        return !matcher.find();
    }
}
