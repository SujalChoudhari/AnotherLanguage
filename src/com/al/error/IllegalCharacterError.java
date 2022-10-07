package com.al.error;

public class IllegalCharacterError extends Error {

	public IllegalCharacterError(Position start, Position end, String details) {
		super("Illegal Character", start, end, details);
		// TODO Auto-generated constructor stub
	}

}
