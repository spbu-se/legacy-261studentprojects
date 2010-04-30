package savenko.ast;

import java.util.ArrayList;
import java.util.List;

public class Sequence implements Tree{
    
    private ArrayList<Expression> statements = new ArrayList<Expression>();
    
    public Expression addStatement(Expression new_statement){
    	statements.add(new_statement);
    	return new_statement;
    }

    public List<Expression> returnStatement(){
    	return statements;
    }
}