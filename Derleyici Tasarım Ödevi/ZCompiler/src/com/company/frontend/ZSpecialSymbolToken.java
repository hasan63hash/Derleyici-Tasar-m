package com.company.frontend;

import static com.company.frontend.ZTokenType.ERROR;
import static com.company.frontend.ZTokenType.SPECIAL_SYMBOLS;

public class ZSpecialSymbolToken extends Token {
    public ZSpecialSymbolToken(Source source) throws Exception {
        super(source);
    }

    public void extract() throws Exception
    {
        char currentChar = source.currentChar();
        text = Character.toString(currentChar);
        tokenType = null;
        switch (currentChar) {
            case '-': case '*': case '/': case ',':
            case ';': case '\'': case '(': case ')':
            case '{': case '}': {
                source.nextChar();
                break;
            }
            case '!':
            case '=':
            case '>': {
                currentChar = source.nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    source.nextChar();
                }
                break;
            }
            case '+': {
                currentChar = source.nextChar();
                if (currentChar == '+') {
                    text += currentChar;
                    source.nextChar();
                }
                break;
            }
            case '<': {
                currentChar = source.nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    source.nextChar();
                }
                else if (currentChar == '>') {
                    text += currentChar;
                    source.nextChar();
                }
                break;
            }
            case '.': {
                currentChar = source.nextChar();
                if (currentChar == '.') {
                    text += currentChar;
                    source.nextChar();
                }
                break;
            }
            default: {
                source.nextChar(); // consume bad character
                tokenType = ERROR;
                value = null;
            }
        }

        if (tokenType == null) {
            tokenType = SPECIAL_SYMBOLS.get(text);
        }
    }

}
