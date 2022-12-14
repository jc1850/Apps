/*  Copyright (C) 2018-2020 Andreas Shimokawa, Carsten Pfeiffer, Roi Greenberg

    This file is part of Gadgetbridge.

    Gadgetbridge is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Gadgetbridge is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */
package nodomain.freeyourgadget.gadgetbridge.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nodomain.freeyourgadget.gadgetbridge.GBApplication;

public class RtlUtils {

    /**
     * Checks the status of right-to-left option
     * @return true if right-to-left option is On, and false, if Off or not exist
     */
    public static boolean rtlSupport()
    {
        return GBApplication.getPrefs().getBoolean(GBPrefs.RTL_SUPPORT, false);
    }

    public enum characterType{
        ltr,
        rtl,
        rtl_arabic,
        punctuation,
        lineEnd,
        space,
    }

    public static characterType getCharacterType(Character c){
        characterType type;
        switch (Character.getDirectionality(c)) {
            case Character.DIRECTIONALITY_RIGHT_TO_LEFT:
                type = characterType.rtl;
                break;
            case Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC:
                type = characterType.rtl_arabic;
                break;
            case Character.DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR:
            case Character.DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR:
            case Character.DIRECTIONALITY_COMMON_NUMBER_SEPARATOR:
            case Character.DIRECTIONALITY_OTHER_NEUTRALS:
                type = characterType.punctuation;
                break;
            case Character.DIRECTIONALITY_BOUNDARY_NEUTRAL:
            case Character.DIRECTIONALITY_PARAGRAPH_SEPARATOR:
                type = characterType.lineEnd;
                break;
            case Character.DIRECTIONALITY_WHITESPACE:
                type = characterType.space;
                break;
            default:
                type = characterType.ltr;
        }

        return type;
    }

    /**
     * Checks the status of right-to-left option
     * @return true if right-to-left option is On, and false, if Off or not exist
     */
    public static boolean contextualSupport()
    {
        return GBApplication.getPrefs().getBoolean(GBPrefs.RTL_CONTEXTUAL_ARABIC, false);
    }

    //map with brackets chars to change there direction
    private static Map<Character, Character> directionSignsMap = new HashMap<Character, Character>(){
        {
            put('(', ')'); put(')', '('); put('[', ']'); put(']', '['); put('{','}'); put('}','{');


        }
    };

    /**
     * @return true if the char is in the rtl range, otherwise false
     */
    public static boolean isHebrew(Character c){

        return getCharacterType(c) == characterType.rtl;
    }

    /**
     * @return true if the char is in the rtl range, otherwise false
     */
    public static boolean isArabic(Character c){

        return getCharacterType(c) == characterType.rtl_arabic;
    }

    /**
     * @return true if the char is in the rtl range, otherwise false
     */
    public static boolean isLtr(Character c){

        return getCharacterType(c) == characterType.ltr;
    }

    /**
     * @return true if the char is in the rtl range, otherwise false
     */
    public static boolean isRtl(Character c){

        return (getCharacterType(c) == characterType.rtl) || (getCharacterType(c) == characterType.rtl_arabic);
    }

    /**
     * @return true if the char is in the punctuations range, otherwise false
     */
    public static boolean isPunctuations(Character c){

        return getCharacterType(c) == characterType.punctuation;
    }


    /**
     * @return true if the char is in the end of word list, otherwise false
     */
    public static boolean isSpaceSign(Character c){

        return getCharacterType(c) == characterType.space;
    }

    /**
     * @return true if the char is in the end of word list, otherwise false
     */
    public static boolean isEndLineSign(Character c){

        return getCharacterType(c) == characterType.lineEnd;
    }

