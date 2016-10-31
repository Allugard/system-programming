package lab2;

/**
 * Created by 111 on 31.10.2016.
 */
public class Main {
    public static void main(String[] args) {
       // System.out.println(Token.var.toChar('s'));



        LexNode [] lexNodes= new LexNode[7];
        for (int i = 0; i <lexNodes.length ; i++) {
            lexNodes[i]=new LexNode();
        }
        lexNodes[0].setLex('c');
        lexNodes[0].setPrevNode(lexNodes[1]);
        lexNodes[0].setNextNode(lexNodes[2]);
        lexNodes[0].setToken(Token.var);

        lexNodes[1].setToken(Token.If);

        lexNodes[2].setToken(Token.nEqual);
        /*lexNodes[0]=new LexNode(Token.var,lexNodes[1],lexNodes[2],'c');
        lexNodes[1]=new LexNode(Token.If);
        lexNodes[2]=new LexNode(Token.nEqual);*/
       // System.out.print(lexNodes[1]);
        lexNodes[0].printTree();
        //System.out.println(lexNodes[2]);
     //   lexNodes[0].toString();

    }
}
