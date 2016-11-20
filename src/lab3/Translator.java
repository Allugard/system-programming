package lab3;

import lab4.Exception.*;

/**
 * Created by 111 on 01.11.2016.
 */
public class Translator {
    Table table=new Table();
    String message="";
    public Translator() {

    }

    public void lexicalAnalysis(String input){
        int position=0;
        while (position<input.length()){
            boolean charact=false;
            boolean num=false;
            boolean operator=false;
            char ch=input.charAt(position);

            if(Character.compare(ch,' ')==0){
                position++;
                continue;
            }

            if(Lexeme.SEPARATOR.contains(Character.toString(ch))){
                table.add(Token.SEPARATOR,Character.toString(ch));
                position++;
                continue;
            }

            if (Lexeme.checkOperatorSymbol(ch)) {
                operator=true;
            }else {
                if(Lexeme.VALIDNUMBERS.contains(Character.toString(ch))){
                    num=true;
                }else {
                    charact=true;
                }
            }

            if (operator){
                char ch2=input.charAt(position+1);
                if(Lexeme.checkOperatorSymbol(ch2)){
                    String buf=Character.toString(ch)+Character.toString(ch2);
                    if(Lexeme.checkOperator(buf)) {
                        table.add(Token.OPERATOR, buf);
                    }else {
                        table.add(Token.UNDEFINEDOPERATOR,buf);
                    }
                    position+=2;
                }else {
                    if(Lexeme.checkOperator(Character.toString(ch))) {
                        table.add(Token.OPERATOR, Character.toString(ch));
                    }else {
                        table.add(Token.UNDEFINEDOPERATOR,Character.toString(ch));
                    }
                    position++;
                }
            }

            if(num){
                int pos=position+1;
                while (Lexeme.VALIDNUMBERS.contains(Character.toString(input.charAt(pos)))){
                    pos++;
                }
                String buf=input.substring(position,pos);
                position=pos;
                table.add(Token.CONSTANT,buf);
            }

            if(charact){
                int pos=position+1;
                while (input.length()>pos&&(Lexeme.VALIDSYMBOLS.contains((Character.toString(input.charAt(pos))))||Lexeme.VALIDNUMBERS.contains((Character.toString(input.charAt(pos)))))){
                    pos++;
                }
                String buf=input.substring(position,pos);
                position=pos;
                if (Lexeme.checkStatement(buf)){
                    table.add(Token.STATEMENT, buf);
                }else {
                    table.add(Token.VARIABLE,buf);
                }
            }
        }
    }
    public void syntaxAnalysis()  {
        boolean successfully=true;
        if(table.checkUndefinedOprerator()){
            try{
                throw new UndefinedOperatorException();
            } catch (UndefinedOperatorException e) {
                message+="Undefined operators: \n";
            }
            message+=table.getUndefinedOperator();
            successfully=false;
        }

        if(table.checkParenthesis()){
            try {
                throw new ParenthesisException();
            } catch (ParenthesisException e) {
                message+="Wrong numbers of parenthesis \n";
            }
            successfully=false;
        }

        if(table.getLexNodes().get(1).getValue().compareTo(":=")!=0){
            try {
                throw new AssignException();
            } catch (AssignException e) {
                message+="Expected variable in left part of expression \n";
            }
            successfully=false;
        }

        if(table.getLexNodes().get(table.getLexNodes().size()-1).getSubToken()!=Token.EndStatement){
            try {
                throw new EndStatementException();
            } catch (EndStatementException e) {
                message+="Expected ';' in the end of expression \n";
            }
            successfully=false;

        }

        if(table.chechRightStatement()){
            try {
                throw new StatementException();
            } catch (StatementException e) {
                message+="Wrong expression \n";
            }
            successfully=false;
        }
        if (successfully){
            message+="Syntax analysis succeeded!!! \n";
        }else {
            message+="Syntax analysis failed!!! \n";
        }
    }



    public void printTable(){
        System.out.println(table);
    }

    public void printMessage() {
        System.out.println(message);
    }

}