    //map from Arabian characters to their contextual form in the beginning of the word
    private static Map<Character, Character> contextualArabicIsolated = new HashMap<Character, Character>(){
        {
            put('??', '\uFE8D');
            put('??', '\uFE8F');
            put('??', '\uFE95');
            put('??', '\uFE99');
            put('??', '\uFE9D');
            put('??', '\uFEA1');
            put('??', '\uFEA5');
            put('??', '\uFEA9');
            put('??', '\uFEAB');
            put('??', '\uFEAD');
            put('??', '\uFEAF');
            put('??', '\uFEB1');
            put('??', '\uFEB5');
            put('??', '\uFEB9');
            put('??', '\uFEBD');
            put('??', '\uFEC1');
            put('??', '\uFEC5');
            put('??', '\uFEC9');
            put('??', '\uFECD');
            put('??', '\uFED1');
            put('??', '\uFED5');
            put('??', '\uFED9');
            put('??', '\uFEDD');
            put('??', '\uFEE1');
            put('??', '\uFEE5');
            put('??', '\uFEE9');
            put('??', '\uFEED');
            put('??', '\uFEF1');
            put('??', '\uFE81');
            put('??', '\uFE93');
            put('??', '\uFEEF');
            put('??', '\uFE89');
            put('??', '\uFE87');
            put('??', '\uFE83');
            put('??', '\uFE80');
            put('??', '\uFE85');
            put((char)('??' + '??'), '\uFEF5');
            put((char)('??' + '??'), '\uFEF7');
            put((char)('??' + '??'), '\uFEF9');
            put((char)('??' + '??'), '\uFEFB');
            //Farsi
            put('??', '\uFB92');
            put('??', '\uFB8E');
            put('??', '\uFB7A');
            put('??', '\uFB56');
            put('??', '\uFB8A');
            put('??', '\uFBFC');

        }
    };

    //map from Arabian characters to their contextual form in the beginning of the word
    private static Map<Character, Character> contextualArabicBeginning = new HashMap<Character, Character>(){
        {
            put('??', '\uFE91');
            put('??', '\uFE97');
            put('??', '\uFE9B');
            put('??', '\uFE9F');
            put('??', '\uFEA3');
            put('??', '\uFEA7');
            put('??', '\uFEB3');
            put('??', '\uFEB7');
            put('??', '\uFEBB');
            put('??', '\uFEBF');
            put('??', '\uFEC3');
            put('??', '\uFEC7');
            put('??', '\uFECB');
            put('??', '\uFECF');
            put('??', '\uFED3');
            put('??', '\uFED7');
            put('??', '\uFEDB');
            put('??', '\uFEDF');
            put('??', '\uFEE3');
            put('??', '\uFEE7');
            put('??', '\uFEEB');
            put('??', '\uFEF3');
            put('??', '\uFE8B');
            //Farsi
            put('??', '\uFB94');
            put('??', '\uFB90');
            put('??', '\uFB7C');
            put('??', '\uFB58');
            put('??', '\uFBFE');
        }
    };

    //map from Arabian characters to their contextual form in the middle of the word
    private static Map<Character, Character> contextualArabicMiddle = new HashMap<Character, Character>(){
        {
            put('??', '\uFE92');
            put('??', '\uFE98');
            put('??', '\uFE9C');
            put('??', '\uFEA0');
            put('??', '\uFEA4');
            put('??', '\uFEA8');
            put('??', '\uFEB4');
            put('??', '\uFEB8');
            put('??', '\uFEBC');
            put('??', '\uFEC0');
            put('??', '\uFEC4');
            put('??', '\uFEC8');
            put('??', '\uFECC');
            put('??', '\uFED0');
            put('??', '\uFED4');
            put('??', '\uFED8');
            put('??', '\uFEDC');
            put('??', '\uFEE0');
            put('??', '\uFEE4');
            put('??', '\uFEE8');
            put('??', '\uFEEC');
            put('??', '\uFEF4');
            put('??', '\uFE8C');
            //Farsi
            put('??', '\uFB95');
            put('??', '\uFB91');
            put('??', '\uFB7D');
            put('??', '\uFB59');
            put('??', '\uFBFF');
        }
    };

