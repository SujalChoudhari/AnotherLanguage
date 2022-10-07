package com.al.node;

import com.al.error.Position;
import com.al.token.Token;

public class BinaryOperationNode extends Node {
	public final Node left;
	public final Token operatorToken;
	public final Node right;
	public Position startPosition;
	public Position endPosition;

	public BinaryOperationNode(Node left, Token operatorToken, Node right) {
		super();
		this.left = left;
		this.operatorToken = operatorToken;
		this.right = right;
		startPosition = left.startPosition;
		endPosition = right.endPosition;
	}

	@Override
	public String toString() {
		return "[" + left + ", " + operatorToken + ", " + right + "]";
	}

}
