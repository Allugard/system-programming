package lab3;

/**
 * Created by 111 on 01.11.2016.
 */
public enum  Token {
    SEPARATOR, STATEMENT, OPERATOR, CONSTANT, VARIABLE,
    UNDEFINEDOPERATOR{
        @Override
        public String toString() {
            return "Undefined operator";
        }
    },
    LeftParenthesis,RightParenthesis,EndStatement,
    EQUAL, Assign
}
