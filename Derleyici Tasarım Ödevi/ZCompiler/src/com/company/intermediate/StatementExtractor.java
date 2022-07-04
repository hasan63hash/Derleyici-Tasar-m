package com.company.intermediate;

import com.company.frontend.Token;
import com.company.frontend.ZTokenType;

import java.util.ArrayList;

public class StatementExtractor {
    ArrayList<Token> tokens;
    ArrayList<Statement> statements;

    public StatementExtractor(ArrayList<Token> tokens){
        this.tokens = tokens;
    }

    public ArrayList<Statement> extract() throws Exception {
        statements = new ArrayList<>();

        for(int i=0; i<tokens.size(); i++){
            Token token = tokens.get(i);
            ArrayList<Token> splittedTokens = new ArrayList<>();
            int headLine = token.getLineNumber();
            String stm = "";

            if(token.getType() == ZTokenType.İF){
                while(token.getType() != ZTokenType.RIGHT_BRACE){
                    splittedTokens.add(token);
                    stm += token.getType().toString() + " ";

                    if(i < tokens.size() - 1){
                        i++;
                        token = tokens.get(i);
                    }
                    else{
                        System.out.printf(String.format("Line: %03d, Hata: '}' bekleniyor!", headLine));
                        throw new Exception();
                    }
                }

                splittedTokens.add(token);
                stm += token.getType().toString();

                if(stm.matches(IfStatement.regex)){
                    IfStatement statement = new IfStatement(splittedTokens);
                    statements.add(statement);
                }
                else{
                    System.out.println(String.format("Line: %03d, Hatalı if bloğu!", headLine));
                    throw new Exception();
                }
            }
            else if(token.getType() == ZTokenType.FOR){
                // for ifadesini ayır
                while(token.getType() != ZTokenType.RIGHT_BRACE){
                    splittedTokens.add(token);
                    stm += token.getType().toString() + " ";

                    if(i < tokens.size() - 1){
                        i++;
                        token = tokens.get(i);
                    }
                    else{
                        System.out.printf(String.format("Line: %03d, Hata: '}' bekleniyor!", headLine));
                        throw new Exception();
                    }
                }

                splittedTokens.add(token);
                stm += token.getType().toString();

                if(stm.matches(ForStatement.regex)){
                    ForStatement statement = new ForStatement(splittedTokens);
                    statements.add(statement);
                }
                else{
                    System.out.println(String.format("Line: %03d, Hatalı for bloğu!", headLine));
                    throw new Exception();
                }
            }
            else {
                // ;'e kadar ayır
                while(token.getType() != ZTokenType.SEMICOLON){
                    splittedTokens.add(token);
                    stm += token.getType().toString() + " ";


                    if(i < tokens.size() - 1){
                        i++;
                        token = tokens.get(i);
                    }
                    else{
                        System.out.printf(String.format("Line: %03d, Hata: ';' bekleniyor!", headLine));
                        throw new Exception();
                    }
                }

                // sondaki fazladan boşluğu sil
                stm = stm.trim();

                if(stm.matches(DefStatement.regex)){
                    // Tanımlama ifadesi
                    DefStatement statement = new DefStatement(splittedTokens);
                    statements.add(statement);
                }
                else if(stm.matches(MathStatement.regex)){
                    // Dört işlem ifadesi
                    MathStatement statement = new MathStatement(splittedTokens);
                    statements.add(statement);
                }
                else if(stm.matches(PrintStatement.regex)){
                    // Print ifadesi
                    PrintStatement statement = new PrintStatement(splittedTokens);
                    statements.add(statement);
                }
                else if(stm.matches(InputStatement.regex)){
                    // Input ifadesi
                    InputStatement statement = new InputStatement(splittedTokens);
                    statements.add(statement);
                }
                else{
                    // Syntax hatası
                    System.out.printf(String.format("Line: %03d, Geçersiz ifade!", headLine));
                    throw new Exception();
                }
            }
        }

        return this.statements;
    }
}
