/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arexprnew;

//import java.util.ArrayList;

/**
 *
 * @author kate
 */
public class Interpreter {

    Enviroment env;
   // private ArrayList<StackFrame> stack;

    public Interpreter() {
        env = new Enviroment();
   //     stack = new ArrayList<StackFrame>();
    }

    public Value interpret(Sq sq) throws InterException {
        Value val = null;

        for (Expression op : sq.returnOps()) {
            val = interpret(op);
        }

        return val;
    }

    private Value interpret(Binding binding) throws InterException {
        Value oldValue = null, val;

        if (env.isId(binding.getId())) {
            oldValue = env.addToEnv(binding.getId(), interpret(binding.getExpression()));
            val = interpret(binding.getValue());
            env.addToEnv(binding.getId(), oldValue);
        } else {
            if (binding.getValue() != null) {
                env.addToEnv(binding.getId(),
                        interpret(binding.getExpression()));
                val = interpret(binding.getValue());
                env.removeId(binding.getId());
            } else {
                val = interpret(binding.getExpression());
            }
        }

        return val;
    }



    private Value interpret(Plus plus) throws InterException {
        IntValue left = (IntValue) interpret(plus.getLeftNode());
        IntValue right = (IntValue) interpret(plus.getRightNode());
        return new IntValue(left.getValue() + right.getValue());
    }

    private Value interpret(Minus minus) throws InterException {
        IntValue left = (IntValue) interpret(minus.getLeftNode());
        IntValue right = (IntValue) interpret(minus.getRightNode());
        return new IntValue(left.getValue() - right.getValue());
    }

    private Value interpret(Div div) throws InterException {
        IntValue left = (IntValue) interpret(div.getLeftNode());
        IntValue right = (IntValue) interpret(div.getRightNode());
        return new IntValue(left.getValue() / right.getValue());
    }

    private Value interpret(Mult mult) throws InterException {
        IntValue left = (IntValue) interpret(mult.getLeftNode());
        IntValue right = (IntValue) interpret(mult.getRightNode());
        return new IntValue(left.getValue() * right.getValue());
    }

    private Value interpret(Number number) throws InterException {
        IntValue val = new IntValue(number.getNumber());
        return val;
    }

    private Value interpret(Id id) throws NoValueException {
        Value val = env.getValue(id);
        if (val != null) {
            return val;
        } else {
            throw new NoValueException(null); 
        }
    }

    private Value interpret(Print id) throws InterException {
        Value val = interpret(id.getExpression());
        return val;
    }

    private Value interpret(IfThenElse id) throws InterException {
        Value val = interpret(id.getCondition());
        if (((BoolValue) val).getBoolValue()) {
            val = interpret(id.getExpression1());
        } else if (id.getExpression2() != null) {
            val = interpret(id.getExpression2());
        }

        return val;
    }

    private Value interpret(Function id) throws InterException {
        return new FunctionEnviroment(id, env.clone());
    }

    private Value interpret(FunctionRec id) throws InterException{
        return new FunctionRecEnv(id, env.clone());
    }

    private Value interpret(Application id) throws InterException {
        Value oldValue = null, val;
        Function function = ((FunctionEnviroment) interpret(id.getFunction())).getFunction();

        if (env.isId(function.getId())) {
            oldValue = env.addToEnv(function.getId(), interpret(id.getArg()));
            val = interpret(function.getExpression());
            env.addToEnv(function.getId(), oldValue);
        } else {
            if (id.getArg() != null) {
                env.addToEnv(function.getId(),
                        interpret(id.getArg()));
                val = interpret(function.getExpression());
                env.removeId(function.getId());
            } else {
                val = interpret(function.getExpression());
            }
        }

        return val;
    }

    private Value interpret(LessOrEq id) throws InterException {
        if (((IntValue) interpret(id.getLeftNode())).getValue() <= ((IntValue) interpret(id.getRightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Equal id) throws InterException {
        if (((IntValue) interpret(id.getLeftNode())).getValue() == ((IntValue) interpret(id.getRightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(GreaterOrEq id) throws InterException {
        if (((IntValue) interpret(id.getLeftNode())).getValue() >= ((IntValue) interpret(id.getRightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Greater id) throws InterException {
        if (((IntValue) interpret(id.getLeftNode())).getValue() > ((IntValue) interpret(id.getRightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(UnEq id) throws InterException {
        if (((IntValue) interpret(id.getLeftNode())).getValue() != ((IntValue) interpret(id.getRightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Less id) throws InterException {
        if (((IntValue) interpret(id.getLeftNode())).getValue() < ((IntValue) interpret(id.getRightNode())).getValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Not id) throws InterException {
        if (((BoolValue) interpret(id)).getBoolValue()) {
            return new BoolValue(true);
        } else {
            return new BoolValue(false);
        }
    }

    private Value interpret(Or id) throws InterException {
        return new BoolValue(((BoolValue) interpret(id.getLeftNode())).getBoolValue() || ((BoolValue) interpret(id.getRightNode())).getBoolValue());
    }

    private Value interpret(And id) throws InterException {
        return new BoolValue(((BoolValue) interpret(id.getLeftNode())).getBoolValue() && ((BoolValue) interpret(id.getRightNode())).getBoolValue());
    }

    private Value interpret(BeginEnd id) throws InterException {
        return interpret(id.getSq());
    }

    private Value interpret(BoolOp id) throws InterException {
        return new BoolValue(((BoolOp) id).getBool());
    }

    private Value interpret(Tree node) throws InterException {
        if (node instanceof Sq) {
            return interpret((Sq) node);
        } else if (node instanceof Number) {
            return interpret((Number) node);
        } else if (node instanceof Binding) {
            return interpret((Binding) node);
        } else if (node instanceof Or) {
            return interpret((Or) node);
        } else if (node instanceof And) {
            return interpret((And) node);
        } else if (node instanceof Plus) {
            return interpret((Plus) node);
        } else if (node instanceof Minus) {
            return interpret((Minus) node);
        } else if (node instanceof Div) {
            return interpret((Div) node);
        } else if (node instanceof Mult) {
            return interpret((Mult) node);
        } else if (node instanceof Id) {
            return interpret((Id) node);
        } else if (node instanceof Print) {
            return interpret((Print) node);
        } else if (node instanceof Application) {
            return interpret((Application) node);
        } else if (node instanceof Function) {
            return interpret((Function) node);
        } else if(node instanceof FunctionRec){//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            return interpret((FunctionRec) node);
        
        } else if (node instanceof IfThenElse) {
            return interpret((IfThenElse) node);
        } else if (node instanceof BoolOp) {
            return interpret((BoolOp) node);
        } else if (node instanceof BeginEnd) {
            return interpret((BeginEnd) node);
        } else if (node instanceof LessOrEq) {
            return interpret((LessOrEq) node);
        } else if (node instanceof GreaterOrEq) {
            return interpret((GreaterOrEq) node);
        } else if (node instanceof Greater) {
            return interpret((Greater) node);
        } else if (node instanceof Less) {
            return interpret((Less) node);
        } else if (node instanceof UnEq) {
            return interpret((UnEq) node);
        } else if (node instanceof Not) {
            return interpret((Not) node);
        } else if (node instanceof Equal) {
            return interpret((Equal) node);
        } else {
            throw new InterException(null); 
        }
    }
}