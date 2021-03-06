package ru.hotheart.interpreter.lexer;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class BinaryOperationLexeme extends Lexeme{
    public char operation;
    public BinaryOperationLexeme(char operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return Character.toString(operation);
    }
}
