package com.company.frontend;

public class EOFToken extends Token{

    public EOFToken(Source source) throws Exception {
        super(source);
        this.tokenType = ZTokenType.END_OF_FILE;
    }

    @Override
    public void extract(){
        // Hiçbir şey yapma
    }
}
