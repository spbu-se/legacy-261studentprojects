package savenko;

public class Multiply extends BinaryOperation{

	Multiply(Tree leftNode, Tree rightNode) {
		super(leftNode, rightNode);
	}

	@Override
	public void print() {
	    LeftNode.print();
        RightNode.print();
		System.out.print("*");
	}

}
