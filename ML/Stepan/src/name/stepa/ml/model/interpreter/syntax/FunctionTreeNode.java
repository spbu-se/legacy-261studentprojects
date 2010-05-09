package name.stepa.ml.model.interpreter.syntax;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 08.05.2010
 * Time: 23:01:24
 * To change this template use File | Settings | File Templates.
 */
public class FunctionTreeNode extends SyntaxTreeNode {
    public String function;

    public FunctionTreeNode(String function, SyntaxTreeNode argument) {
        super(argument, null);
        this.function = function;
    }

    @Override
    public String toString() {
        return "[call " +function + " " + left.toString() + "]";
    }
}