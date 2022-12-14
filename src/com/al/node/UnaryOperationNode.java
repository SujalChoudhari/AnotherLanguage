package com.al.node;
import com.al.token.Token;

public class UnaryOperationNode extends Node {
	public Token operatorToken;
	public Node operatorNode;
	
	
	public UnaryOperationNode(Token operatorToken, Node operatorNode) {
		super();
		this.operatorToken = operatorToken;
		this.operatorNode = operatorNode;
		startPosition = operatorToken.start;
		endPosition = operatorNode.endPosition;
	}


	@Override
	public String toString() {
		return "[" + operatorToken + ", " + operatorNode + "]";
	}


	
	
	

}
