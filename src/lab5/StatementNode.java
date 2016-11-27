package lab5;

/**
 * Created by 111 on 27.11.2016.
 */
public class StatementNode {
    private StatementNode rightStatementNode;
    private StatementNode leftStatementNode;
    private Statement statement;

    public StatementNode() {
    }

    public StatementNode(Statement statement) {
        this.statement = statement;
    }

    public StatementNode getRightStatementNode() {
        return rightStatementNode;
    }

    public void setRightStatementNode(StatementNode rightStatementNode) {
        this.rightStatementNode = rightStatementNode;
    }

    public StatementNode getLeftStatementNode() {
        return leftStatementNode;
    }

    public void setLeftStatementNode(StatementNode leftStatementNode) {
        this.leftStatementNode = leftStatementNode;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}