package lab3;

/**
 * Created by 111 on 01.11.2016.
 */
public class Translator {
    Table table=new Table();
    public Translator() {

    }

    public void analysis(String input){
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
                while (Lexeme.VALIDSYMBOLS.contains((Character.toString(input.charAt(pos))))){
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

    public void printTable(){
        System.out.println(table);
    }
}
