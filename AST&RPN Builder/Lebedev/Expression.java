//Lebedev Dmitry 2010 (c)
package ast;

public class Expression implements Tree {

    private Tree LeftNode;
    private Tree RightNode;

    Expression(Tree left, Tree right) {
        this.LeftNode = left;
        this.RightNode = right;
    }

    public Tree getLeft() {
        return LeftNode;
    }

    public Tree getRight() {
        return RightNode;
    }

    public void print() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int evaluate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
