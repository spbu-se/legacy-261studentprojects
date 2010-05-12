package name.stepa.ml.model.interpreter.syntax;

import name.stepa.ml.model.interpreter.lexer.*;
import name.stepa.ml.model.interpreter.lexer.keywords.*;

import java.util.ArrayList;

/**
 * Autor: Korshakov Stepan (korshakov.stepan@gmail.com)
 */
public class SyntaxProcessor {

    private int pointer;

    public SyntaxTreeNode process(Lexeme[] data) throws Exception {
        pointer = 0;
        return processExpressionList(data);
    }

    private SyntaxTreeNode processExpressionList(Lexeme[] data) throws Exception {
        if (data[pointer] instanceof BeginLexeme) {
            ArrayList<SyntaxTreeNode> nodes = new ArrayList<SyntaxTreeNode>();
            pointer++;
            nodes.add(processExpression(data));
            // Skip semicolons by "++pointer"
            while ((pointer < data.length - 1) && (!(data[++pointer] instanceof EndLexeme))) {
                nodes.add(processExpression(data));
            }
            //Skip end
            pointer++;
            return new ExpressionListTreeNode(nodes.toArray(new SyntaxTreeNode[0]));
        } else
            return processExpression(data);
    }


    private SyntaxTreeNode processExpression(Lexeme[] data) throws Exception {
        if (data[pointer] instanceof LetLexeme) {
            String variable = ((IdentifierLexeme) data[pointer + 1]).value;
            pointer += 3;
            AssignNode assign = new AssignNode(variable, processExpression(data));
            if ((data.length > pointer) && ((data[pointer] instanceof InLexeme))) {
                pointer++;
                return new InTreeNode(assign, processExpressionList(data));
            } else
                return assign;
        } else if (data[pointer] instanceof IfLexeme) {
            pointer++;
            SyntaxTreeNode comp = processExpression(data);
            if (!(data[pointer] instanceof ThenLexeme))
                throw new Exception("Syntax error! Expected: then");
            pointer++;
            SyntaxTreeNode thenExpr = processExpression(data);
            if (!(data[pointer] instanceof ElseLexeme))
                throw new Exception("Syntax error! Expected: else");
            pointer++;
            SyntaxTreeNode elseExpr = processExpression(data);
            return new IfTreeNode(comp, thenExpr, elseExpr);
        } else if (data[pointer] instanceof FunLexeme) {
            String argument = ((IdentifierLexeme) data[++pointer]).value;
            if (!(data[++pointer] instanceof ArrowLexeme))
                throw new Exception("Syntax error! Expected: ->");
            pointer++;

            SyntaxTreeNode expression = processExpression(data);
            return new FunctionDeclarationTreeNode(expression, argument);
        } else {
            SyntaxTreeNode res = processLogic(data);
            if (isEndOfExpression(data[pointer]))
                return res;
            else {
                return new ExpressionCallTreeNode(res, processExpression(data));
            }
        }
        /*else
            throw new Exception("Wrong expression!");*/
    }

    private boolean isEndOfExpression(Lexeme lex) {
        if (lex instanceof SemicolonLexeme)
            return true;
        if (lex instanceof ThenLexeme)
            return true;
        if (lex instanceof ElseLexeme)
            return true;
        if (lex instanceof InLexeme)
            return true;
        if (lex instanceof EOFLexeme)
            return true;
        if (lex instanceof CloseBracketLexeme)
            return true;

        return false;
    }

    private SyntaxTreeNode processLogic(Lexeme[] data) throws Exception {
        if (data[pointer] instanceof NotLexeme) {
            pointer++;
            return new UnaryOperationTreeNode(UnaryOperationTreeNode.NOT, processLogic(data));
        }
        SyntaxTreeNode left = processComparison(data);
        if (!(data[pointer] instanceof OperationLexeme)) {
            return left;
        } else {
            char op = ((OperationLexeme) data[pointer]).operation;
            if (op == '&') {
                pointer++;
                return new BinaryOperationTreeNode(left, processLogic(data), op);
            }
        }

        throw new Exception("Syntax error!");
    }

    private SyntaxTreeNode processComparison(Lexeme[] data) throws Exception {
        SyntaxTreeNode left = processAlgebraic(data);
        if (!(data[pointer] instanceof ComparisonLexeme)) {
            return left;
        }
        ComparisonLexeme lex = (ComparisonLexeme) data[pointer];
        pointer++;
        return new CaparisonTreeNode(lex.type, left, processAlgebraic(data));
    }

    private SyntaxTreeNode processAlgebraic(Lexeme[] data) throws Exception {
        return processAdditive(data);
    }


    private SyntaxTreeNode processAdditive(Lexeme[] data) throws Exception {
        if ((data[pointer] instanceof OperationLexeme) && (((OperationLexeme) data[pointer]).operation == '-')) {
            pointer++;
            return new UnaryOperationTreeNode(UnaryOperationTreeNode.MINUS, processAdditive(data));
        }

        SyntaxTreeNode left = processFraction(data);
        if (!(data[pointer] instanceof OperationLexeme)) {
            return left;
        }
        OperationLexeme lex = (OperationLexeme) data[pointer];
        if ((lex.operation == '+') || (lex.operation == '-')) {
            pointer++;
            return new BinaryOperationTreeNode(left, processAdditive(data), lex.operation);
        } else
            return left;
    }

    private SyntaxTreeNode processFraction(Lexeme[] data) throws Exception {
        SyntaxTreeNode left = processValue(data);
        if (!(data[pointer] instanceof OperationLexeme)) {
            return left;
        }
        OperationLexeme lex = (OperationLexeme) data[pointer];
        if ((lex.operation == '*') || (lex.operation == '/')) {
            pointer++;
            return new BinaryOperationTreeNode(left, processFraction(data), lex.operation);
        } else
            return left;
    }

    private SyntaxTreeNode processValue(Lexeme[] data) throws Exception {
        if (data[pointer] instanceof ValueLexeme) {

            ValueTreeNode res = new ValueTreeNode(((ValueLexeme) data[pointer]).value);
            pointer++;
            return res;
        }
        if (data[pointer] instanceof IdentifierLexeme) {

            VariableTreeNode res = new VariableTreeNode(((IdentifierLexeme) data[pointer]).value);
            pointer++;
            return res;
        }
        if (data[pointer] instanceof OpenBracketLexeme) {
            return processBracket(data);
        }
        throw new Exception("Syntax error!");
    }

    private SyntaxTreeNode processBracket(Lexeme[] data) throws Exception {
        pointer++;
        SyntaxTreeNode res = processExpression(data);
        if (!(data[pointer] instanceof CloseBracketLexeme))
            throw new Exception("Syntax error!");
        pointer++;
        return res;
    }
}