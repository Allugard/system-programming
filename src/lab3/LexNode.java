package lab3;

/**
 * Created by 111 on 02.11.2016.
 */
public class LexNode {
    private String value;
    private Token token;
    private Token subToken;

    public LexNode(String value, Token token) {
        this.value = value;
        this.token = token;
        switch (token){
            case SEPARATOR: switch (value) {
                case "(":
                    subToken = Token.LeftParenthesis;
                    break;
                case ")":
                    subToken = Token.RightParenthesis;
                    break;
                case ";":
                    subToken = Token.EndStatement;
                    break;
            } break;
            case OPERATOR: switch (value){
                case ":=":subToken=Token.Assign;
                    break;
                case "<":subToken=Token.EQUAL;
                    break;
                case ">":subToken=Token.EQUAL;
                    break;
                case "=":subToken=Token.EQUAL;
                    break;
            }

        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getSubToken() {
        return subToken;
    }

    @Override
    public String toString() {
        return  "Lexeme='" + value + '\'' +
                ": Token=" + token;
    }
}
