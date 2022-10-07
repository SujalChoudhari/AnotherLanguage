package com.al;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.al.node.BinaryOperationNode;
import com.al.node.Node;
import com.al.builtin.*;
import com.al.node.NumberNode;
import com.al.node.UnaryOperationNode;
import com.al.token.TokenType;

public class Interpreter {
	public Object visit(Node node) {
		String methodName = "visit" + node.getClass().getSimpleName();
		Method method = null;
		try {
			method = this.getClass().getMethod(methodName,Node.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			return method.invoke(this,node);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Int32 visitNumberNode(Node node) {
		NumberNode n = (NumberNode) node;
		if(n.token.type == TokenType.INT) {
			int value = Integer.valueOf(n.token.value);
			return new Int32(value);
		}
		return null;
	}

	public Int32 visitBinaryOperationNode(Node node) {
		BinaryOperationNode n = (BinaryOperationNode) node;
		Int32 left = (Int32) visit(n.left);
		Int32 right = (Int32) visit(n.right);
		
		Int32 result = null;
		if(n.operatorToken.type == TokenType.PLUS) result = left.addedTo(right);
		else if(n.operatorToken.type == TokenType.MINUS) result = left.subbedTo(right);
		else if(n.operatorToken.type == TokenType.MUL) result = left.multipliedBy(right);
		else if(n.operatorToken.type == TokenType.DIV) result = left.dividedBy(right);
		
		return result;
	}
	
	public Int32 visitUnaryOperationNode(Node node) {
		System.out.println("UNARY NODE");
		UnaryOperationNode n = (UnaryOperationNode) node;
		Int32 n2 = (Int32) visit(n.operatorNode);
		
		Int32 result = null;
		if(n.operatorToken.type == TokenType.MINUS)result = n2.multipliedBy(new Int32(-1));
		
		return result;
	}
}
