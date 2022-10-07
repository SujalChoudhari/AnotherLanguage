package com.al.builtin;

import com.al.error.Position;

public abstract class Number<T> {

	public T value;
	public Position startPosition = null;
	public Position endPosition = null;

	public Number() {
		// TODO Auto-generated constructor stub
	}

	public abstract Object addedTo(Object other);

	public abstract Object subbedTo(Object other);

	public abstract Object multipliedBy(Object other);

	public abstract Object dividedBy(Object other);

	public abstract String toString();

}
