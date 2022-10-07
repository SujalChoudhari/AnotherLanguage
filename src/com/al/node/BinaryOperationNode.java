package com.al.node;

import com.al.token.Token;

public class BinaryOperationNode extends Node {
	public final Node left;
	public final Token operatorToken;
	public final Node right;

	public BinaryOperationNode(Node left, Token operatorToken, Node right) {
		super();
		this.left = left;
		this.operatorToken = operatorToken;
		this.right = right;
	}

	@Override
	public String toString() {
		return "[" + left + ", " + operatorToken + ", " + right + "]";
	}

}
