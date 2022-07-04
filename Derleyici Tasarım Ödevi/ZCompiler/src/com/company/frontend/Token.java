package com.company.frontend;

public class Token {
    Source source;
    String text;
    Object value;
    int lineNum, position;
    ZTokenType tokenType;

    public Token(Source source) throws Exception {
        this.source = source;
        this.lineNum = source.getLineNum();
        this.position = source.getCurrentPosition();
        extract();
    }

    public void extract() throws Exception {
        text = Character.toString(source.currentChar());
        value = null;
        source.nextChar(); // bir sonraki karaktere kaydır
    }

    public int getLineNumber(){
        return lineNum;
    }

    public int getPosition(){
        return position;
    }

    public String getText(){
        return text;
    }

    public Object getValue(){
        return value;
    }

    public void setValue(Object value){
        this.value = value;
    }

    public ZTokenType getType(){
        return tokenType;
    }

    public void print(){
        System.out.println(String.format("Line: %03d, Position: %d, Type: %s Text: %s", lineNum, position, tokenType.toString(), text));
    }
}
