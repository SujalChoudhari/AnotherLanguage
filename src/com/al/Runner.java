package com.al;

import java.util.ArrayList;

import com.al.builtin.Int32;
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
		// Generate Tokens
		this.text = text;
		lexer = new Lexer(filename,text);
		tokens = lexer.generateTokens();
		
		if (!Error.isSafe()) {
			System.out.println(Error.diaplayAll());
			return;
		}
		
		// Generate AbstractSyntaxTokens
		parser = new Parser(tokens);
		ast = (Node) parser.parse().node;
		
		if (!Error.isSafe()) {
			System.out.println(Error.diaplayAll());
			return;
		}
		
		// Run Interpreter
		Interpreter interpreter = new Interpreter();
		Int32 number = (Int32) interpreter.visit(ast);
		System.out.println(number);
	}

	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public Node getAbstractSyntaxTree() {
		return ast;
	}
}
