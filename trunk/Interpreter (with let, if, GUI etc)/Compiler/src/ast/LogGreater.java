/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ast;

import lexerandparser.Position;

public class LogGreater extends BinaryOperation {
    public LogGreater(Expression left, Expression right, Position position) {
        super(left, right, position);
    }
}