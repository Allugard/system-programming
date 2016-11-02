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
}
