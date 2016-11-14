package lab4;

import lab3.LexNode;
import lab3.Token;
import lab3.Translator;

/**
 * Created by 111 on 14.11.2016.
 */
public class Main {
    public static void main(String[] args)  {
        String input="b+c:=(2*a+c)*a2*a;";
        Translator translator=new Translator();
        translator.lexicalAnalysis(input);
        translator.printTable();
        translator.syntaxAnalysis();
        translator.printMessage();

    }
}
