package dk.huffman;

public class Node {

	private Node parent;
	private int frequency;
	private int name;

	public Node(Node parent, int frequency, int name) {
		this.parent = parent;
		this.frequency = frequency;
		this.name = name;
	}

	public Node(int frequency, int name) {
		this.setFrequency(frequency);
		this.setName(name);
	}

	public Node(Node leftNode, Node rightNode) {
		this.frequency = leftNode.frequency + rightNode.frequency;
		this.name = 0;
	}

	public Node getParent() {
		return parent;
	}

	/*
	 * compare name and 0 ==> leaf or not leaf
	 */
	public boolean isLeaf() {
		if (this.name != 0) {
			return true;
		} else
			return false;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String s = this.getName() + "  ";
		return s;
	}

	public Node clone() {
		Node clone = new Node(this.parent, this.frequency, this.name);
		return clone;
	}
	
	@Override
	public boolean equals(Object obj) {
		Node n = (Node) obj;
		if (this.frequency == n.frequency && this.name == n.name
				&& this.parent == n.parent) {
			return true;
		} else
			return false;
	}
}
