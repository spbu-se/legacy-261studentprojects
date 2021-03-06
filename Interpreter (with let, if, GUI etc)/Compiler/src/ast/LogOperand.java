/*
 *
 * (c) Яськов Сергей, 261, 2010;
 *
 */

package ast;

import lexerandparser.Position;

public class LogOperand extends Expression {
    private Boolean value;

    public LogOperand(Boolean value, Position position) {
        this.value = value;
        setPositon(position);
    }

    public Boolean getValue() {
        return value;
    }
}
