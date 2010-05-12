package name.stepa.ml.model.interpreter;

import name.stepa.ml.model.interpreter.functions.*;
import name.stepa.ml.model.interpreter.lexer.ComparisonLexeme;
import name.stepa.ml.model.interpreter.syntax.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ex3NDR
 * Date: 12.05.2010
 * Time: 16:17:33
 * To change this template use File | Settings | File Templates.
 */
public class InterpretationCore {

    private static final void initContext(Context context) {
        // System
        context.put("print", new SystemFunctions.PrintlnFunctionValue());

        // Math
        context.put("abs", new MathFunctions.AbsFunctionValue());
        context.put("acos", new MathFunctions.AcosFunctionValue());
        context.put("asin", new MathFunctions.AsinFunctionValue());
        context.put("atan", new MathFunctions.AtanFunctionValue());
        context.put("ceil", new MathFunctions.CeilFunctionValue());
        context.put("cos", new MathFunctions.CosFunctionValue());
        context.put("exp", new MathFunctions.ExpFunctionValue());
        context.put("floor", new MathFunctions.FloorFunctionValue());
        context.put("log10", new MathFunctions.Log10FunctionValue());
        context.put("log", new MathFunctions.LogFunctionValue());
        context.put("round", new MathFunctions.RoundFunctionValue());
        context.put("sin", new MathFunctions.SinFunctionValue());
        context.put("sqrt", new MathFunctions.SqrtFunctionValue());
        context.put("tan", new MathFunctions.TanFunctionValue());

        context.put("PI", Math.PI);
    }


    public Context context;

    public InterpretationCore(Context context) {
        this.context = context;
    }

    public InterpretationCore() {
        this.context = new Context();
        resetContext();
    }

    public void resetContext() {
        this.context.clear();
        initContext(this.context);
    }

    public Object interpret(SyntaxTreeNode node) throws Exception {
        if (node instanceof CaparisonTreeNode)
            return interpret((CaparisonTreeNode) node);
        if (node instanceof ValueTreeNode)
            return interpret((ValueTreeNode) node);
        if (node instanceof VariableTreeNode)
            return interpret((VariableTreeNode) node);
        if (node instanceof BinaryOperationTreeNode)
            return interpret((BinaryOperationTreeNode) node);
        if (node instanceof UnaryOperationTreeNode)
            return interpret((UnaryOperationTreeNode) node);
        if (node instanceof AssignNode)
            return interpret((AssignNode) node);
        if (node instanceof InTreeNode)
            return interpret((InTreeNode) node);
        if (node instanceof ExpressionListTreeNode)
            return interpret((ExpressionListTreeNode) node);
        if (node instanceof IfTreeNode)
            return interpret((IfTreeNode) node);
        if (node instanceof FunctionDeclarationTreeNode)
            return interpret((FunctionDeclarationTreeNode) node);
        if (node instanceof ExpressionCallTreeNode)
            return interpret((ExpressionCallTreeNode) node);


        throw new Exception("Unsupported syntax tree item: " + node.getClass().getSimpleName());
    }

    private Object interpret(ExpressionListTreeNode node) throws Exception {
        Object res = null;
        for (SyntaxTreeNode i : node.nodes) {
            res = interpret(i);
        }
        return res;
    }

    private Object interpret(ExpressionCallTreeNode node) throws Exception {
        Object expression = interpret(node.expression);
        if (expression instanceof AbstractFunctionValue) {
            return ((AbstractFunctionValue) expression).execute(interpret(node.argument));
        } else
            return expression;
    }

    private Object interpret(FunctionDeclarationTreeNode node) throws Exception {
        return new FunctionValue(this.context.clone(), node.expression, node.argumentName);
    }

    private Object interpret(IfTreeNode node) throws Exception {
        if (getLogicValue(interpret(node.ifExpr))) {
            return interpret(node.thenExpr);
        } else
            return interpret(node.elseExpr);
    }

    private Object interpret(InTreeNode node) throws Exception {
        Object old = context.put(node.assignment.variable, interpret(node.assignment.assignExpression));
        Object res = interpret(node.expression);
        context.put(node.assignment.variable, old);
        return res;
    }

    private Object interpret(AssignNode node) throws Exception {
        Object value = interpret(node.assignExpression);
        String name = node.variable;
        context.put(name, value);
        IO.println("set value " + value + " -> " + name);
        return value;
    }

    private Object interpret(UnaryOperationTreeNode node) throws Exception {
        if (node.operation == UnaryOperationTreeNode.MINUS) {
            return -getAlgebraicValue(interpret(node.argument));
        } else if (node.operation == UnaryOperationTreeNode.NOT) {
            return !getLogicValue(interpret(node.argument));
        }

        throw new Exception("Unsupported unary operation: " + node.operation);
    }

    private Object interpret(CaparisonTreeNode node) throws Exception {
        if ((node.operation == ComparisonLexeme.E) || (node.operation == ComparisonLexeme.NE)) {
            Object left = interpret(node.left);
            Object right = interpret(node.right);
            switch (node.operation) {
                case ComparisonLexeme.E:
                    return left.equals(right);
                case ComparisonLexeme.NE:
                    return !left.equals(right);
            }
        } else {
            Double left = (Double) interpret(node.left);
            Double right = (Double) interpret(node.right);
            switch (node.operation) {
                case ComparisonLexeme.G:
                    return left > right;
                case ComparisonLexeme.GE:
                    return left >= right;
                case ComparisonLexeme.L:
                    return left < right;
                case ComparisonLexeme.LE:
                    return left <= right;
            }
        }

        throw new Exception("Invalid comparison operation: " + node.operation);
    }

    private Object interpret(ValueTreeNode node) {
        return node.value;
    }

    private Object interpret(VariableTreeNode node) {
        return context.get(node.variable);
    }

    private Object interpret(BinaryOperationTreeNode node) throws Exception {
        if (isAlgebraicOperation(node.operation)) {
            Double left = getAlgebraicValue(interpret(node.left));
            Double right = getAlgebraicValue(interpret(node.right));
            if (node.operation == '+')
                return left + right;
            else if (node.operation == '-')
                return left - right;
            else if (node.operation == '*')
                return left * right;
            else if (node.operation == '/')
                return left / right;
        } else {
            Boolean left = getLogicValue(interpret(node.left));
            Boolean right = getLogicValue(interpret(node.right));
            if (node.operation == '&')
                return left & right;
            else if (node.operation == '|')
                return left | right;
            else if (node.operation == '^')
                return left ^ right;
        }

        throw new Exception("Invalid binary operation: " + node.operation);
    }

    private boolean isAlgebraicOperation(char c) {
        if (c == '+')
            return true;
        else if (c == '-')
            return true;
        else if (c == '*')
            return true;
        else if (c == '/')
            return true;
        else
            return false;
    }

    private Double getAlgebraicValue(Object value) throws Exception {
        if (value instanceof Double)
            return (Double) value;

        throw new Exception("Type mismatch! Expected Double, got " + value.getClass().getSimpleName());
    }

    private Boolean getLogicValue(Object value) throws Exception {
        if (value instanceof Boolean)
            return (Boolean) value;

        throw new Exception("Type mismatch! Expected Boolean, got " + value.getClass().getSimpleName());
    }
}
