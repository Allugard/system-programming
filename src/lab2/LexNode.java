package lab2;

/**
 * Created by 111 on 31.10.2016.
 */
public class LexNode {
    private Token token;
    private LexNode prevNode;
    private LexNode nextNode;
    private Object lex;

    public LexNode(Token token, LexNode prevNode, LexNode nextNode) {
        this.token = token;
        this.prevNode = prevNode;
        this.nextNode = nextNode;
    }

    public LexNode(Token token, Object lex) {
        this.token = token;
        this.lex = lex;
    }

    public LexNode(Token token) {
        this.token = token;
    }

    public LexNode(Token token, LexNode prevNode, LexNode nextNode, Object lex) {
        this.token = token;
        this.prevNode = prevNode;
        this.nextNode = nextNode;
        this.lex = lex;
    }

    public LexNode() {

    }

    public void printTree(){
        if(prevNode!=null){
            prevNode.printTree();
        }
        System.out.print(this.toString());
        if(nextNode!=null){
            nextNode.printTree();
        }
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public LexNode getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(LexNode prevNode) {
        this.prevNode = prevNode;
    }

    public LexNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(LexNode nextNode) {
        this.nextNode = nextNode;
    }

    public Object getLex() {
        return lex;
    }

    public void setLex(Object lex) {
        this.lex = lex;
    }

    @Override
    public String toString() {
        if(token==Token.var){
            return Character.toString(Token.var.toChar((char)lex));
        }
        if(token==Token.cnst){
            return Integer.toString(Token.var.toNum((int)lex));
        }
        return token.toString();
    }

    public static boolean checkRightBraces(LexNode[] lexNodes) {
        int i=0;
        for (int j = 0; j <lexNodes.length ; j++) {
            if(lexNodes[j].getToken()==Token.leftParenthesis) i++;
            if(lexNodes[j].getToken()==Token.rightParenthesis) i--;
            if (i<0) return false;
        }
        if (i!=0) return false;
        return true;
    }
}
