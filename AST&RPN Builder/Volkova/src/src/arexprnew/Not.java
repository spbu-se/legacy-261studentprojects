/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

/**
 *
 * @author kate
 */
public class Not implements Expression {

    private Expression expression = null;

    public Not(Expression expression) {
        this.expression = expression;
    }
}