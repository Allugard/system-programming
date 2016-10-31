package lab2;

/**
 * Created by 111 on 31.10.2016.
 */
public class Main {
    public static void main(String[] args) {
       // System.out.println(Token.var.toChar('s'));



        LexNode [] lexNodes= new LexNode[20];
        for (int i = 0; i <lexNodes.length ; i++) {
            lexNodes[i]=new LexNode();
        }
        lexNodes[0].setLex('c');
        lexNodes[0].setPrevNode(lexNodes[1]);
        lexNodes[0].setNextNode(lexNodes[2]);
        lexNodes[0].setToken(Token.var);

        lexNodes[1].setToken(Token.If);

        lexNodes[2].setToken(Token.cnst);
        lexNodes[2].setPrevNode(lexNodes[3]);
        lexNodes[2].setNextNode(lexNodes[4]);
        lexNodes[2].setLex(0);

        lexNodes[3].setToken(Token.nEqual);

        lexNodes[4].setToken(Token.Then);
        lexNodes[4].setNextNode(lexNodes[5]);

        lexNodes[5].setToken(Token.var);
        lexNodes[5].setLex('b');
        lexNodes[5].setNextNode(lexNodes[6]);

        lexNodes[6].setToken(Token.ass);
        lexNodes[6].setNextNode(lexNodes[7]);



        lexNodes[7].setToken(Token.rightParenthesis);
        lexNodes[7].setNextNode(lexNodes[8]);

        lexNodes[8].setToken(Token.cnst);
        lexNodes[8].setLex(2);
        lexNodes[8].setNextNode(lexNodes[9]);

        lexNodes[9].setToken(Token.mul);
        lexNodes[9].setNextNode(lexNodes[10]);

        lexNodes[10].setToken(Token.var);
        lexNodes[10].setLex('a');
        lexNodes[10].setNextNode(lexNodes[11]);

        lexNodes[11].setToken(Token.sum);
        lexNodes[11].setNextNode(lexNodes[12]);

        lexNodes[12].setToken(Token.var);
        lexNodes[12].setLex('c');
        lexNodes[12].setNextNode(lexNodes[13]);

        lexNodes[13].setToken(Token.leftParenthesis);
        lexNodes[13].setNextNode(lexNodes[14]);

        lexNodes[14].setToken(Token.mul);
        lexNodes[14].setNextNode(lexNodes[15]);

        lexNodes[15].setToken(Token.cnst);
        lexNodes[15].setLex(2);
        lexNodes[15].setNextNode(lexNodes[16]);

        lexNodes[16].setToken(Token.mul);
        lexNodes[16].setNextNode(lexNodes[17]);

        lexNodes[17].setToken(Token.var);
        lexNodes[17].setLex('a');
        lexNodes[17].setNextNode(lexNodes[18]);

        lexNodes[18].setToken(Token.sem);

        //if c<>0thenb:=(2*a+c)*2*a;
        if(LexNode.checkRightBraces(lexNodes)) {
            lexNodes[0].printTree();
        } else{
            System.out.println("Неправильное кол-во скобок");
        }


    }
}
