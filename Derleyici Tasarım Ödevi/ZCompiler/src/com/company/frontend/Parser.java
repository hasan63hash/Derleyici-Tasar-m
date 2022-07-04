package com.company.frontend;

import com.company.intermediate.ICode;

import static com.company.frontend.ZTokenType.ERROR;

public class Parser {
    ICode iCode;
    Source source;
    Scanner scanner;

    public Parser(Source source){
        this.source = source;
        this.scanner = new Scanner(source);
    }

    // Ara kodu ve sembol tablosunu oluştur
    public void parse() throws Exception {
        Token token;
        iCode = new ICode();

        long startTime = System.currentTimeMillis();

        // Dosya sonuna gelene kadar tokenleri oku
        while(!((token = scanner.getNextToken()) instanceof EOFToken)){
            if(token.getType() == ERROR){
                token.print();
                // throw new Exception();
            }
            else{
                iCode.addToken(token);
                token.print();
            }
        }

        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        System.out.println(elapsedTime + " saniyede parse edildi.");
    }

    public ICode getICode(){
        return iCode;
    }
}
