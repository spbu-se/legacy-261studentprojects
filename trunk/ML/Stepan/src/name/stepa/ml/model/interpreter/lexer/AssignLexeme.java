package name.stepa.ml.model.interpreter.lexer;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 08.05.2010
 * Time: 22:41:41
 * To change this template use File | Settings | File Templates.
 */
public class AssignLexeme extends Lexeme {
    public AssignLexeme(int start) {
        super(start, start+1);
    }

    @Override
    public String toString() {
        return "=";
    }
}