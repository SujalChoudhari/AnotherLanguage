package com.al.parser;

import com.al.node.Node;

public class ParseResult {

	public Object node;

	
	public Object register(Object result) {
		if(result.getClass() == ParseResult.class) {
			ParseResult r = (ParseResult) result;
			return r.node;
		}
		else {
			return result;
		}
	}
	
	public ParseResult success(Object node) {
		this.node = node;
		return this;
	}
	
	
	public ParseResult failure() {
		return this;
	}
}
