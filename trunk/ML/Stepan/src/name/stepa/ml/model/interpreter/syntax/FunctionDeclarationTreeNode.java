package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.keywords.FunLexeme;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:36:53
 * To change this template use File | Settings | File Templates.
 */
public class FunctionDeclarationTreeNode extends SyntaxTreeNode {
    public SyntaxTreeNode expression;
    public String argumentName;

    public FunctionDeclarationTreeNode(SyntaxTreeNode expression, String argumentName, FunLexeme lexeme) {
        super(lexeme, expression.end);
        this.expression = expression;
        this.argumentName = argumentName;
    }

    @Override
    public String toString() {
        return "[fun " + argumentName + " -> " + expression.toString() + " ]";
    }
}