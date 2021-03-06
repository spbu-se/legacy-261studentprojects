/*
 * This class receives the string, then by the recursive descent builds the program tree
 * Antonov Kirill(c), 2010
 */
package parser;

import exception.ParserException;
import ast.Begin;
import ast.Unequality;
import ast.And;
import ast.Application;
import ast.BinaryOperation;
import ast.Div;
import ast.GE;
import ast.Negate;
import ast.Number;
import ast.If;
import ast.BooleanOperation;
import ast.Binding;
import ast.Identifier;
import ast.Or;
import ast.Equality;
import ast.Expression;
import ast.Function;
import ast.Minus;
import ast.Mult;
import ast.Less;
import ast.LE;
import ast.Print;
import ast.Plus;
import ast.Greater;
import ast.Sequence;
import environment.Environment;
import exception.IncompatibleTypedException;

import exception.RightBracketException;
import exception.UnknownSymbolException;
import lexer.LexemKind;
import lexer.Lexer;
import types.BasicType;
import types.CombinedType;
import types.TBasicType;
import types.Type;

public class Parser {

    private Lexer lexer;
    private Environment environment;

    public Parser(Lexer args) {
        lexer = args;
        lexer.moveNext();
        environment = new Environment();
    }

    public Type ParseType() throws ParserException {
        Type type;

        if (lexer.getCurrent().getTypeLexem() == LexemKind.LeftBracket) {
            lexer.moveNext();
            
            BasicType left = ParseBasicType();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.ARROW) {
                lexer.moveNext();
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            BasicType right = ParseBasicType();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.RightBracket) {
                lexer.moveNext();
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }

            type = new CombinedType(left, right);
        } else {
            type = ParseBasicType();
            lexer.moveNext();
        }

