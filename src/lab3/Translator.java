package lab3;

import lab4.Exception.*;
import lab5.SyntaxStatement;
import lab5.SyntaxNode;

/**
 * Created by 111 on 01.11.2016.
 */
public class Translator {
    Table table=new Table();
    String message="";
    SyntaxNode parentNode=new SyntaxNode(SyntaxStatement.Program);
    public Translator() {

    }

    public SyntaxNode getParentNode() {
        return parentNode;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
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
    public void syntaxAnalysisStatement(Table table)  {
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

    public void syntaxAnalysis(Table table, SyntaxNode syntaxNode) {
        if (table.isSimpleExpression()){
            syntaxAnalysisStatement(table);
            SyntaxNode syntaxNodeExp =new SyntaxNode(SyntaxStatement.Expression);
            syntaxNode.setLeftSyntaxNode(syntaxNodeExp);
            return;
        }
        int i=0;
        LexNode buf=table.getLexNodes().get(i);

        if (buf.getValue().equals("if")) {
            SyntaxNode syntaxNodeIf = new SyntaxNode(SyntaxStatement.If);
            syntaxNode.setLeftSyntaxNode(syntaxNodeIf);
            int b = i;
            while (!table.getLexNodes().get(b).getValue().equals("then")) {
                b++;
            }
            Table bufTable = new Table();
            for (int j = i + 1; j < b; j++) {
                bufTable.add(table.getLexNodes().get(j).getToken(), table.getLexNodes().get(j).getValue());
            }
            if (!bufTable.checkEqualStatement()) {
                try {
                    throw new EqualStatementException();
                } catch (EqualStatementException e) {
                    message += "Wrong equal statement  \n";
                }
                return;
            }
            SyntaxNode boolStatement = new SyntaxNode(SyntaxStatement.BoolExpression);
            syntaxNodeIf.setLeftSyntaxNode(boolStatement);
            int counter = 0;
            int c = b;
            while (c < table.getLexNodes().size() && (!table.getLexNodes().get(c).getValue().equals("else") || counter != 0)) {
                if (table.getLexNodes().get(c).getValue().equals("begin")) {
                    counter++;
                }
                if (table.getLexNodes().get(c).getValue().equals("end")) {
                    counter--;
                }
                c++;
            }
            bufTable = new Table();

            if (table.getLexNodes().get(b + 1).getValue().equals("begin") && table.getLexNodes().get(c - 2).getValue().equals("end")) {
                for (int j = b + 2; j < c - 2; j++) {
                    bufTable.add(table.getLexNodes().get(j).getToken(), table.getLexNodes().get(j).getValue());
                }
                SyntaxNode syntaxNodeThen = new SyntaxNode(SyntaxStatement.Then);
                boolStatement.setLeftSyntaxNode(syntaxNodeThen);
                syntaxAnalysis(bufTable, syntaxNodeThen);
            } else {
                message += "Wrong begin or End \n";
            }

            counter = 0;
            b = c;
            while (b < table.getLexNodes().size() && (!table.getLexNodes().get(b).getValue().equals("end") || counter != 0)) {
                if (table.getLexNodes().get(b).getValue().equals("begin")) {
                    counter++;
                }
                if (table.getLexNodes().get(b).getValue().equals("end")) {
                    counter--;
                }
                b++;
            }
            bufTable = new Table();
            //chage equal operate
            if (table.getLexNodes().size() < b){
                if (table.getLexNodes().get(c).getValue().equals("else") && table.getLexNodes().get(c + 1).getValue().equals("begin") && table.getLexNodes().get(b - 2).getValue().equals("end")) {
                    for (int j = c + 2; j < b - 2; j++) {
                        bufTable.add(table.getLexNodes().get(j).getToken(), table.getLexNodes().get(j).getValue());
                    }
                    SyntaxNode syntaxNodeElse = new SyntaxNode(SyntaxStatement.Else);
                    boolStatement.setRightSyntaxNode(syntaxNodeElse);
                    syntaxAnalysis(bufTable, syntaxNodeElse);

                    syntaxAnalysis(bufTable, syntaxNodeElse);
                } else {
                    message += "Wrong begin or End \n";
                }
        }
        }

        if (buf.getValue().equals("for")) {
            SyntaxNode syntaxNodeFor =new SyntaxNode(SyntaxStatement.For);
            syntaxNode.setLeftSyntaxNode(syntaxNodeFor);
            int b = i;
            while (!table.getLexNodes().get(b).getValue().equals("do")) {
                b++;
            }
            Table bufTable = new Table();
            for (int j = i + 1; j <= b; j++) {
                bufTable.add(table.getLexNodes().get(j).getToken(), table.getLexNodes().get(j).getValue());
            }

            if (bufTable.incorrectForCycle()) {
                try {
                    throw new EqualStatementException();
                } catch (EqualStatementException e) {
                    message += "Wrong cycle  \n";
                }
                return;
            }

            int counter = 0;
            int c = b;
            while (c < table.getLexNodes().size() && (!table.getLexNodes().get(c).getValue().equals("end") || counter != 0)) {
                if (table.getLexNodes().get(c).getValue().equals("begin")) {
                    counter++;
                }
                if (table.getLexNodes().get(c).getValue().equals("end")) {
                    counter--;
                }
                c++;
            }
            bufTable = new Table();

            if (table.getLexNodes().get(b + 1).getValue().equals("begin") && table.getLexNodes().get(c - 2).getValue().equals("end")) {
                for (int j = b + 2; j < c - 2; j++) {
                    bufTable.add(table.getLexNodes().get(j).getToken(), table.getLexNodes().get(j).getValue());
                }
                syntaxAnalysis(bufTable, syntaxNodeFor);
            } else {
                message += "Wrong begin or End \n";
            }
        }
    }

    public void printSyntaxTree(SyntaxNode syntaxNode, String s) {
        System.out.println(s+ syntaxNode);
        s+="  ";
        if (syntaxNode.getLeftSyntaxNode()!=null){
            printSyntaxTree(syntaxNode.getLeftSyntaxNode(),s);
        }
        if (syntaxNode.getRightSyntaxNode()!=null){
            printSyntaxTree(syntaxNode.getRightSyntaxNode(),s);
        }
    }
}
