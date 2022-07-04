package com.company.intermediate;

import com.company.frontend.Token;

import java.util.ArrayList;

public class PrintStatement extends Statement {
    static String regex = "PRT LEFT_PAREN (STRING|INTEGER|IDENTIFIER) RIGHT_PAREN";
    public Token st;

    public PrintStatement(ArrayList<Token> tokens){
        super(tokens);
        this.st = tokens.get(2);
    }
}
