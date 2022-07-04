package com.company.frontend;

public class ZErrorToken extends Token{


    public ZErrorToken(Source source, String tokenText) throws Exception
    {
        super(source);
        this.text = tokenText;
        this.tokenType = ZTokenType.ERROR;
    }

    @Override
    public void extract() throws Exception {
        // birşey yapma
    }

    @Override
    public void print(){
        System.out.println(String.format("Line: %03d, Position: %d, Tokenler okunurken hata bulundu!", lineNum, position, tokenType.toString(), text));
    }
}
