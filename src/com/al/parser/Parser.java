package com.al.parser;

import java.util.ArrayList;

import com.al.error.Error;
import com.al.error.InvalidSyntaxError;
import com.al.node.BinaryOperationNode;
import com.al.node.UnaryOperationNode;
import com.al.node.Node;
import com.al.node.NumberNode;
import com.al.token.Token;
import com.al.token.TokenType;

public class Parser {
	public ArrayList<Token> tokens;
	public int tokenIndex;
	public Token current;

	public Parser(ArrayList<Token> tokens) {
		super();
		this.tokens = tokens;
		this.tokenIndex = -1;
		advance();
	}

	public Token advance() {
		tokenIndex++;
		if (tokenIndex < tokens.size()) {
			current = tokens.get(tokenIndex);
		}
		return current;
	}

	public ParseResult parse() {
		ParseResult response = parseExpression();
		if (Error.isSafe() && current.type != TokenType.EOF) {
			new InvalidSyntaxError(current.start, current.end,
					"Expected (PLUS|MINUS|DIV|MUL), but got " + current.type);
			return response.failure();
		}

		return response;
	}

	public ParseResult parseFactor() {
		ParseResult response = new ParseResult();
		Token token = current;
		if (token.type == TokenType.PLUS || token.type == TokenType.MINUS) {
			response.register(advance());
			var factor = response.register(parseFactor());
			if (!Error.isSafe())
				return response;
			return response.success(new UnaryOperationNode(token, (Node) factor));
		} else if (token.type == TokenType.INT || token.type == TokenType.FLOAT || token.type == TokenType.DOUBLE
				|| token.type == TokenType.LONG) {
			response.register(advance());
			return response.success(new NumberNode(token));
		}
		if (token.type == TokenType.LPAREN) {
			response.register(advance());
			var expression = response.register(parseExpression());
			if (!Error.isSafe())
				return response;
			if (current.type == TokenType.RPAREN) {
				response.register(advance());
				return response.success(expression);
			}
			else {
				new InvalidSyntaxError(current.start,current.end,"Expected ')', got " + current.type);
				return response.failure();
			}
		}
		new InvalidSyntaxError(token.start, token.end,
				"Expected (INT|FLOAT|DOUBLE|LONG), got " + token.type + " instead.");
		return response.failure();
	}

	public ParseResult parseTerm() {
		ParseResult response = new ParseResult();
		Node left = (Node) response.register(parseFactor());
		if (!Error.isSafe())
			return response;

		while (current.type == TokenType.MUL || current.type == TokenType.DIV) {
			Token operatorToken = current;
			response.register(advance());
			Node right = (Node) response.register(parseFactor());
			if (!Error.isSafe())
				return response;
			left = new BinaryOperationNode(left, operatorToken, right);

		}
		return response.success(left);
	}

	public ParseResult parseExpression() {
		ParseResult response = new ParseResult();
		Node left = (Node) response.register(parseTerm());
		if (!Error.isSafe())
			return response;

		while (current.type == TokenType.PLUS || current.type == TokenType.MINUS) {
			Token operatorToken = current;
			response.register(advance());
			Node right = (Node) response.register(parseTerm());
			if (!Error.isSafe())
				return response;
			left = new BinaryOperationNode(left, operatorToken, right);

		}
		return response.success(left);
	}

}
