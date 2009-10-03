package Regular;

/**
 *
 * @author Кирилл
 */
import java.util.TreeSet;

public class Parser {

    static TreeSet<String> alphabet = new TreeSet<String>();
    static int pos = 0;
    static char EOL = '\n';

    public static boolean isLetter(char symb) {
        if (alphabet.contains("" + symb)) {
            return true;
        } else {
            return false;
        }
    }

    public static NFA parse(String expr) {
        NFA nfa = new NFA();
        nfa.getAlphabet(expr);
        alphabet = NFA.alphabet;
        if (expr.equals("")) {
            return NFA.buildPrimitive(NFA.EMPTY);
        }
        nfa = parseConcat(expr);
        while (currentChar(expr) == '|') {
            next();
            nfa = NFA.buildAltern(nfa, parse(expr));
        }
        return nfa;
    }

    private static NFA parseConcat(String expr) {
        NFA nfa = parseClosureQuestion(expr);
        while (isLetter(currentChar(expr)) || (currentChar(expr) == '(')) {
            nfa = NFA.buildConcat(nfa, parseConcat(expr));
        }
        return nfa;
    }

    private static NFA parseClosureQuestion(String expr) {
        NFA auto = parseSymb(expr);
        if (currentChar(expr) == '*') {
            auto = NFA.buildClosure(auto);
            next();
        } else if (currentChar(expr) == '?') {
            auto = NFA.buildQuestion(auto);
            next();
        }
        return auto;

    }

    private static NFA parseSymb(String expr) {
        NFA nfa;
        if (isLetter(currentChar(expr))) {
            nfa = NFA.buildPrimitive(currentChar(expr));
            next();
        } else if (currentChar(expr) == '(') {
            next();
            nfa = parse(expr);
            next();
            if (currentChar(expr) == ')') {
            } else {
                try {
                    throw (new ParserException(ParserException.MISSED_CLOSING_BRACKET));
                } catch (ParserException p) {
                }
            }
        } else {
            return null;
        }

        return nfa;

    }

    private static void next() {
        ++pos;
    }

    private static char currentChar(String expr) {

        if (pos == expr.length()) {
            return EOL;
        } else {
            return expr.charAt(pos);
        }
    }
}


