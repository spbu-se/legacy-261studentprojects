/*
 * This class get the string from parser and
 * then necessary tells parser what kind of symbol is the curr symbol &
 * moves right on one symbol then asked
 * Antonov Kirill(c), 2010
 */
package lexer;

public class Lexer {

    private String expression;
    private String[] dictionary = new String[]{"true", "false", "print", "let",
        "if", "then", "else", "fun", "int", "in", "begin", "end", "bool", "unit"};
    private int curr_index = 0;
    private int old_index = 0;
    private int row_num = 0;
    private int col_num = 0;
    private Lexem currLexem;

    public Lexer(String programm) {
        expression = programm;
    }

    public Lexem getCurrent() {
        return currLexem;
    }

    private Position getPosition() {
        return new Position(col_num, row_num, old_index, curr_index - old_index);
    }

    private boolean ifIdentifier() {
        return (expression.charAt(curr_index) >= 'A' && expression.charAt(curr_index) <= 'z');
    }

    private boolean ifNumber() {
        return (expression.charAt(curr_index) >= '0' && expression.charAt(curr_index) <= '9');
    }

    private boolean ifEOF() {
        return (curr_index == expression.length());
    }

    private String getIdentifier() {
        String identificator = String.valueOf(expression.charAt(curr_index));

        curr_index++;
        while (true) {
            if (expression.length() > curr_index && expression.charAt(curr_index) >= 'A' && expression.charAt(curr_index) <= 'z') {
                identificator += String.valueOf(expression.charAt(curr_index));
                curr_index++;
            } else {
                break;
            }
        }

        return identificator;
    }

    private Integer getNumber() {
        String number = String.valueOf(expression.charAt(curr_index));

        curr_index++;
        while (true) {
            if (expression.length() > curr_index && expression.charAt(curr_index) >= '0' && expression.charAt(curr_index) <= '9') {
                number += String.valueOf(expression.charAt(curr_index));
                curr_index++;
            } else {
                break;
            }
        }

        return Integer.parseInt(number);
    }

