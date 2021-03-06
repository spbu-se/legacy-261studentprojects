/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class ExFunction extends Expression {
    private int id;
    private Expression functionBody;

    public ExFunction(int id, Expression functionBody, Position position) {
        this.id = id;
        this.functionBody = functionBody;
        setPositon(position);
    }

    public int getId() {
        return id;
    }

    public Expression getFunctionBody() {
        return functionBody;
    }
}
