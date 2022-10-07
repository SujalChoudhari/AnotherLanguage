package com.al.node;

import com.al.error.Position;
import com.al.token.Token;

public class NumberNode extends Node {
	public final Token token;
	public Position startPosition;
	public Position endPosition;
	
	public NumberNode(Token token) {
		this.token = token;
		startPosition = token.start;
		endPosition = token.end;
	}
	
	public String toString() {
		return ""+token;
	}
}
