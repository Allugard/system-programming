package lab3;

/**
 * Created by 111 on 01.11.2016.
 */
public class Lexeme {
    public static final String SEPARATOR=";()[]";
    public static final String [] OPERATORS={"+","-","*","/","=",":=","<>","<",">"};
    public static final String [] STATEMENTS={"if","then","else","while","for","to","end","begin","do"};
    public static final String VALIDSYMBOLS="abcdefghijklmnopqrstyuvwxzABCDEFGHIJKLMNOPQRSTYUVWXZ";
    public static final String VALIDNUMBERS="1234567890";

    public static boolean checkOperatorSymbol(Character ch){
        boolean contains=false;
        for (int i = 0; i <OPERATORS.length ; i++) {
            if(OPERATORS[i].contains(ch.toString())){
                contains=true;
                break;
            }
        }
        return contains;
    }
    public static boolean checkOperator(String str){
        boolean isOperator=false;
        for (int i = 0; i <OPERATORS.length ; i++) {
            if(str.compareTo(OPERATORS[i])==0){
                isOperator=true;
                break;
            }
        }
        return isOperator;
    }

    public static boolean checkStatement(String str){
        boolean isStatement=false;
        for (int i = 0; i <STATEMENTS.length ; i++) {
            if(str.compareTo(STATEMENTS[i])==0){
                isStatement=true;
                break;
            }
        }
        return isStatement;
    }
}
