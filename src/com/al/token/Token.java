package com.al.token;

import com.al.error.Position;

public class Token {
	public TokenType type;
	public String value;

	public Position start;
	public Position end;

	public Token(TokenType type, String value, Position start, Position end) {
		super();
		this.type = type;
		this.value = value;
		this.start = start.copy();
		this.end = end.copy();
	}


	public Token(TokenType type, String value) {
		super();
		this.type = type;
		this.value = value;
	}

	public String toString() {
		if (type == TokenType.INT || type == TokenType.LONG || type == TokenType.FLOAT || type == TokenType.DOUBLE)
			return "<" + type + ": " + value + ">";
		return "<" + type + ">";
	}
}
