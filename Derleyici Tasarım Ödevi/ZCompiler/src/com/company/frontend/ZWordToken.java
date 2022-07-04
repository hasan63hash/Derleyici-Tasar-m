package com.company.frontend;

import static com.company.frontend.ZTokenType.IDENTIFIER;
import static com.company.frontend.ZTokenType.RESERVED_WORDS;

public class ZWordToken extends Token {

    public ZWordToken(Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = source.currentChar();

        while (Character.isLetterOrDigit(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = source.nextChar();
        }
        text = textBuffer.toString();
        tokenType = (RESERVED_WORDS.contains(text.toLowerCase())) ? ZTokenType.valueOf(text.toUpperCase()) : IDENTIFIER;
    }
}
