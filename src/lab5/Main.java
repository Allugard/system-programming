package lab5;

import lab3.Translator;

/**
 * Created by 111 on 27.11.2016.
 */
public class Main {
    public static void main(String[] args)  {
        String input="if (a>b) then begin  a:=b; end; else begin a:=b; end;";
        Translator translator=new Translator();
        translator.lexicalAnalysis(input);
        translator.printTable();
        translator.syntaxAnalysis(translator.getTable());
        translator.printMessage();

    }
}
