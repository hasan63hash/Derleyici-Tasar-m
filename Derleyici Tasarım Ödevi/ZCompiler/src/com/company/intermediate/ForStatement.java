package com.company.intermediate;

import com.company.frontend.Token;
import com.company.frontend.ZTokenType;

import java.util.ArrayList;

public class ForStatement extends Statement {
    static String regex = "FOR LEFT_PAREN IDENTIFIER COMMA INTEGER COMMA INTEGER COMMA INCREASE RIGHT_PAREN LEFT_BRACE .* RIGHT_BRACE";
    public Token identifier, stepOp;
    public int start, end;

    public ForStatement(ArrayList<Token> tokens) {
        super(tokens);
        this.identifier = tokens.get(2);
        stepOp = tokens.get(8);
        start = (int) tokens.get(4).getValue();
        end = (int) tokens.get(6).getValue();
    }

    public ArrayList<Statement> extractInnerStatements() throws Exception {
        ArrayList<Token> bodyTokens = new ArrayList<>();
        int index = 0;
        Token token = tokens.get(index);
        while(token.getType() != ZTokenType.LEFT_BRACE){
            index++;
            token = tokens.get(index);
        }
        index++;
        token = tokens.get(index);

        while(token.getType() != ZTokenType.RIGHT_BRACE){
            bodyTokens.add(token);
            index++;
            token = tokens.get(index);
        }

        StatementExtractor se = new StatementExtractor(bodyTokens);
        return se.extract();
    }
}
