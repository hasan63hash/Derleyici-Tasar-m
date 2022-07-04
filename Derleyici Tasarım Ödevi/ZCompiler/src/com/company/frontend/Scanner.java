package com.company.frontend;

import static com.company.frontend.Source.EOF;
import static com.company.frontend.ZTokenType.END_OF_FILE;

public class Scanner {
    Source source;
    Token currentToken;

    public Scanner(Source source){
        this.source = source;
    }

    public Token getCurrentToken(){
        return currentToken;
    }

    public Token getNextToken() throws Exception{
        currentToken = extractToken();
        return currentToken;
    }

    protected Token extractToken() throws Exception
    {
        skipWhiteSpace();
        Token token;
        char currentChar = source.currentChar();

        if (currentChar == EOF) { // Dosya sonu
            token = new EOFToken(source);
        }
        else if (Character.isLetter(currentChar)) {
            token = new ZWordToken(source);
        }
        else if (Character.isDigit(currentChar)) {
            token = new ZNumberToken(source);
        }
        else if (currentChar == '\'') {
            token = new ZStringToken(source);
        }
        else if (ZTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar))) {
            token = new ZSpecialSymbolToken(source);
        }
        else {
            token = new ZErrorToken(source, Character.toString(currentChar));
            source.nextChar(); // consume character
        }
        return token;
    }

    private void skipWhiteSpace() throws Exception {
        char currentChar = source.currentChar();
        while (Character.isWhitespace(currentChar) || (currentChar == '%')) {

            if (currentChar == '%') {
                do {
                    currentChar = source.nextChar();
                } while ((currentChar != '%') && (currentChar != EOF));

                if (currentChar == '%') {
                    currentChar = source.nextChar();
                }
            }
            else {
                currentChar = source.nextChar();
            }
        }
    }
}