    public void moveNext() {
        char currChar = ' ';

        if (curr_index < expression.length()) {
            currChar = expression.charAt(curr_index);

            while ((currChar == ' ' || currChar == '\r' || currChar == '\n') && curr_index < expression.length() - 1) {
                curr_index++;
                if (currChar == '\n') {
                    col_num = 0;
                    row_num++;
                } else {
                    col_num++;
                }
                currChar = expression.charAt(curr_index);
            }
        }

        old_index = curr_index;

        if (ifEOF()) {
            currLexem = new Lexem(LexemKind.EOL, '0', getPosition());
        } else {
            for (String word : dictionary) {
                if (expression.substring(curr_index).startsWith(word)) {
                    if (word.equals("true")) {
                        currLexem = new Lexem(LexemKind.TRUE, getPosition());
                    }
                    if (word.equals("false")) {
                        currLexem = new Lexem(LexemKind.FALSE, getPosition());
                    }
                    if (word.equals("if")) {
                        currLexem = new Lexem(LexemKind.IF, getPosition());
                    }
                    if (word.equals("then")) {
                        currLexem = new Lexem(LexemKind.THEN, getPosition());
                    }
                    if (word.equals("else")) {
                        currLexem = new Lexem(LexemKind.ELSE, getPosition());
                    }
                    if (word.equals("let")) {
                        currLexem = new Lexem(LexemKind.LET, getPosition());
                    }
                    if (word.equals("begin")) {
                        currLexem = new Lexem(LexemKind.BEGIN, getPosition());
                    }
                    if (word.equals("end")) {
                        currLexem = new Lexem(LexemKind.END, getPosition());
                    }
                    if (word.equals("fun")) {
                        currLexem = new Lexem(LexemKind.FUN, getPosition());
                    }
                    if (word.equals("print")) {
                        currLexem = new Lexem(LexemKind.PRINT, getPosition());
                    }
                    if (word.equals("int")) {
                        currLexem = new Lexem(LexemKind.Int, getPosition());
                    }
                    if (word.equals("in")) {
                        currLexem = new Lexem(LexemKind.IN, getPosition());
                    }
                    if (word.equals("bool")) {
                        currLexem = new Lexem(LexemKind.Bool, getPosition());
                    }
                    if (word.equals("unit")) {
                        currLexem = new Lexem(LexemKind.Unit, getPosition());
                    }
                    curr_index += word.length();
                    return;
                }
            }
            switch (currChar) {
                case ':':
                    curr_index++;
                    currLexem = new Lexem(LexemKind.Colon, ':', getPosition());
                    break;
                case '+':
                    curr_index++;
                    currLexem = new Lexem(LexemKind.Plus, '+', getPosition());
                    break;
                case '*':
                    curr_index++;
                    currLexem = new Lexem(LexemKind.Multiply, '*', getPosition());
                    break;
                case '/':
                    curr_index++;
                    currLexem = new Lexem(LexemKind.Divide, '/', getPosition());
                    break;
                case '(':
                    curr_index++;
                    currLexem = new Lexem(LexemKind.LeftBracket, '(', getPosition());
                    break;
                case ')':
                    curr_index++;
                    currLexem = new Lexem(LexemKind.RightBracket, ')', getPosition());
                    break;
                case '=':
                    curr_index++;
                    currLexem = new Lexem(LexemKind.equality, '=', getPosition());
                    break;
                case ';':
                    curr_index++;
                    currLexem = new Lexem(LexemKind.semicolon, ';', getPosition());
                    break;
                case '!':
                    if (expression.charAt(curr_index + 1) == '=') {
                        curr_index += 2;
                        currLexem = new Lexem(LexemKind.unequality, '!', getPosition());
                        break;
                    } else {
                        curr_index++;
                        currLexem = new Lexem(LexemKind.No, '!', getPosition());
                        break;
                    }
                case '-':
                    if (expression.charAt(curr_index + 1) == '>') {
                        curr_index += 2;
                        currLexem = new Lexem(LexemKind.ARROW, '-', getPosition());
                        break;
                    } else {
                        curr_index++;
                        currLexem = new Lexem(LexemKind.Minus, '-', getPosition());
                        break;
                    }
                case '>':
                    if (expression.charAt(curr_index + 1) == '=') {
                        curr_index += 2;
                        currLexem = new Lexem(LexemKind.GE, '>', getPosition());
                        break;
                    } else {
                        curr_index++;
                        currLexem = new Lexem(LexemKind.Greater, '>', getPosition());
                        break;
                    }
                case '<':
                    if (expression.charAt(curr_index + 1) == '=') {
                        curr_index += 2;
                        currLexem = new Lexem(LexemKind.LE, '<', getPosition());
                        break;
                    } else {
                        curr_index++;
                        currLexem = new Lexem(LexemKind.Less, '<', getPosition());
                        break;
                    }

                case '&':
                    if (expression.charAt(curr_index + 1) == '&') {
                        curr_index += 2;
                        currLexem = new Lexem(LexemKind.And, '&', getPosition());
                        break;
                    } else {
                        curr_index++;
                        currLexem = new Lexem(LexemKind.Unknown, '?', getPosition());
                        break;
                    }
                case '|':
                    if (expression.charAt(curr_index + 1) == '|') {
                        curr_index += 2;
                        currLexem = new Lexem(LexemKind.Or, '|', getPosition());
                        break;
                    } else {
                        curr_index++;
                        currLexem = new Lexem(LexemKind.Unknown, '?', getPosition());
                        break;
                    }
                default:
                    if (ifNumber()) {
                        currLexem = new Lexem(LexemKind.Number, getNumber(), getPosition());
                    } else if (ifIdentifier()) {
                        String identificator = getIdentifier();
                        currLexem = new Lexem(LexemKind.Identifier, identificator, getPosition());
                    } else {
                        currLexem = new Lexem(LexemKind.Unknown, '?', getPosition());
                    }
            }
        }

    }
}
