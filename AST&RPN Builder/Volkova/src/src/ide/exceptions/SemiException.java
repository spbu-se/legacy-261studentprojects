package ide.exceptions;

import ide.*;

public class SemiException extends ParserException {

    public SemiException(Position position) {
        super(position);
    }
}