package savenko;


public abstract class BinaryOperation implements Expression {
	
	Tree LeftNode, RightNode;
	
	BinaryOperation(Tree left_node, Tree right_node){
		LeftNode = left_node;
		RightNode = right_node;
	}
	
	public Tree LeftNode(){
		return LeftNode;
	}
	
	public Tree RightNode(){
		return RightNode;
	}
	
}
