package com.al.node;

import com.al.token.Token;

public class NumberNode extends Node {
	public final Token token;
	
	public NumberNode(Token token) {
		this.token = token;
	}
	
	public String toString() {
		return ""+token;
	}
}
