package name.stepa.ml.model.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class IdentifierLexeme extends Lexeme {
    public String value;

    public IdentifierLexeme(String value, int pos) {
        super(pos, pos + value.length());
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
