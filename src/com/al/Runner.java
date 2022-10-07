package com.al;

import java.util.ArrayList;

import com.al.error.Error;
import com.al.node.Node;
import com.al.parser.Parser;
import com.al.token.Token;

public class Runner {
	public Lexer lexer;
	public Parser parser;
	
	public String text;
	public ArrayList<Token> tokens;
	public Node ast;

	public void run(String filename,String text) {
		this.text = text;
		lexer = new Lexer(filename,text);
		tokens = lexer.generateTokens();
		parser = new Parser(tokens);
		ast = (Node) parser.parse().node;
		

		if (!Error.isSafe()) {
			System.out.println(Error.diaplayAll());
		}
	}

	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public Node getAbstractSyntaxTree() {
		return ast;
	}
}
