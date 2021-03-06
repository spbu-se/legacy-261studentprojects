/*
 * узел для операции Let
 * Let <clause> in <expression>
 * Savenko Maria(c)
 */
package savenko.ast;

public class Let implements Expression{
    
    private Expression clause, expression;
    
    public Let(Expression new_clause, Expression new_expr){
        clause = new_clause;
        expression = new_expr;
    }
    
    public Expression getClause(){
        return clause;
    }

    public Expression getExpression(){
        return expression;
    }

}
