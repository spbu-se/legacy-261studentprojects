package savenko;

public class Minus extends BinaryOperation{

	Minus(Tree leftNode, Tree rightNode) {
		super(leftNode, rightNode);
	}

	@Override
	public void print() {
		LeftNode.print();
		RightNode.print();
		System.out.print("-");
	}

}
