package com.al.error;

public class RuntimeError extends Error {

	public RuntimeError(Position start, Position end, String details) {
		super("Runtime Error", start, end, details);
		// TODO Auto-generated constructor stub
	}

}
