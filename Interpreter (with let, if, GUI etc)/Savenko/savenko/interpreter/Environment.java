/*
 * хранит все переменные с их значениями
 * Savenko Maria(c)
 */
package savenko.interpreter;

import savenko.ast.Identifier;
import java.util.HashMap;


public class Environment {
    
    private HashMap<Identifier, Value> environment = new HashMap<Identifier, Value>();
    
    public Value addToEnvironment(Identifier identifier,Value value){
        return environment.put(identifier, value);
    }
    
    public Value getValue(Identifier identifier){
        for (Identifier i:environment.keySet())
            if (i.GetName().equals(identifier.GetName())) return environment.get(i);
        return null;
    }
    
    public boolean ifIdentifierExist(Identifier identifier){
    	if (environment.containsKey(identifier)) return true;
    	else return false;
    }
    
    public void removeIdentifier(Identifier ident){
    	environment.remove(ident);
    }
    
    @SuppressWarnings("unchecked")
	public Environment clone(){
    	Environment env = new Environment();
    	env.environment = (HashMap<Identifier, Value>)this.environment.clone();
    	return env;
    }

}
