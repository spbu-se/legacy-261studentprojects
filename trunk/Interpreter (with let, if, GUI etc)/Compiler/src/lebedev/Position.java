/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lebedev;

public class Position {
    private int line;
    private int column;
    private int begAbs;
    private int endAbs;

    public Position(int begAbs, int endAbs, int line, int column) {
        this.begAbs = begAbs;
        this.endAbs = endAbs;
    	this.line = line;
        this.column = column;
    }

    public int getBegAbs() {
    	return begAbs;
    }

    public int getEndAbs() {
    	return endAbs;
    }
    
    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
