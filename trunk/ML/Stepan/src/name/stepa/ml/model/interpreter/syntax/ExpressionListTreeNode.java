package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.BeginLexeme;
import name.stepa.ml.model.interpreter.lexer.keywords.EndLexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 15:01:59
 * To change this template use File | Settings | File Templates.
 */
public class ExpressionListTreeNode extends SyntaxTreeNode {
    public SyntaxTreeNode[] nodes;

    public ExpressionListTreeNode(SyntaxTreeNode[] nodes, BeginLexeme start, EndLexeme end) {
        super(start, end);
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("[expressions start]");
        sb.append('\n');
        for (int i = 0; i < nodes.length; i++) {
            sb.append(nodes[i].toString());
            sb.append('\n');
        }
        sb.append("[expressions end]");
        sb.append('\n');
        return sb.toString();
    }
}