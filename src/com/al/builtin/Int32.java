package com.al.builtin;

import com.al.error.Position;

public class Int32 {
	@Override
	public String toString() {
		return  ""+value;
	}

	public int value;
	public Position startPosition = null;
	public Position endPosition = null;

	public Int32(int number) {
		this.value = number;
	}

	public Int32 addedTo(Int32 other) {
		return new Int32(other.value + value);
	}

	public Int32 subbedTo(Int32 other) {
		return new Int32(value - other.value);
	}

	public Int32 multipliedBy(Int32 other) {
		return new Int32(other.value * value);
	}

	public Int32 dividedBy(Int32 other) {
		return new Int32(value / other.value);
	}

	
}
