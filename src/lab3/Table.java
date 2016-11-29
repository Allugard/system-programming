package lab3;

import lab3.*;
import lab3.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 111 on 02.11.2016.
 */
public class Table {
    //private Map<Integer,List<String>> integerListMap;
    private List<LexNode>lexNodes;
    public Table(){
        lexNodes=new ArrayList<>();
    }
    public void add(Token token, String s){
        lexNodes.add(new LexNode(s,token));
    }

    @Override
    public String toString() {
        String output="Table{";
        for (LexNode lexNode:lexNodes) {
            output+="\n    "+lexNode;
        }
        output+="\n}";
        return output;
    }

    public List<LexNode> getLexNodes() {
        return lexNodes;
    }

    public void setLexNodes(List<LexNode> lexNodes) {
        this.lexNodes = lexNodes;
    }

    public boolean checkUndefinedOprerator() {
        boolean bool=false;
        for (LexNode lexNode:lexNodes) {
            if (lexNode.getToken()==Token.UNDEFINEDOPERATOR){
                bool=true;
                break;
            }
        }
        return bool;
    }

    public String getUndefinedOperator() {
        String undefinedOperator="";
        for (LexNode lexNode:lexNodes) {
            if (lexNode.getToken()==Token.UNDEFINEDOPERATOR){
                undefinedOperator+=lexNode+"\n";
            }
        }
        return undefinedOperator;
    }

    public boolean checkParenthesis() {
        boolean bool=false;
        int i=0;
        for (LexNode lexNode:lexNodes) {
            if (lexNode.getSubToken()==Token.LeftParenthesis) i++;
            if (lexNode.getSubToken()==Token.RightParenthesis) i--;
            if(i<0){
                bool=true;
                break;
            }
        }
        if (i!=0){
            bool=true;
        }
        return bool;
    }

    public boolean chechRightStatement() {
        boolean bool=false;
        for (int i = 1; i <lexNodes.size()-1; i++) {
            if (lexNodes.get(i).getToken()==Token.CONSTANT||lexNodes.get(i).getToken()==Token.VARIABLE){
                if((lexNodes.get(i-1).getToken()!=Token.OPERATOR&&lexNodes.get(i-1).getSubToken()!=Token.LeftParenthesis) ||
                        (lexNodes.get(i+1).getToken()!=Token.OPERATOR&&lexNodes.get(i+1).getSubToken()!=Token.RightParenthesis&&lexNodes.get(i+1).getSubToken()!=Token.EndStatement )){
                    bool=true;
                    break;
                }
            }
           /* if(lexNodes.get(i).getToken()==Token.OPERATOR){
                if(lexNodes.get(i-1).getToken()!=(Ti))
            }*/
        }

        return bool;
    }

    public boolean checkEqualStatement() {
        boolean bool=true;
        if(lexNodes.size()==5) {
            if ((lexNodes.get(1).getToken() == Token.CONSTANT || lexNodes.get(1).getToken() == Token.VARIABLE) &&
                    (lexNodes.get(3).getToken() == Token.CONSTANT || lexNodes.get(3).getToken() == Token.VARIABLE) &&
                    (lexNodes.get(2).getSubToken() == Token.EQUAL) && lexNodes.get(0).getSubToken() == Token.LeftParenthesis &&
                    lexNodes.get(4).getSubToken() == Token.RightParenthesis) {
                bool = false;
            }
        }else {
            bool=false;
        }
        return bool;
    }

    public boolean isSimpleExpression() {
        boolean bool=true;
        for (LexNode lexNode:lexNodes) {
            if (lexNode.getValue().equals("for")||lexNode.getValue().equals("if")){
                bool=false;
                break;
            }
        }
        return bool;
    }

    public boolean incorrectForCycle() {
        boolean bool=true;
        if (lexNodes.size()==6) {
            if (lexNodes.get(0).getToken() == Token.VARIABLE && lexNodes.get(1).getSubToken() == Token.Assign &&
                    (lexNodes.get(2).getToken() == Token.VARIABLE || lexNodes.get(2).getToken() == Token.CONSTANT) &&
                    lexNodes.get(3).getValue().equals("to") && lexNodes.get(5).getValue().equals("do") &&
                    (lexNodes.get(4).getToken() == Token.VARIABLE || lexNodes.get(4).getToken() == Token.CONSTANT)) {
                bool = false;
            }
        }else {
            bool=false;
        }
        return bool;
    }
}
