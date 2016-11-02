package lab3;

/**
 * Created by 111 on 02.11.2016.
 */
public class LexNode {
    private String value;
    private Token token;

    public LexNode(String value, Token token) {
        this.value = value;
        this.token = token;
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

    @Override
    public String toString() {
        return  "Lexeme='" + value + '\'' +
                ": Token=" + token;
    }
}
