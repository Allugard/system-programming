package lab5;

/**
 * Created by 111 on 27.11.2016.
 */
public class SyntaxNode {
    private SyntaxNode rightSyntaxNode;
    private SyntaxNode leftSyntaxNode;
    private SyntaxStatement syntaxStatement;

    public SyntaxNode() {
    }

    public SyntaxNode(SyntaxStatement syntaxStatement) {
        this.syntaxStatement = syntaxStatement;
    }

    public SyntaxNode getRightSyntaxNode() {
        return rightSyntaxNode;
    }

    public void setRightSyntaxNode(SyntaxNode rightSyntaxNode) {
        this.rightSyntaxNode = rightSyntaxNode;
    }

    public SyntaxNode getLeftSyntaxNode() {
        return leftSyntaxNode;
    }

    public void setLeftSyntaxNode(SyntaxNode leftSyntaxNode) {
        this.leftSyntaxNode = leftSyntaxNode;
    }

    public SyntaxStatement getSyntaxStatement() {
        return syntaxStatement;
    }

    public void setSyntaxStatement(SyntaxStatement syntaxStatement) {
        this.syntaxStatement = syntaxStatement;
    }

    @Override
    public String toString() {
        return ""+ syntaxStatement;
    }
}