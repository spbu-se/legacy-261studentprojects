/*
 *
 * "Простейший транслятор"
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 * представитель списка токенов, создаваемого интерпретатором. у каждого токена есть тип, у некоторых из них -
 * атрибут;
 *
 */
package lexerandparser;

public class Token {

    private TokenType tokenType;
    private int attribute;
    private Position position;

    public Token() {
    }

    public Token(TokenType token, int attribute, Position position) {
        this.tokenType = token;
        this.attribute = attribute;
        this.position = position;
    }

    public Token(TokenType token, Position position) {
        this.tokenType = token;
        this.position = position;
    }

    public TokenType getType() {
        return tokenType;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
