package com.company;

import com.company.backend.Executor;
import com.company.frontend.Parser;
import com.company.frontend.Source;
import com.company.intermediate.ICode;

public class ZDili {

    public static void main(String[] args) {
        try{
            Source source = new Source();
            Parser parser = new Parser(source);
            System.out.println("Tokenler Çıkarılıyor");
            parser.parse();
            source.close();

            ICode iCode = parser.getICode();
            iCode.splitStatements();

            Executor executor = new Executor(iCode.getStatements());
            executor.execute();
        }
        catch(Exception ex){
            System.out.println(" Hata oluştu! " + ex);
        }
    }
}