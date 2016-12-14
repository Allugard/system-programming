package lab5;

import lab3.Translator;

/**
 * Created by 111 on 27.11.2016.
 */
public class Main {
    public static void main(String[] args)  {
        String input="If (a>b) then begin For i:= 1 to n do begin a:=b; end; end; ";
        Translator translator=new Translator();
        translator.lexicalAnalysis(input.toLowerCase());
        translator.printTable();
        translator.syntaxAnalysis(translator.getTable(),translator.getParentNode());
        translator.printMessage();
        translator.printSyntaxTree(translator.getParentNode(),"");

    }
}
