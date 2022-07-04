package com.company.frontend;

import static com.company.frontend.ZTokenType.ERROR;
import static com.company.frontend.ZTokenType.INTEGER;

public class ZNumberToken extends Token {

    public ZNumberToken(Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        extractNumber(textBuffer);
        text = textBuffer.toString();
    }

    protected void extractNumber(StringBuilder textBuffer) throws Exception
    {
        String wholeDigits = null;
        char exponentSign = '+';
        if(source.currentChar() == '+'){
            source.nextChar();
        }
        else if(source.currentChar() == '-'){
            exponentSign = '-';
            source.nextChar();
        }

        char currentChar; // current character
        tokenType = INTEGER;

        wholeDigits = unsignedIntegerDigits(textBuffer);
        if(tokenType == ERROR){
            return;
        }

        int integerValue = computeIntegerValue(wholeDigits);
        if (tokenType != ERROR) {
            if(exponentSign == '-'){
                integerValue = integerValue * -1;
            }
            value = Integer.valueOf(integerValue);
        }
    }

    public String unsignedIntegerDigits (StringBuilder textBuffer) throws Exception
    {
        char currentChar = source.currentChar();
        if (!Character.isDigit(currentChar)) {
            tokenType = ERROR;
            value = null;
            return null;
        }
        StringBuilder digits = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            textBuffer.append(currentChar);
            digits.append(currentChar);
            currentChar = source.nextChar();
        }
        return digits.toString();
    }

    private int computeIntegerValue(String digits)
    {
        if (digits == null) {
            return 0;
        }

        int integerValue = 0;
        int prevValue = -1;
        int index = 0;

        while ((index < digits.length()) && (integerValue >= prevValue)) {
            prevValue = integerValue;
            integerValue = 10 * integerValue + Character.getNumericValue(digits.charAt(index++));
        }

        if (integerValue >= prevValue) {
            return integerValue;
        }
        else {
            tokenType = ERROR;
            value = null;
            return 0;
        }
    }

}
