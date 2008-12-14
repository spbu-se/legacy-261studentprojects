package eda.tm;

import java.util.Vector;

public class Tape {

    public static final char STAR = '*';
    public static final char EMPTY = '*';
    private Vector<Character> tape;
    private int position;

    public Tape(Vector<Character> tape) {
        this.tape = tape;
        this.position = 0;
    }

    public void write(char symbol) {
        tape.remove(position);
        tape.add(position, symbol);
    }

    public char returnCurrentSymbol() {
        if (position >= tape.size()) {
            tape.add(EMPTY);
            return EMPTY;
        }
        return tape.get(position);
    }

    public void changePosition(Moving move) {
        if (move == Moving.STEND) {
            position += 0;
        }
        if (move == Moving.RIGHT) {
            position += 1;
            if (position >= tape.size()) {
                tape.add(EMPTY);
            }
        }
        if (move == Moving.LEFT) {
            position -= 1;
            if (position < 0) {
                position = 0;
                tape.add(0, EMPTY);
            }
        }
    }

    @Override
    public String toString() {
        return tape.toString() + " : " + position;
    }
    
    public Vector<Character> trace(){
        return (Vector<Character>) tape.clone();
    }

    int getPosition() {
        return position;
    }
}