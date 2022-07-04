package com.company.intermediate;

import com.company.frontend.Token;
import com.company.frontend.ZTokenType;

import java.util.ArrayList;

public class DefStatement extends Statement{
    // STRING|INTEGER|TRUE|FALSE
    static String regex = "IDENTIFIER EQUALS (STRING|INTEGER|MINUS INTEGER|TRUE|FALSE)";
    public String identifier;
    public String type;
    public Object value;

    public DefStatement(ArrayList<Token> tokens){
        super(tokens);
        // Identifier token'i
        Token it = tokens.get(0);
        identifier = it.getText();

        // Value token'i
        Token vt = tokens.get(2);
        if (vt.getType() == ZTokenType.MINUS){ // - den kurtul ve sayıyı negatif yap
            vt = tokens.get(3);
            vt.setValue(-(int)vt.getValue());
        }
        type = vt.getType().toString();

        // TRUE veya FALSE ise BOOLEAN'a çevir
        if(type.equals("TRUE")){
            type = "BOOLEAN";
            value = true;
        }
        else if(type.equals("FALSE")){
            type = "BOOLEAN";
            value = false;
        }
        else {
            value = vt.getValue();
        }
    }
}
