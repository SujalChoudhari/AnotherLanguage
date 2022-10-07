package com.al;

import java.util.ArrayList;

import com.al.error.IllegalCharacterError;
import com.al.error.Position;
import com.al.token.Token;
import com.al.token.TokenType;

public class Lexer {

	public String text;
	public Position position;
	public char currentChar;
	public String filename;

	public Lexer(String filename,String text) {
		super();
		this.text = text;
		this.filename = filename;
		position = new Position(-1,0,-1,filename,text);
		advance();
	}

	public void advance() {
		position.advance(currentChar);
		if (position.index < text.length())
			currentChar = text.charAt(position.index);
		else
			currentChar = '\0';
	}

	public ArrayList<Token> generateTokens() {
		ArrayList<Token> tokens = new ArrayList<>(100);

		while (position.index < text.length()) {
			if (currentChar == ' ' || currentChar == '\t') {
				advance();
			} else if (Character.isDigit(currentChar)) {
				tokens.add(generateNumbers());
//				advance();

			} else if (currentChar == '+') {
				tokens.add(new Token(TokenType.PLUS, null,position,position));
				advance();
			} else if (currentChar == '-') {
				tokens.add(new Token(TokenType.MINUS, null,position,position));
				advance();
			} else if (currentChar == '*') {
				tokens.add(new Token(TokenType.MUL, null,position,position));
				advance();
			} else if (currentChar == '/') {
				tokens.add(new Token(TokenType.DIV, null,position,position));
				advance();
			} else if (currentChar == '(') {
				tokens.add(new Token(TokenType.LPAREN, null,position,position));
				advance();
			} else if (currentChar == ')') {
				tokens.add(new Token(TokenType.RPAREN, null,position,position));
				advance();
			} else {
				Position start = position.copy();
				char character = currentChar;
				advance();
				new IllegalCharacterError(start,position,"<" + character + ">");
				return null;
			}
		}
		tokens.add(new Token(TokenType.EOF,null, position,position));
		return tokens;
	}

	public Token generateNumbers() {
		String number = "";
		int dotCount = 0;
		Position positionStart = position.copy();
		TokenType type = TokenType.INT;

		while ("0123456789_.LlDdFf".indexOf(currentChar) != -1) {
//			Check for decimal
			if (currentChar == '.') {
				if (dotCount == 1)
					break;
				dotCount++;
				number += '.';
				type = TokenType.FLOAT;

//				Check for Digits
			} else if (Character.isDigit(currentChar))
				number += currentChar;
			else if ("Ll".indexOf(currentChar) != -1)
				type = TokenType.LONG;
			else if ("Dd".indexOf(currentChar) != -1)
				type = TokenType.DOUBLE;
			else if ("Ff".indexOf(currentChar) != -1)
				type = TokenType.FLOAT;


			advance();

		}

		return new Token(type, number,positionStart,position);
	}
}
