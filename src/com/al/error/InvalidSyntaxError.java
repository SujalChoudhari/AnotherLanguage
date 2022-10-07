package com.al.error;

public class InvalidSyntaxError extends Error {

	public InvalidSyntaxError(Position start, Position end, String details) {
		super("Invalid Syntax", start, end, details);
	}

}