    //map from Arabian characters to their contextual form in the end of the word
    private static Map<Character, Character> contextualArabicEnd = new HashMap<Character, Character>(){
        {
            put('??', '\uFE8E');
            put('??', '\uFE90');
            put('??', '\uFE96');
            put('??', '\uFE9A');
            put('??', '\uFE9E');
            put('??', '\uFEA2');
            put('??', '\uFEA6');
            put('??', '\uFEAA');
            put('??', '\uFEAC');
            put('??', '\uFEAE');
            put('??', '\uFEB0');
            put('??', '\uFEB2');
            put('??', '\uFEB6');
            put('??', '\uFEBA');
            put('??', '\uFEBE');
            put('??', '\uFEC2');
            put('??', '\uFEC6');
            put('??', '\uFECA');
            put('??', '\uFECE');
            put('??', '\uFED2');
            put('??', '\uFED6');
            put('??', '\uFEDA');
            put('??', '\uFEDE');
            put('??', '\uFEE2');
            put('??', '\uFEE6');
            put('??', '\uFEEA');
            put('??', '\uFEEE');
            put('??', '\uFEF2');
            put('??', '\uFE82');
            put('??', '\uFE94');
            put('??', '\uFEF0');
            put('??', '\uFE8A');
            put('??', '\uFE88');
            put('??', '\uFE84');
            put('??', '\uFE86');
            put((char)('??' + '??'), '\uFEF6');
            put((char)('??' + '??'), '\uFEF8');
            put((char)('??' + '??'), '\uFEFA');
            put((char)('??' + '??'), '\uFEFC');
            //Farsi
            put('??', '\uFB93');
            put('??', '\uFB8F');
            put('??', '\uFB7B');
            put('??', '\uFB57');
            put('??', '\uFB8B');
            put('??', '\uFBFD');
        }
    };
    public enum contextualState{
        isolate,
        begin,
        middle,
        end
    }

    public static boolean exceptionAfterLam(Character c){
        switch (c){
            case '\u0622':
            case '\u0623':
            case '\u0625':
            case '\u0627':
                return true;
            default:
                return false;

        }
    }

    /**
     * This function return the contextual form of Arabic characters in a given state
     * @param c - the character to convert
     * @param state - the character state: beginning, middle, end or isolated
     * @return the contextual character
     */
    public static Character getContextualSymbol(Character c, contextualState state) {
        Character newChar;
        switch (state){
            case begin:
                newChar = contextualArabicBeginning.get(c);
                break;
            case middle:
                newChar = contextualArabicMiddle.get(c);
                break;
            case end:
                newChar = contextualArabicEnd.get(c);
                break;
            case isolate:
            default:
                newChar  = contextualArabicIsolated.get(c);;
        }
        if (newChar != null){
            return newChar;
        } else{
            return c;
        }
    }

    /**
     * This function return the contextual state of a given character, depend of the previous
     * character state and the next charachter.
     * @param prevState - previous character state or isolated if none
     * @param curChar - the current character
     * @param nextChar - the next character or null if none
     * @return the current character contextual state
     */
    public static contextualState getCharContextualState(contextualState prevState, Character curChar, Character nextChar) {
        contextualState curState;
        if ((prevState == contextualState.isolate || prevState == contextualState.end) &&
                contextualArabicBeginning.containsKey(curChar) &&
                contextualArabicEnd.containsKey(nextChar)){

            curState = contextualState.begin;

        } else if ((prevState == contextualState.begin || prevState == contextualState.middle) &&
                contextualArabicEnd.containsKey(curChar)){

            if (contextualArabicMiddle.containsKey(curChar) && contextualArabicEnd.containsKey(nextChar)){
                curState = contextualState.middle;
            }else{
                curState = contextualState.end;
            }
        }else{
            curState = contextualState.isolate;
        }
        return curState;
    }

    /**
     * this function convert given string to it's contextual form
     * @param s - the given string
     * @return the contextual form of the string
     */
    public static String convertToContextual(String s){
        if (s == null || s.isEmpty() || s.length() == 1){
            return s;
        }

        int length = s.length();
        StringBuilder newWord = new StringBuilder(length);

        Character curChar, nextChar = s.charAt(0);
        contextualState prevState = contextualState.isolate;
        contextualState curState = contextualState.isolate;

        for (int i = 0; i < length - 1; i++){
            curChar = nextChar;
            nextChar = s.charAt(i + 1);

            if (curChar == '??' && exceptionAfterLam(nextChar)){
                i++;
                curChar = (char)(nextChar + curChar);
                if (i < length - 1) {
                    nextChar = s.charAt(i + 1);
                }else{
                    nextChar = curChar;
                    prevState = curState;
                    break;
                }

            }

            curState = getCharContextualState(prevState, curChar, nextChar);
            newWord.append(getContextualSymbol(curChar, curState));
            prevState = curState;


        }
        curState = getCharContextualState(prevState, nextChar, null);
        newWord.append(getContextualSymbol(nextChar, curState));

        return newWord.toString();
    }