        return type;
    }

    public BasicType ParseBasicType() throws ParserException {
        switch (lexer.getCurrent().getTypeLexem()) {
            case Int:
                return new BasicType(TBasicType.Int);
            case Bool:
                return new BasicType(TBasicType.Bool);
            case Unit:
                return new BasicType(TBasicType.Unit);
            default:
                throw new ParserException(lexer.getCurrent().getPosition());
        }
    }

    public Sequence getSequence() throws ParserException, IncompatibleTypedException {
        Sequence sequence = new Sequence();
        while (lexer.getCurrent().getTypeLexem() != LexemKind.EOL && lexer.getCurrent().getTypeLexem() != LexemKind.END) {
            if (lexer.getCurrent().getTypeLexem() == LexemKind.semicolon) {
                lexer.moveNext();
            } else {
                sequence.addStatement(getExpression());
            }
        }

        return sequence;
    }

    private Expression getBoolExpression() throws ParserException {

        return boolExpression();

    }

    private Expression boolExpression() throws ParserException {
        Expression left = null;

        left = or();
        while (lexer.getCurrent().getTypeLexem() == LexemKind.Or) {
            lexer.moveNext();
            Expression right = or();
            left = new Or(left, right);
        }

        return left;
    }

    private Expression or() throws ParserException {
        Expression left = null;

        left = and();
        while (lexer.getCurrent().getTypeLexem() == LexemKind.And) {
            lexer.moveNext();
            Expression right = and();
            left = new And(left, right);
        }

        return left;
    }

    private Expression and() throws ParserException {
        return comparison();
    }

    private Expression comparison() throws ParserException {
        Expression left = null;
        Expression right = null;

        left = arithmeticExpression();
        switch (lexer.getCurrent().getTypeLexem()) {
            case LE:
                lexer.moveNext();
                right = comparison();
                return new LE(left, right);
            case GE:
                lexer.moveNext();
                right = comparison();
                return new GE(left, right);
            case Greater:
                lexer.moveNext();
                right = comparison();
                return new Greater(left, right);
            case Less:
                lexer.moveNext();
                right = comparison();
                return new Less(left, right);
            case unequality:
                lexer.moveNext();
                right = comparison();
                return new Unequality(left, right);
            case equality:
                lexer.moveNext();
                right = comparison();
                return new Equality(left, right);
            case TRUE:
                lexer.moveNext();
                return new BooleanOperation(true);
            case FALSE:
                lexer.moveNext();
                return new BooleanOperation(false);
            default:
                return left;
        }
    }

    private Expression getExpression() throws ParserException {
        Expression left = null;

        left = boolExpression();
        if (lexer.getCurrent().getTypeLexem() == LexemKind.Unknown) {
            throw new UnknownSymbolException(lexer.getCurrent().getPosition());
        }

        return left;
    }

    private Expression arithmeticExpression() throws ParserException {
        Expression left = null;

        left = term();
        while (lexer.getCurrent().getTypeLexem() == LexemKind.Plus
                || lexer.getCurrent().getTypeLexem() == LexemKind.Minus) {
            LexemKind sign = lexer.getCurrent().getTypeLexem();
            lexer.moveNext();
            Expression right = term();
            if (sign == LexemKind.Plus) {
                left = new Plus(left, right);
            } else {
                left = new Minus(left, right);
            }
        }

        return left;
    }

    private Expression term() throws ParserException {
        Expression left = null;

        left = Application();
        while (lexer.getCurrent().getTypeLexem() == LexemKind.Multiply || lexer.getCurrent().getTypeLexem() == LexemKind.Divide) {
            LexemKind sign = lexer.getCurrent().getTypeLexem();
            lexer.moveNext();
            Expression right = UnarOp();
            if (sign == LexemKind.Multiply) {
                left = new Mult(left, right);
            } else {
                left = new Div(left, right);
            }
        }

        return left;
    }

    private Expression Application() throws ParserException {
        Expression left = UnarOp();

        switch (lexer.getCurrent().getTypeLexem()) {
            case Number:
            case TRUE:
            case FALSE:
            case LeftBracket:
            case Identifier:
            case FUN:
            case LET:
            case PRINT:
            case IF:
            case BEGIN:
                return new Application(left, UnarOp());
            default:
                break;
        }

        return left;
    }

    private Expression UnarOp() throws ParserException {
        if (lexer.getCurrent().getTypeLexem() == LexemKind.No) {
            lexer.moveNext();
            return new Negate(prime());
        } else {
            return prime();
        }
    }

    private Expression prime() throws ParserException {
        Expression left = null;
        Expression expr = null;
        Expression expr2 = null;
        Identifier identificator = null;

        if (lexer.getCurrent().getTypeLexem() == LexemKind.Number) {
            left = new Number(lexer.getCurrent().getIntLexem());
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.TRUE) {
            left = new BooleanOperation(true);
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Minus) {
            left = new Number(0);
            lexer.moveNext();
            Minus minus = new Minus(left, arithmeticExpression());
            return minus;

        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.FALSE) {
            left = new BooleanOperation(false);
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.LeftBracket) {
            lexer.moveNext();
            left = arithmeticExpression();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.RightBracket) {
                throw new RightBracketException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.Identifier) {

            String name = lexer.getCurrent().getStringLexem();
            Type idType = environment.GetIdentifier(name).GetType();
//            if (idType instanceof CombinedType){
//                idType = ((CombinedType)idType).GetLeft();
//            }
            left = new Identifier(name, idType);
            lexer.moveNext();
            return left;
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.FUN) {
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.Identifier) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            String idName = lexer.getCurrent().getStringLexem();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.Colon) {
                lexer.moveNext();
                identificator = new Identifier(idName, ParseType());
                environment.addToEnvironment(identificator, null);
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }

            if (lexer.getCurrent().getTypeLexem() != LexemKind.ARROW) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr = getExpression();
            return new Function(identificator, expr);

        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.LET) {
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.Identifier) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            String idName = lexer.getCurrent().getStringLexem();
            lexer.moveNext();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.Colon) {
                lexer.moveNext();

                identificator = new Identifier(idName, ParseType());
                environment.addToEnvironment(identificator, null);
            } else {
                throw new ParserException(lexer.getCurrent().getPosition());
            }


            if (lexer.getCurrent().getTypeLexem() != LexemKind.equality) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr = getExpression();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.IN) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr2 = getExpression();
            return new Binding(identificator, expr, expr2);
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.PRINT) {
            lexer.moveNext();
            return new Print(getExpression());
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.IF) {
            Expression else_expr = null;
            lexer.moveNext();
            expr = getBoolExpression();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.THEN) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            expr2 = getExpression();
            if (lexer.getCurrent().getTypeLexem() == LexemKind.ELSE) {
                lexer.moveNext();
                else_expr = getExpression();
            }
            return new If(expr, expr2, else_expr);
        } else if (lexer.getCurrent().getTypeLexem() == LexemKind.BEGIN) {
            Sequence seq;
            lexer.moveNext();
            seq = getSequence();
            if (lexer.getCurrent().getTypeLexem() != LexemKind.END) {
                throw new ParserException(lexer.getCurrent().getPosition());
            }
            lexer.moveNext();
            return new Begin(seq);
        } else {
            throw new ParserException(lexer.getCurrent().getPosition());
        }
    }
}
//let f=fun x:Int->>Unit -> print x in x 2

