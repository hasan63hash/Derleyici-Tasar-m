package com.company.frontend;

import static com.company.frontend.Source.EOF;
import static com.company.frontend.ZTokenType.ERROR;
import static com.company.frontend.ZTokenType.STRING;

public class ZStringToken extends Token{

    public ZStringToken(Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();
        char currentChar = source.nextChar();
        textBuffer.append('\'');

        do {
            if (Character.isWhitespace(currentChar)) {
                currentChar = ' ';
            }
            if ((currentChar != '\'') && (currentChar != EOF)) {
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = source.nextChar();
            }
            if (currentChar == '\'') {
                while ((currentChar == '\'') && (source.peekChar() == '\'')) {
                    textBuffer.append("''");
                    valueBuffer.append(currentChar);
                    currentChar = source.nextChar();
                    currentChar = source.nextChar();
                }
            }
        } while ((currentChar != '\'') && (currentChar != EOF));
        if (currentChar == '\'') {
            source.nextChar();
            textBuffer.append('\'');
            tokenType = STRING;
            value = valueBuffer.toString();
        }
        else {
            tokenType = ERROR;
            value = null;
        }
        text = textBuffer.toString();
    }

}