    /**
     * The function get a string and reverse it.
     * in case of end-of-word sign, it will leave it at the end.
     * in case of sign with direction like brackets, it will change the direction.
     * @param s - the string to reverse
     * @return reversed string
     */
    public static String reverse(String s) {
        int j = s.length();
        int isEndLine = 0;
        char[] newWord = new char[j];

        if (j == 0) {
            return s;
        }

        for (int i = 0; i < s.length() - isEndLine; i++) {
            if (directionSignsMap.containsKey(s.charAt(i))) {
                newWord[--j] = directionSignsMap.get(s.charAt(i));
            } else {
                newWord[--j] = s.charAt(i);
            }
        }

        return new String(newWord);
    }

    public static String fixWhitespace(String s){
        int length = s.length();

        if (length > 0 && isSpaceSign(s.charAt(length - 1))){
            return s.charAt(length - 1) + s.substring(0, length - 1);
        } else {
            return s;
        }
    }

    /**
     * The function get a string and fix the rtl words.
     * since simple reverse puts the beginning of the text at the end, the text should have been from bottom to top.
     * To avoid that, we save the text in lines (line max size can be change in the settings)
     * @param oldString - the string to fix.
     * @return a fix string.
     */
    public static String fixRtl(String oldString) {
        if (oldString == null || oldString.isEmpty()){
            return oldString;
        }
        debug("before: |" + org.apache.commons.lang3.StringEscapeUtils.escapeJava(oldString) + "|");

        int length = oldString.length();
        String newString = "";
        List<String> lines = new ArrayList<>();
        char[] newWord = new char[length];
        int line_max_size = GBApplication.getPrefs().getInt("rtl_max_line_length", 18);

        int startPos = 0;
        int endPos = 0;
        characterType CurRtlType = isRtl(oldString.charAt(0))? characterType.rtl : characterType.ltr;
        characterType PhraseRtlType = CurRtlType;

        Character c;
//        String word = "", phrase = "", line = "";
        StringBuilder word = new StringBuilder();
        StringBuilder phrase = new StringBuilder();
        StringBuilder line = new StringBuilder();
        String phraseString = "";
        boolean addCharToWord = false;
        for (int i = 0; i < length; i++) {
            c = oldString.charAt(i);
            addCharToWord = false;
            debug("char: " + c + " :" + Character.getDirectionality(c));
//            debug( "hex : " + (int)c);

            if (isLtr(c)){
                CurRtlType = characterType.ltr;
            } else if (isRtl(c)) {
                CurRtlType = characterType.rtl;
            }

            if ((CurRtlType == PhraseRtlType) && !(isSpaceSign(c) || isEndLineSign(c))){
                debug("add: " + c + " to: " + word);
                word.append(c);
                addCharToWord = true;
                if (i < length - 1) {
                    continue;
                }
            }



            do {
                if ((line.length() + phrase.length() + word.length() < line_max_size) ||
                        (line.length() == 0 && word.length() >= line_max_size)) {
                    if (isSpaceSign(c)) {
                        word.append(c);
                        addCharToWord = true;
                    }

                    phrase.append(word);
                    word.setLength(0);

                    if (isSpaceSign(c)) {
                        break;
                    }
                }


                phraseString = phrase.toString();
                debug("phrase:   |" + phraseString + "|");
                if (PhraseRtlType == characterType.rtl) {
                    if (contextualSupport()) {
                        phraseString = convertToContextual(phraseString);
                    }
                    phraseString = reverse(phraseString);
                }

                line.insert(0, fixWhitespace(phraseString));
                debug("line now: |" + line + "|");
                phrase.setLength(0);

                if (word.length() > 0){
                    line.append('\n');
                } else if (isEndLineSign(c)) {
                    line.append(c);
                } else if (!addCharToWord) {
                    word.append(c);
                    if (i == length - 1){
                        addCharToWord = true;
                        continue;
                    }
                    PhraseRtlType = PhraseRtlType == characterType.rtl ? characterType.ltr : characterType.rtl;
                    break;
                }

                lines.add(line.toString());
                debug("line: |" + line + "|");
                line.setLength(0);

                if (word.length() == 0){
                    break;
                }

            } while (true);

        }

        lines.add(line.toString());

        newString = TextUtils.join("", lines);

        debug("after : |" + org.apache.commons.lang3.StringEscapeUtils.escapeJava(newString) + "|");

        return newString;
    }

    private static void debug(String s) {
//        Log.d("ROIGR", s);
    }
}
