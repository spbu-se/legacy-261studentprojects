/*
 * common class for all interpret exceptions
 * Antonov Kirill(c), 2010
 */
package name.kirill.ml.exception;

import name.kirill.ml.lexer.Position;

public class InterpreterException extends Exception {

    private Position position = null;

    public InterpreterException(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}