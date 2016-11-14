package lab3;

/**
 * Created by 111 on 01.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        String input="if c<>0then b:=(2*a+c)*2*a;";
        Translator translator=new Translator();
        translator.analysis(input);
        translator.printTable();

    }

}
