package com.company.frontend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Source {
    String fileName = "aciklama.txt";
    BufferedReader reader; // Kaynak kodu okuyacak reader
    String line;
    int lineNum, currentPosition;
    public static final char EOL = '\n';
    public static final char EOF = (char) 0;

    public Source() throws Exception {
        this.lineNum = 0;
        this.currentPosition = -2;
        this.reader = new BufferedReader(new FileReader(new File(fileName)));
    }

    public char currentChar () throws Exception {
        if(currentPosition == -2){ // İlk satırı oku ve ilk karakteri döndür
            readLine();
            return nextChar();
        }
        else if(line == null){ // Dosya sonu
            return EOF;
        }
        else if(currentPosition == line.length()){ // Satır sonu
            return EOL;
        }
        else if(currentPosition > line.length()){ // Bir sonraki satırı oku ve sıradaki karakteri döndür
            readLine();
            return nextChar();
        }
        else{ // Mevcut konumdaki karakteri döndür
            return line.charAt(currentPosition);
        }
    }

    public char nextChar() throws Exception {
        currentPosition++;
        return currentChar();
    }

    public char peekChar() throws Exception {
        currentChar();
        if(line == null){
            return EOF;
        }

        int nextPos = currentPosition + 1;

        return nextPos < line.length() ? line.charAt(nextPos) : EOL;
    }

    public void readLine() throws IOException
    {
        line = reader.readLine();
        currentPosition = -1;
        if (line != null) {
            ++lineNum;
        }
    }

    public void close() throws Exception {
        if (reader != null) {
            try {
                reader.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    public void printCurrentLine(){
        System.out.println(String.format("%03d", lineNum) + ": " + line);
    }

    public int getLineNum(){
        return lineNum;
    }

    public int getCurrentPosition(){
        return currentPosition;
    }
}
