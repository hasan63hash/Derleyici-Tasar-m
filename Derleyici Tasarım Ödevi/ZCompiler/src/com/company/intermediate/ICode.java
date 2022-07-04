package com.company.intermediate;

import com.company.frontend.Token;

import java.util.ArrayList;

public class ICode {
    ArrayList<Token> tokens;
    ArrayList<Statement> statements;

    public ICode(){
        tokens = new ArrayList<>();
        statements = new ArrayList<>();
    }

    public void addToken(Token token){
        tokens.add(token);
    }

    public void splitStatements() throws Exception {
        StatementExtractor se = new StatementExtractor(tokens);
        statements = se.extract();
    }

    public ArrayList<Statement> getStatements(){
        return this.statements;
    }
}
