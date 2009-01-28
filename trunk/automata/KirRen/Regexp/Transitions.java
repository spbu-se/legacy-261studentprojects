package Regexp;

import java.util.Vector;
import java.util.HashSet;

class Transition {
    char c;
    Integer to;

    public Transition(char c, Integer to) {
        this.c = c;
        this.to = to;
    }
}

class Transitions {
    Vector<Transition> trans = new Vector<Transition>();

    public void put(char c, Integer to) {
        for (Transition i: trans) {
            if (i.c==c & i.to == to)
                return;
        }
        trans.add(new Transition(c, to));
    }

    public String formatState(int state, int first, HashSet<Integer> fins) {
        if (state == first) return "\"S " + state + "\"";
        if (fins.contains(state)) return "\"F " + state + "\"";
        return "\"" + state + "\"";
    }

    public String produceDotty(int state, int first, HashSet<Integer> fins) {
        String output = "";
        if (trans.isEmpty()) {
            output = formatState(state, first, fins) + ";\n";
        } else {
            for (Transition t: trans) {
                output += formatState(state, first, fins) + " -> " + formatState(t.to, first, fins);
                if (t.c == NFA.EMPTY) {
                    output += ";\n";
                } else {
                    output += " [taillabel = \"" + t.c + "\"];\n";
                }
            }
        }
        return output;
    }
}
