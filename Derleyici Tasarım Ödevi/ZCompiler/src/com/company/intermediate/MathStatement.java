package com.company.intermediate;

import com.company.frontend.Token;

import java.util.ArrayList;

public class MathStatement extends Statement {
    static String regex = "IDENTIFIER EQUALS (INTEGER|IDENTIFIER) (PLUS|MINUS|STAR|SLASH) (INTEGER|IDENTIFIER)";
    public String identifier, operation;
    public Token x, y;

    public MathStatement(ArrayList<Token> tokens){
        super(tokens);

        // Identifier token'i
        Token it = tokens.get(0);
        this.identifier = it.getText();

        // Sayı1 token'i
        this.x = tokens.get(2);

        // operatör token'i
        Token op = tokens.get(3);
        operation = op.getType().toString();

        // Sayı2 token'i
        this.y = tokens.get(4);
    }
}
