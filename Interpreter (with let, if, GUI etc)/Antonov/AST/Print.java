/*
 * Print node Print <expr>
 * Antonov Kirill(c), 2010
 */
package AST;

public class Print extends Expression {

	private Expression expression;

	public Print(Expression new_expr){
		expression = new_expr;
	}

	public Expression getExpression(){
		return expression;
	}

}
