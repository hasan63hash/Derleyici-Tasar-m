package com.company.intermediate;

import com.company.frontend.Token;
import com.company.frontend.ZTokenType;

import java.util.ArrayList;

public class IfStatement extends Statement {
    static String regex = "İF LEFT_PAREN (TRUE|FALSE|IDENTIFIER IS_EQUALS IDENTIFIER|IDENTIFIER NOT_EQUALS IDENTIFIER) RIGHT_PAREN LEFT_BRACE .* RIGHT_BRACE";
    public Token bt, i1, i2, op;

    public IfStatement(ArrayList<Token> tokens) throws Exception {
        super(tokens);

        Token tmp = tokens.get(2);
        if (tmp.getType() == ZTokenType.TRUE || tmp.getType() == ZTokenType.FALSE){
            this.bt = tmp;
            this.i1 = null;
            this.i2 = null;
            this.op = null;
        }
        else{
            this.bt = null;
            this.i1 = tmp;
            this.op = tokens.get(3);
            this.i2 = tokens.get(4);
        }
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
