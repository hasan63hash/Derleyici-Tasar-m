package com.company.backend;

import com.company.intermediate.DefStatement;

public class Variable {
    String identifier;
    String type;
    Object value;

    public Variable(String identifier, String type, Object value){
        this.identifier = identifier;
        this.type = type;
        this.value = value;
    }

    public Variable(DefStatement statement){
        this.identifier = statement.identifier;
        this.type = statement.type;
        this.value = statement.value;
    }

    public String toString(){
        return String.format("{type: %s, identifier: %s, value: %s}", type, identifier, value);
    }
}
