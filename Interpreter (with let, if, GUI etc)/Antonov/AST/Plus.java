/*
 * Plus node
 * Antonov Kirill(c), 2010
 */
package AST;

public class Plus extends BinaryOperation {

    public Plus(Tree leftNode, Tree rightNode) {
        super(leftNode, rightNode);
    //this.Left=leftNode;
    //this.Right=rightNode;
    }

    /*@Override
    public void print() {
        Left.print();
        Right.print();
        System.out.print("+");
    }

    

    public int calculate() {
        int result = this.LeftNode().calculate() + this.RightNode().calculate();
        return result;
    }

  */
}
