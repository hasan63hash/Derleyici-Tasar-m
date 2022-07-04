package com.company.intermediate;

import com.company.frontend.Token;

import java.util.ArrayList;

public class InputStatement extends Statement{
    static String regex = "IDENTIFIER EQUALS ENTER LEFT_PAREN STRING RIGHT_PAREN";
    public String identifier, text;

    public InputStatement(ArrayList<Token> tokens){
        super(tokens);
        this.identifier = tokens.get(0).getText();
        this.text = tokens.get(4).getText();
    }
}
