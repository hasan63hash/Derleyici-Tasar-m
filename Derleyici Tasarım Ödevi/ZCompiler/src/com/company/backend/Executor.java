package com.company.backend;

import com.company.frontend.ZTokenType;
import com.company.intermediate.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Executor {
    ArrayList<Statement> statements;
    HashMap<String, Variable> variables;
    Scanner sc;

    public Executor(ArrayList<Statement> statements){
        this.statements = statements;
        this.variables = new HashMap<>();
        this.sc = new Scanner(System.in);
    }

    public void execute() throws Exception {
        System.out.println("Program Çalıştırılıyor... Çıktı: ");
        long startTime = System.currentTimeMillis();

        for (Statement statement : statements) {
            execStatement(statement);
        }

        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        System.out.println(elapsedTime + " saniyede çalıştırıldı.");

        System.out.println("Tanımlanan değişkenler ve değerleri");
        for (Variable v : variables.values()){
            System.out.println(v.toString());
        }
    }

    public void putVariable(Variable variable){
        variables.put(variable.identifier, variable);
    }

    public int getIntVariable(String identifier) throws Exception {
        int value = 0;
        Variable v = getVariable(identifier);

        if(!v.type.equals("INTEGER")){
            // Bu değişken int değil
            System.out.println("Hatalı değişken tipi(INTEGER değil)! " + identifier);
            throw new Exception();
        }

        return (int) v.value;
    }

    public Variable getVariable(String identifier) throws Exception {
        Variable v = variables.get(identifier);

        if(v == null){
            // Böyle bir değişken yok
            System.out.println("Tanımlanmayan değişken kullanımı! " + identifier);
            throw new Exception();
        }

        return v;
    }

    public void execStatement(Statement statement) throws Exception {
        if(statement instanceof DefStatement){
            // Değişken tanımlama veya değer atama
            DefStatement stm = (DefStatement) statement;
            Variable v = new Variable(stm);
            putVariable(v);
        }
        else if(statement instanceof MathStatement){
            MathStatement stm = (MathStatement) statement;
            int num1, num2;

            if(stm.x.getType() == ZTokenType.IDENTIFIER){ // Değişken değerini al
                num1 = getIntVariable(stm.x.getText());
            }
            else{
                num1 = (int) stm.x.getValue();
            }

            if(stm.y.getType() == ZTokenType.IDENTIFIER){
                num2 = getIntVariable(stm.y.getText());
            }
            else {
                num2 = (int) stm.y.getValue();
            }

            int result = 0;

            if(stm.operation == "PLUS"){
                result = num1 + num2;
            }
            else if(stm.operation == "MINUS"){
                result = num1 - num2;
            }
            else if(stm.operation == "STAR"){
                result = num1 * num2;
            }
            else{ // SLASH
                result = num1 / num2;
            }

            Variable v = new Variable(stm.identifier, "INTEGER", result);
            putVariable(v);
        }
        else if(statement instanceof PrintStatement){
            PrintStatement stm = (PrintStatement) statement;
            String toPrint = "";

            if(stm.st.getType() == ZTokenType.IDENTIFIER){
                // Değişkeni al
                String identifier = stm.st.getText();
                toPrint = getVariable(identifier).value.toString();
            }
            else{
                toPrint = stm.st.getValue().toString();
            }

            System.out.println(toPrint);
        }
        else if(statement instanceof InputStatement){
            InputStatement stm = (InputStatement) statement;
            System.out.print(stm.text + " ");
            String input = sc.nextLine();

            Variable v = new Variable(stm.identifier, "STRING", input);
            putVariable(v);
        }
        else if(statement instanceof IfStatement){
            IfStatement stm = (IfStatement) statement;

            boolean condition = false;
            if (stm.bt != null){
                if (stm.bt.getType().toString().equals("TRUE")){
                    condition = true;
                }
                else{
                    condition = false;
                }
            }
            else{ // Değişken karşılaştırma
                Variable var1 = getVariable(stm.i1.getText());
                Variable var2 = getVariable(stm.i2.getText());

                // Tipleri farklıysa
                if(!var1.type.equals(var2.type)){
                    condition = false;
                }
                else{
                    if(var1.type.equals("BOOLEAN")){ // Boolean karşılaştırma
                        condition = (boolean) var1.value == (boolean) var2.value;
                    }
                    else if(var1.type.equals("INTEGER")){ // Integer karşılaştırma
                        condition = (int) var1.value == (int) var2.value;
                    }
                    else if(var1.type.equals("STRING")){ // String karşılaştırma
                        String s1 = (String) var1.value;
                        String s2 = (String) var2.value;
                        condition = s1.equals(s2);
                    }
                    else{
                        condition = false;
                    }
                }

                if(stm.op.getType() == ZTokenType.IS_EQUALS){
                    // Birşey yapma
                }
                else if(stm.op.getType() == ZTokenType.NOT_EQUALS){
                    // Tersini al
                    condition = !condition;
                }
            }

            // Koşul sağlanıyorsa
            if (condition){
                // If'in içine gir ve içindeki ifadeleri çalıştır
                ArrayList<Statement> ss = stm.extractInnerStatements();
                for (Statement st : ss){
                    execStatement(st);
                }
            }
        }
        else if(statement instanceof ForStatement){
            ForStatement stm = (ForStatement) statement;
            // Değişkeni tanımla ve ilk değerini ata
            Variable v = new Variable(stm.identifier.getText(), "INTEGER", stm.start);

            ArrayList<Statement> bodyStatements = stm.extractInnerStatements();

            if(stm.stepOp.getType() == ZTokenType.INCREASE){
                int i = stm.start;
                for(; i<stm.end; i++){
                    // Değişkeni güncelle
                    v.value = i;
                    putVariable(v);

                    // For'un içindeki komutları çalıştır
                    for(Statement st : bodyStatements){
                        execStatement(st);
                    }
                }

                // Değişkeni güncelle
                v.value = i;
                putVariable(v);
            }
        }
        else{
            System.out.println("Başka ifade");
        }
    }
}
